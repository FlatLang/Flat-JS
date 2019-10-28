package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class LocalDeclarationWriter extends VariableDeclarationWriter
{
	public abstract LocalDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		builder.append("var ");
		
		writeName(builder);
		
		return builder.append(" = null;\n");
	}
}