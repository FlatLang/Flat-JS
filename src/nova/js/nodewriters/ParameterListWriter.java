package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class ParameterListWriter extends TypeListWriter
{
	public abstract ParameterList node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		final int[] i = new int[] { 0 };
		
		builder.append('(');
		
		node().forEachVisibleChild(child -> {
			if (i[0]++ > 0)
			{
				builder.append(", ");
			}
			
			getWriter(child).writeExpression(builder);
		});
		
		return builder.append(')');
	}
}