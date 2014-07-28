package tppitweaks;

import java.io.File;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import tppitweaks.block.ModBlocks;
import tppitweaks.command.CommandGetInvolved;
import tppitweaks.command.CommandOres;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.creativeTab.CreativeTabTPPI;
import tppitweaks.event.TPPIEventHandler;
import tppitweaks.item.ModItems;
import tppitweaks.lib.Reference;
import tppitweaks.proxy.CommonProxy;
import tppitweaks.recipetweaks.AdditionalTweaks;
import tterrag.rtc.RecipeTweakingCore;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "TPPITweaks", name = "TPPI Tweaks", version = TPPITweaks.VERSION, dependencies = Reference.DEPENDENCIES)
public class TPPITweaks
{
	public static final String VERSION = "1.1.0";

	@Instance("TPPITweaks")
	public static TPPITweaks instance;

	@SidedProxy(clientSide = "tppitweaks.proxy.ClientProxy", serverSide = "tppitweaks.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static TPPIEventHandler eventHandler;

	public static final Logger logger = Logger.getLogger("TPPITweaks");

	public static CreativeTabTPPI creativeTab = new CreativeTabTPPI(CreativeTabs.getNextID());

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger.setParent(FMLCommonHandler.instance().getFMLLogger());
		
		RecipeTweakingCore.registerPackageName("tppitweaks.recipetweaks.modTweaks");

		ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/TPPI/TPPITweaks.cfg"));
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		// AM2SpawnControls.doAM2SpawnControls();

		ModItems.initItems();
		ModBlocks.initBlocks();
		
		eventHandler = new TPPIEventHandler();
		MinecraftForge.EVENT_BUS.register(eventHandler);
		ModItems.registerRecipes();
		ModBlocks.registerRecipes();

		if (event.getSide().isClient())
			proxy.initTickHandler();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		AdditionalTweaks.doOreDictTweaks();
		AdditionalTweaks.addMiscRecipes();
	}

	@EventHandler
	public void onFMLServerStart(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandOres());
		event.registerServerCommand(new CommandGetInvolved());
	}
}
