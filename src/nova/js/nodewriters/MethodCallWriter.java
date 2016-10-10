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
			if (node().isSuperCall())
			{
				getWriter((NovaMethodDeclaration)callable).writeSuperName(builder);
			}
			else
			{
				getWriter((MethodDeclaration)callable).writeName(builder);
			}
		}
		else
		{
			builder.append(node().getName());
		}
		
		if (callable instanceof InitializationMethod)
		{
			builder.append(".call(this");
			
			if (node().getArgumentList().getNumVisibleChildren() > 0)
			{
				builder.append(", ");
			}
			
			getWriter(node().getArgumentList()).write(builder, false).append(')');
		}
		else
		{
			getWriter(node().getArgumentList()).write(builder);
		}
		
		return writeArrayAccess(builder);
	}
}