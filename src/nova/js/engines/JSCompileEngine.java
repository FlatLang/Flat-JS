package nova.js.engines;

import net.fathomsoft.nova.CompileEngine;
import net.fathomsoft.nova.Nova;

public class JSCompileEngine extends CompileEngine
{
	public JSCompileEngine(Nova controller)
	{
		super(controller);
		
		
	}
	
	@Override
	public boolean checkArgument(String arg)
	{
		return false;
	}
	
	/**
	 * Compile the generated c code into an executable file.
	 */
	public void compile()
	{
		
	}
}