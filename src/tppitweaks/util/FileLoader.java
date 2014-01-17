package tppitweaks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import net.minecraft.client.Minecraft;
import tppitweaks.TPPITweaks;
import tppitweaks.lib.Reference;

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
	
	public static void disableTT()
	{
		for(File f : getTT()) {
			f.renameTo(new File(f.getParent() + f.getName() + ".disabled"));
		}
	}
	
	public static void enableTT()
	{
		for(File f : getTT()) {
			f.renameTo(new File(f.getAbsolutePath().replace(".disabled", "")));
		}
		
	}
	
	private static ArrayList<File> getTT()
	{
		
		ArrayList<File> thaumicTinkererFiles = new ArrayList<File>();
		File modJar;
		
		modJar = new File(Reference.modsFolder, Reference.TT_FILENAME);
		if(modJar.exists()) {
			thaumicTinkererFiles.add(modJar);
		}
		
		modJar = new File(Reference.modsFolder, Reference.TT_KAMI_FILENAME);
		if(modJar.exists()) {
			thaumicTinkererFiles.add(modJar);
		}
		
		return thaumicTinkererFiles;
		
	}
	
	public static void getThaumicTinkererFilenameState() {
		
		File modJar;
		
		modJar = new File(Reference.modsFolder, Reference.TT_FILENAME).exists() ? new File(Reference.modsFolder, Reference.TT_FILENAME) : new File(Reference.modsFolder, Reference.TT_FILENAME + ".disabled");
		if(modJar.exists()) {
			Reference.TT_FILENAME = modJar.getName();
		}
		
		modJar = new File(Reference.modsFolder, Reference.TT_KAMI_FILENAME).exists() ? new File(Reference.modsFolder, Reference.TT_KAMI_FILENAME) : new File(Reference.modsFolder, Reference.TT_KAMI_FILENAME + ".disabled");
		if(modJar.exists()) {
			Reference.TT_FILENAME = modJar.getName();
		}
		
	}
	
}
