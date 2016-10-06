package nova.js.nodewriters;

import net.fathomsoft.nova.tree.MethodCall;
import net.fathomsoft.nova.tree.variables.Super;

public abstract class SuperWriter extends PriorityWriter
{
	public abstract Super node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		builder.append("this.");
		
		if (node().getAccessedNode() instanceof MethodCall)
		{
			builder.append("super_");
		}
		
		return builder.append(writeAccessedExpression(false));
	}
}