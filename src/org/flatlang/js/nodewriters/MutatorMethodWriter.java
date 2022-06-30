package org.flatlang.js.nodewriters;

import org.flatlang.tree.MutatorMethod;

public abstract class MutatorMethodWriter extends PropertyMethodWriter
{
	public abstract MutatorMethod node();
	
	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		builder.append("mutator_");
		
		return super.writeName(builder);
	}
}