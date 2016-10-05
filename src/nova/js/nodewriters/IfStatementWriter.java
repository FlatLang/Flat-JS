package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class IfStatementWriter extends ControlStatementWriter
{
	public abstract IfStatement node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		builder.append("if (").append(getWriter(node().getCondition()).writeExpression()).append(") ");
		
		getWriter(node().getScope()).write(builder, true, false);
		
		return builder.append(node().getNextNode() instanceof ElseStatement ? ' ' : '\n');
	}
}