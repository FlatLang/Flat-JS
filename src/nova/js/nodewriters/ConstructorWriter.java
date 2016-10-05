package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ConstructorWriter extends BodyMethodDeclarationWriter
{
	public abstract Constructor node();
	
	@Override
	public StringBuilder writeBody(StringBuilder builder)
	{
		builder.append("{\n");
		
		getWriter(node().getScope()).write(builder, false, true);
		
		ClassDeclaration extended = node().getDeclaringClass().getExtendedClassDeclaration();
		
		if (extended != null)
		{
			builder.append(extended.getName()).append(".call(this);\n");
		}
		
		return builder.append('}');
	}
	
	@Override
	public StringBuilder writeAssignedVariable(StringBuilder builder)
	{
		return builder.append("novaConstructors.new").append(writeName());
	}
}