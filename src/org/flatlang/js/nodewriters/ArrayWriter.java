package org.flatlang.js.nodewriters;

import org.flatlang.tree.variables.Array;

public abstract class ArrayWriter extends VariableDeclarationWriter
{
	public abstract Array node();

	@Override
	public StringBuilder writeExpression(StringBuilder builder) {
		return writeUseExpression(builder);
	}
	
	@Override
	public StringBuilder writeUseExpression(StringBuilder builder)
	{
		if (isInstanceClosure())
		{
			return writeInstanceClosure(builder);
		}
		
		return builder.append("[]");
	}
}