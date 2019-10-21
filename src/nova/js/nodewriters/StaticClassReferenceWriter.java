package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.util.Location;

public abstract class StaticClassReferenceWriter extends IIdentifierWriter
{
	public abstract StaticClassReference node();
	
	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		return getWriter(node().getTypeClass()).writeName(builder);
	}

	@Override
	public StringBuilder writeExpression(StringBuilder builder) {
		if (node().isMetaClass()) {
			ClassDeclaration c = node().getFileDeclaration().getImportedClass(node(), node().getName());
			String classLocation = c.getClassLocation();
			boolean isInterface = false;

			Instantiation instantiation = Instantiation.decodeStatement(node().parent, "new String(\"" + classLocation + "\")", Location.INVALID, true);

			return builder.append("new novaConstructors.newClass(").append(getWriter(instantiation).writeExpression()).append(", ").append(isInterface).append(")");
		} else {
			return super.writeExpression(builder);
		}
	}

	@Override
	public void writeNullFallbackPrefix(StringBuilder builder, int skipCount) {
		super.writeNullFallbackPrefix(builder, skipCount + 1);
	}

	@Override
	public void writeNullFallbackPostfix(StringBuilder builder) {

	}
}