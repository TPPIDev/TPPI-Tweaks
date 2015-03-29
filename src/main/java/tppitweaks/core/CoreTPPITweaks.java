package tppitweaks.core;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import tppitweaks.lib.Reference;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class CoreTPPITweaks implements IFMLLoadingPlugin
{
	@Override
	public String[] getASMTransformerClass()
	{
		return null; // FIXME ASM return new String[]{"tppitweaks.core.CoreTransformer"};
	}

	@Override
	public String getModContainerClass()
	{
		return null;
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
		System.out.println("TPPITweaks coremod, rise from your grave, we require you once more.");

		File mcDir = (File) data.get("mcLocation");
		File modsDir = null;

		try
		{
			modsDir = new File(mcDir.getCanonicalPath() + "/mods/");
		}
		catch (IOException e)
		{
			System.err.println("Mods dir does not exist. How did you mess that up?");
		}

		Reference.modsFolder = modsDir;
		
		/*
		Reference.thaumcraftFilename = FileLoader.manuallyGetConfigValue(data, "Thaumcraft_filename");
		//Reference.TTFilename = FileLoader.manuallyGetConfigValue(data, "ThaumicTinkerer_filename");
		Reference.KAMIFilename = FileLoader.manuallyGetConfigValue(data, "KAMI_filename");

		//FileLoader.removeDuplicateMods();

		if (FileLoader.manuallyGetConfigValue(data, "autoEnableTT").equals("true"))
		{
			try
			{
				modsDir = new File(mcDir.getCanonicalPath() + "/mods/");
			}
			catch (IOException e)
			{
				System.out.println("Mods dir does not exist. How did you mess that up?");
			}

			Reference.modsFolder = modsDir;

			File thaumcraft = new File(modsDir, Reference.thaumcraftFilename);

			FileLoader.getThaumicTinkererFilenameState();

			if (!thaumcraft.exists())
			{
				System.out.println("TPPITweaks failed to locate Thaumcraft. Disabling Thaumic Tinkerer.");
				FileLoader.disableMod();
				System.out.println("Thaumic Tinkerer Disabled.");
			}
			else
			{
				System.out.println("TPPITweaks found Thaumcraft! Enabling Thaumic Tinkerer and addons if needed.");
				FileLoader.enableTT();
			}
		}
		*/
	}

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }
}
