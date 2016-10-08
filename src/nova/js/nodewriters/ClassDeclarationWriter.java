package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

import java.util.Arrays;

public abstract class ClassDeclarationWriter extends InstanceDeclarationWriter
{
	public abstract ClassDeclaration node();
	
	@Override
	public StringBuilder write(StringBuilder builder)
	{
		builder.append("var ").append(writeName()).append(" = function () {\n");
		
		getWriter(node().getFieldList().getPrivateFieldList()).write(builder);
		getWriter(node().getFieldList().getPublicFieldList()).write(builder);
		
		builder.append('\n');
		
		node().forEachVisibleChild(child -> {
			builder.append('\n').append(getWriter(child).write());
		});
		
		builder.append("\n};\n\n");
		
		if (node().doesExtendClass())
		{
			writeName(builder).append(".prototype = Object.create(").append(getWriter(node().getExtendedClassDeclaration()).writeName()).append(".prototype);\n");
			writeName(builder).append(".prototype.constructor = ").append(writeName()).append(";\n\n");
			
			node().getExtendedClassDeclaration().getMethodList().forEachNovaMethod(method -> {
				getWriter(method).writePrototypeAssignment(builder, node(), true);
			});
			/*node().getMethodList().forEachNovaMethod(method -> {
				getWriter(method).writePrototypeAssignment(builder, node(), true);
			});*/
			
			builder.append("\n");
		}
		
		node().getInterfacesImplementationList().forEachVisibleListChild(i -> {
			Arrays.stream(i.getTypeClass().getMethods()).forEach(method -> {
				getWriter(method).writePrototypeAssignment(builder, node());
			});
		});
		
		builder.append("\n");
		
		getWriter(node().getDestructorList()).write(builder);
		getWriter(node().getMethodList()).write(builder);
		getWriter(node().getPropertyMethodList()).write(builder);
		getWriter(node().getHiddenMethodList()).write(builder);
		getWriter(node().getVirtualMethodList()).write(builder);
		getWriter(node().getConstructorList()).write(builder);
		
		return builder;
	}
	
	public StringBuilder writeStaticBlocks(final StringBuilder builder)
	{
		if (node().getStaticBlockList().getNumVisibleChildren() > 0)
		{
			node().getStaticBlockList().forEachVisibleChild(block -> {
				if (block.getScope().getNumVisibleChildren() > 0)
				{
					builder.append('\n');
				}
				
				getWriter(block).write(builder);
			});
		}
		
		return builder;
	}
	
	@Override
	public StringBuilder writeName(StringBuilder builder)
	{
		for (String c : new String[] { "nova/Object", "nova/String", "nova/io/Console", "nova/datastruct/list/Array", "nova/time/Date", "nova/math/Math", "nova/datastruct/Node" })
		{
			if (node().getClassLocation().equals(c))
			{
				return builder.append("Nova").append(node().getName());
			}
		}
		
		return super.writeName(builder);
	}
}