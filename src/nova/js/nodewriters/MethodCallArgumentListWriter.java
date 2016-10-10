package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class MethodCallArgumentListWriter extends ArgumentListWriter
{
	public abstract MethodCallArgumentList node();
	
	@Override
	public StringBuilder write(StringBuilder builder, boolean parenthesis)
	{
		MethodCall call = node().getMethodCall();
		
		CallableMethod method = call.getInferredDeclaration();
		
		Value[] values = method instanceof NovaMethodDeclaration ? node().getArgumentsInOrder((NovaMethodDeclaration)method) : node().getArgumentsInOrder();
		
		if (parenthesis)
		{
			builder.append('(');
		}
		
		for (int i = 0; i < values.length; i++)
		{
			if (i > 0)
			{
				builder.append(", ");
			}
			
			getWriter(values[i]).writeExpression(builder);
		}
		
		if (parenthesis)
		{
			builder.append(')');
		}
		
		return builder;
	}
}