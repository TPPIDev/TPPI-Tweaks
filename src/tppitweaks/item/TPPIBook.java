package tppitweaks.item;

import tppitweaks.TPPITweaks;
import tppitweaks.config.ConfigurationHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class TPPIBook extends ItemEditableBook
{

	public TPPIBook(int par1)
	{
		super(par1);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("tppibook");
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("tppitweaks:tppibook");
	}

	public static void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(getBook(), Item.book, Item.ingotIron);
		GameRegistry.addShapelessRecipe(getBook(), getBook().getItem());
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (!stack.getTagCompound().getString("version").equals(TPPITweaks.VERSION))
		{
			if (!world.isRemote)
			{
				player.inventory.consumeInventoryItem(this.itemID);

				ItemStack book = TPPIBook.getBook();
				book.setTagCompound(new NBTTagCompound("version"));
				book.getTagCompound().setString("version", TPPITweaks.VERSION);
				//player.inventory.addItemStackToInventory(book);
				
				Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBook(player, book, false));
				return book;
			}
		}
		else if (!world.isRemote)
			Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBook(player, stack, false));
		return stack;
	}

	@Override
	public void onUpdate(ItemStack tppiBook, World world, Entity par3Entity, int par4, boolean par5)
	{
		super.onUpdate(tppiBook, world, par3Entity, par4, par5);
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return ConfigurationHandler.bookTitle;
	}

	public static ItemStack getBook()
	{
		ItemStack tppiBook = new ItemStack(ModItem.tppiBook);
		
		tppiBook.setTagInfo("author", new NBTTagString("author", ConfigurationHandler.bookAuthor));
		tppiBook.setTagInfo("title", new NBTTagString("title", ConfigurationHandler.bookTitle));

		NBTTagCompound nbttagcompound = tppiBook.getTagCompound();
		NBTTagList bookPages = new NBTTagList("pages");

		for (int i = 0; i < ConfigurationHandler.bookText.size(); i++)
		{
			bookPages.appendTag(new NBTTagString("" + i, ConfigurationHandler.bookText.get(i)));
		}

		nbttagcompound.setTag("pages", bookPages);
		
		return tppiBook;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass)
	{
		return false;
	}

}