package nova.js.engines;

import net.fathomsoft.nova.CodeGeneratorEngine;
import net.fathomsoft.nova.Nova;
import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.tree.ClassDeclaration;
import net.fathomsoft.nova.tree.FileDeclaration;
import net.fathomsoft.nova.tree.MethodDeclaration;
import net.fathomsoft.nova.tree.Program;
import net.fathomsoft.nova.util.FileUtils;
import net.fathomsoft.nova.util.SyntaxUtils;

import java.io.File;
import java.io.IOException;

import static net.fathomsoft.nova.Nova.LIBRARY;
import static nova.js.nodewriters.Writer.getWriter;

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
		if (controller.isFlagEnabled(Nova.SINGLE_FILE))
		{
			String extension = FileUtils.getFileExtension(controller.outputFile.getName());
			
			if (extension != null && !extension.toLowerCase().equals("js"))
			{
				controller.error("Invalid output file extension '" + extension + "'. The extension must be js");
				
				return;
			}
			
			try
			{
				writeFile(FileUtils.removeFileExtension(controller.outputFile.getName()) + ".js", controller.outputFile.getParentFile(), formatText(getWriter(tree.getRoot()).write().toString()));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			tree.getRoot().forEachVisibleListChild(file -> {
				try
				{
					File outputDir = getOutputDirectory(file);
					
					new File(outputDir, file.getPackage().getLocation()).mkdirs();
					
					writeFile(file.getPackage().getLocation() + "/" + file.getName() + ".js", outputDir, formatText(getWriter(file).write().toString()));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			});
		}
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
		
		FileDeclaration fileDeclaration = mainMethod.getFileDeclaration();
		
		if (mainMethod != null)
		{
			
		}
	}
}