package tppitweaks.client.gui;

import java.util.logging.Level;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import tppitweaks.TPPITweaks;
import tppitweaks.config.ConfigurationHandler;

public class RestartGui extends GuiScreen
{
	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2, 200, 20, "Exit the game"));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 + 30, 200, 20, "Don't exit (mods will not be loaded)"));
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		
		this.drawCenteredString(this.fontRenderer, "You have installed all desired mods!", this.width / 2, this.height / 2 - 40, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "However, you must restart Minecraft for your changes to take effect.", this.width / 2, this.height / 2 - 20, 0xFFFFFF);
		
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 0)
		{
			System.exit(0);
		}
		else
		{
			TPPITweaks.logger.log(Level.INFO, "Mod installations finished! Restart minecraft for your changes to take effect.");
			this.mc.displayGuiScreen(ConfigurationHandler.showMaricultureGui ? new MaricultureGui() : null);
		}
	}
}
