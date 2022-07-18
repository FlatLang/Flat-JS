package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

public abstract class AssignmentWriter extends ValueWriter
{
	public abstract Assignment node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		ClassDeclaration set = node().getAssigneeNode().getReturnedNode().getTypeClass();
		ClassDeclaration value = node().getAssignmentNode().getReturnedNode().getTypeClass();

		if (!node().getAssignmentNode().getReturnedNode().isPrimitiveArray() && set != null && value != null)
		{
			ClassDeclaration integerClass = node().getProgram().getClassDeclaration("flatlang/primitive/number/Integer");
			ClassDeclaration charClass = node().getProgram().getClassDeclaration("flatlang/primitive/number/Char");

			if (set == charClass && value != charClass && value.implementsInterface(integerClass))
			{
				return getWriter(node().getAssigneeNode()).writeExpression(builder).append(" = ")
					.append("String.fromCharCode(").append(getWriter(node().getAssignmentNode()).writeExpression()).append(')');
			}
			else if (set != charClass && set.implementsInterface(integerClass) && value == charClass)
			{
				return getWriter(node().getAssigneeNode()).writeExpression(builder).append(" = ")
					.append(getWriter(node().getAssignmentNode()).writeExpression()).append(".charCodeAt(0)");
			}
		}
		
		return getWriter(node().getAssigneeNode()).writeExpression(builder).append(" = ")
			.append(getWriter(node().getAssignmentNode()).writeExpression());
	}
}