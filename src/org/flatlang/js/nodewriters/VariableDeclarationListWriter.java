package org.flatlang.js.nodewriters;

import org.flatlang.tree.variables.VariableDeclarationList;

public abstract class VariableDeclarationListWriter extends ListWriter
{
	public abstract VariableDeclarationList node();
}