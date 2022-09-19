package org.flat.js.nodewriters;

import org.flat.tree.*;
import org.flat.tree.annotations.AsyncAnnotation;
import org.flat.tree.lambda.LambdaMethodDeclaration;

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