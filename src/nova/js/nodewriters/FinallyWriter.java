package nova.js.nodewriters;

import net.fathomsoft.nova.tree.exceptionhandling.Finally;

public abstract class FinallyWriter extends ExceptionHandlerWriter
{
	public abstract Finally node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder.append("finally ").append(getWriter(node().getScope()).write());
	}
}