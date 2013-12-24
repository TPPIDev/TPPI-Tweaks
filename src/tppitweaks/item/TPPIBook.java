package tppitweaks.item;

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
		this.itemIcon = par1IconRegister.registerIcon("tppitweaks:tppiBook");
	}

	public static void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(getBook(), Item.book, Item.ingotIron);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		System.out.println("right clicked");
		Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBook(par3EntityPlayer, par1ItemStack, false));
		return par1ItemStack;
	}

	@Override
	public void onUpdate(ItemStack tppiBook, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if (!tppiBook.hasTagCompound())
		{
			tppiBook.setTagInfo("author", new NBTTagString("author", "The TPPI Team"));
			tppiBook.setTagInfo("title", new NBTTagString("title", "TPPI Worldgen Field Guide"));

			NBTTagCompound nbttagcompound = tppiBook.getTagCompound();
			NBTTagList bookPages = new NBTTagList("pages");
<<<<<<< HEAD
			bookPages.appendTag(new NBTTagString("1", ConfigurationHandler.bookText));
			bookPages.appendTag(new NBTTagString("2", "Two!"));
			bookPages.appendTag(new NBTTagString("3", "Hi tterrag."));
=======

			// Judge put your pages in here
			bookPages.appendTag(new NBTTagString("1", "Page about ore stuff 1"));
			bookPages.appendTag(new NBTTagString("2", "Page about ore stuff 2"));
			bookPages.appendTag(new NBTTagString("3", "etc etc"));
>>>>>>> bb10f626df9233964527b51eb9bc2e09f3097d33
			nbttagcompound.setTag("pages", bookPages);
		}
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return "TPPI Worldgen Field Guide";
	}

	public static ItemStack getBook()
	{
		ItemStack tppiBook = new ItemStack(ModItem.tppiBook);
		return tppiBook;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass)
	{
		return false;
	}

}