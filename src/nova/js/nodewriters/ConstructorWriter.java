package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ConstructorWriter extends BodyMethodDeclarationWriter
{
	public abstract Constructor node();
	
	@Override
	public StringBuilder writeBody(StringBuilder builder)
	{
		builder.append("{\n");
		
		ClassDeclaration extended = node().getDeclaringClass().getExtendedClassDeclaration();
		
		if (extended != null)
		{
			getWriter(extended).writeName(builder).append(".call(this);\n");
		}
		
		getWriter(node().getDeclaringClass()).writeName(builder).append(".call(this);\n");
		builder.append("this.__proto__ = ").append(getWriter(node().getDeclaringClass()).writeName()).append(".prototype;\n\n");
		
		getWriter(node().getScope()).write(builder, false, true);
		
		return builder.append('}');
	}
	
	@Override
	public StringBuilder writeAssignedVariable(StringBuilder builder)
	{
		return writeName(builder);//builder.append("novaConstructors.new").append(writeName());
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
		return builder.append("novaConstructors.").append(writeConstructorListName());
	}
}