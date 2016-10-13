package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.lambda.LambdaMethodDeclaration;

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
	
	private static boolean isLambda(Node n)
	{
		return ((Closure)n).isLambda();
	}
	
	public StringBuilder writeBody(StringBuilder builder)
	{
		builder.append("{\n");
		
		if (node().whereChildOfType(Closure.class, BodyMethodDeclarationWriter::isLambda))
		{
			builder.append("var self = this;\n\n");
		}
		
		getWriter(node().getScope()).write(builder, false);
		
		return builder.append('}');
	}
}