package nova.js.nodewriters;

import net.fathomsoft.nova.tree.ClassDeclaration;
import net.fathomsoft.nova.tree.InitializationMethod;

public abstract class InitializationMethodWriter extends BodyMethodDeclarationWriter
{
	public abstract InitializationMethod node();
	
	@Override
	public StringBuilder writePrototypeAccess(StringBuilder builder)
	{
		return builder;
	}
	
	@Override
	public StringBuilder writePrototypeAssignment(StringBuilder builder, ClassDeclaration clazz, boolean superCall)
	{
		return builder;
	}
	
	@Override
	public StringBuilder writeSuperName(StringBuilder builder)
	{
		return writeName(builder);
	}
	
	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		return builder.append("init").append(writeOverload());
	}
}