package nova.js.nodewriters;

import net.fathomsoft.nova.tree.InitializationMethod;

public abstract class InitializationMethodWriter extends BodyMethodDeclarationWriter
{
	public abstract InitializationMethod node();
	
	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		return builder.append("init").append(writeOverload());
	}
}