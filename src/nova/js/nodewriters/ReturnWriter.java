package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ReturnWriter extends IValueWriter
{
	public abstract Return node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		builder.append("return");
		
		if (node().getValueNode() != null)
		{
			builder.append(' ');
			
			getWriter(node().getReturnedNode()).writeExpression(builder);
		}
		
		return builder;
	}
}