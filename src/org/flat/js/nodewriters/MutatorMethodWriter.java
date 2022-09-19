package org.flat.js.nodewriters;

import org.flat.tree.MutatorMethod;

public abstract class MutatorMethodWriter extends PropertyMethodWriter
{
	public abstract MutatorMethod node();

	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		builder.append("mutator_");

		return super.writeName(builder);
	}

	@Override
	public StringBuilder write(StringBuilder builder) {
		if (node().isDisabled()) {
			return builder;
		}

		return super.write(builder);
	}
}