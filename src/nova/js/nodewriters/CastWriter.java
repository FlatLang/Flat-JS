package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class CastWriter extends IValueWriter
{
	public abstract Cast node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return getWriter(node().getValueNode()).writeExpression(builder);
	}
}