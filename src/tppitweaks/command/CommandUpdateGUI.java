package tppitweaks.command;

import java.util.HashSet;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ChatMessageComponent;
import tppitweaks.client.gui.GuiHelper;
import tppitweaks.lib.Reference;

public class CommandUpdateGUI extends CommandBase
{

	private static HashSet<String> validCommands = new HashSet<String>();

	public static void initValidCommandArguments()
	{
		validCommands.add("download");
	}

	private boolean isValidArgument(String s)
	{
		return validCommands.contains(s);
	}

	@Override
	public String getCommandName()
	{
		return "tppi";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "tppi <arg>";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		
		System.out.println(astring[0] + " " + icommandsender.getCommandSenderName() + " " + icommandsender.getEntityWorld().getPlayerEntityByName(icommandsender.getCommandSenderName()) );

		try
		{
			if (true)
			{
				Packet250CustomPayload packet = new Packet250CustomPayload();

				packet.channel = Reference.CHANNEL;

				byte[] bytes = new byte[2];
				bytes[0] = 0;
				boolean showGui  = icommandsender.getEntityWorld().getPlayerEntityByName(icommandsender.getCommandSenderName()) != null;

				if (showGui)
				{
					packet.length = 1;
					packet.data = bytes;
					PacketDispatcher.sendPacketToPlayer(packet, (Player) icommandsender.getEntityWorld().getPlayerEntityByName(icommandsender.getCommandSenderName()));
				}
				else
					System.err.println("Invalid Player");
			}
		}
		catch (Throwable t)
		{
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("You cannot use this command on a server."));
		}

	}
}