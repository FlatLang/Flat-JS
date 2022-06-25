package nova.js.nodewriters;

import net.fathomsoft.nova.tree.Constructor;
import net.fathomsoft.nova.tree.InitializationMethod;
import net.fathomsoft.nova.tree.InstanceDeclaration;
import net.fathomsoft.nova.tree.ReferenceParameter;
import net.fathomsoft.nova.tree.lambda.LambdaMethodDeclaration;
import net.fathomsoft.nova.tree.variables.FieldDeclaration;
import net.fathomsoft.nova.tree.variables.Variable;
import net.fathomsoft.nova.tree.variables.VariableDeclaration;

public abstract class VariableWriter extends IdentifierWriter
{
	public abstract Variable node();

	public StringBuilder writeUsePrefix(StringBuilder builder) {
		if (!node().isAccessed())
		{
			VariableDeclaration declaration = node().getDeclaration();

			if (!declaration.isExternal())
			{
				// static field
				if (!declaration.isInstance())
				{
					getWriter(declaration.getDeclaringClass()).writeName(builder).append('.');
				}
				else if (declaration instanceof InitializationMethod)
				{
					getWriter(declaration.getDeclaringClass()).writeName(builder).append('.');
				}
				// field access
				else if (!(declaration instanceof Constructor) && !declaration.isLocal())
				{
					if (!(node().getDeclaration() instanceof ReferenceParameter))
					{
						if (node().getParentMethod() instanceof LambdaMethodDeclaration) {
							builder.append("self");
						} else if (declaration instanceof InstanceDeclaration && ((InstanceDeclaration) declaration).isStatic()) {
							getWriter(declaration.getDeclaringClass()).writeUseExpression(builder);
						} else {
							builder.append("this");
						}

						builder.append('.');
					}
				}
			}
		}

		return builder;
	}

	@Override
	public StringBuilder writeUseExpression(StringBuilder builder) {
		writeUsePrefix(builder);

		return super.writeUseExpression(builder);
	}

	@Override
	public StringBuilder writeExpression(StringBuilder builder) {
		if (isInstanceClosure())
		{
			return writeInstanceClosure(builder);
		}

		return super.writeExpression(builder);
	}

	@Override
	public void writeNullFallbackPrefix(StringBuilder builder, int skipCount) {
		if (!(node().declaration instanceof ReferenceParameter) || !node().doesAccess()) {
			super.writeNullFallbackPrefix(builder, skipCount);
		} else {
			super.writeNullFallbackPrefix(builder, skipCount + 1);
		}
	}

	@Override
	public void writeNullFallbackPostfix(StringBuilder builder) {
		if (!(node().declaration instanceof ReferenceParameter) || !node().doesAccess()) {
			super.writeNullFallbackPostfix(builder);
		}
	}
}