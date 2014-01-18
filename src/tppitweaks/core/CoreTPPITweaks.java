package tppitweaks.core;

import java.io.File;
import java.util.Map;

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
		if ((Boolean) FileLoader.manuallyGetConfigValue(data, "autoEnableTT", new Boolean(true))) {

			File mcDir = (File) data.get("mcLocation");
			File modsDir = new File(mcDir.getParentFile(), "mods/");

			Reference.modsFolder = modsDir;
			
			File thaumcraft = new File(modsDir, Reference.THAUMCRAFT_FILENAME);

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
