package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class LiteralWriter extends IValueWriter implements AccessibleWriter
{
	public abstract Literal node();
	
	@Override
	public StringBuilder writeExpression(final StringBuilder builder)
	{
		if (node().isStringInstantiation())
		{
			getWriter(node().getStringInstantiation()).writeExpression(builder);
		}
		else
		{
			builder.append(node().value);
		}
		
		return writeAccessedExpression(builder);
	}
}