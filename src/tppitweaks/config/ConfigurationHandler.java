package tppitweaks.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import net.minecraftforge.common.Configuration;

public class ConfigurationHandler {

	public static HashMap<String, Boolean> am2SpawnControls = new HashMap<String, Boolean>();
	public static int bookID;
	public static String bookText = "";
	
	public static String[] am2MobKeys = {"EntityHecate", "EntityDarkMage", "EntityWaterElemental", "EntityManaElemental", 
		"EntityDryad", "EntityManaCreeper", "EntityDarkling"};
	
	public static void init(File file) {
		
		Configuration config = new Configuration(file);
		config.load();
		
		for(String s : am2MobKeys) {
			am2SpawnControls.put(s, config.get("GLOBAL ARS MAGICA 2 MOB SPAWN CONTROLS", "globallyDisable"+s, true).getBoolean(true));
		}
		bookID = config.getItem("tppiBookId", 21000).getInt() - 256;
		
		config.save();
		
	}
	
	public static void loadBookText(File bookTextFile) {
		Scanner scanner;
		try {
			scanner = new Scanner(bookTextFile);
			while(scanner.hasNext()) {
				bookText = bookText + scanner.next();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Unable to locate TPPI Book file.");
		}
	}
	
	
}
