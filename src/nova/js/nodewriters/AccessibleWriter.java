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
	
	default StringBuilder writeAccessedExpression(StringBuilder builder)
	{
		if (node().doesAccess())
		{
			builder.append('.').append(getWriter(node().getAccessedNode()).writeExpression());
		}
		
		return builder;
	}
}