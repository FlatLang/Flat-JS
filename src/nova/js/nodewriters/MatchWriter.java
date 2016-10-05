package nova.js.nodewriters;

import net.fathomsoft.nova.tree.Scope;
import net.fathomsoft.nova.tree.Value;
import net.fathomsoft.nova.tree.match.Match;

public abstract class MatchWriter extends ControlStatementWriter
{
	public abstract Match node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		Scope scope = node().getScope();
		
		if (node().isConventionalSwitch())
		{
			Value control = node().getControlValue();
			
			builder.append("switch (" + getWriter(control).writeExpression() + ")\n");
			
			getWriter(scope).write(builder);
		}
		else
		{
			boolean requiresFacade = node().requiresLoopFacade();
			
			if (requiresFacade)
			{
				builder.append("do\n{\n");
			}
			
			getWriter(scope).write(builder, false);
			
			if (requiresFacade)
			{
				builder.append("}\nwhile (0);\n");
			}
		}
		
		return builder;
	}
}