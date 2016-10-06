package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class IdentifierWriter extends ValueWriter implements AccessibleWriter
{
	public abstract Identifier node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		writeUseExpression(builder);
		writeArrayAccess(builder);
		writeAccessedExpression(builder);
		
		return builder;
	}
	
	public StringBuilder writeUseExpression(StringBuilder builder)
	{
		return builder.append(node().getName());
	}
}