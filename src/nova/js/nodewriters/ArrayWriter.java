package nova.js.nodewriters;

import net.fathomsoft.nova.tree.variables.Array;

public abstract class ArrayWriter extends VariableDeclarationWriter
{
	public abstract Array node();
	
	@Override
	public StringBuilder writeUseExpression(StringBuilder builder)
	{
		if (isInstanceClosure())
		{
			return writeInstanceClosure(builder);
		}
		
		return builder.append("[]");
	}
}