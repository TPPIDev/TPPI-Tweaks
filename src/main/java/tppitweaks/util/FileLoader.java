package tppitweaks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import tppitweaks.TPPITweaks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.lib.Reference;

public class FileLoader
{
	private static InputStream bookText, supportedMods, changelogText;

	public static void init(File file, int attempt) throws IOException
	{
		File dir = new File(file.getParent() + "");
		if (!dir.exists())
			dir.mkdir();

		/* 
		 * From here down commented out as it was 
		 * deemed useless and counter-productive. 
		 * If we add versioning for user-added content,
		 * this becomes useful again 
		 */

		/*
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
		 */
	}

	public static void disableMod(String partOfName, String extension)
	{
		for(File f : getMod(partOfName)) {
			System.out.println("Disabling: " + f.getName());
			if(!f.getName().contains(extension)) {
				f.renameTo(new File(f.getAbsolutePath() + extension));
			}
		}
	}

	public static void enableMod(String partOfName, String extensionToRemove)
	{
		for(File f : getMod(partOfName)) {
			System.out.println("Enabling: " + f.getName());
			if(f.getName().contains(extensionToRemove)) {
				f.renameTo(new File(f.getAbsolutePath().replace(extensionToRemove, "")));
			}
		}
	}

	private static ArrayList<File> getMod(String partOfName)
	{
		ArrayList<File> files = new ArrayList<File>();
		File mods;

		mods = Reference.modsFolder;
		
		String[] fileNames = mods.list();
		ArrayList<String> foundNames = new ArrayList<String>();
		
		for (String s : fileNames)
		{
			if (s.contains(partOfName))
				foundNames.add(s);
		}
		
		for (String s : foundNames)
			files.add(new File(mods, s));
		
		return files;
	}

	/*
	public static void getThaumicTinkererFilenameState() {

		File modJar;

		modJar = new File(Reference.modsFolder, Reference.TTFilename).exists() ? new File(Reference.modsFolder, Reference.TTFilename) : new File(Reference.modsFolder, Reference.TTFilename + EXTENSION);
		if(modJar.exists()) {
			Reference.TTFilename = modJar.getName();
		}

		modJar = new File(Reference.modsFolder, Reference.KAMIFilename).exists() ? new File(Reference.modsFolder, Reference.KAMIFilename) : new File(Reference.modsFolder, Reference.KAMIFilename + EXTENSION);
		if(modJar.exists()) {
			Reference.KAMIFilename = modJar.getName();
		}

	}
	*/

	public static String manuallyGetConfigValue(Map<String, Object> m, String string) {
		File config = new File(((File) m.get("mcLocation")).getAbsolutePath() + "/config/TPPI/TPPITweaks.cfg");
		boolean noConfig = false;
		Scanner scan = null;

		try {
			scan = new Scanner(config);
		} catch (FileNotFoundException e) {
			noConfig = true;
		}	

		if (noConfig)
			return "";

		while (scan.hasNext())
		{
			String s = scan.next();

			if (s.contains(string))
			{
				scan.close();
				return s.substring(s.indexOf("=") + 1, s.length());
			}
		}
		return "";
	}

	public static InputStream getGuideText()
	{
		bookText = TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/BookText.txt");
		return bookText;
	}

	public static InputStream getChangelogText() throws FileNotFoundException
	{
		changelogText = new FileInputStream(new File(ConfigurationHandler.cfg.getParent() + "/changelog.txt"));
		return changelogText;
	}

	public static InputStream getSupportedModsFile()
	{
		supportedMods = TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/" + ConfigurationHandler.supportedModsName + ".txt");
		return supportedMods;
	}

	/*
	public static void removeDuplicateMods()
	{
		ArrayList<File> jars = getMod();
		File removedTT = new File(Reference.modsFolder.getAbsolutePath() + "/" + Reference.TTFilename + EXTENSION);
		File removedKAMI = new File(Reference.modsFolder.getAbsolutePath() + "/" + Reference.KAMIFilename + EXTENSION);

		for (File f : jars)
		{
			if (f != null && removedTT.exists())
			{
				removedTT.delete();
			}
			if (f != null && removedKAMI.exists())
			{
				removedKAMI.delete();
			}
		}
	}
	*/
}
