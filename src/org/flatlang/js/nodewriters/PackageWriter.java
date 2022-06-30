package org.flatlang.js.nodewriters;

import org.flatlang.tree.Package;

public abstract class PackageWriter extends NodeWriter
{
	public abstract Package node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}