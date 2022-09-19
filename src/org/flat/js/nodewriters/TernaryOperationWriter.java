package org.flat.js.nodewriters;

import org.flat.tree.*;

public abstract class TernaryOperationWriter extends IValueWriter implements AccessibleWriter
{
	public abstract TernaryOperation node();

	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return getWriter(node().getCondition()).writeExpression(builder).append(" ? ")
			.append(getWriter(node().getTrueValue()).writeExpression()).append(" : ")
			.append(getWriter(node().getFalseValue()).writeExpression());
	}
}