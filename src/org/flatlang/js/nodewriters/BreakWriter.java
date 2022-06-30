package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

public abstract class BreakWriter extends NodeWriter
{
	public abstract Break node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return builder.append("break");
	}
}