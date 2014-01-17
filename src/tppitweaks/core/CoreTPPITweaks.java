package tppitweaks.core;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import net.minecraft.client.Minecraft;
import tppitweaks.lib.Reference;
import tppitweaks.util.FileLoader;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class CoreTPPITweaks implements IFMLLoadingPlugin{

	public CoreTPPITweaks()
	{
		
		try {
			//Likely incorrect - a placeholder location.
			Reference.modsFolder = new File(CoreTPPITweaks.class.getResource("").toURI().getRawPath()).getParentFile().getParentFile();
			System.out.println(Reference.modsFolder.getAbsolutePath()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		} catch (Throwable e) { //asdf
			e.printStackTrace();
			System.exit(-1);
		}
		
		FileLoader.getThaumicTinkererFilenameState();
		
		if (new File(Reference.modsFolder, Reference.THAUMCRAFT_FILENAME).exists()) {
			System.out.println("FOUND THAUMCRAAAAAAAAAAAAAAAAAAAAAAAAAAAAFT");
			
			FileLoader.enableTT();
			
		}else {
			
			FileLoader.disableTT();
			
		}
	}
	
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
		
	}

}
