package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.variables.ObjectReference;

public abstract class MethodCallWriter extends VariableWriter
{
	public abstract MethodCall node();
	
	@Override
	public StringBuilder writeUseExpression(StringBuilder builder)
	{
		CallableMethod callable = node().getCallableDeclaration();
		
		if (callable instanceof MethodDeclaration)
		{
			getWriter((MethodDeclaration)callable).writeName(builder);
			
			if (node().isSuperCall())
			{
				builder.append("_base");
			}
		}
		else
		{
			builder.append(node().getName());
		}
		
		getWriter(node().getArgumentList()).write(builder);
		
		return builder;
	}
}