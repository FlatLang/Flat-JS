package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class BodyMethodDeclarationWriter extends NovaMethodDeclarationWriter
{
	public abstract BodyMethodDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		builder.append("this.").append(writeName()).append(" = function ");
		
		getWriter(node().getParameterList()).write(builder).append(" ");
		
		getWriter(node().getScope()).write(builder, true, false);
		
		return builder.append(";\n\n");
	}
}