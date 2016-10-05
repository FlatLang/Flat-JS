package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class NovaMethodDeclarationWriter extends MethodDeclarationWriter
{
	public abstract NovaMethodDeclaration node();
	
	public StringBuilder writeName()
	{
		return writeName(new StringBuilder());
	}
	
	public StringBuilder writeName(StringBuilder builder)
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
		
		builder.append(name);
		
		return builder;
	}
	
	public StringBuilder writeAssignedVariable()
	{
		return writeName(new StringBuilder());
	}
	
	public StringBuilder writeAssignedVariable(StringBuilder builder)
	{
		String prototype = node().isStatic() ? "" : ".prototype";
		
		return builder.append(node().getParentClass().getName()).append(prototype).append(".").append(writeName());
	}
}