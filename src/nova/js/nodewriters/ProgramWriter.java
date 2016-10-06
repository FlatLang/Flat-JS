package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import nova.js.engines.JSCompileEngine;

public abstract class ProgramWriter extends TypeListWriter
{
	public abstract Program node();
	
	@Override
	public StringBuilder write(final StringBuilder builder)
	{
		boolean localScope = ((JSCompileEngine)node().getProgram().getController().compileEngine).localScope;
		
		if (localScope)
		{
			builder.append("(function () {\n");
		}
		
		builder.append("var novaConstructors = {};\n\n");
		
		node().forEachChild(child -> {
			getWriter(child).write(builder);
		});
		
		if (localScope)
		{
			builder.append("})();");
		}
		
		return builder;
	}
}