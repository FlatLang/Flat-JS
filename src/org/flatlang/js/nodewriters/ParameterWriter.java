package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

public abstract class ParameterWriter extends LocalDeclarationWriter
{
	public abstract Parameter node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return writeName(builder);
	}
}