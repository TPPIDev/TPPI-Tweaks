package tppitweaks.proxy;

import tppitweaks.util.TPPITickHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	public static TPPITickHandler tickhandler = new TPPITickHandler();

	public void initTickHandler()
	{
		TickRegistry.registerTickHandler(tickhandler, Side.CLIENT);
	}
}
