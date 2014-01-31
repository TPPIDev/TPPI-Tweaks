package tppitweaks.client.gui.library.gui.element;

import tppitweaks.client.gui.library.gui.GuiBase;
import tppitweaks.client.gui.modGuides.GuiGuideBase;

public class ElementTextClickable extends ElementText
{
	private String modid;
	
	public ElementTextClickable(GuiBase gui, int x, int y, String displayText, String tooltip, int color, String modid)
	{
		super(gui, x, y, displayText, tooltip, color, false);
		
		this.modid = modid;
	}
	
	@Override
	public boolean handleMouseClicked(int x, int y, int mouseButton)
	{
		((GuiGuideBase)this.gui).displayModInfo(modid);
		return true;
	}
}
