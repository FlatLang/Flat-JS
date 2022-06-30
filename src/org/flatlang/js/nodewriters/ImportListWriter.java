package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;

public abstract class ImportListWriter extends TypeListWriter
{
	public abstract ImportList node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}