package tppitweaks.client.gui.modGuides;


public class GuiMod extends GuiGuideBase
{		
	
	String modid;
	
	public GuiMod(String modName, String body, String modid)
	{
		super();
		this.title = modName;
		this.body = body;
		
		this.modid = modid;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		super.drawGuiContainerForegroundLayer(x, y);
	}
}
