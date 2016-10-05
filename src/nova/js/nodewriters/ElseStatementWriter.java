package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ElseStatementWriter extends ControlStatementWriter
{
	public abstract ElseStatement node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder.append("else ").append(getWriter(node().getScope()).write());
	}
}