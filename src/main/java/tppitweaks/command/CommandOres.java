package tppitweaks.command;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import tppitweaks.TPPITweaks;
import tppitweaks.util.TxtParser;

public class CommandOres extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "ores";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/ores";
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
		return null;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		processVanillaBookCommand("TPPI Ore Generation Guide", "OreGen.txt", icommandsender, astring);
	}

	public static void processVanillaBookCommand(String title, String textFileName, ICommandSender command, String[] astring)
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
	public int compareTo(Object o)
	{
		return this.getCommandName().compareTo(((ICommand)o).getCommandName());
	}
}
