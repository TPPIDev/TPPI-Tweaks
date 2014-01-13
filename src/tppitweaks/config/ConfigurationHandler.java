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
import tppitweaks.util.TxtParser;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property.Type;

public class ConfigurationHandler
{

	public static HashMap<String, Boolean> am2SpawnControls = new HashMap<String, Boolean>();
	public static int bookID;
	public static int materialID;
	
	public static String bookTitle;
	public static String bookAuthor;
	
	public static boolean enderChestTesseract;
	public static boolean enderPouchTesseract;
	public static boolean enderTankTesseract;
	public static boolean steelReactorCasings;
	public static boolean glassFuelRods;
	public static boolean eloraamBreakersAndDeployers;
	public static boolean ic2TEGlassInterchangeability;
	public static boolean tweakDA;
	public static boolean tweakSFM;
	
	public static boolean doPlatinumInCentrifuge;

	public static boolean showDownloadGUI;
	
	public static File cfg;
	
	/** ArrayList of Strings, the strings are each one whole page **/
	public static List<String> bookText;

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
		
		bookTitle = config.get("BOOK INFO", "bookTitle", "TPPI Field Guide", "The title of the custom spawn book", Type.STRING).getString();
		bookAuthor = config.get("BOOK INFO", "bookAuthor", "The TPPI Team", "The author of the custom spawn book", Type.STRING).getString();
		
		enderChestTesseract = config.get("OPTIONS", "enderChestTesseract", true, "EnderStorage Ender Chests require tesseracts instead of ender pearls.").getBoolean(true);
		enderPouchTesseract = config.get("OPTIONS", "enderPouchTesseract", false, "EnderStorage Ender Pouches require tesseracts instead of ender pearls.").getBoolean(false);
		enderTankTesseract = config.get("OPTIONS", "enderTankTesseract", false, "EnderStorage Ender Tanks require tesseracts instead of ender pearls.").getBoolean(false);
		steelReactorCasings = config.get("OPTIONS", "steelReactorCasings", true, "Big Reactors reactor casings require steel. Affects ONLY the casings.").getBoolean(true);
		ic2TEGlassInterchangeability = config.get("OPTIONS", "ic2TEGlassInterchangeability", true, "IC2 reinforced glass (glassReinforced) and Thermal Expansion hardened glass (glassHardened)\nwill be cross-registered as each other in the ore dictionary.").getBoolean(true);
		tweakDA = config.get("OPTIONS", "tweakDARecipe", true, "Make Dimensional Anchors' recipe closer to that of a chicken chunks chunk loader.").getBoolean(true);
		tweakSFM = config.get("OPTIONS", "stevesFactoryManagerAERecipes", true, "Recipes from Steve's Factory Manager take items from Applied Energistics.").getBoolean(true);
		glassFuelRods = config.get("OPTIONS", "glassFuelRods", true, "Big Reactors fuel rods take just a touch of reactor glass.").getBoolean(true);
		eloraamBreakersAndDeployers = config.get("OPTIONS", "eloraamBreakersAndDeployers", true, "OpenBlocks block breakers and placers have the same recipes as Redpower 2's.").getBoolean(true);
		
		doPlatinumInCentrifuge = config.get("OPTIONS", "doPlatinumInCentrifuge", true, "Re-adds the old GregTech centrifuge recipe for platinum dust to iridium nugget + small nickel dust.").getBoolean(true);
		
		showDownloadGUI = config.get("Mod Downloads", "showDownloadGUI", true, "Show the Download GUI on startup.").getBoolean(true);
		
		config.save();
	}

	/**
	 * Method that gathers the info for the book given to players on spawn
	 * 
	 * @param file
	 *            - The input stream to gather the text from
	 */
	public static void loadBookText(InputStream file)
	{
		bookText = file == null ? new ArrayList<String>() : TxtParser.parseFileMain(file);
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
				
				System.out.println(s);
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
