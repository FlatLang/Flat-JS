package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.variables.Array;

public abstract class InstantiationWriter extends IIdentifierWriter
{
	public abstract Instantiation node();
	
	@Override
	public StringBuilder writeUseExpression(StringBuilder builder)
	{
		if (node().getIdentifier() instanceof Array == false)
		{
			builder.append("new ");
		}
		
		return getWriter(node().getIdentifier()).writeUseExpression(builder);
	}
}