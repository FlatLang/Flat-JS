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
		return builder.append(node().getName());
	}
}