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
	public static boolean enderChestTesseract;
	public static boolean enderPouchTesseract;
	public static boolean enderTankTesseract;
	public static boolean steelReactorCasings;


	/** ArrayList of Strings, the strings are each one whole page **/
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
		bookTitle = config.get("BOOK INFO", "bookTitle", "TPPI Field Guide", "The title of the custom spawn book", Type.STRING).getString();
		bookAuthor = config.get("BOOK INFO", "bookAuthor", "The TPPI Team", "The author of the custom spawn book", Type.STRING).getString();
		enderChestTesseract = config.get("OPTIONS", "enderChestTesseract", true, "EnderStorage Ender Chests require tesseracts instead of ender pearls.").getBoolean(true);
		enderPouchTesseract = config.get("OPTIONS", "enderPouchTesseract", false, "EnderStorage Ender Pouches require tesseracts instead of ender pearls.").getBoolean(false);
		enderTankTesseract = config.get("OPTIONS", "enderTankTesseract", false, "EnderStorage Ender Tanks require tesseracts instead of ender pearls.").getBoolean(false);
		steelReactorCasings = config.get("OPTIONS", "steelReactorCasings", true, "Big Reactors reactor casing require steel. Affects ONLY the casings.").getBoolean(true);
		
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
		Scanner scanner;
		scanner = new Scanner(file);

		bookText = new ArrayList<String>();

		String nextPage = "";
		while (scanner.hasNextLine())
		{
			String temp = scanner.nextLine();

			// If the line is a comment
			if (temp.length() == 0 || temp.startsWith("**"))
			{
				// If the line is possibly a line-skip comment
				if (temp.startsWith("***") && temp.length() == 4)
				{
					// Skip the requested amount of lines by parsing the number after the asterisks
					for (int i = 0; i <= Integer.parseInt(temp.substring(3, 4)); i++)
					{
						scanner.nextLine();
					}
				}
				
				// If the line is not a valid line-skip comment, and does not have more than 3 asterisks
				else if (temp.startsWith("***") && !temp.startsWith("****"))
				{
					System.err.println("TPPI - Invalid line-skip in changelog. This may not work as intended");
				}
				
				// Finally, do not add this to the page
				continue;
			}

			// If this line is a page break
			if (temp.equals("~~~"))
			{
				bookText.add(nextPage);
				nextPage = "";
			}
			else
			{
				// If there is a line break
				if (temp.charAt(temp.length() - 1) == '~')
				{
					temp = temp.substring(0, temp.length() - 1);
					temp += "\n";
					nextPage += temp;
				}
				// In the end just concatenate to the page
				else
					nextPage += temp + " ";
			}
		}

		bookText.add(nextPage);

		scanner.close();
	}
}
