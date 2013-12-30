package tppitweaks.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatMessageComponent;
import tppitweaks.TPPITweaks;
import tppitweaks.client.gui.UpdateGui;

public class CommandUpdateGUI extends CommandBase
{

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
		if (astring.length == 0)
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("Invalid Argument"));
		else if (astring[0].equals("download"))
			Minecraft.getMinecraft().displayGuiScreen(new UpdateGui(Minecraft.getMinecraft().currentScreen, TPPITweaks.getModFlags()));
		else
			return;
	}

}
