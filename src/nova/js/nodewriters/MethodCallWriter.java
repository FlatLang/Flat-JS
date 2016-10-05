package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class MethodCallWriter extends VariableWriter
{
	public abstract MethodCall node();
	
	@Override
	public StringBuilder writeUseExpression(StringBuilder builder)
	{
		CallableMethod callable = node().getCallableDeclaration();
		
		if (callable instanceof NovaMethodDeclaration)
		{
			getWriter((NovaMethodDeclaration)callable).writeName(builder);
		}
		else
		{
			builder.append(node().getName());
		}
		
		getWriter(node().getArgumentList()).write(builder);
		
		return builder;
	}
}