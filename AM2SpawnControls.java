package tppitweaks;

import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.event.FMLInterModComms;

public class AM2SpawnControls {

	static void doAM2SpawnControls() {
		for(int i = -50; i <= 100; i++) {
			FMLInterModComms.sendMessage("arsmagica2", "dsb", "EntityDryad|"+i);
		}
	}
	
}
