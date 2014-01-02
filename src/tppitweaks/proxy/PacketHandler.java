package tppitweaks.proxy;

import tppitweaks.client.gui.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		GuiScreen gui = Minecraft.getMinecraft().currentScreen;
		
		switch(packet.data[0])
		{
		case 0:
			GuiHelper.doDownloaderGUI();
		}
	}
}
