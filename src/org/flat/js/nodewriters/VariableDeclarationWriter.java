package org.flat.js.nodewriters;

import org.flat.tree.variables.VariableDeclaration;

public abstract class VariableDeclarationWriter extends IIdentifierWriter
{
	public abstract VariableDeclaration node();

	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return writeName(builder).append(" = ").append(writeDefaultValue());
	}

	public StringBuilder writeDefaultValue()
	{
		return writeDefaultValue(new StringBuilder());
	}

	public StringBuilder writeDefaultValue(StringBuilder builder)
	{
		if (node().isPrimitive())
		{
			builder.append(0);
		}
		else
		{
			builder.append("flat_null");
		}

		return builder;
	}
}