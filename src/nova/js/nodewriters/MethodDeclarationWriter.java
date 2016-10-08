package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class MethodDeclarationWriter extends InstanceDeclarationWriter
{
	public abstract MethodDeclaration node();
	
	public StringBuilder writeSignature()
	{
		return writeSignature(new StringBuilder());
	}
	
	public StringBuilder writeSignature(StringBuilder builder)
	{
		return builder.append("function ").append(getWriter(node().getParameterList()).write());
	}
}