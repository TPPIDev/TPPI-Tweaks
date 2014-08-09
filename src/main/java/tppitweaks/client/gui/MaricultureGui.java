package tppitweaks.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.util.TPPITweaksUtils;

public class MaricultureGui extends GuiScreen
{
	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		this.buttonList.add(new GuiButton(0, this.width / 2 - 144, this.height / 2 + 40, 140, 20, "Enable the Mod"));
		this.buttonList.add(new GuiButton(1, this.width / 2 + 4, this.height / 2 + 40, 140, 20, "Disable the Mod"));
		this.buttonList.add(new GuiButton(2, this.width / 2 - 144, this.height / 2 + 68, 140, 20, "Exit the Game"));
		this.buttonList.add(new GuiButton(3, this.width / 2 + 4, this.height / 2 + 68, 140, 20, "Continue to the Game"));
		this.buttonList.add(new GuiButton(4, this.width / 2 - 144, this.height / 2 + 96, 288, 20, "Don't Show this Again"));
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();

		this.drawCenteredString(this.mc.fontRenderer, "In the latest update to TPPI, 0.3, Mariculture was removed.", this.width / 2,  this.height / 2 - 105, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "If you have items from this mod in your world,", this.width / 2,  this.height / 2 - 90, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "it could render your world corrupt.", this.width / 2,  this.height / 2 - 80, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "To avoid this, if you haven't already,", this.width / 2,  this.height / 2 - 65, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "please clear your inventory of mariculture items.", this.width / 2,  this.height / 2 - 55, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "To do this, hit \"Enable the mod\" to enable mariculture,", this.width / 2,  this.height / 2 - 40, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "and then restart the game. Then, MAKE A BACKUP!", this.width / 2,  this.height / 2 - 30, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "Log into your world, and delete as many mariculture items as you can.", this.width / 2,  this.height / 2 - 20, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "Once this is done you may disable the mod,", this.width / 2,  this.height / 2 - 5, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "and restart once more.", this.width / 2,  this.height / 2 + 5, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "NOTE: If still crashing, repeat this process and delete more items.", this.width / 2, this.height / 2 + 20, 0xFFFFFF);
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		switch (button.id)
		{
		case 0:
			TPPITweaksUtils.enableMod("Mariculture", ".disabled");
			break;
		case 1:
			TPPITweaksUtils.disableMod("Mariculture", ".disabled");
			break;
		case 2:
			System.exit(0);
			break;
		case 3:
			this.mc.displayGuiScreen(null);
			break;
		case 4:
			ConfigurationHandler.manuallyChangeConfigValue("B:showMaricultureGUI", "true", "false");
			this.mc.displayGuiScreen(null);
			break;
		default:
			return;
		}
	}
}