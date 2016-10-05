package nova.js.nodewriters;

import net.fathomsoft.nova.tree.variables.FieldDeclaration;

public abstract class FieldDeclarationWriter extends InstanceDeclarationWriter
{
	public abstract FieldDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		builder.append("this.");
		
		return super.write(builder);
	}
}