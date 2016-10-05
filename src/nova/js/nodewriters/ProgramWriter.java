package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ProgramWriter extends NodeWriter
{
	public abstract Program node();
	
	@Override
	public StringBuilder write(final StringBuilder builder)
	{
		builder.append("var novaConstructors = {};\n\n");
		
		node().forEachChild(child -> {
			getWriter(child).write(builder);
		});
		
		return builder;
	}
}