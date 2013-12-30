package tppitweaks.command;

import java.util.HashSet;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatMessageComponent;
import tppitweaks.client.gui.GuiHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CommandUpdateGUI extends CommandBase
{

	private static HashSet<String> validCommands = new HashSet<String>();
	
	public static void initValidCommandArguments() {
		validCommands.add("download");
	}
	
	private boolean isValidArgument(String s) {
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
		
		if (astring.length <= 0 || !isValidArgument(astring[0])) {
			
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("Invalid Argument"));
			
		}else if (astring[0].equals("download")) {
			
			GuiHelper.doDownloaderGUI(icommandsender.getCommandSenderName(), icommandsender.getEntityWorld());
			
		}
		else System.out.println("fail");
		
	}

}