package tppitweaks.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Beware all ye who enter here, for the sake of your sanity, turn back
 */
public class TxtParser
{
	/** The useable lines (not commented) from the file last processed by parseFileMain **/
	private static ArrayList<String> useableLines = new ArrayList<String>();

	/**
	 * Parses a file, taking into account all comments, line-skips, and pagination/formatting
	 * @param file
	 * @return an array of pages
	 */
	public static ArrayList<String> parseFileMain(InputStream file)
	{
		ArrayList<String> bookText = new ArrayList<String>();
		useableLines.clear();

		Scanner scanner;
		scanner = new Scanner(file);

		String nextPage = "";

		while (scanner.hasNextLine())
		{
			String temp = scanner.nextLine();

			// If the line is a comment
			if (temp.length() == 0 || temp.startsWith("**"))
			{
				// If the line is possibly a line-skip comment
				if (temp.startsWith("***"))
				{
					boolean validSkip = true;
					for (int i = 3; i < temp.length(); i++)
						if (!isANumber(temp.charAt(i)))
						{
							validSkip = false;
							break;
						}
					// Skip the requested amount of lines by parsing the number
					// after the asterisks
					if (validSkip)
					{
						for (int i = 0; i <= Integer.parseInt(temp.substring(3, temp.length())); i++)
						{
							scanner.nextLine();
						}
					}
					else
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
				useableLines.add(temp);

				// If there is a line break
				if (temp.charAt(temp.length() - 1) == '~')
				{
					temp = temp.substring(0, temp.length() - 1);
					temp += "\n";
					if (nextPage.length() + temp.length() > 240 && nextPage.length() != 0 && temp.length() != 0)
					{
						bookText.add(nextPage);
						nextPage = temp;
						temp = "";
					}
					else		
						nextPage += temp;
				}
				// In the end just concatenate to the page
				else
				{
					if (nextPage.length() + temp.length() > 240 && nextPage.length() != 0 && temp.length() != 0)
					{
						bookText.add(nextPage);
						nextPage = temp;
						temp = "";
					}
					else		
						nextPage += temp + " ";
				}
			}
		}

		if (nextPage.length() > 1)
			bookText.add(nextPage);
		
		scanner.close();

		return bookText;
	}

	/**
	 * @param charAt
	 * @return If this char is a number
	 */
	private static boolean isANumber(char charAt)
	{
		for (int i = 0; i < 10; i++)
		{
			try
			{
				if (Integer.parseInt(String.valueOf(charAt)) == i)
					return true;
			}
			catch (Exception e)
			{
				// Do Nothing
			}
		}
		return false;
	}

	/**
	 * Parses a file, identically to ParseFileMain, but takes into account mod names, searching for the >< identifier.
	 * @param file
	 * @param modName
	 * @return an array of pages, only containing the lines of info about the passed in modname
	 */
	public static ArrayList<String> parseFileMods(InputStream file, String modName)
	{
		ArrayList<String> bookText = new ArrayList<String>();

		Scanner scanner = new Scanner(file);

		String nextPage = "";

		while (scanner.hasNextLine())
		{
			String temp = scanner.nextLine();

			if (temp.startsWith(">") && temp.contains("<"))
				if (temp.substring(1, temp.length() - 1).equals(modName))
					break;
		}

		while (scanner.hasNextLine())
		{
			String temp = scanner.nextLine();
			if (temp.startsWith(">") && temp.contains("<"))
				break;

			// If the line is a comment
			if (temp.length() == 0 || temp.startsWith("**"))
			{
				// If the line is possibly a line-skip comment
				if (temp.startsWith("***"))
				{
					boolean validSkip = true;
					for (int i = 3; i < temp.length(); i++)
						if (!isANumber(temp.charAt(i)))
						{
							validSkip = false;
							break;
						}
					// Skip the requested amount of lines by parsing the number
					// after the asterisks
					if (validSkip)
					{
						for (int i = 0; i <= Integer.parseInt(temp.substring(3, temp.length())); i++)
						{
							scanner.nextLine();
						}
					}
					else
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
					if (nextPage.length() + temp.length() > 240 && nextPage.length() != 0 && temp.length() != 0)
					{
						bookText.add(nextPage);
						nextPage = temp;
						temp = "";
					}
					else		
						nextPage += temp;
				}
				// In the end just concatenate to the page
				else
				{
					if (nextPage.length() + temp.length() > 240 && nextPage.length() != 0 && temp.length() != 0)
					{
						bookText.add(nextPage);
						nextPage = temp;
						temp = "";
					}
					else		
						nextPage += temp + " ";
				}
			}
		}

		if (nextPage.length() > 1)
			bookText.add(nextPage);

		scanner.close();

		return bookText;
	}

	/**
	 * Gets mod names from the file
	 * @param file
	 * @return an array of Strings, the names of mods that are described in the file, evidenced by the >< identifier
	 */
	public static ArrayList<String> getSupportedMods(InputStream file)
	{
		ArrayList<String> mods = new ArrayList<String>();

		Scanner scanner;
		scanner = new Scanner(file);

		parseFileMain(file);

		for (String s : useableLines)
			if (s.startsWith(">") && s.contains("<"))
			{
				mods.add(s.substring(1, s.indexOf('<')));
			}

		scanner.close();

		return mods;
	}
}
