package org.flat.js.nodewriters;

import org.flat.tree.*;

public abstract class ParameterWriter extends LocalDeclarationWriter
{
	public abstract Parameter node();

	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return writeName(builder);
	}
}