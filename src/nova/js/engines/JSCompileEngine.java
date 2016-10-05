package nova.js.engines;

import net.fathomsoft.nova.CompileEngine;
import net.fathomsoft.nova.Nova;

public class JSCompileEngine extends CompileEngine
{
	public boolean localScope = false;
	
	public String scopeExportName;
	
	public JSCompileEngine(Nova controller)
	{
		super(controller);
		
		
	}
	
	@Override
	public boolean checkArgument(String arg, String[] args, int index)
	{
		if (arg.equals("-local-scope"))
		{
			localScope = true;
		}
		else if (arg.equals("-scope-export-name"))
		{
			if (args.length > index + 1)
			{
				scopeExportName = args[index + 1];
			}
			else
			{
				controller.error("-scope-export-name argument requires the name as the next argument");
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Compile the generated c code into an executable file.
	 */
	public void compile()
	{
		
	}
}