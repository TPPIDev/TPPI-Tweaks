package tppitweaks.command;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatMessageComponent;
import tppitweaks.TPPITweaks;
import tppitweaks.util.TxtParser;

public class CommandTPPI extends CommandBase
{
	private static HashMap<String, String> modProperNames = new HashMap<String, String>();
	private static HashSet<String> validCommands = new HashSet<String>();

	/** First index is list, rest are mod names **/
	private static ArrayList<String> supportedModsAndList = new ArrayList<String>();

	public static void initValidCommandArguments()
	{
		validCommands.add("ores");
		validCommands.add("getInvolved");
	}

	public static void addProperNameMapping(String argName, String properName)
	{
		modProperNames.put(argName, properName);
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
	public boolean canCommandSenderUseCommand(ICommandSender par1iCommandSender)
	{
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List addTabCompletionOptions(ICommandSender command, String[] par2ArrayOfStr)
	{
		if (par2ArrayOfStr.length == 1)
		{
			return getListOfStringsMatchingLastWord(par2ArrayOfStr, validCommands.toArray(new String[validCommands.size()]));
		}
		else if (par2ArrayOfStr.length == 2)
		{
			if (par2ArrayOfStr[0].equals("mods"))
				return getListOfStringsMatchingLastWord(par2ArrayOfStr, supportedModsAndList.toArray(new String[supportedModsAndList.size()]));
			else
				return null;
		}
		else
			return null;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if (astring.length > 0 && isValidArgument(astring[0]))
		{
			if (astring[0].equalsIgnoreCase("ores"))
			{
				processVanillaBookCommand("TPPI Ore Generation Guide", "OreGen.txt", icommandsender, astring);
			}
			else if (astring[0].equalsIgnoreCase("getInvolved"))
			{
				processVanillaBookCommand("Getting Involved In TPPI", "GetInvolved.txt", icommandsender, astring);
			}
		}
		else
		{
			String validCommandString = "";
			Iterator<String> it = validCommands.iterator();
			for (int i = 0; i < validCommands.size(); i++)
			{
				validCommandString += it.next();
				if (i < validCommands.size() - 1)
					validCommandString += ", ";
			}
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("Proper Usage: /tppi <arg>"));
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("Valid args:"));
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText(validCommandString));
		}

	}

	private void processVanillaBookCommand(String title, String textFileName, ICommandSender command, String[] astring)
	{
		InputStream file = TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/" + textFileName);
		List<String> vanillaBookText = file == null ? new ArrayList<String>() : TxtParser.parseFileMain(file);
		ItemStack book = new ItemStack(Item.writtenBook);

		book.setTagInfo("author", new NBTTagString("author", "The TPPI Team"));
		book.setTagInfo("title", new NBTTagString("title", title));

		NBTTagCompound nbttagcompound = book.getTagCompound();
		NBTTagList bookPages = new NBTTagList("pages");

		for (int i = 0; i < vanillaBookText.size(); i++)
		{
			bookPages.appendTag(new NBTTagString("" + i, vanillaBookText.get(i)));
		}

		nbttagcompound.setTag("pages", bookPages);
		nbttagcompound.setString("version", TPPITweaks.VERSION);

		if (!command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()).inventory.addItemStackToInventory(book))
			command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()).entityDropItem(book, 0);

	}
	
	@Override
    public int compareTo(Object o) {
        if (o instanceof ICommand) {
            return this.compareTo((ICommand) o);
        } else {
            return 0;
        }
    }
}
