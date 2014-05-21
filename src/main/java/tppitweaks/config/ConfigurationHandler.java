package tppitweaks.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property.Type;
import tppitweaks.lib.Reference;
import tppitweaks.util.TxtParser;

public class ConfigurationHandler
{

	public static HashMap<String, Boolean> am2SpawnControls = new HashMap<String, Boolean>();
	public static int bookID;
	public static int materialID;
	public static int blockID;
	
	public static String bookTitle;
	public static String bookAuthor;
	public static String changelogTitle;
	public static String supportedModsName;
	public static int guideSkin;
	
	public static boolean enderChestResonant;
	public static boolean enderPouchNerf;
	public static boolean enderTankResonant;
	public static boolean steelReactorCasings;
	public static boolean glassFuelRods;
	public static boolean twoReactorGlass;
	public static boolean eloraamBreakersAndDeployers;
	public static boolean ic2TEGlassInterchangeability;
	public static boolean tweakDA;
	public static boolean tweakSFM;
	public static boolean tweakAM2;
	public static boolean tweakJABBA;
	public static boolean buffUnifierRecipe;

	public static boolean disableAGAutoOutputter;
	
	public static boolean fixExURecipes;
	public static boolean changeMPSARecipes;
	public static boolean nerfEnderQuarry;
	
	public static boolean nerfRedstoneGen;
	public static boolean nerfEnderGen;

	public static boolean doPlatinumInCentrifuge;
	public static boolean addLapisDustMortarRecipes;
	public static boolean tinkersAluminumPlates;
	public static boolean tinkersAluminumOreInGTMachines;
	public static boolean addOneToOnePlateHammerRecipes;
	public static boolean unnerfPaperRecipe;
	public static boolean readdResinSmelting;
	public static boolean doCharcoalBlockCompression;
	public static boolean makeEIOHardModeEasier;
	public static boolean unregisterFusedQuartz;

	public static boolean addEssenceSeedRecipe;
	
	public static boolean harderActivatorRecipe;
	public static boolean harderDisassemblerRecipe;
	public static boolean disableCardboardBox;
	public static boolean disableMiner;
	public static boolean nerfMiner;
	public static boolean disableUniversalCables;
	
	public static boolean harderLillipadRecipe;

	public static boolean removeStupidEnergyCrystalRecipe;
	public static boolean disableForceShears;
	
	public static boolean addOsmiumToOreWasher;
	
	public static boolean registerMagicalCropsOre;

	public static boolean showDownloadGUI;
	public static boolean showMaricultureGui;
	public static boolean doSpawnBook;
	
	public static boolean autoEnableTT;
	
	public static File cfg;
	
	/** ArrayList of Strings, the strings are each one whole page **/
	public static List<String> bookText;
	public static List<String> changelog;

	public static String[] am2MobKeys = { "EntityHecate", "EntityDarkMage", "EntityLightMage", "EntityEarthElemental", "EntityFireElemental", "EntityWisp", "EntityWaterElemental", "EntityManaElemental", "EntityDryad", "EntityManaCreeper", "EntityDarkling" };
	
	public static boolean allowCapes = true;

	public static void init(File file)
	{
		cfg = file;
		
		Configuration config = new Configuration(file);
		config.load();

		for (String s : am2MobKeys)
		{
			am2SpawnControls.put(s, config.get("GLOBAL ARS MAGICA 2 MOB SPAWN CONTROLS", "globallyDisable" + s, true).getBoolean(true));
		}
		
		blockID = config.getBlock("tppiBlockId", 3115).getInt();
		
		bookID = config.getItem("tppiBookId", 21650).getInt() - 256;
		materialID = config.getItem("tppiMaterialId", 21651).getInt() - 256;
		
		bookTitle = config.get("TPPI Guide Info", "bookTitle", "TPPI Welcome Packet", "The title of the custom spawn book", Type.STRING).getString();
		bookAuthor = config.get("TPPI Guide Info", "bookAuthor", "The TPPI Team", "The author of the custom spawn book", Type.STRING).getString();
		changelogTitle = config.get("TPPI Guide Info", "changelogTitle", "TPPI Changelog", "The title of the changelog").getString();
		supportedModsName = config.get("TPPI Guide Info", "supportedModsFilename", "SupportedMods", "The file name of the file to read the mod documentation from (used to support translation). Do not include the extension in the filename (it is .txt)").getString();
		guideSkin = config.get("TPPI Guide Info", "TPPIGuideSkin", 0, "The skin of the guide GUI/item, 0=tech, 1=scroll").getInt();
		doSpawnBook = config.get("TPPI Guide Info", "doSpawnBook", true, "Whether or not to give the player a welcome book on first spawn").getBoolean(true);

		enderChestResonant = config.get("Ender Storage Tweaks", "enderChestResonant", true, "EnderStorage Ender Chests require resonant strongboxes instead of ender pearls.").getBoolean(true);
		enderPouchNerf = config.get("Ender Storage Tweaks", "enderPouchNerf", true, "EnderStorage Ender Pouches require pyrotheum dust and liquid ender instead of blaze rods and ender pearls.").getBoolean(true);
		enderTankResonant = config.get("Ender Storage Tweaks", "enderTankResonant", true, "EnderStorage Ender Tanks require resonant portable tanks instead of ender pearls.").getBoolean(true);
		
		steelReactorCasings = config.get("Other Mod Tweaks", "steelReactorCasings", false, "Big Reactors reactor casings require steel. Affects ONLY the casings.").getBoolean(true);
		ic2TEGlassInterchangeability = config.get("Other Mod Tweaks", "ic2TEGlassInterchangeability", true, "IC2 reinforced glass (glassReinforced) and Thermal Expansion hardened glass (glassHardened)\nwill be cross-registered as each other in the ore dictionary.").getBoolean(false);
		tweakDA = config.get("Other Mod Tweaks", "tweakDARecipe", true, "Make Dimensional Anchors' recipe closer to that of a chicken chunks chunk loader.").getBoolean(true);
		tweakSFM = config.get("Other Mod Tweaks", "stevesFactoryManagerAERecipes", true, "Recipes from Steve's Factory Manager take items from Applied Energistics.").getBoolean(true);
		tweakAM2 = config.get("Other Mod Tweaks", "addAM2NatureGuardianRecipe", true, "Add recipe for Ars Magica 2 Nature Guardian spawn egg. Useful when dryads are disabled.").getBoolean(true);
		glassFuelRods = config.get("Other Mod Tweaks", "glassFuelRods", false, "Big Reactors fuel rods take just a touch of reactor glass.").getBoolean(false);
		twoReactorGlass = config.get("Other Mod Tweaks", "twoReactorGlass", true, "Big Reactors\' reactor glass recipe gives two reactor glass in exchange for the harder recipe.").getBoolean(true);
		eloraamBreakersAndDeployers = config.get("Other Mod Tweaks", "eloraamBreakersAndDeployers", true, "OpenBlocks block breakers and placers have the same recipes as Redpower 2's.").getBoolean(true);
		removeStupidEnergyCrystalRecipe = config.get("Other Mod Tweaks", "removeDartCraftEnergyCrystalRecipe", true, "Remove DartCraft's IC2 energy crystal recipe.").getBoolean(true);
		disableForceShears = config.get("Other Mod Tweaks", "disableForceShears", true, "Remove the force shears recipe because they were crashing servers rarely, but in a serious way. Disable this at your own risk.").getBoolean(true);
		disableAGAutoOutputter = config.get("Other Mod Tweaks", "disableAGAutoOutputter", true, "Remove the recipe for the Advanced Genetics auto output upgrade, because crashes.").getBoolean(true);
		tweakJABBA = config.get("Other Mod Tweaks", "tweakJABBA", true, "Alter the JABBA barrel recipe to be a little more complicated, leaving FZ barrels as a \"low-tech\" option").getBoolean(true);
		buffUnifierRecipe = config.get("Other Mod Tweaks", "buffUnifierRecipe", true, "Make the unifier cheaper.").getBoolean(true);
		makeEIOHardModeEasier = config.get("Other Mod Tweaks", "makeEnderIOHardModeEasier", true, "Give some EnderIO recipes a buff as they are way too diffucult. Currently affects: basic capacitor, reservoir").getBoolean(true);
		unregisterFusedQuartz = config.get("Other Mod Tweaks", "unregisterFusedQuartz", true, "Having fused quartz be registered under hardened glass doesn't make much sense. This fixes that.").getBoolean(true);
		
		fixExURecipes = config.get("ExtraUtils Tweaks", "fixExtraUtilsRecipes", true, "Current version of ExU has broken recipes for the unstable ingot block. This fixes that.").getBoolean(true);
		nerfEnderQuarry = config.get("ExtraUtils Tweaks", "nerfEnderQuarry", true, "Make the Extra Utilities ender quarry expensive.").getBoolean(true);
		nerfRedstoneGen = config.get("ExtraUtils Tweaks", "nerfRedstoneGen", true, "Make the Extra Utilities heated redstone generator expensive.").getBoolean(true);
		nerfEnderGen = config.get("ExtraUtils Tweaks", "nerfEnderGen", true, "Make the Extra Utilities ender generator expensive.").getBoolean(true);
		
		changeMPSARecipes = config.get("MPS Extra Recipes", "change_MPSA_recipes", true, "Changes MPSA recipes to match the MPS recipes that are in TPPI").getBoolean(true);
		
		doPlatinumInCentrifuge = config.get("Gregtech Tweaks", "doPlatinumInCentrifuge", true, "Re-adds the old GregTech centrifuge recipe for platinum dust to iridium nugget + small nickel dust.").getBoolean(true);
		addLapisDustMortarRecipes = config.get("Gregtech Tweaks", "addLapisDustMortarRecipes", true, "Lapis dust can be made from lapis using GregTech's mortars.").getBoolean(true);
		tinkersAluminumPlates = config.get("Gregtech Tweaks", "tinkersAluminumPlates", true, "Tinkers' Construct aluminum ingots can make aluminum plates in the GregTech plate bending machine.").getBoolean(true);
		tinkersAluminumOreInGTMachines = config.get("Gregtech Tweaks", "tinkersAluminumOreInGTMachines", true, "Tinkers' Construct aluminum ore works in GregTech machines.").getBoolean(true);
		unnerfPaperRecipe = config.get("Gregtech Tweaks", "unnerfPaperRecipe", true, "Revert GregTech's paper recipe output nerf.").getBoolean(true);
		readdResinSmelting = config.get("Gregtech Tweaks", "readdResinSmelting", true, "Re-add the IC2 sticky resin to rubber smelting recipe.").getBoolean(true);
		doCharcoalBlockCompression = config.get("Gregtech Tweaks", "doCharcoalBlockCompression", true, "Charcoal blocks can be compressed to coal via compressor.").getBoolean(true);
		
		showDownloadGUI = config.get("Mod Downloads", "showDownloadGUI", false, "Show the Download GUI on startup.").getBoolean(true);
		showMaricultureGui = config.get("Mod Loading Tweaks", "showMaricultureGUI", false, "Show the mariculture fix GUI on startup.").getBoolean(false);

		registerMagicalCropsOre = config.get("Other Mod Tweaks", "registerMagicalCropsOre", true, "Register essence ores from Magical Crops in the ore dictionary under \"oreMCropsEssence\" and \"oreMCropsNetherEssence\".").getBoolean(true);
		harderActivatorRecipe = config.get("Other Mod Tweaks", "harderActivatorRecipe", true, "Make the autonomous activator recipe slightly harder").getBoolean(true);
		harderLillipadRecipe = config.get("Other Mod Tweaks", "harderLillipadOfFertility", true, "Make the lillipad of fertility much harder to craft").getBoolean(true);
		addEssenceSeedRecipe = config.get("Other Mod Tweaks", "addEssenceSeedRecipe", false, "Add a recipe for the essence seeds in magical crops").getBoolean(false);
		
		addOsmiumToOreWasher = config.get("Mekanism Tweaks", "addOsmiumToOreWasher", true, "Add a recipe for impure osmium dust to clean osmium dust in the IC2 ore washer.").getBoolean(true);
		harderDisassemblerRecipe = config.get("Mekanism Tweaks", "harderAtomicDisassembler", true, "Makes the recipe for the Atomic Disassembler much more difficult").getBoolean(true);
		disableCardboardBox = config.get("Mekanism Tweaks", "disableCardboardBox", false, "Remove the recipe for the cardboard box (it can move ANY tile entity including nodes and spanwers)").getBoolean(false);
		disableMiner = config.get("Mekanism Tweaks", "disableDigitalMiner", false, "Remove the recipe for the digital miner (not really any reason to do this now but I'm not deleting code)").getBoolean(false);
		nerfMiner = config.get("Mekanism Tweaks", "nerfDigitalMiner", true, "Make the recipe for the digital miner a bit...ok a lot harder").getBoolean(true);
		disableUniversalCables = config.get("Mekanism Tweaks", "disableUniversalCables", false, "Remove the recipe for universal cables.").getBoolean(false);
		
		autoEnableTT = config.get("Mod Loading Tweaks", "autoEnableTT", true, "Allow this mod to disable and enable Thaumic Tinkerer automatically").getBoolean(true);
		
		Reference.thaumcraftFilename = config.get("Mod Loading Tweaks", "Thaumcraft_filename", Reference.DEFAULT_THAUMCRAFT_FILENAME, "The filename for Thaumcraft4 to use to check for its presence").getString();
		Reference.TTFilename = config.get("Mod Loading Tweaks", "ThaumicTinkerer_filename", Reference.DEFAULT_TT_FILENAME, "The filename for Thaumic Tinkerer to use to check for its presence and disable/enable it automatically").getString();
		Reference.KAMIFilename = config.get("Mod Loading Tweaks", "KAMI_filename", Reference.DEFAULT_KAMI_FILENAME, "The filename for KAMI to use to check for its presence and disable/enable it automatically").getString();
		
		Reference.packName = config.get("Pack Info", "packName", "Test Pack Please Ignore", "The full name of the pack").getString();
		Reference.packVersion = config.get("Pack Info", "packVerison", "1.0.0", "The version of the pack").getString();
		Reference.packAcronym = config.get("Pack Info", "packAcronym", "TPPI", "The acronym of the pack (required, can be the same as name)").getString();
		allowCapes = config.get("Pack Info", "allowDevCapes", true, "Enables/Disables the visibility of dev capes. This only affects the user and does NOT have to be the same between client and server.").getBoolean(true);
		
		config.save();
	}

	/**
	 * Method that gathers the info for the book given to players on spawn
	 * 
	 * @param file
	 *            - The input stream to gather the text from
	 */
	public static void loadGuideText(InputStream file)
	{
		bookText = file == null ? new ArrayList<String>() : TxtParser.parseFileMain(file);
	}
	
	public static void loadChangelogText(InputStream file)
	{
		changelog = file == null ? new ArrayList<String>() : TxtParser.parseFileMain(file);
	}

	/**
	 * Sets a config value manually by editing the text file
	 * @param prefix - The prefix of the config option (anythin before the '=')
	 * @param from - The setting to change it from 
	 * @param to - The setting to change it to
	 */
	public static void manuallyChangeConfigValue(String prefix, String from, String to)
	{
		try
		{
			FileReader fr1 = new FileReader(cfg);
			BufferedReader read = new BufferedReader(fr1);
			
			ArrayList<String> strings = new ArrayList<String>();
			
			while (read.ready())
			{
				strings.add(read.readLine());
			}
			
			fr1.close();
			read.close();
			
			FileWriter fw = new FileWriter(cfg);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (String s : strings)
			{
				if (s.equals("    " + prefix + "=" + from))
					s = "    " + prefix + "=" + to;
				
				fw.write(s + "\n");
			}	
			
			bw.flush();
			bw.close();
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
}
