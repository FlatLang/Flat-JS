package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class BodyMethodDeclarationWriter extends NovaMethodDeclarationWriter
{
	public abstract BodyMethodDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		writeAssignedVariable(builder).append(" = function ");
		
		getWriter(node().getParameterList()).write(builder).append(" ");
		
		writeBody(builder);
		
		return builder.append(";\n\n");
	}
	
	public StringBuilder writeBody()
	{
		return writeBody(new StringBuilder());
	}
	
	public StringBuilder writeBody(StringBuilder builder)
	{
		return getWriter(node().getScope()).write(builder, true, false);
	}
}