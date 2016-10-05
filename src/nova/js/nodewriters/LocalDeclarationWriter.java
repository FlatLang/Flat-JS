package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class LocalDeclarationWriter extends VariableDeclarationWriter
{
	public abstract LocalDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder.append("var ").append(node().getName()).append(";\n");
	}
}