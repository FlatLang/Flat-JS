package org.flatlang.js.nodewriters;

import org.flatlang.tree.exceptionhandling.Finally;

public abstract class FinallyWriter extends ExceptionHandlerWriter
{
	public abstract Finally node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder.append("finally ").append(getWriter(node().getScope()).write());
	}
}