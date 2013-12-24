package tppitweaks.config;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import net.minecraftforge.common.Configuration;

public class ConfigurationHandler
{

	public static HashMap<String, Boolean> am2SpawnControls = new HashMap<String, Boolean>();
	public static int bookID;
	public static List<String> bookText;
	
	public static String[] am2MobKeys = { "EntityHecate", "EntityDarkMage", "EntityWaterElemental", "EntityManaElemental", "EntityDryad", "EntityManaCreeper", "EntityDarkling" };

	public static void init(File file)
	{

		Configuration config = new Configuration(file);
		config.load();

		for (String s : am2MobKeys)
		{
			am2SpawnControls.put(s, config.get("GLOBAL ARS MAGICA 2 MOB SPAWN CONTROLS", "globallyDisable" + s, true).getBoolean(true));
		}
		bookID = config.getItem("tppiBookId", 21000).getInt() - 256;

		config.save();
	}

	public static void loadBookText(InputStream file)
	{
		Scanner scanner;
		scanner = new Scanner(file);
		
		bookText = new ArrayList<String>();
		
		while (scanner.hasNextLine())
		{
			bookText.add(scanner.nextLine());
		}
		
		System.out.println(bookText.toString());
		scanner.close();
	}
}
