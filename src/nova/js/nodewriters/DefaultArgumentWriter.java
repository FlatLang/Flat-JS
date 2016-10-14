package nova.js.nodewriters;

import net.fathomsoft.nova.tree.DefaultArgument;

public abstract class DefaultArgumentWriter extends IValueWriter
{
	public abstract DefaultArgument node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return builder.append("undefined");
	}
}