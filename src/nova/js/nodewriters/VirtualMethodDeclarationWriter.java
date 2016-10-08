package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class VirtualMethodDeclarationWriter extends BodyMethodDeclarationWriter
{
	public abstract VirtualMethodDeclaration node();
	
	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		return getWriter(node().base).writeName(builder);
	}
}