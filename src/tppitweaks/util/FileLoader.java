package tppitweaks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import tppitweaks.TPPITweaks;

public class FileLoader
{
	public static InputStream bookText, supportedMods;
	
	public static void init(File file, int attempt) throws IOException
	{
		File dir = new File(file.getParent() + "");
		if (!dir.exists())
			dir.mkdir();
		
		try
		{
			bookText = new FileInputStream(new File(file.getParent() + "/BookText.txt"));
		}
		catch(FileNotFoundException e)
		{
			Scanner scan = new Scanner(TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/BookText.txt"));
			
			FileWriter newBookText = new FileWriter(new File(file.getParent() + "/BookText.txt"));
			
			while (scan.hasNext())
			{
				newBookText.write(scan.nextLine() + "\n");
			}
			
			scan.close();
			newBookText.flush(); 
			newBookText.close();
			
			if (attempt < 1)
				init(file, 1);
			else
			{
				System.err.println("TPPI Tweaks - IO Error, books will be partly non-functional. \n");
				e.printStackTrace();
			}
		}
		
		try
		{
			supportedMods = new FileInputStream(new File(file.getParent() + "/SupportedMods.txt"));
		}
		catch(FileNotFoundException e)
		{
			Scanner scan = new Scanner(TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/SupportedMods.txt"));
			
			FileWriter newBookText = new FileWriter(new File(file.getParent() + "/SupportedMods.txt"));
			
			while (scan.hasNext())
			{
				newBookText.write(scan.nextLine() + "\n");
			}
			
			scan.close();
			newBookText.flush(); 
			newBookText.close();
			
			if (attempt < 3)
				init(file, 2);
			else
			{
				System.err.println("TPPI Tweaks - IO Error, books will be partly non-functional. \n");
				e.printStackTrace();
			}
		}
	}
}
