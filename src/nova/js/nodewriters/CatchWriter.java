package nova.js.nodewriters;

import net.fathomsoft.nova.tree.exceptionhandling.Catch;
import net.fathomsoft.nova.tree.exceptionhandling.ExceptionHandler;

public abstract class CatchWriter extends ExceptionHandlerWriter
{
	public abstract Catch node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		builder.append("catch (").append(node().getExceptionDeclaration().getName()).append(") ").append(getWriter(node().getScope()).write(true, false));
		
		if (node().getNextNode() instanceof ExceptionHandler)
		{
			builder.append(' ');
		}
		else
		{
			builder.append('\n');
		}
		
		return builder;
	}
}