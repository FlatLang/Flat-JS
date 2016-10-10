package nova.js.nodewriters;

import net.fathomsoft.nova.tree.ExternalCodeBlock;

public abstract class ExternalCodeBlockWriter extends NodeWriter
{
	public abstract ExternalCodeBlock node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder.append(node().joinContents(x -> getWriter(x).writeExpression().toString())).append('\n');
	}
}