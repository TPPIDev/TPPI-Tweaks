package tppitweaks.core;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import tppitweaks.lib.Reference;
import tppitweaks.util.FileLoader;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class CoreTPPITweaks implements IFMLLoadingPlugin {

	@Override
	@Deprecated
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		Reference.thaumcraftFilename = (String) FileLoader.manuallyGetConfigValue(data, "Thaumcraft_filename", "");
		Reference.TTFilename = (String) FileLoader.manuallyGetConfigValue(data, "ThaumicTinkerer_filename", "");
		Reference.TTFilename = (String) FileLoader.manuallyGetConfigValue(data, "KAMI_filename", "");
		
		FileLoader.removeDuplicateMods();
		
		File mcDir = (File) data.get("mcLocation");
		File modsDir = null;
		
		try
		{
			modsDir = new File(mcDir.getCanonicalPath() + "/mods/");
		}
		catch (IOException e)
		{
			System.out.println("Mods dir does not exist. How did you mess that up?");
		}

		Reference.modsFolder = modsDir;
		
		
		if ((Boolean) FileLoader.manuallyGetConfigValue(data, "autoEnableTT", new Boolean(true))) {
			
			File thaumcraft = new File(modsDir, Reference.thaumcraftFilename);

			FileLoader.getThaumicTinkererFilenameState();
			
			if (!thaumcraft.exists()) {
				System.out.println("TPPITweaks failed to locate Thaumcraft. Disabling Thaumic Tinkerer.");
				FileLoader.disableTT();
				System.out.println("Thaumic Tinkerer Disabled.");
			}else {
				System.out.println("TPPITweaks found Thaumcraft! Enabling Thaumic Tinkerer and addons if needed.");
				FileLoader.enableTT();
			}
		}
	}
}
