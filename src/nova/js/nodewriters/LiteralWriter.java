package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class LiteralWriter extends IValueWriter implements AccessibleWriter
{
	public abstract Literal node();
	
	public StringBuilder writeUseExpression(StringBuilder builder)
	{
		if (node().isStringInstantiation())
		{
			getWriter(node().getStringInstantiation()).writeExpression(builder);
		}
		else
		{
			builder.append(node().value);
		}
		
		return writeArrayAccess(builder);
	}
	
	@Override
	public StringBuilder writeExpression(final StringBuilder builder)
	{
		if (isInstanceClosure())
		{
			return writeInstanceClosure(builder);
		}
		
		return writeUseExpression(builder).append(writeAccessedExpression());
	}
}