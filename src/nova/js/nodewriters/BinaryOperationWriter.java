package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class BinaryOperationWriter extends IValueWriter
{
	public abstract BinaryOperation node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return getWriter(node().getLeftOperand()).writeExpression(builder).append(' ').append(getWriter(node().getOperator()).write()).append(' ').append(getWriter(node().getRightOperand()).writeExpression());
	}
}