package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class NovaMethodDeclarationWriter extends MethodDeclarationWriter
{
	public abstract NovaMethodDeclaration node();
	
	public StringBuilder writeName(StringBuilder builder)
	{
		super.writeName(builder);
		writeOverload(builder);
		
		return builder;
	}
	
	public StringBuilder writeOverload()
	{
		return writeOverload(new StringBuilder());
	}
	
	public StringBuilder writeOverload(StringBuilder builder)
	{
		String name = node().getName();
		
		if (node().getOverloadID() >= 0)
		{
			name += node().getOverloadID();
			
			while (node().getParentClass().containsMethod(name))
			{
				name += '_';
			}
		}
		
		return builder.append(name.substring(node().getName().length()));
	}
	
	public StringBuilder writeAssignedVariable()
	{
		return writeAssignedVariable(new StringBuilder());
	}
	
	public StringBuilder writeAssignedVariable(StringBuilder builder)
	{
		String prototype = node().isStatic() ? "" : ".prototype";
		
		return getWriter(node().getParentClass()).writeName(builder).append(prototype).append(".").append(writeName());
	}
	
	public StringBuilder writePrototypeAssignment(ClassDeclaration clazz)
	{
		return writePrototypeAssignment(new StringBuilder(), clazz);	
	}
	
	public StringBuilder writePrototypeAssignment(StringBuilder builder, ClassDeclaration clazz)
	{
		return writePrototypeAssignment(builder, clazz, false);
	}
		
	public StringBuilder writePrototypeAssignment(StringBuilder builder, ClassDeclaration clazz, boolean superCall)
	{
		String prototype = node().isStatic() ? "" : ".prototype";
		
		return getWriter(clazz).writeName(builder).append(prototype).append('.').append(getWriter(node()).writeName()).append(superCall ? "_base" : "").append(" = ").append(getWriter(node()).writeAssignedVariable()).append(";\n");
	}
}