package org.flatlang.js.nodewriters;

import org.flatlang.tree.*;
import org.flatlang.tree.annotations.AsyncAnnotation;
import org.flatlang.tree.lambda.LambdaMethodDeclaration;

import java.util.Arrays;

public abstract class AnonymousCompilerMethodWriter extends BodyMethodDeclarationWriter
{
	public abstract AnonymousCompilerMethod node();

	@Override
	public StringBuilder writePrototypeAccess(StringBuilder builder) {
		if (node().isExtension()) {
			return builder;
		}

		return super.writePrototypeAccess(builder);
	}
}