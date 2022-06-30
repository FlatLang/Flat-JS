package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.annotations.AsyncAnnotation;
import net.fathomsoft.nova.tree.variables.ObjectReference;

public abstract class MethodCallWriter extends VariableWriter
{
	public abstract MethodCall node();
	
	@Override
	public StringBuilder writeUseExpression(StringBuilder builder)
	{
		CallableMethod callable = node().getCallableDeclaration();

		if (callable instanceof InitializationMethod)
		{
			if (node().getParentMethod() instanceof InitializationMethod) {
				builder.append("self = ");
			} else {
				builder.append("__value = ");
			}
		}

		writeUsePrefix(builder);

		if (node().isChainNavigation()) {

			builder.append("__chain");

			if (((Node)callable).containsAnnotationOfType(AsyncAnnotation.class)) {
				builder.append("Async");
			}

			builder.append("('").append(writeName()).append("', [");
			getWriter(node().getArgumentList()).write(builder, false).append("])");
		} else {
			writeName(builder);

			if (callable instanceof InitializationMethod) {
				builder.append(".call(");

				if (node().getParentMethod() instanceof Constructor) {
					builder.append("__value");
				} else {
					builder.append("this");
				}

				if (node().getArgumentList().getNumVisibleChildren() > 0) {
					builder.append(", ");
				}

				return getWriter(node().getArgumentList()).write(builder, false).append(')');
			} else {
				getWriter(node().getArgumentList()).write(builder);
			}
		}

		writeArrayAccess(builder);

//		writeNullFallbackPostfix(builder);

		return builder;
	}

	@Override
	public StringBuilder writeName(StringBuilder builder) {
		CallableMethod callable = node().getCallableDeclaration();

		if (callable instanceof MethodDeclaration) {
			if (node().isSuperCall()) {
				return getWriter((NovaMethodDeclaration) callable).writeSuperName(builder);
			} else {
				return getWriter((MethodDeclaration) callable).writeName(builder);
			}
		} else {
			return builder.append(node().getName());
		}
	}

	@Override
	public void writeNullFallbackPostfix(StringBuilder builder) {
		if (!(node().getNovaMethod() instanceof Constructor)) {
			super.writeNullFallbackPostfix(builder);
		}
	}
}