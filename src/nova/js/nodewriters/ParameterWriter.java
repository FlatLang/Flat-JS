package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ParameterWriter extends LocalDeclarationWriter
{
	public abstract Parameter node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return writeName(builder);
	}
}