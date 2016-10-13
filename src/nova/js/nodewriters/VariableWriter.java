package nova.js.nodewriters;

import net.fathomsoft.nova.tree.Constructor;
import net.fathomsoft.nova.tree.InitializationMethod;
import net.fathomsoft.nova.tree.ReferenceParameter;
import net.fathomsoft.nova.tree.lambda.LambdaMethodDeclaration;
import net.fathomsoft.nova.tree.variables.Variable;
import net.fathomsoft.nova.tree.variables.VariableDeclaration;

public abstract class VariableWriter extends IdentifierWriter
{
	public abstract Variable node();
	
	@Override
	public StringBuilder writeExpression(StringBuilder builder)
	{
		if (isInstanceClosure())
		{
			return writeInstanceClosure(builder);
		}
		
		if (!node().isAccessed())
		{
			VariableDeclaration declaration = node().getDeclaration();
			
			if (!declaration.isExternal())
			{
				if (!declaration.isInstance())
				{
					getWriter(declaration.getDeclaringClass()).writeName(builder).append('.');
				}
				else if (declaration instanceof InitializationMethod)
				{
					getWriter(declaration.getDeclaringClass()).writeName(builder).append('.');
				}
				else if (declaration instanceof Constructor == false && !declaration.isLocal())
				{
					if (node().getDeclaration() instanceof ReferenceParameter)
					{
						return super.writeExpression(builder);
					}
					
					if (node().getParentMethod() instanceof LambdaMethodDeclaration)
					{
						builder.append("self");
					}
					else
					{
						builder.append("this");
					}
					
					builder.append('.');
				}
			}
		}
		
		return super.writeExpression(builder);
	}
}