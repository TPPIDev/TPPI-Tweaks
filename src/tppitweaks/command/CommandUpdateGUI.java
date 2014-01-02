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
		if (astring.length > 0 && isValidArgument(astring[0]))
		{
			if (astring[0].equals("download"))
			{
				Packet250CustomPayload packet = new Packet250CustomPayload();

				packet.channel = Reference.CHANNEL;

				byte[] bytes = { (byte) 0 };
				boolean showGui = icommandsender.getEntityWorld().getPlayerEntityByName(icommandsender.getCommandSenderName()) != null;

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
		else if (astring.length <= 0)
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("Proper Usage: /tppi <arg>"));
		else
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("Invalid Argument"));
	}
}