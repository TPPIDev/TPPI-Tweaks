package tppitweaks;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tppitweaks.tweak.aspect.TweakVanilla;
import tppitweaks.block.ModBlocks;
import tppitweaks.command.CommandGetInvolved;
import tppitweaks.command.CommandOres;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.creativeTab.CreativeTabTPPI;
import tppitweaks.event.TPPIEventHandler;
import tppitweaks.item.ModItems;
import tppitweaks.lib.Reference;
import tppitweaks.proxy.CommonProxy;
import tppitweaks.tweak.AdditionalTweaks;
import tterrag.rtc.RecipeTweakingCore;

import java.io.File;

@Mod(modid = "TPPITweaks", name = "TPPI Tweaks", version = TPPITweaks.VERSION, dependencies = Reference.DEPENDENCIES)
public class TPPITweaks
{
	public static final String VERSION = "@VERSION@";

	@Instance("TPPITweaks")
	public static TPPITweaks instance;

	@SidedProxy(clientSide = "tppitweaks.proxy.ClientProxy", serverSide = "tppitweaks.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static TPPIEventHandler eventHandler;

	public static final Logger logger = LogManager.getLogger("TPPITweaks");

	public static CreativeTabTPPI creativeTab = new CreativeTabTPPI(CreativeTabs.getNextID());

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{		
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
		
		if (Loader.isModLoaded("Thaumcraft"))
			TweakVanilla.init();
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
