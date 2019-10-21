package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.util.Location;
import nova.js.engines.JSCompileEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
		
		builder.append("var novaConstructors = {\n");
		
		for (ClassDeclaration child : classes)
		{
			child.getConstructorList().forEachVisibleListChild(constructor -> {
				getWriter((Constructor)constructor).writeConstructorListName(builder).append(": function() {},\n");
			});
		}
		
		builder.append("};\n\n");

		builder.append("var nova_null = undefined;\n\n");

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

		NovaMethodDeclaration method = node().getTree().getMainMethod(node().getController().codeGeneratorEngine.mainClass);

		Value novaNullString = Instantiation.decodeStatement(method, "new Null()", Location.INVALID, true);

		builder.append("nova_null = ").append(getWriter(novaNullString).writeUseExpression()).append(";\n\n");

		for (ClassDeclaration child : classes)
		{
			getWriter(child).writeStaticBlockCalls(builder);
		}

		getWriter(method.getDeclaringClass()).writeName(builder).append('.').append(getWriter(method).writeName()).append("();\n");

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