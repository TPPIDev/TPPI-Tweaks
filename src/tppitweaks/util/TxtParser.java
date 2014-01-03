package tppitweaks.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtParser
{
	private static ArrayList<String> useableLines = new ArrayList<String>();
	
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
					nextPage += temp;
				}
				// In the end just concatenate to the page
				else
					nextPage += temp + " ";
			}
		}

		bookText.add(nextPage);

		scanner.close();

		return bookText;
	}

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

	public static ArrayList<String> parseFileMods(InputStream file, String modName)
	{
		System.out.println("test");
		ArrayList<String> bookText = new ArrayList<String>();

		Scanner scanner = new Scanner(file);

		String nextPage = "";

		while (scanner.hasNextLine())
		{
			String temp = scanner.nextLine();

			System.out.println(temp + " *************************************************************************************");
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
					nextPage += temp;
				}
				// In the end just concatenate to the page
				else
					nextPage += temp + " ";
			}
		}

		bookText.add(nextPage);

		scanner.close();

		return bookText;
	}

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
