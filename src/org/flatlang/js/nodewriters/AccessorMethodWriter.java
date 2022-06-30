package org.flatlang.js.nodewriters;

import org.flatlang.tree.AccessorMethod;

public abstract class AccessorMethodWriter extends PropertyMethodWriter
{
	public abstract AccessorMethod node();
	
	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		builder.append("accessor_");
		
		return super.writeName(builder);
	}
}