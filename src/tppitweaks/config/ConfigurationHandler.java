package tppitweaks.config;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property.Type;

public class ConfigurationHandler
{

	public static HashMap<String, Boolean> am2SpawnControls = new HashMap<String, Boolean>();
	public static int bookID;
	public static String bookTitle;
	public static String bookAuthor;
	public static List<String> bookText;
	
	private static final int COMMENT_LINE_NUM = 12;
	
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
		bookTitle = config.get("BOOK INFO", "bookTitle", "TPPI Field Guide", "The title of the custom spawn book", Type.STRING).getString();
		bookAuthor = config.get("BOOK INFO", "bookAuthor", "The TPPI Team", "The author of the custom spawn book", Type.STRING).getString();


		config.save();
	}

	public static void loadBookText(InputStream file)
	{
		Scanner scanner;
		scanner = new Scanner(file);
		
		bookText = new ArrayList<String>();
		
		for (int i = 0; i <= COMMENT_LINE_NUM; i++)
			scanner.nextLine();
		
		String nextPage = "";
		while (scanner.hasNextLine())
		{
			String temp = scanner.nextLine();
			if (temp.equals("~~~"))
			{
				bookText.add(nextPage);
				nextPage = "";
			}
			else
				nextPage += temp + " ";
			
		}
		
		bookText.add(nextPage);
		
		scanner.close();
	}
}
