package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;
import org.flatlang.tree.annotations.AwaitAnnotation;

public abstract class IdentifierWriter extends ValueWriter implements AccessibleWriter
{
	public abstract Identifier node();

	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		if (isInstanceClosure())
		{
			return writeInstanceClosure(builder);
		}

		if (node().containsAnnotationOfType(AwaitAnnotation.class)) {
			builder.append("await ");
		}

		writeNullFallbackPrefix(builder);

		writeUseExpression(builder);

		writeNullFallbackPostfix(builder);

		writeAccessedExpression(builder);

		return builder;
	}

	@Override
	public void writeNullFallbackPrefix(StringBuilder builder, int skipCount) {
		AccessibleWriter.super.writeNullFallbackPrefix(builder, skipCount);
	}

	public StringBuilder writeUseExpression(StringBuilder builder)
	{
		return writeName(builder).append(writeArrayAccess());
	}

	public StringBuilder writeName()
	{
		return writeName(new StringBuilder());
	}

	public StringBuilder writeName(StringBuilder builder)
	{
		switch (node().getName()) {
			case "class": return builder.append("_js_class");
			case "default": return builder.append("_js_default");
			case "case": return builder.append("_js_case");
			case "function": return builder.append("_js_function");
		}

		return builder.append(node().getName());
	}
}