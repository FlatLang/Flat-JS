package org.flat.js.nodewriters;

import org.flat.tree.ExceptionDeclaration;

public abstract class ExceptionDeclarationWriter extends LocalDeclarationWriter
{
	public abstract ExceptionDeclaration node();

	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}