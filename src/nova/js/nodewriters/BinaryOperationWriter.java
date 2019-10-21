package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class BinaryOperationWriter extends IValueWriter
{
	public abstract BinaryOperation node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		boolean requiresFloor = false;
		
		ClassDeclaration charClass = node().getProgram().getClassDeclaration("nova/primitive/number/Char");
		
		if (node().getOperator().getOperator().equals("/") || node().getOperator().getOperator().equals("%"))
		{
			ClassDeclaration integer = node().getProgram().getClassDeclaration("nova/primitive/number/Integer");

			requiresFloor = node().getLeftOperand().getReturnedNode().getTypeClass().isOfType(integer) &&
					node().getRightOperand().getReturnedNode().getTypeClass().isOfType(integer);

			if (!requiresFloor) {
				builder.append("/*").append(node().getLeftOperand().getReturnedNode().getType()).append("*/");
				builder.append("/*").append(node().getRightOperand().getReturnedNode().getType()).append("*/");
			}
		}
		
		if (requiresFloor)
		{
			builder.append("~~(");
		}
		
		getWriter(node().getLeftOperand()).writeExpression(builder);
		
		boolean numerical = node().getOperator().isNumerical();
		
		if (numerical && node().getLeftOperand().getReturnedNode().getTypeClass() == charClass)
		{
			builder.append(".charCodeAt(0)");
		}

		if (node().getOperator().getOperator().equals(Operator.EQUALS)) {
			builder.append(' ').append("===").append(' ');
		} else {
			builder.append(' ').append(getWriter(node().getOperator()).write()).append(' ');
		}
		
		builder.append(getWriter(node().getRightOperand()).writeExpression());
		
		if (numerical && node().getRightOperand().getReturnedNode().getTypeClass() == charClass)
		{
			builder.append(".charCodeAt(0)");
		}
		
		if (requiresFloor)
		{
			builder.append(')');
		}
		
		return builder;
	}
}