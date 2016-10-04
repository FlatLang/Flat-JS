package nova.js.engines;

import net.fathomsoft.nova.CodeGeneratorEngine;
import net.fathomsoft.nova.Nova;
import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.tree.ClassDeclaration;
import net.fathomsoft.nova.tree.FileDeclaration;
import net.fathomsoft.nova.tree.MethodDeclaration;
import net.fathomsoft.nova.tree.Program;

import static net.fathomsoft.nova.Nova.LIBRARY;

public class JSCodeGeneratorEngine extends CodeGeneratorEngine
{
	public JSCodeGeneratorEngine(Nova controller)
	{
		super(controller);
		
		
	}
	
	/**
	 * Generate the C Source and Header output from the data contained
	 * within the syntax tree.
	 */
	public void generateOutput()
	{
		
	}
	
	public void formatOutput()
	{
		
	}
	
	public void writeFiles()
	{
		
	}
	
	/**
	 * Insert the main method into the correct file. Also set up the
	 * initialization for the program within the main method.
	 */
	public void insertMainMethod()
	{
		MethodDeclaration mainMethod = tree.getMainMethod(mainClass);
		
		if (mainMethod == null)
		{
			if (!controller.isFlagEnabled(LIBRARY))
			{
				if (mainClass != null)
				{
					SyntaxMessage.error("No main method found in class '" + mainClass + "'", controller);
				}
				else
				{
					SyntaxMessage.error("No main method found in program", controller);
				}
				
				controller.completed(true);
			}
			
			return;
		}
		
		StringBuilder staticBlockCalls  = generateStaticBlockCalls();
		
		FileDeclaration fileDeclaration = mainMethod.getFileDeclaration();
		
		if (mainMethod != null)
		{
			
		}
	}
	
	private StringBuilder generateStaticBlockCalls()
	{
		StringBuilder builder = new StringBuilder();
		
		Program root = tree.getRoot();
		
		for (int i = 0; i < root.getNumVisibleChildren(); i++)
		{
			FileDeclaration  file  = (FileDeclaration)root.getVisibleChild(i);
			
			for (ClassDeclaration clazz : file.getClassDeclarations())
			{
				clazz.getStaticBlockList().forEachVisibleChild(block -> {
					// do something with block
				});
			}
		}
		
		return builder;
	}
}