package tppitweaks.command;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ChatMessageComponent;
import tppitweaks.TPPITweaks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tppitweaks.lib.Reference;
import tppitweaks.util.FileLoader;
import tppitweaks.util.TxtParser;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class CommandTPPI extends CommandBase
{

	private static HashMap<String, String> modProperNames = new HashMap<String, String>();
	private static HashSet<String> validCommands = new HashSet<String>();

	/** First index is list, rest are mod names **/
	private static ArrayList<String> supportedModsAndList = new ArrayList<String>();

	public static void initValidCommandArguments(InputStream file)
	{
		validCommands.add("download");
		validCommands.add("mods");
		validCommands.add("ores");
		validCommands.add("getInvolved");
		validCommands.add("changelog");

		supportedModsAndList.add("list");

		supportedModsAndList.addAll(TxtParser.getSupportedMods(file));
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
			if (astring[0].equalsIgnoreCase("download"))
			{
				if (!processCommandDownload(icommandsender, astring))
					TPPITweaks.logger.log(Level.SEVERE, "Invalid Player");
			}
			else if (astring[0].equalsIgnoreCase("mods"))
			{
				processCommandMods(icommandsender, astring);
			}else if(astring[0].equalsIgnoreCase("ores")) {
				processVanillaBookCommand("TPPI Ore Generation Guide", "OreGen.txt", icommandsender, astring);
			}else if(astring[0].equalsIgnoreCase("getInvolved")) {
				processVanillaBookCommand("Getting Involved In TPPI", "GetInvolved.txt", icommandsender, astring);
			}else if(astring[0].equalsIgnoreCase("changelog")) {
				processCommandChangelog(icommandsender);
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

	private void processVanillaBookCommand(String title, String textFileName, ICommandSender command, String[] astring) {
		
		InputStream file = TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/"+textFileName);
		List<String> vanillaBookText = file == null ? new ArrayList<String>() : TxtParser.parseFileMain(file);
		ItemStack book = new ItemStack(Item.writtenBook);
		
		book.setTagInfo("author", new NBTTagString("author", ConfigurationHandler.bookAuthor));
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

	private boolean processCommandMods(ICommandSender command, String[] args)
	{
		if (args.length == 2)
		{
			if (args[1].equals("list"))
			{
				listMods(command);
				return true;
			}
			else if (supportedModsAndList.contains(args[1]))
			{
				giveModBook(args[1], command);
			}
			else
			{
				command.sendChatToPlayer(new ChatMessageComponent().addText("Valid mod names:"));
				listMods(command);
			}

		}
		else
		{
			command.sendChatToPlayer(new ChatMessageComponent().addText("Proper Usage: /tppi mods <modname>"));
			command.sendChatToPlayer(new ChatMessageComponent().addText("or /tppi mods list to see valid names."));
		}

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
	
	private boolean processCommandChangelog(ICommandSender command)
	{
		ItemStack changelog = ModItems.tppiBook.getChangelog();
		
		if (changelog == null)
			return false;
		
		if (!command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()).inventory.addItemStackToInventory(changelog));
			command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()).entityDropItem(changelog, 0);
			
		return true;
	}

	private void listMods(ICommandSender icommandsender)
	{
		String s = "";
		String total = "";
		icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("Listing mods:\n"));
		for (int i = 1; i < supportedModsAndList.size(); i++)
		{
			s += supportedModsAndList.get(i);
			if (i < supportedModsAndList.size() - 1)
				s += ", ";
			if (s.length() > 40)
			{
				total += s + "\n";
				s = "";
			}
		}

		icommandsender.sendChatToPlayer(new ChatMessageComponent().addText(total));
	}

	private void giveModBook(String modName, ICommandSender command)
	{
		String properName = modProperNames.get(modName);

		ItemStack stack = new ItemStack(Item.writtenBook);

		stack.setTagInfo("author", new NBTTagString("author", ConfigurationHandler.bookAuthor));
		stack.setTagInfo("title", new NBTTagString("title", "Guide To " + properName));

		NBTTagCompound nbttagcompound = stack.getTagCompound();
		NBTTagList bookPages = new NBTTagList("pages");

		ArrayList<String> pages;
		//try
		//{
			pages = TxtParser.parseFileMods(FileLoader.getSupportedMods(), modName + ", " + properName);
		/*}
		catch (FileNotFoundException e)
		{
			pages = new ArrayList<String>();
			e.printStackTrace();
		}*/

		if (pages.get(0).startsWith("<") && pages.get(0).endsWith("> "))
		{
			command.sendChatToPlayer(new ChatMessageComponent().addText(pages.get(0).substring(1, pages.get(0).length() - 2)));
			return;
		}

		for (int i = 0; i < pages.size(); i++)
		{
			bookPages.appendTag(new NBTTagString("" + i, pages.get(i)));
		}

		nbttagcompound.setTag("pages", bookPages);

		if (!command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()).inventory.addItemStackToInventory(stack))
			command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()).entityDropItem(stack, 0);
	}

}
