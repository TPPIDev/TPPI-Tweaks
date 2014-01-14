package tppitweaks;

import cpw.mods.fml.common.event.FMLInterModComms;
import tppitweaks.config.ConfigurationHandler;

public class AM2SpawnControls {

	static void doAM2SpawnControls() {
		System.out.println("TPPI TWEAKS IS ABOUT TO DO A THING. PAY NO ATTENTION TO THE ARS MAGICA 2 CONSOLE SPAM - IT MEANS STUFF IS WORKING.");
		for(String s : ConfigurationHandler.am2MobKeys) {
			if(ConfigurationHandler.am2SpawnControls.get(s)) {
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