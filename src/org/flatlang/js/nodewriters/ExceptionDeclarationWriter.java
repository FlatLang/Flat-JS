package org.flatlang.js.nodewriters;

import org.flatlang.tree.ExceptionDeclaration;

public abstract class ExceptionDeclarationWriter extends LocalDeclarationWriter
{
	public abstract ExceptionDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}