package tppitweaks;

import net.minecraftforge.common.MinecraftForge;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.event.BookEventHandler;
import tppitweaks.item.TPPIBook;
import tppitweaks.recipetweaks.EnderStorageTesseracts;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "TPPITweaks", name = "TPPI Tweaks", version = "1", dependencies = "after:arsmagica2")
@NetworkMod(channels = { "tppitweaks" }, clientSideRequired = true, serverSideRequired = false)
public class TPPITweaks
{

	@Instance("TPPITweaks")
	public static TPPITweaks instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		ConfigurationHandler.loadBookText(TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/TPPIChangelog.txt"));
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		AM2SpawnControls.doAM2SpawnControls();
		MinecraftForge.EVENT_BUS.register(new BookEventHandler());
		TPPIBook.registerRecipes();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		EnderStorageTesseracts.doEnderStorageTweaks();
	}
}