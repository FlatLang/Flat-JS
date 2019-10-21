package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.variables.Array;

public abstract class InstantiationWriter extends IIdentifierWriter
{
	public abstract Instantiation node();
	
	@Override
	public StringBuilder writeUseExpression(StringBuilder builder)
	{
		getWriter(node().getIdentifier()).writeUseExpression(builder);

		writeNullFallbackPostfix(builder);

		return builder;
	}

	@Override
	public void writeNullFallbackPrefix(StringBuilder builder, int skipCount) {
		super.writeNullFallbackPrefix(builder, skipCount + 1);
	}

	@Override
	public void writeNullFallbackPostfix(StringBuilder builder) {
		if (node().doesAccess()) {
//			builder.append(")");
		}
	}
}