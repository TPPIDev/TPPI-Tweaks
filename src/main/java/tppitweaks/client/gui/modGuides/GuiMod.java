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
	public void drawGuiForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiForegroundLayer(mouseX, mouseY);
	}
}
