package tppitweaks;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.MinecraftForge;
import tppitweaks.command.CommandTPPI;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.event.TPPIEventHandler;
import tppitweaks.item.ModItems;
import tppitweaks.lib.Reference;
import tppitweaks.proxy.PacketHandler;
import tppitweaks.recipetweaks.RecipeTweaks;
import tppitweaks.util.FileLoader;
import tppitweaks.util.TPPIPlayerTracker;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "TPPITweaks", name = "TPPI Tweaks", version = TPPITweaks.VERSION, dependencies = "before:ThaumicTinkerer;after:Thaumcraft;after:TwilightForest;after:AppliedEnergistics;after:StevesFactoryManager")
@NetworkMod(serverSideRequired = true, clientSideRequired = true, channels = { Reference.CHANNEL }, packetHandler = PacketHandler.class)
public class TPPITweaks {

	public static final String VERSION = "0.0.5";

	@Instance("TPPITweaks")
	public static TPPITweaks instance;

	public static TPPIEventHandler eventHandler;
	public static TPPIPlayerTracker playerTracker;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(new File(event.getModConfigurationDirectory()
				.getAbsolutePath() + "/TPPI/TPPITweaks.cfg"));

		try {
			FileLoader.init(ConfigurationHandler.cfg, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ConfigurationHandler.loadBookText(FileLoader.bookText);
		CommandTPPI.initValidCommandArguments(FileLoader.supportedMods);

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