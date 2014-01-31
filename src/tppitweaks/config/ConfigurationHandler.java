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
	
	public static String bookTitle;
	public static String bookAuthor;
	public static String changelogTitle;
	
	public static boolean enderChestTesseract;
	public static boolean enderPouchTesseract;
	public static boolean enderTankTesseract;
	public static boolean steelReactorCasings;
	public static boolean glassFuelRods;
	public static boolean eloraamBreakersAndDeployers;
	public static boolean ic2TEGlassInterchangeability;
	public static boolean tweakDA;
	public static boolean tweakSFM;
	public static boolean tweakAM2;
	public static boolean fixExURecipes;
	public static boolean changeMPSARecipes;

	public static boolean doPlatinumInCentrifuge;
	public static boolean addLapisDustMortarRecipes;
	public static boolean tinkersAluminumPlates;
	public static boolean tinkersAluminumOreInGTMachines;
	public static boolean addOneToOnePlateHammerRecipes;
	public static boolean unnerfPaperRecipe;
	public static boolean readdResinSmelting;
	
	public static boolean removeStupidEnergyCrystalRecipe;
	
	public static boolean addOsmiumToOreWasher;
	
	public static boolean registerMagicalCropsOre;

	public static boolean showDownloadGUI;
	
	public static boolean autoEnableTT;
	
	public static File cfg;
	
	/** ArrayList of Strings, the strings are each one whole page **/
	public static List<String> bookText;
	public static List<String> changelog;

	public static String[] am2MobKeys = { "EntityHecate", "EntityDarkMage", "EntityLightMage", "EntityEarthElemental", "EntityFireElemental", "EntityWisp", "EntityWaterElemental", "EntityManaElemental", "EntityDryad", "EntityManaCreeper", "EntityDarkling" };

	public static void init(File file)
	{
		cfg = file;
		
		Configuration config = new Configuration(file);
		config.load();

		for (String s : am2MobKeys)
		{
			am2SpawnControls.put(s, config.get("GLOBAL ARS MAGICA 2 MOB SPAWN CONTROLS", "globallyDisable" + s, true).getBoolean(true));
		}
		
		bookID = config.getItem("tppiBookId", 21000).getInt() - 256;
		materialID = config.getItem("tppiMaterialId", 21001).getInt() - 256;
		
		bookTitle = config.get("TPPI Guide Info", "bookTitle", "TPPI Welcome Packet", "The title of the custom spawn book", Type.STRING).getString();
		bookAuthor = config.get("TPPI Guide Info", "bookAuthor", "The TPPI Team", "The author of the custom spawn book", Type.STRING).getString();
		changelogTitle = config.get("TPPI Guide Info", "changelogTitle", "TPPI Changelog", "The title of the changelog").getString();
		
		enderChestTesseract = config.get("Ender Storage Tweaks", "enderChestTesseract", true, "EnderStorage Ender Chests require tesseracts instead of ender pearls.").getBoolean(true);
		enderPouchTesseract = config.get("Ender Storage Tweaks", "enderPouchTesseract", false, "EnderStorage Ender Pouches require tesseracts instead of ender pearls.").getBoolean(false);
		enderTankTesseract = config.get("Ender Storage Tweaks", "enderTankTesseract", false, "EnderStorage Ender Tanks require tesseracts instead of ender pearls.").getBoolean(false);
		steelReactorCasings = config.get("Other Mod Tweaks", "steelReactorCasings", true, "Big Reactors reactor casings require steel. Affects ONLY the casings.").getBoolean(true);
		ic2TEGlassInterchangeability = config.get("Other Mod Tweaks", "ic2TEGlassInterchangeability", true, "IC2 reinforced glass (glassReinforced) and Thermal Expansion hardened glass (glassHardened)\nwill be cross-registered as each other in the ore dictionary.").getBoolean(true);
		tweakDA = config.get("Other Mod Tweaks", "tweakDARecipe", true, "Make Dimensional Anchors' recipe closer to that of a chicken chunks chunk loader.").getBoolean(true);
		tweakSFM = config.get("Other Mod Tweaks", "stevesFactoryManagerAERecipes", true, "Recipes from Steve's Factory Manager take items from Applied Energistics.").getBoolean(true);
		tweakAM2 = config.get("Other Mod Tweaks", "addAM2NatureGuardianRecipe", true, "Add recipe for Ars Magica 2 Nature Guardian spawn egg. Useful when dryads are disabled.").getBoolean(true);
		glassFuelRods = config.get("OPTIONS", "glassFuelRods", true, "Big Reactors fuel rods take just a touch of reactor glass.").getBoolean(true);
		eloraamBreakersAndDeployers = config.get("Other Mod Tweaks", "eloraamBreakersAndDeployers", true, "OpenBlocks block breakers and placers have the same recipes as Redpower 2's.").getBoolean(true);
		removeStupidEnergyCrystalRecipe = config.get("Other Mod Tweaks", "removeDartCraftEnergyCrystalRecipe", true, "Remove DartCraft's IC2 energy crystal recipe.").getBoolean(true);
		fixExURecipes = config.get("Other Mod Tweaks", "fixExtraUtilsRecipes", true, "Current version of ExU has broken recipes for the unstable ingot block. This fixes that.").getBoolean(true);
		changeMPSARecipes = config.get("MPS Extra Recipes", "change_MPSA_recipes", true, "Changes MPSA recipes to match the MPS recipes that are in TPPI").getBoolean(true);
		
		doPlatinumInCentrifuge = config.get("Gregtech Tweaks", "doPlatinumInCentrifuge", true, "Re-adds the old GregTech centrifuge recipe for platinum dust to iridium nugget + small nickel dust.").getBoolean(true);
		addLapisDustMortarRecipes = config.get("Gregtech Tweaks", "addLapisDustMortarRecipes", true, "Lapis dust can be made from lapis using GregTech's mortars.").getBoolean(true);
		tinkersAluminumPlates = config.get("Gregtech Tweaks", "tinkersAluminumPlates", true, "Tinkers' Construct aluminum ingots can make aluminum plates in the GregTech plate bending machine.").getBoolean(true);
		tinkersAluminumOreInGTMachines = config.get("Gregtech Tweaks", "tinkersAluminumOreInGTMachines", true, "Tinkers' Construct aluminum ore works in GregTech machines.").getBoolean(true);
		unnerfPaperRecipe = config.get("Gregtech Tweaks", "unnerfPaperRecipe", true, "Revert GregTech's paper recipe output nerf.").getBoolean(true);
		readdResinSmelting = config.get("Gregtech Tweaks", "readdResinSmelting", true, "Re-add the IC2 sticky resin to rubber smelting recipe.").getBoolean(true);
		
		showDownloadGUI = config.get("Mod Downloads", "showDownloadGUI", true, "Show the Download GUI on startup.").getBoolean(true);
		
		registerMagicalCropsOre = config.get("Other Mod Tweaks", "registerMagicalCropsOre", true, "Register essence ores from Magical Crops in the ore dictionary under \"oreMCropsEssence\" and \"oreMCropsNetherEssence\".").getBoolean(true);
		addOsmiumToOreWasher = config.get("Other Mod Tweaks", "addOsmiumToOreWasher", true, "Add a recipe for impure osmium dust to clean osmium dust in the IC2 ore washer.").getBoolean(true);

		autoEnableTT = config.get("Mod Loading Tweaks", "autoEnableTT", true, "Allow this mod to disable and enable Thaumic Tinkerer automatically").getBoolean(true);
		
		Reference.thaumcraftFilename = config.get("Mod Loading Tweaks", "Thaumcraft_filename", Reference.DEFAULT_THAUMCRAFT_FILENAME, "The filename for Thaumcraft4 to use to check for its presence").getString();
		Reference.TTFilename = config.get("Mod Loading Tweaks", "ThaumicTinkerer_filename", Reference.DEFAULT_TT_FILENAME, "The filename for Thaumic Tinkerer to use to check for its presence and disable/enable it automatically").getString();
		Reference.KAMIFilename = config.get("Mod Loading Tweaks", "KAMI_filename", Reference.DEFAULT_KAMI_FILENAME, "The filename for KAMI to use to check for its presence and disable/enable it automatically").getString();
		
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

	public static void stopShowingGUI()
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
				if (s.equals("    B:showDownloadGUI=true"))
					s = "    B:showDownloadGUI=false";
				
				fw.write(s + "\n");
			}	
			
			bw.flush();
			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
