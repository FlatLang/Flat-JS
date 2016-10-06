package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class AbstractMethodDeclarationWriter extends NovaMethodDeclarationWriter
{
	public abstract AbstractMethodDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}