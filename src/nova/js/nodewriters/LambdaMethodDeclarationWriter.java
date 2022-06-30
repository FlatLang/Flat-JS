package nova.js.nodewriters;

import net.fathomsoft.nova.tree.ClassDeclaration;
import net.fathomsoft.nova.tree.annotations.AsyncAnnotation;
import net.fathomsoft.nova.tree.lambda.LambdaMethodDeclaration;

public abstract class LambdaMethodDeclarationWriter extends BodyMethodDeclarationWriter
{
	public abstract LambdaMethodDeclaration node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		return writeSignature(builder).append(' ').append(writeBody());
	}

	@Override
	public StringBuilder writeSignature(StringBuilder builder) {
		if (node().containsAnnotationOfType(AsyncAnnotation.class)) {
			builder.append("async ");
		}

		return builder.append(getWriter(node().getParameterList()).write()).append(" =>");
	}

	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
	
	@Override
	public StringBuilder writePrototypeAssignment(StringBuilder builder, ClassDeclaration clazz, boolean superCall)
	{
		return builder;
	}
}