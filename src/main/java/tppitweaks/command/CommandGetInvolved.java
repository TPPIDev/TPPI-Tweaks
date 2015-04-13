package tppitweaks.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

public class CommandGetInvolved extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "getInvolved";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/getInvolved";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		CommandOres.processVanillaBookCommand("Getting Involved In TPPI", "GetInvolved.txt", icommandsender, astring);
	}

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

	@Override
	public int compareTo(Object o)
	{
		return this.getCommandName().compareTo(((ICommand)o).getCommandName());
	}
}
