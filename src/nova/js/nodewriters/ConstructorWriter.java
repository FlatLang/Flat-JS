package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ConstructorWriter extends BodyMethodDeclarationWriter
{
	public abstract Constructor node();
	
	@Override
	public StringBuilder writeAssignedVariable(StringBuilder builder)
	{
		return builder.append("novaConstructors.new").append(writeName());
	}
}