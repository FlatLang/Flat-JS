package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.lambda.LambdaMethodDeclaration;

public abstract class ClosureWriter extends VariableWriter
{
	public abstract Closure node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		if (node().getMethodDeclaration() instanceof LambdaMethodDeclaration)
		{
			return getWriter(node().getMethodDeclaration()).writeExpression(builder);
		}
		
		return super.writeExpression(builder);
	}
}