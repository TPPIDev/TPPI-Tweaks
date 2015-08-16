package tppitweaks.config;

import net.minecraftforge.common.config.Configuration;
import tppitweaks.TPPITweaks;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigurationHandler
{
	public static boolean enderChestNerf;
	public static boolean enderPouchNerf;
	public static boolean enderTankNerf;

	public static boolean steelReactorCasings;
	public static boolean glassFuelRods;
	public static boolean twoReactorGlass;

	public static boolean eloraamBreakersAndDeployers;
	public static boolean disablePigmenTrophyDrop;
	public static boolean enableOcelotAndWolfEggRecipes;

	public static boolean ic2TEGlassInterchangeability;
	public static boolean tweakDA;
	public static boolean tweakSFM;
	public static boolean disableDeconstructor;
	public static boolean tweakJABBA;
	public static boolean buffUnifierRecipe;
	public static boolean disablePlasticCups;

	public static boolean enableSoulFragmentRecipes;
	public static boolean fixExURecipes;
	public static boolean harderDiamondSpikeRecipe;
	public static boolean nerfEnderQuarry;
	
	public static boolean nerfRedstoneGen;
	public static boolean nerfEnderGen;
	public static boolean nerfAngelRings;

	public static boolean doPlatinumInCentrifuge;
	public static boolean addLapisDustMortarRecipes;
	public static boolean tinkersAluminumPlates;
	public static boolean tinkersAluminumOreInGTMachines;
	public static boolean addOneToOnePlateHammerRecipes;
	public static boolean unnerfPaperRecipe;
	public static boolean readdResinSmelting;

	public static boolean doCharcoalBlockCompression;
	public static boolean makeEIOHardModeEasier;
	public static String[] removeExtruderInput;
	public static boolean addDarkMenagerieMobDrops;
	public static boolean harderQuarryRecipe;
	
	public static boolean harderActivatorRecipe;
	public static boolean harderDisassemblerRecipe;
	public static boolean disableCardboardBox;
	public static boolean disableMiner;
	public static boolean nerfMiner;
	public static boolean disableUniversalCables;
	
	public static boolean addIridiumPanelRecipe;
	
	public static boolean addOsmiumToOreWasher;

	public static boolean addGreenHeartRecipe;
	
	public static boolean harderAdvancedItemCollectorRecipe;
	
	public static boolean nerfTECaches;
	
	public static boolean showIRCGui;
		
	public static File cfg;
		
	public static boolean allowCapes = true;

	public static void init(File file)
	{
		cfg = file;
		
		Configuration config = new Configuration(file);
		config.load();
		
		enderChestNerf = config.get("Ender Storage Tweaks", "enderChestNerf", true, "EnderStorage Ender Chests require tesseract frames instead of ender pearls.").getBoolean(true);
		enderPouchNerf = config.get("Ender Storage Tweaks", "enderPouchNerf", true, "EnderStorage Ender Pouches require pyrotheum dust and liquid ender instead of blaze rods and ender pearls.").getBoolean(true);
		enderTankNerf = config.get("Ender Storage Tweaks", "enderTankNerf", true, "EnderStorage Ender Tanks require resonant portable tanks instead of ender pearls.").getBoolean(true);
		
		steelReactorCasings = config.get("Other Mod Tweaks", "steelReactorCasings", false, "Big Reactors reactor casings require steel. Affects ONLY the casings.").getBoolean(true);
		ic2TEGlassInterchangeability = config.get("Other Mod Tweaks", "ic2TEGlassInterchangeability", true, "IC2 reinforced glass (glassReinforced) and Thermal Expansion hardened glass (glassHardened)\nwill be cross-registered as each other in the ore dictionary.").getBoolean(false);
		tweakDA = config.get("Other Mod Tweaks", "tweakDARecipe", true, "Make Dimensional Anchors' recipe closer to that of a chicken chunks chunk loader.").getBoolean(true);
		tweakSFM = config.get("Other Mod Tweaks", "stevesFactoryManagerAERecipes", true, "Recipes from Steve's Factory Manager take items from Applied Energistics.").getBoolean(true);
		disableDeconstructor = config.get("Other Mod Tweaks", "disableArcaneDeconstructor", true, "Disable the arcane deconstructor. It has serious dupes with witchery and other mods. RE-ENABLING THIS WILL OPEN YOUR SERVER TO MAJOR DUPES.").getBoolean(true);
		glassFuelRods = config.get("Other Mod Tweaks", "glassFuelRods", false, "Big Reactors fuel rods take just a touch of reactor glass.").getBoolean(false);
		twoReactorGlass = config.get("Other Mod Tweaks", "twoReactorGlass", true, "Big Reactors\' reactor glass recipe gives two reactor glass in exchange for the harder recipe.").getBoolean(true);
		addIridiumPanelRecipe = config.get("Other Mod Tweaks", "addIridiumPanelRecipe", true, "Add additional recipe for the Iridium Reinforced Plate, because AOBD removes all ways to process the ore into the appropriate type.").getBoolean(true);
		harderQuarryRecipe = config.get("Other Mod Tweaks", "harderQuarryRecipe", true, "Replace the Diamond Pickaxe in the Quarry with a Diamond Drill to increase initial investment while Greg's away.").getBoolean(true);
		
		eloraamBreakersAndDeployers = config.get("Other Mod Tweaks", "eloraamBreakersAndDeployers", true, "OpenBlocks block breakers and placers have the same recipes as Redpower 2's.").getBoolean(true);
		disablePigmenTrophyDrop = config.get("Other Mod Tweaks", "disablePigmenTrophyDrop", true, "Disables pigmen trophies from dropping.").getBoolean(true);
		enableOcelotAndWolfEggRecipes = config.get("Other Mod Tweaks", "enableOcelotAndWolfEggRecipes", true, "Add runic altar recipes to create spawn eggs for ocelots and wolves, to make Witchery less painful with the larger default biome size.").getBoolean(true);
		
		tweakJABBA = config.get("Other Mod Tweaks", "tweakJABBA", true, "Increase cost of diamond dolly to put it in line with other ways to move mob spawners.").getBoolean(true);
		buffUnifierRecipe = config.get("Other Mod Tweaks", "buffUnifierRecipe", true, "Make the unifier cheaper.").getBoolean(true);
		disablePlasticCups = config.get("Other Mod Tweaks", "disablePlasticCups", true, "Disable the plastic cups from MFR as they dupe liquids.").getBoolean(true);
		makeEIOHardModeEasier = config.get("Other Mod Tweaks", "makeEnderIOHardModeEasier", true, "Give some EnderIO recipes a buff as they are way too diffucult. Currently affects: basic capacitor, reservoir").getBoolean(true);
//		addDarkMenagerieMobDrops = config.get("Other Mod Tweaks", "addDarkMenagerieMobDrops", true, "The mobs are interesting but some don't drop anything. This fixes that.").getBoolean(true);
		
		enableSoulFragmentRecipes = config.get("ExtraUtils Tweaks", "enableSoulFragmentRecipes", true, "Add in expensive recipes to allow auto-crafting of soul fragments to either offset health loss or avoid it.").getBoolean(true);
		fixExURecipes = config.get("ExtraUtils Tweaks", "fixExtraUtilsRecipes", true, "Current version of ExU has broken recipes for the unstable ingot block. This fixes that.").getBoolean(true);
		harderDiamondSpikeRecipe = config.get("ExtraUtils Tweaks", "harderDiamondSpikeRecipe", true, "Increase cost of diamond spikes to account for more expensive drops in pack.").getBoolean(true);
		nerfEnderQuarry = config.get("ExtraUtils Tweaks", "nerfEnderQuarry", true, "Make the Extra Utilities ender quarry expensive.").getBoolean(true);
		nerfRedstoneGen = config.get("ExtraUtils Tweaks", "nerfRedstoneGen", true, "Make the Extra Utilities heated redstone generator expensive.").getBoolean(true);
		nerfEnderGen = config.get("ExtraUtils Tweaks", "nerfEnderGen", true, "Make the Extra Utilities ender generator expensive.").getBoolean(true);
		nerfAngelRings = config.get("ExtraUtils Tweaks", "nerfAngelRings", true, "Make the Extra Utilities Angel Rings expensive.").getBoolean(true);

//		doPlatinumInCentrifuge = config.get("Gregtech Tweaks", "doPlatinumInCentrifuge", true, "Re-adds the old GregTech centrifuge recipe for platinum dust to iridium nugget + small nickel dust.").getBoolean(true);
//		addLapisDustMortarRecipes = config.get("Gregtech Tweaks", "addLapisDustMortarRecipes", true, "Lapis dust can be made from lapis using GregTech's mortars.").getBoolean(true);
//		tinkersAluminumPlates = config.get("Gregtech Tweaks", "tinkersAluminumPlates", true, "Tinkers' Construct aluminum ingots can make aluminum plates in the GregTech plate bending machine.").getBoolean(true);
//		tinkersAluminumOreInGTMachines = config.get("Gregtech Tweaks", "tinkersAluminumOreInGTMachines", true, "Tinkers' Construct aluminum ore works in GregTech machines.").getBoolean(true);
//		unnerfPaperRecipe = config.get("Gregtech Tweaks", "unnerfPaperRecipe", true, "Revert GregTech's paper recipe output nerf.").getBoolean(true);
//		readdResinSmelting = config.get("Gregtech Tweaks", "readdResinSmelting", true, "Re-add the IC2 sticky resin to rubber smelting recipe.").getBoolean(true);
//		doCharcoalBlockCompression = config.get("Gregtech Tweaks", "doCharcoalBlockCompression", true, "Charcoal blocks can be compressed to coal via compressor.").getBoolean(true);
//		removeExtruderInput = config.get("Gregtech Tweaks", "removeExtruderInput", new String[] {"crystalIron", "crystalGold", "crystalOsmium", "crystalCopper", "crystalTin", "crystalSilver", "crystalObsidian", "crystalLead", "shardIron", "shardGold", "shardOsmium", "shardCopper", "shardTin", "shardSilver", "shardObsidian", "shardLead"}, "Disable these Ore Dict items as valid inputs for the Extruder").getStringList();
		
		showIRCGui = config.get("Mod Loading Tweaks", "showIRCGui", true, "Show the IRC integration startup GUI").getBoolean(true);

		addGreenHeartRecipe = config.get("Tinker's Construct Tweaks", "addGreenHeartRecipe", true, "Adds a recipe for the green heart and canister.").getBoolean(true);

		harderActivatorRecipe = config.get("Other Mod Tweaks", "harderActivatorRecipe", true, "Make the autonomous activator recipe slightly harder").getBoolean(true);
		
		addOsmiumToOreWasher = config.get("Mekanism Tweaks", "addOsmiumToOreWasher", true, "Add a recipe for impure osmium dust to clean osmium dust in the IC2 ore washer.").getBoolean(true);
		harderDisassemblerRecipe = config.get("Mekanism Tweaks", "harderAtomicDisassembler", true, "Makes the recipe for the Atomic Disassembler much more difficult").getBoolean(true);
		disableCardboardBox = config.get("Mekanism Tweaks", "disableCardboardBox", false, "Remove the recipe for the cardboard box (it can move ANY tile entity including nodes and spanwers)").getBoolean(false);
		disableMiner = config.get("Mekanism Tweaks", "disableDigitalMiner", false, "Remove the recipe for the digital miner (not really any reason to do this now but I'm not deleting code)").getBoolean(false);
		nerfMiner = config.get("Mekanism Tweaks", "nerfDigitalMiner", true, "Make the recipe for the digital miner a bit...ok a lot harder").getBoolean(true);
		disableUniversalCables = config.get("Mekanism Tweaks", "disableUniversalCables", false, "Remove the recipe for universal cables.").getBoolean(false);
				
		harderAdvancedItemCollectorRecipe = config.get("Random Things Tweaks", "harderAdvancedItemCollectorRecipe", true, "Increase the cost of the advanced item collector.").getBoolean(true);
		
		nerfTECaches = config.get("Thermal Expansion Tweaks", "nerfTECaches", true, "Add Mekanism Bins to basic cache recipe.").getBoolean(true);
		
//		allowCapes = config.get("Pack Info", "allowDevCapes", true, "Enables/Disables the visibility of dev capes. This only affects the user and does NOT have to be the same between client and server.").getBoolean(true);
		
		config.save();
	}

	/**
	 * Sets a config value manually by editing the text file
	 * @param prefix - The prefix of the config option (anything before '='), must match exactly.
	 * @param from - The setting to change it from 
	 * @param to - The setting to change it to
	 * @return whether anything changed
	 */
	public static boolean manuallyChangeConfigValue(String prefix, String from, String to)
	{
		return manuallyChangeConfigValue(null, prefix, from, to);
	}
	
	/**
	 * Same as <code>manuallyChangeConfigValue(String, String, String)</code>, but with an additional parameter for <i>what</i> config file to edit
	 * @param filePathFromConfigFolder - the full path to the files, including extensions, from inside config/
	 * @param prefix - The prefix of the config option (anything before '='), must match exactly.
	 * @param from - The setting to change it from
	 * @param to - The setting to change it to
	 * @return whether anything changed
	 */
	public static boolean manuallyChangeConfigValue(String filePathFromConfigFolder, String prefix, String from, String to)
	{
		File config = filePathFromConfigFolder == null ? cfg : new File(cfg.getParentFile().getParent() + "/" + filePathFromConfigFolder);
		boolean found = false;

		try
		{
			FileReader fr1 = new FileReader(config);
			BufferedReader read = new BufferedReader(fr1);
			
			ArrayList<String> strings = new ArrayList<String>();
			
			while (read.ready())
			{
				strings.add(read.readLine());
			}
			
			fr1.close();
			read.close();
			
			FileWriter fw = new FileWriter(config);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (String s : strings)
			{
				if (!found && s.contains(prefix + "=" + from) && !s.contains("=" + to))
				{
					s = s.replace(prefix + "=" + from, prefix + "=" + to);
					TPPITweaks.logger.info("Successfully changed config value " + prefix + " from " + from + " to " + to); 
					found = true;
				}
				
				fw.write(s + "\n");
			}	
			
			bw.flush();
			bw.close();
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
		
		return found;
	}
	
	/**
	 * Finds the config value in the file specified (path starting after config/), and for the key specified
	 * @param filePathFromConfigFolder - The path to the file, everything up to config/ is calculated for you
	 * @param key - A key to find the value by, does not need to match exactly
	 * @return A parseable string that can be transformed into any of the types of config values, for instance using <code>Boolean.parseBoolean(String)</code>
	 */
	public static String manuallyGetConfigValue(String filePathFromConfigFolder, String key) {
		File config = new File(ConfigurationHandler.cfg.getParentFile().getParent() + "/" + filePathFromConfigFolder);
		boolean noConfig = false;
		Scanner scan = null;

		try {
			scan = new Scanner(config);
		} catch (FileNotFoundException e) {
			noConfig = true;
		}	

		if (noConfig)
			return "";

		while (scan.hasNext())
		{
			String s = scan.next();

			if (s.contains(key))
			{
				scan.close();
				return s.substring(s.indexOf("=") + 1, s.length());
			}
		}
		return "";
	}
}
