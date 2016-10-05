package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class AssignmentWriter extends ValueWriter
{
	public abstract Assignment node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return getWriter(node().getAssigneeNode()).writeExpression(builder).append(" = ").append(getWriter(node().getAssignmentNode()).writeExpression());
	}
}