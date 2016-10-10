package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class PriorityWriter extends ValueWriter implements AccessibleWriter
{
	public abstract Priority node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return builder.append('(').append(getWriter(node().getContents()).writeExpression()).append(')').append(writeArrayAccess()).append(writeAccessedExpression());
	}
}