package nova.js.nodewriters;

import net.fathomsoft.nova.tree.ExceptionDeclaration;

public abstract class ExceptionDeclarationWriter extends LocalDeclarationWriter
{
	public abstract ExceptionDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}