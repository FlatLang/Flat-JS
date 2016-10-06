package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

import static nova.js.nodewriters.Writer.getWriter;

public interface AccessibleWriter
{
	public abstract Accessible node();
	
	default StringBuilder writeAccessedExpression()
	{
		return writeAccessedExpression(new StringBuilder());
	}
	
	default StringBuilder writeAccessedExpression(boolean dot)
	{
		return writeAccessedExpression(new StringBuilder(), dot);
	}
	
	default StringBuilder writeAccessedExpression(StringBuilder builder)
	{
		return writeAccessedExpression(builder, true);
	}
	
	default StringBuilder writeAccessedExpression(StringBuilder builder, boolean dot)
	{
		if (node().doesAccess())
		{
			if (dot)
			{
				builder.append('.');
			}
			
			builder.append(getWriter(node().getAccessedNode()).writeExpression());
		}
		
		return builder;
	}
}