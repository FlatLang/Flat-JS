package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.variables.FieldList;
import net.fathomsoft.nova.tree.variables.InstanceFieldList;

public abstract class ClassDeclarationWriter extends InstanceDeclarationWriter
{
	public abstract ClassDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		builder.append("var ").append(node().getName()).append(" = function () {\n");
		
		getWriter(node().getFieldList().getPrivateFieldList()).write(builder);
		getWriter(node().getFieldList().getPublicFieldList()).write(builder);
		
		builder.append('\n');
		
		node().forEachVisibleChild(child -> {
			builder.append('\n').append(getWriter(child).write());
		});
		
		builder.append("\n};\n\n");
		
		getWriter(node().getConstructorList()).write(builder);
		getWriter(node().getDestructorList()).write(builder);
		getWriter(node().getMethodList()).write(builder);
		getWriter(node().getPropertyMethodList()).write(builder);
		getWriter(node().getHiddenMethodList()).write(builder);
		getWriter(node().getVirtualMethodList()).write(builder);
		
		generateStaticBlocksSource(builder);
		
		return builder;
	}
	
	private StringBuilder generateStaticBlocksSource(final StringBuilder builder)
	{
		if (node().getStaticBlockList().getNumVisibleChildren() > 0)
		{
			builder.append('\n');
			
			node().getStaticBlockList().forEachVisibleChild(block -> {
				getWriter(block).write(builder);
			});
			
			builder.append('\n');
		}
		
		return builder;
	}
}