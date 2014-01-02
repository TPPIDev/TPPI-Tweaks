package tppitweaks.command;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ChatMessageComponent;
import tppitweaks.lib.Reference;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class CommandTPPI extends CommandBase
{

	private static HashSet<String> validCommands = new HashSet<String>();
	
	/** First index is list, rest are mod names **/
	private String[] supportedModsAndList = {"list", "TPPITweaks"};

	public static void initValidCommandArguments()
	{
		validCommands.add("download");
		validCommands.add("mods");
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
	
	@SuppressWarnings("rawtypes")
	@Override
	public List addTabCompletionOptions(ICommandSender command, String[] par2ArrayOfStr)
    {			
		if (par2ArrayOfStr.length == 1)
		{
			return getListOfStringsMatchingLastWord(par2ArrayOfStr, (String[]) validCommands.toArray(new String[validCommands.size()]));
		}
		else if (par2ArrayOfStr.length == 2)
		{
			System.out.println(par2ArrayOfStr[1]);
			if (par2ArrayOfStr[0].equals("mods"))
				return getListOfStringsMatchingLastWord(par2ArrayOfStr, supportedModsAndList);
			else return null;
		}
		else return null;
    }

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if (astring.length > 0 && isValidArgument(astring[0]))
		{
			if (astring[0].equals("download"))
			{
				if (!processCommandDownload(icommandsender, astring))
					System.err.println("Invalid Player");
			}
			else if (astring[0].equals("mods"))
			{
				processCommandMods(icommandsender, astring);	
			}
			
		}
		else if (astring.length <= 0)
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("Proper Usage: /tppi <arg>"));
		else
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("Invalid Argument"));
	}

	private boolean processCommandMods(ICommandSender command, String[] args)
	{
		if (args.length == 2)
		{
			if (args[1].equals("list"))
			{
				listMods(command);
				return true;
			}
			else if (ArrayUtils.contains(supportedModsAndList, args[1]))
				displayModInfo(args[1], command);
			else
				command.sendChatToPlayer(new ChatMessageComponent().addText("Invalid Argument After: mods"));
		}
		else
			command.sendChatToPlayer(new ChatMessageComponent().addText("Proper Usage: /tppi mods <arg>"));
		
		return false;
	}


	private boolean processCommandDownload(ICommandSender command, String[] args)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();

		packet.channel = Reference.CHANNEL;

		byte[] bytes = { (byte) 0 };
		boolean showGui = command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()) != null;

		if (showGui)
		{
			packet.length = 1;
			packet.data = bytes;
			PacketDispatcher.sendPacketToPlayer(packet, (Player) command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()));
			return true;
		}
		
		return false;
	}
	
	private void listMods(ICommandSender icommandsender)
	{
		String s = "";
		
		for (int i = 1; i < supportedModsAndList.length; i++)
		{
			s += supportedModsAndList[i];
			if (i < supportedModsAndList.length - 1)
				s += ", ";
		}
		
		icommandsender.sendChatToPlayer(new ChatMessageComponent().addText(s));		
	}
	
	private void displayModInfo(String modName, ICommandSender command)
	{
		// TODO Display mod info
	}
	
	
}