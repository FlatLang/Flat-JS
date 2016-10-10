package nova.js.nodewriters;

import net.fathomsoft.nova.Nova;
import net.fathomsoft.nova.tree.*;

public abstract class AssignmentWriter extends ValueWriter
{
	public abstract Assignment node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		ClassDeclaration integerClass = node().getProgram().getClassDeclaration("nova/primitive/number/Integer");
		ClassDeclaration charClass = node().getProgram().getClassDeclaration("nova/primitive/number/Char");
		
		ClassDeclaration set = node().getAssigneeNode().getReturnedNode().getTypeClass();
		ClassDeclaration value = node().getAssignmentNode().getReturnedNode().getTypeClass();
		
		String before = "";
		String after = "";
		
		if (!node().getAssignmentNode().getReturnedNode().isPrimitiveArray() && set != null && value != null)
		{
			if (set == charClass && value != charClass && value.implementsInterface(integerClass))
			{
				before = "String.fromCharCode(";
				after = ")";
			}
			else if (set != charClass && set.implementsInterface(integerClass) && value == charClass)
			{
				after = ".charCodeAt(0)";
			}
		}
		
		return getWriter(node().getAssigneeNode()).writeExpression(builder).append(" = ")
			.append(before).append(getWriter(node().getAssignmentNode()).writeExpression()).append(after);
	}
}