package org.flat.js.nodewriters;

import org.flat.tree.*;

public abstract class ParameterListWriter extends TypeListWriter
{
	public abstract ParameterList node();

	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return write(builder, true);
	}

	public StringBuilder write(boolean parenthesis)
	{
		return write(new StringBuilder(), parenthesis);
	}

	public StringBuilder write(StringBuilder builder, boolean parenthesis)
	{
		final int[] i = new int[] { 0 };

		if (parenthesis)
		{
			builder.append('(');
		}

		node().forEachVisibleChild(child -> {
			if (i[0]++ > 0)
			{
				builder.append(", ");
			}

			getWriter(child).writeExpression(builder);
		});

		if (parenthesis)
		{
			builder.append(')');
		}

		return builder;
	}
}