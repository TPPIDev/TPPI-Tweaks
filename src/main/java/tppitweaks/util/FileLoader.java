package tppitweaks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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

	public static boolean disableMod(String partOfName, String extension)
	{
		boolean hasChanged = false;
		for (File f : getMod(partOfName))
		{
			TPPITweaks.logger.info("Disabling: " + f.getName());
			if (!f.getName().contains(extension))
			{
				f.renameTo(new File(f.getAbsolutePath() + extension));
				System.out.println(f.getAbsolutePath() + "   " + extension);
				hasChanged = true;
			}
			else
			{
				TPPITweaks.logger.info(partOfName + " was already disabled!");
			}
		}
		return hasChanged;
	}

	public static boolean enableMod(String partOfName, String extensionToRemove)
	{
		boolean hasChanged = false;
		for (File f : getMod(partOfName))
		{
			TPPITweaks.logger.info("Enabling: " + f.getName());
			if (f.getName().contains(extensionToRemove))
			{
				f.renameTo(new File(f.getAbsolutePath().replace(extensionToRemove, "")));
				hasChanged = true;
			}
			else
			{
				TPPITweaks.logger.info(partOfName + " was already enabled!");
			}
		}
		return hasChanged;
	}

	public static ArrayList<File> getMod(String partOfName)
	{
		ArrayList<File> files = new ArrayList<File>();
		File mods;

		mods = Reference.modsFolder;
		
		String[] fileNames = mods.list();
		ArrayList<String> foundNames = new ArrayList<String>();
		
		for (String s : fileNames)
		{
			if (s.toLowerCase().contains(partOfName.toLowerCase()))
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

	public static InputStream getGuideText() 
	{
		bookText = loadFile(new File(ConfigurationHandler.cfg.getParent() + "/BookText.txt"));
		return bookText;
	}

	public static InputStream getChangelogText()
	{
		changelogText = loadFile(new File(ConfigurationHandler.cfg.getParent() + "/changelog.txt"));
		return changelogText;
	}

	public static InputStream getSupportedModsFile()
	{
		supportedMods = loadFile(new File(ConfigurationHandler.cfg.getParent() + "/" + ConfigurationHandler.supportedModsName + ".txt"));
		return supportedMods;
	}
	
	private static InputStream loadFile(File file)
	{
		if (!file.exists())
		{
			FileWriter fw;
			try
			{
				file.createNewFile();
				fw = new FileWriter(file);
				fw.write("Default file, please make sure the correct file, " + file.getName() + ", exists in the TPPI config directory before launching next time!");
				fw.flush();
				fw.close();
			} catch (IOException e1) {
				TPPITweaks.logger.severe("Could not create default file" + file.getName() + "!");
				e1.printStackTrace();
			}
		}

		try
		{
			return new FileInputStream(file);
		}
		catch (FileNotFoundException e)
		{
			IOErr(file.getName(), e);
			return null;
		}
	}
	
	private static void IOErr(String filename, IOException e)
	{
		TPPITweaks.logger.severe("IO error while loading TPPITweaks, make sure nothing in the config folder is actively open and Minecraft has permission to read those files!");
		e.printStackTrace();
		throw new RuntimeException("IO Error in TPPITweaks file loading, file: " + filename);
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
