package org.flatlang.js.nodewriters;

import org.flatlang.tree.annotations.Annotation;

public abstract class AnnotationWriter extends NodeWriter
{
	public abstract Annotation node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		return builder;
	}
}