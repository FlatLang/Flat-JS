package org.flat.js.nodewriters;

import org.flat.tree.Package;

public abstract class PackageWriter extends NodeWriter
{
	public abstract Package node();

	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}