package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

public abstract class VirtualMethodDeclarationWriter extends BodyMethodDeclarationWriter
{
	public abstract VirtualMethodDeclaration node();
	
	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		return getWriter(node().base).writeName(builder);
	}
}