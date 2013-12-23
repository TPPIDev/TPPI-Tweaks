package tppitweaks;

import net.minecraftforge.common.MinecraftForge;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.event.BookEventHandler;
import tppitweaks.item.SpecialRecipes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="TPPITweaks", name="TPPI Tweaks", version="1", dependencies="after:arsmagica2")
@NetworkMod(channels={"tppitweaks"}, clientSideRequired=true, serverSideRequired=false)
public class TPPITweaks {
 
	@Instance("TPPITweaks")
	public static TPPITweaks instance;
 
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
		SpecialRecipes.registerSpecialRecipes();
		
		MinecraftForge.EVENT_BUS.register(new BookEventHandler());
	}
 
	@EventHandler
	public void init(FMLInitializationEvent event) {
		AM2SpawnControls.doAM2SpawnControls();
		new TPPIBook().registerRecipe();
	}
 
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}