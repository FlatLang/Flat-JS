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
		
		if (value != null && value instanceof Throw == false) {
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
		} else if (node().getReturnValues().getNumVisibleChildren() > 0) {
			Node n = node().getReturnValues().getVisibleChild(0);

			getWriter(n).write(builder);
		} else {
			builder.append("return");
		}
		
		return builder;
	}
}