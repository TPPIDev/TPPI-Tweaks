package tppitweaks.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class InstructionsGui extends UpdateGui
{

	public InstructionsGui(GuiScreen parentScreen)
	{
		super(parentScreen);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		this.buttonList.add(new GuiButton(3, this.width / 2 - 150, this.height / 4 + 135, 300, 20, "Go back to the main menu"));
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		
		this.drawCenteredString(this.fontRenderer, "Not finished", this.width / 2, 20, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "...Good luck <3", this.width / 2, 50, 0xFFFFFF);
				
		super.drawScreen(par1, par2, par3, false);
	}

}
