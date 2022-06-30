package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

public abstract class UnaryOperationWriter extends IValueWriter
{
	public abstract UnaryOperation node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		node().forEachChild(child -> {
			getWriter(child).writeExpression(builder);
		});
		
		return builder;
	}
}