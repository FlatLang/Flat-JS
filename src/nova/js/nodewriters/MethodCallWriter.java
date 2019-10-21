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

		writeUsePrefix(builder);

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
			builder.append(".call(");

			if (node().getParentMethod() instanceof Constructor) {
				builder.append("__value");
			} else {
				builder.append("this");
			}
			
			if (node().getArgumentList().getNumVisibleChildren() > 0)
			{
				builder.append(", ");
			}
			
			return getWriter(node().getArgumentList()).write(builder, false).append(')');
		}
		else
		{
			getWriter(node().getArgumentList()).write(builder);
		}
		
		writeArrayAccess(builder);

//		writeNullFallbackPostfix(builder);

		return builder;
	}

	@Override
	public void writeNullFallbackPostfix(StringBuilder builder) {
		if (!(node().getNovaMethod() instanceof Constructor)) {
			super.writeNullFallbackPostfix(builder);
		}
	}
}