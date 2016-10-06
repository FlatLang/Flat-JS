package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ValueWriter extends NodeWriter
{
	public abstract Value node();
	
	public StringBuilder writeArrayAccess()
	{
		return writeArrayAccess(new StringBuilder());
	}
	
	public StringBuilder writeArrayAccess(StringBuilder builder)
	{
		if (node().arrayAccess != null)
		{
			return getWriter(node().arrayAccess).writeExpression(builder);
		}
		
		return builder;
	}
}