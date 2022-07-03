package org.flatlang.js.nodewriters;

import org.flatlang.error.SyntaxMessage;
import org.flatlang.tree.*;
import org.flatlang.util.Location;
import org.flatlang.js.engines.JSCompileEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public abstract class ProgramWriter extends TypeListWriter
{
	public abstract Program node();
	
	public ClassDeclaration[] getClassesHierarchicalInOrder()
	{
		final ArrayList<ClassDeclaration> list = new ArrayList<>();
		
		node().forEachVisibleListChild(file -> {
			Arrays.stream(file.getClassDeclarations()).forEach(c -> {
				ClassDeclaration[] hierarchy = c.getClassHierarchy(true, true);
				
				for (int i = hierarchy.length - 1; i >= 0; i--)
				{
					if (!list.contains(hierarchy[i]))
					{
						list.add(hierarchy[i]);
					}
				}
			});
		});
		
		return list.toArray(new ClassDeclaration[0]);
	}
	
	@Override
	public StringBuilder write(final StringBuilder builder)
	{
		boolean localScope = ((JSCompileEngine)node().getProgram().getController().compileEngine).localScope;
		
		if (localScope)
		{
			builder.append("(function () {\n");
		}
		
		ClassDeclaration[] classes = getClassesHierarchicalInOrder();
		
		builder.append("var flatConstructors = {\n");
		
		for (ClassDeclaration child : classes)
		{
			child.getConstructorList().forEachVisibleListChild(constructor -> {
				getWriter((Constructor)constructor).writeConstructorListName(builder).append(": function() {},\n");
			});
		}
		
		builder.append("};\n\n");

		builder.append("var flat_null = undefined;\n\n");

		HashSet<String> printedClasses = new HashSet<>();

		for (ClassDeclaration child : classes)
		{
			printClass(builder, printedClasses, child);
		}

		HashSet<String> printedPrototypes = new HashSet<>();

		node().forEachVisibleListChild(file -> {
			Arrays.stream(file.getClassDeclarations())
				.forEach((c) -> printPrototypes(builder, printedPrototypes, c));
		});

		builder.append('\n');

		for (ClassDeclaration child : classes)
		{
			getWriter(child).writeStaticBlocks(builder);
		}

		FlatMethodDeclaration method = node().getTree().getMainMethod(node().getController().codeGeneratorEngine.mainClass);

		if (method == null) {
			SyntaxMessage.error("Could not find main function", node().getController());

			return builder;
		}

		Value flatNullString = Instantiation.decodeStatement(method, "new Null()", Location.INVALID, true);

		builder.append("flat_null = ").append(getWriter(flatNullString).writeUseExpression()).append(";\n\n");

		for (ClassDeclaration child : classes)
		{
			getWriter(child).writeStaticBlockCalls(builder);
		}

		Value emptyArgsArray = Instantiation.decodeStatement(method, "new Array<String>()", Location.INVALID, true);
		Accessible argvArray = SyntaxTree.decodeIdentifierAccess(method, "System.jsStringArrayToFlatArray(null)", Location.INVALID, true);

		MethodCall jsStringArrayToFlatArrayCall = (MethodCall)argvArray.getAccessedNode();

		Value nullArg = jsStringArrayToFlatArrayCall.getArgumentList().getArgumentsInOrder()[0];
		Literal processArgv = new Literal(nullArg.getParent(), Location.INVALID);
		processArgv.value = "process.argv";

		nullArg.replaceWith(processArgv);

		builder.append("\n");
		builder.append("var flat_main_args = process && process.argv ?\n");
		getWriter(argvArray.toValue()).writeExpression(builder);
		builder.append(" :\n");
		getWriter(emptyArgsArray).writeExpression(builder);
		builder.append(";\n\n");

		builder
			.append("process.on('unhandledRejection', (reason, promise) => {\n")
			.append(  "process.exit(1);\n")
			.append("});\n\n");

		getWriter(method.getDeclaringClass()).writeName(builder).append('.').append(getWriter(method).writeName()).append("(flat_main_args);\n");

		if (localScope)
		{
			builder.append("})();");
		}
		
		return builder;
	}

	private void printPrototypes(StringBuilder builder, HashSet<String> printedClasses, ClassDeclaration c) {
		if (!printedClasses.contains(c.getClassLocation())) {
			printedClasses.add(c.getClassLocation());

			ClassDeclaration extension = c.getExtendedClassDeclaration();

			if (extension != null) {
				printPrototypes(builder, printedClasses, extension);
			}

			getWriter(c).writeExtensionPrototypeAssignments(builder);

			builder.append("\n\n");
		}
	}

	private void printClass(StringBuilder builder, HashSet<String> printedClasses, ClassDeclaration c) {
		if (!printedClasses.contains(c.getClassLocation())) {
			printedClasses.add(c.getClassLocation());

			ClassDeclaration extension = c.getExtendedClassDeclaration();

			if (extension != null) {
				printClass(builder, printedClasses, extension);
			}

			getWriter(c).write(builder);
		}
	}
}