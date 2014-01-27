package tppitweaks;

import java.util.logging.Level;

import cpw.mods.fml.common.event.FMLInterModComms;
import tppitweaks.config.ConfigurationHandler;

public class AM2SpawnControls {

	static void doAM2SpawnControls() {
		TPPITweaks.logger.log(Level.INFO, "Ignore incoming console spam. TPPITweaks is attempting to disable mobs from AM2 and it is not functioning. #BlameMithion");
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