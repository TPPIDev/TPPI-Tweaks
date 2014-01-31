package tppitweaks.client.gui.modGuides;

import java.util.Map;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;

import tppitweaks.client.gui.library.gui.GuiBase;
import tppitweaks.client.gui.library.gui.button.SmallButton;
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
		
		this.addElement(((Tab) new Tab(this, true, 0).setVisible(true)).setTexture("guiGuide", 20, 20));
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
		this.addElement(new SmallButton(this, 0, 2, 10));
		this.addElement(new SmallButton(this, 1, 2, 40));
		this.addElement(new SmallButton(this, 2, 2, 70));
		this.addElement(new SmallButton(this, 3, 2, 100));
		this.addElement(new SmallButton(this, 4, 228, 10));
		this.addElement(new SmallButton(this, 5, 228, 40));
		this.addElement(new SmallButton(this, 6, 228, 70));
		this.addElement(new SmallButton(this, 7, 228, 100));
	}
}
