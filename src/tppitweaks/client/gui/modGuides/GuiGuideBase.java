package tppitweaks.client.gui.modGuides;

import java.util.Map;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import tppitweaks.client.gui.library.gui.GuiBase;
import tppitweaks.client.gui.library.gui.button.GuideButton;
import codechicken.nei.VisiblityData;

public class GuiGuideBase extends GuiBase
{
	protected Map<String, GuiMod> mods;
	
	public GuiGuideBase()
	{
		super(new ResourceLocation("tppitweaks", "textures/gui/guiGuide.png"));
		drawInventory = false;
		this.xSize = 256;
		this.ySize = 178;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		initButtons();
	}
	
	@Override
	public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility)
	{
		currentVisibility.showNEI = false;
		return currentVisibility;
	}
	
	private void initButtons()
	{
		this.addElement(new GuideButton(this, 0, 2, 10));
		this.addElement(new GuideButton(this, 1, 2, 40));
		this.addElement(new GuideButton(this, 2, 2, 70));
		this.addElement(new GuideButton(this, 3, 2, 100));
		this.addElement(new GuideButton(this, 4, 228, 10));
		this.addElement(new GuideButton(this, 5, 228, 40));
		this.addElement(new GuideButton(this, 6, 228, 70));
		this.addElement(new GuideButton(this, 7, 228, 100));
	}
}
