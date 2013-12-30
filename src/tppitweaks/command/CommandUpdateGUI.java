package tppitweaks.command;

import java.util.HashSet;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatMessageComponent;
import tppitweaks.client.gui.GuiHelper;

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

		try{
			if (astring.length <= 0 || !isValidArgument(astring[0]) || !Minecraft.getMinecraft().isSingleplayer())
			{

				icommandsender.sendChatToPlayer(new ChatMessageComponent().addText(Minecraft.getMinecraft().isSingleplayer() ? "Invalid Argument" : 
					"You cannot use this command on a server."));

			}
			else if (astring[0].equals("download"))
			{

				GuiHelper.doDownloaderGUI();
				
			}
		}catch(Throwable t) {
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("You cannot use this command on a server."));
		}
		
	}
}