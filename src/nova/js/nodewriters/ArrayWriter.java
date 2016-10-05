package nova.js.nodewriters;

import net.fathomsoft.nova.tree.variables.Array;

public abstract class ArrayWriter extends VariableDeclarationWriter
{
	public abstract Array node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return builder.append("[]");
	}
}