package org.flat.js.nodewriters;

import org.flat.tree.*;

public abstract class ContinueWriter extends NodeWriter
{
	public abstract Continue node();

	@Override
	public StringBuilder writeExpression(StringBuilder builder) {
		return builder.append("continue");
	}

	@Override
	public StringBuilder write(StringBuilder builder) {
		return writeExpression(builder).append(";\n");
	}
}