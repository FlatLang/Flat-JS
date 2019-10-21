package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class TernaryOperationWriter extends IValueWriter implements AccessibleWriter
{
	public abstract TernaryOperation node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return getWriter(node().getCondition()).writeExpression(builder).append(" ? ")
			.append(getWriter(node().getTrueValue()).writeExpression()).append(" : ")
			.append(getWriter(node().getFalseValue()).writeExpression());
	}

	@Override
	public void writeNullFallbackPrefix(StringBuilder builder, int skipCount) {

	}

	@Override
	public void writeNullFallbackPostfix(StringBuilder builder) {

	}
}