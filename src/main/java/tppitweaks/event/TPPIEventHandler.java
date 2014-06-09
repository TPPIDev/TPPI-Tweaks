package tppitweaks.event;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tppitweaks.client.gui.IRCGui;
import tppitweaks.client.gui.MaricultureGui;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.modTweaks.DATweaks;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TPPIEventHandler
{
	private boolean shouldLoadGUI = true;

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onGui(GuiOpenEvent event)
	{
		if (event.gui instanceof GuiMainMenu)
		{
			if (shouldLoadGUI && ConfigurationHandler.showMaricultureGui)
			{
				event.gui = new MaricultureGui();
				shouldLoadGUI = false;
			}
			else if (shouldLoadGUI && ConfigurationHandler.showIRCGui)
			{
				event.gui = new IRCGui();
				shouldLoadGUI = false;
			}
		}
	}
	
	@ForgeSubscribe
	public void onItemTooltip(ItemTooltipEvent event)
	{
		if (Loader.isModLoaded("DimensionalAnchors") && !Loader.isModLoaded("ChickenChunks"))
		{
			DATweaks.addTooltip(event);
		}
	}
}