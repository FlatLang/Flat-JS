package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class MethodListWriter extends TypeListWriter
{
	public abstract MethodList node();
	
	@Override
	public StringBuilder write(final StringBuilder builder)
	{
		node().forEachVisibleChild(method -> {
			getWriter(method).write(builder);
		});
		
		return builder;
	}
}