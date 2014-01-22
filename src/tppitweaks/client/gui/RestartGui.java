package tppitweaks.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class RestartGui extends GuiScreen
{
	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2, 200, 20, "Gotcha!"));
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		
		this.drawCenteredString(this.fontRenderer, "You have installed all desired mods!", this.width / 2, this.height / 2 - 20, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "However, you must restart Minecraft for your changes to take effect.", this.width / 2, this.height / 2 - 40, 0xFFFFFF);
		
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 0)
		{
			System.out.println("Mod installations finished! Restart minecraft for your changes to take effect.");
			this.mc.displayGuiScreen(null);
		}
	}
}
