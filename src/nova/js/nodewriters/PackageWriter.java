package nova.js.nodewriters;

import net.fathomsoft.nova.tree.Package;

public abstract class PackageWriter extends NodeWriter
{
	public abstract Package node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}