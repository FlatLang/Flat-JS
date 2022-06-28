package nova.js.nodewriters;

import net.fathomsoft.nova.Nova;
import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.variables.Variable;

import static nova.js.nodewriters.Writer.getWriter;

public interface AccessibleWriter
{
	public abstract Accessible node();
	
	default StringBuilder writeAccessedExpression()
	{
		return writeAccessedExpression(new StringBuilder());
	}
	
	default StringBuilder writeAccessedExpression(boolean dot)
	{
		return writeAccessedExpression(new StringBuilder(), dot);
	}
	
	default StringBuilder writeAccessedExpression(StringBuilder builder)
	{
		return writeAccessedExpression(builder, true);
	}

	default boolean shouldFallbackToNull() {
		return node() != null &&
				!(node().getParent() instanceof Priority) &&
				node() instanceof Value &&
				!node().isAccessed() &&
				node().doesAccess() &&
				!node().toValue().isPrimitive();
	}

	default void writeNullFallbackPrefix(StringBuilder builder) {
		writeNullFallbackPrefix(builder, 0);
	}

	default void writeNullFallbackPrefix(StringBuilder builder, int skipCount) {
		if (shouldFallbackToNull()) {
			StringBuilder buffer = new StringBuilder();

			Accessible current = node().getAccessedNode();

			while (current != null) {
				buffer.append("(");

				current = current.getAccessedNode();
			}

			builder.append(buffer.substring(skipCount));
		}
	}

	default void writeNullFallbackPostfix(StringBuilder builder) {
		if (node().doesAccess()) {
			Accessible current = node();

			while (current != null && !getWriter(current).shouldFallbackToNull()) {
				current = current.getAccessingNode(true);
			}

			if (current != null && getWriter(current).shouldFallbackToNull()) {
				builder.append(" || nova_null)");
			}
		}
	}

	default StringBuilder writeAccessedExpression(StringBuilder builder, boolean dot)
	{
		if (node().doesAccess())
		{
			if (dot)
			{
				builder.append('.');
			}
			
			builder.append(getWriter(node().getAccessedNode()).writeExpression());
		}
		
		return builder;
	}
	
	default StringBuilder writeUntil(Accessible stopBefore)
	{
		return writeUntil(new StringBuilder(), stopBefore);
	}
	
	default StringBuilder writeUntil(StringBuilder builder, Accessible stopBefore)
	{
		Value current = node().toValue();
		
		while (current != null && current != stopBefore)
		{
			if (current != node())
			{
				builder.append('.');
			}
			
			getWriter(current).writeUseExpression(builder);
			
			current = ((Accessible)current).getAccessedNode();
		}
		
		return builder;
	}
	
	default StringBuilder writeInstanceClosure(StringBuilder builder)
	{
		return getWriter((Closure)node().getReturnedNode()).writeInstanceExpression(builder, node());
	}
	
	default boolean isInstanceClosure()
	{
		Value returned = node().getReturnedNode();
		
		if (returned instanceof Closure)
		{
			Closure c = (Closure)returned;
			
			return c.getMethodDeclaration().isInstance();
		}
		
		return false;
	}
}