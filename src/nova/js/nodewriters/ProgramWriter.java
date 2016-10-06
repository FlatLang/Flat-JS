package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;
import nova.js.engines.JSCompileEngine;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ProgramWriter extends TypeListWriter
{
	public abstract Program node();
	
	public ClassDeclaration[] getClassesHierarchicalInOrder()
	{
		final ArrayList<ClassDeclaration> list = new ArrayList<>();
		
		node().forEachVisibleListChild(file -> {
			Arrays.stream(file.getClassDeclarations()).forEach(c -> {
				ClassDeclaration[] hierarchy = c.getClassHierarchy(true, true);
				
				for (int i = hierarchy.length - 1; i >= 0; i--)
				{
					if (!list.contains(hierarchy[i]))
					{
						list.add(hierarchy[i]);
					}
				}
			});
		});
		
		return list.toArray(new ClassDeclaration[0]);
	}
	
	@Override
	public StringBuilder write(final StringBuilder builder)
	{
		boolean localScope = ((JSCompileEngine)node().getProgram().getController().compileEngine).localScope;
		
		if (localScope)
		{
			builder.append("(function () {\n");
		}
		
		builder.append("var novaConstructors = {};\n\n");
		
		for (ClassDeclaration child : getClassesHierarchicalInOrder())
		{
			getWriter(child).write(builder);
		}
		
		if (localScope)
		{
			builder.append("})();");
		}
		
		return builder;
	}
}