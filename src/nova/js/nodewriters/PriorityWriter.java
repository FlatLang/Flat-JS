package nova.js.nodewriters;

import net.fathomsoft.nova.tree.*;

public abstract class PriorityWriter extends ValueWriter implements AccessibleWriter
{
	public abstract Priority node();
	
	
}