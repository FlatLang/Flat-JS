package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

public abstract class ExternalMethodDeclarationWriter extends MethodDeclarationWriter
{
	public abstract ExternalMethodDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}

	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		return builder.append(node().alias);
	}
}