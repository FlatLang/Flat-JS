package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

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