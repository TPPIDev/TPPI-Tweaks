package tppitweaks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import tppitweaks.command.CommandTPPI;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.creativeTab.CreativeTabTPPI;
import tppitweaks.event.TPPIEventHandler;
import tppitweaks.item.ModItems;
import tppitweaks.lib.Reference;
import tppitweaks.proxy.PacketHandler;
import tppitweaks.recipetweaks.RecipeTweaks;
import tppitweaks.util.FileLoader;
import tppitweaks.util.TPPIPlayerTracker;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "TPPITweaks", name = "TPPI Tweaks", version = TPPITweaks.VERSION, dependencies = Reference.DEPENDENCIES)
@NetworkMod(serverSideRequired = true, clientSideRequired = true, channels = { Reference.CHANNEL }, packetHandler = PacketHandler.class)
public class TPPITweaks {

	public static final String VERSION = "0.6.7a";

	@Instance("TPPITweaks")
	public static TPPITweaks instance;

	public static TPPIEventHandler eventHandler;
	public static TPPIPlayerTracker playerTracker;
	
	public static final Logger logger = Logger.getLogger("TPPITweaks");
	
	public static CreativeTabTPPI creativeTab = new CreativeTabTPPI(CreativeTabs.getNextID());

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	    logger.setParent(FMLCommonHandler.instance().getFMLLogger());

		ConfigurationHandler.init(new File(event.getModConfigurationDirectory()
				.getAbsolutePath() + "/TPPI/TPPITweaks.cfg"));

		try {
			FileLoader.init(ConfigurationHandler.cfg, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ConfigurationHandler.loadGuideText(FileLoader.getGuideText());
		try
		{
			ConfigurationHandler.loadChangelogText(FileLoader.getChangelogText());
		}
		catch (FileNotFoundException e)
		{
			logger.log(Level.WARNING, "TPPI Changelog not found, please check the TPPI config folder.");
		}
		
		CommandTPPI.initValidCommandArguments(FileLoader.getSupportedMods());

		ModItems.initItems();

		playerTracker = new TPPIPlayerTracker();
		GameRegistry.registerPlayerTracker(playerTracker);
		MinecraftForge.EVENT_BUS.register(playerTracker);

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		AM2SpawnControls.doAM2SpawnControls();

		eventHandler = new TPPIEventHandler();
		MinecraftForge.EVENT_BUS.register(eventHandler);
		ModItems.registerRecipes();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RecipeTweaks.doRecipeTweaks();
	}

	@EventHandler
	public void onFMLServerStart(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandTPPI());
	}
}