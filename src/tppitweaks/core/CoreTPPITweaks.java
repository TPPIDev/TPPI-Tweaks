package tppitweaks.core;

import java.io.File;
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
		if ((Boolean) FileLoader.manuallyGetConfigValue(data, "autoEnableTT", new Boolean(true))) {

			File modsDir = (File) data.get("mcLocation");

			File thaumcraft = new File(modsDir.getAbsolutePath() + "/"
					+ Reference.THAUMCRAFT_FILENAME);

			if (thaumcraft.exists()) {
				System.out.println("Disabling TT.");
				FileLoader.disableTT();
				System.out.println("TT Disabled.");
			} else {
				FileLoader.enableTT();
			}
		}
	}

}
