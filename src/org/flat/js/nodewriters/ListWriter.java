package org.flat.js.nodewriters;

import org.flat.tree.List;

public abstract class ListWriter extends NodeWriter
{
	public abstract List node();

	@Override
	public StringBuilder write(StringBuilder builder)
	{
		node().forEachVisibleChild(child -> {
			getWriter(child).write(builder);
		});

		return builder;
	}
}