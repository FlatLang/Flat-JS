package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

public abstract class CastWriter extends IValueWriter
{
	public abstract Cast node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		String before = "";
		String after = "";
		
		ClassDeclaration charClass = node().getProgram().getClassDeclaration("flatlang/primitive/number/Char");
		ClassDeclaration integerClass = node().getProgram().getClassDeclaration("flatlang/primitive/number/Integer");
		
		ClassDeclaration cast = node().getTypeClass();
		ClassDeclaration value = node().getReturnedNode().getTypeClass(false);
		
		if (!node().getReturnedNode().isPrimitiveArray())
		{
			if (value == charClass && cast != charClass && cast.implementsInterface(integerClass))
			{
				after = ".charCodeAt(0)";
			}
			else if (value != null && value != charClass && value.implementsInterface(integerClass) && cast == charClass)
			{
				before = "String.fromCharCode(";
				after = ")";
			}
		}
		
		return builder.append(before).append(getWriter(node().getValueNode()).writeExpression()).append(after);
	}
}