package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ImportListWriter extends ListWriter
{
	public abstract ImportList node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}