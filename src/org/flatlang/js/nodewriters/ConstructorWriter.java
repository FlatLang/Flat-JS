package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

public abstract class ConstructorWriter extends BodyMethodDeclarationWriter
{
	public abstract Constructor node();
	
	@Override
	public StringBuilder writeBody(StringBuilder builder)
	{
		builder.append("{\n");
		
		ClassDeclaration extended = node().getDeclaringClass().getExtendedClassDeclaration();

		builder.append("var __value = new ");
		getWriter(node().getDeclaringClass()).writeName(builder).append("();\n");

		if (extended != null)
		{
			getWriter(extended).writeName(builder).append(".call(__value);\n");
		}

//		builder.append("this.__proto__ = ").append(getWriter(node().getDeclaringClass()).writeName()).append(".prototype;\n\n");
		
		AssignmentMethod assignmentMethod = node().getParentClass().getAssignmentMethodNode();
		
		getWriter(assignmentMethod).writeAssignedVariable(builder).append(".apply(__value, [].slice.call(arguments));\n");
		
		getWriter(node().getScope()).write(builder, false, true);

		builder.append("return __value;\n");
		
		return builder.append('}');
	}

	@Override
	public StringBuilder write(StringBuilder builder)
	{
		writeAssignedVariable(builder).append(" = function ");

		getWriter(node().getParameterList()).write(builder).append(" ");

		writeBody(builder);

		return builder.append(";\n\n");
	}

	@Override
	public StringBuilder writePrototypeAccess(StringBuilder builder) {
		return builder;
	}

	@Override
	public StringBuilder writeAssignedVariable(StringBuilder builder)
	{
		return writeName(builder);//builder.append("flatConstructors.new").append(writeName());
	}
	
	public StringBuilder writeConstructorListName()
	{
		return writeConstructorListName(new StringBuilder());
	}
	
	public StringBuilder writeConstructorListName(StringBuilder builder)
	{
		return builder.append("new").append(super.writeName(new StringBuilder()));
	}
	
	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		return builder.append("flatConstructors.").append(writeConstructorListName());
	}
}