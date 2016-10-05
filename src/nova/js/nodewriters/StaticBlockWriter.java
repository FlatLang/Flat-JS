package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class StaticBlockWriter extends NodeWriter
{
	public abstract StaticBlock node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		if (node().getScope().getNumVisibleChildren() == 0)
		{
			return builder;
		}
		
		builder.append("(function () ");
		
		getWriter(node().getScope()).write(builder, true, false);
		
		return builder.append(")();\n");
	}
}