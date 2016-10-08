package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class StaticClassReferenceWriter extends IIdentifierWriter
{
	public abstract StaticClassReference node();
	
	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		return getWriter(node().getTypeClass()).writeName(builder);
	}
}