package tppitweaks.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtParser
{
	public static ArrayList<String> parseFileMain(InputStream file)
	{
		ArrayList<String> bookText = new ArrayList<String>();
		
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
		
		return bookText;
	}
	
	public ArrayList<String> parseFileMods(InputStream file, String modName)
	{
		ArrayList<String> bookText = new ArrayList<String>();
		
		Scanner scanner;
		scanner = new Scanner(file);
		
		String nextPage = "";

		while (scanner.hasNextLine())
		{
			String temp = scanner.nextLine();
			
			if (temp.startsWith(">") && temp.endsWith("<"))
				if (temp.substring(1, temp.length() - 1).equals(modName))
					break;
		}
		
		while (scanner.hasNextLine())
		{
			String temp = scanner.nextLine();
			if (temp.startsWith(">") && temp.endsWith("<"))
				break;
			
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
		
		return bookText;
	}
}
