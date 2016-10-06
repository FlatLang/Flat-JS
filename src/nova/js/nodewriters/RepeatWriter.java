package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class RepeatWriter extends LoopWriter
{
	public abstract Repeat node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		Value value = node().getValueNode();
		
		if (value != null)
		{
			builder.append("for (").append(node().getName()).append(" = 0; ").append(node().getName()).append(" < ").append(getWriter(value).writeExpression()).append("; ").append(node().getName()).append("++) ");
		}
		else
		{
			builder.append("for (;;) ");
		}
		
		Scope scope = node().getScope();
		
		getWriter(scope).write(builder);
		
		return builder;
	}
}