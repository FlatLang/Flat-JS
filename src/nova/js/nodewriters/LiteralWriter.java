package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class LiteralWriter extends IValueWriter implements AccessibleWriter
{
	public abstract Literal node();
	
	@Override
	public StringBuilder writeExpression(final StringBuilder builder)
	{
		builder.append(node().value);
		
		return builder;
	}
}