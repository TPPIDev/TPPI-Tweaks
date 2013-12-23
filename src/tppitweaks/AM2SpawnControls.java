package tppitweaks;

import cpw.mods.fml.common.event.FMLInterModComms;
import tppitweaks.config.ConfigurationHandler;

public class AM2SpawnControls {

	static void doAM2SpawnControls() {
		for(String s : ConfigurationHandler.am2MobKeys) {
			if(!ConfigurationHandler.am2SpawnControls.get(s)) {
				//Blanket dimensional spawning bans
				for(int i = -50; i <= 100; i++) {
					FMLInterModComms.sendMessage("arsmagica2", "dsb", s+"|"+i);
				}
			}else {
				//More precise spawning bans
			}
		}
	}
	
}