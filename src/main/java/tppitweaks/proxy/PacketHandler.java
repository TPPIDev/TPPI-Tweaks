package tppitweaks.proxy;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import tppitweaks.client.gui.GuiHelper;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		switch(packet.data[0])
		{
		case 0:
			GuiHelper.doDownloaderGUI();
		}
	}
}
