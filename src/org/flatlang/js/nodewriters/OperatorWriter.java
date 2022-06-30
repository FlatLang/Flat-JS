package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

public abstract class OperatorWriter extends IValueWriter
{
	public abstract Operator node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return writeExpression(builder);
	}
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return builder.append(node().operator);
	}
}