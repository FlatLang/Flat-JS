package org.flat.js.nodewriters;

import org.flat.tree.*;

public abstract class AbstractMethodDeclarationWriter extends FlatMethodDeclarationWriter
{
	public abstract AbstractMethodDeclaration node();

	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}