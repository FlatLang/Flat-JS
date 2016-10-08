package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ClosureWriter extends VariableWriter
{
	public abstract Closure node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return getWriter(node().getMethodDeclaration()).writeExpression(builder);
	}
}