package tppitweaks;

import net.minecraftforge.common.MinecraftForge;
import tppitweaks.client.gui.GuiHelper;
import tppitweaks.command.CommandUpdateGUI;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.event.TppiEventHandler;
import tppitweaks.item.ModItems;
import tppitweaks.recipetweaks.RecipeTweaks;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "TPPITweaks", name = "TPPI Tweaks", version = TPPITweaks.VERSION, dependencies = "after:Thaumcraft;after:TwilightForest")
@NetworkMod(channels = { "tppitweaks" }, clientSideRequired = true, serverSideRequired = false)
public class TPPITweaks
{

	public static final String VERSION = "0.0.2";
	

	@Instance("TPPITweaks")
	public static TPPITweaks instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		ConfigurationHandler.loadBookText(TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/TPPIChangelog.txt"));
		ModItems.initItems();
		
		CommandUpdateGUI.initValidCommandArguments();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		AM2SpawnControls.doAM2SpawnControls();
		MinecraftForge.EVENT_BUS.register(new TppiEventHandler());
		ModItems.tppiBook.registerRecipes();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		RecipeTweaks.doRecipeTweaks();

		System.out.println("Opening GUI");
	}
	
	@EventHandler
	public void onFMLServerStart(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandUpdateGUI());
	}
}