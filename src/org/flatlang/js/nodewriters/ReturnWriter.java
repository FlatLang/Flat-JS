package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;
import org.flatlang.tree.exceptionhandling.Throw;

public abstract class ReturnWriter extends IValueWriter
{
	public abstract Return node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		Value value = node().getValueNode();
		
		if (!(value instanceof Throw)) {
			builder.append("return");
		}
		
		if (value != null) {
			builder.append(' ');

			if (!(value instanceof Throw) && !value.getReturnedNode().isPrimitive()) {
				builder.append('(');
			}

			getWriter(value).writeExpression(builder);

			if (!(value instanceof Throw) && !value.getReturnedNode().isPrimitive()) {
				builder.append(") || null");
			}
		}
		
		return builder;
	}
}