package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.variables.Array;

public abstract class InstantiationWriter extends IIdentifierWriter
{
	public abstract Instantiation node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		if (node().getIdentifier() instanceof Array == false)
		{
			builder.append("new ");
		}
		
		getWriter(node().getIdentifier()).writeExpression(builder);
		
		return builder;
	}
}