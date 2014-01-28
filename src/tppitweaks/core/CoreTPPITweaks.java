package tppitweaks.core;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import tppitweaks.TPPITweaks;
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
		
		Logger coreLogger = Logger.getLogger("TPPITweaksCore"); 
		
		File mcDir = (File) data.get("mcLocation");
		File modsDir = null;
		
		try
		{
			modsDir = new File(mcDir.getCanonicalPath() + "/mods/");
		}
		catch (IOException e)
		{
			TPPITweaks.logger.log(Level.SEVERE, "Mods dir does not exist. How did you mess that up?");
		}

		Reference.modsFolder = modsDir;
		
		Reference.thaumcraftFilename = (String) FileLoader.manuallyGetConfigValue(data, "Thaumcraft_filename", "");
		Reference.TTFilename = (String) FileLoader.manuallyGetConfigValue(data, "ThaumicTinkerer_filename", "");
		Reference.TTFilename = (String) FileLoader.manuallyGetConfigValue(data, "KAMI_filename", "");
		
		FileLoader.removeDuplicateMods();
				
		if ((Boolean) FileLoader.manuallyGetConfigValue(data, "autoEnableTT", new Boolean(true))) {
			
			File thaumcraft = new File(modsDir, Reference.thaumcraftFilename);

			FileLoader.getThaumicTinkererFilenameState();
			
			if (!thaumcraft.exists()) {
				coreLogger.log(Level.INFO, "TPPITweaks failed to locate Thaumcraft. Disabling Thaumic Tinkerer.");
				FileLoader.disableTT();
				coreLogger.log(Level.INFO, "Thaumic Tinkerer Disabled.");
			}else {
				coreLogger.log(Level.INFO, "TPPITweaks found Thaumcraft! Enabling Thaumic Tinkerer and addons if needed.");
				FileLoader.enableTT();
			}
		}
	}
}
