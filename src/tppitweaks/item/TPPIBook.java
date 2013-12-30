package tppitweaks.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import tppitweaks.TPPITweaks;
import tppitweaks.client.gui.GuiHelper;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

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

	public void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(getBook(), Item.ingotIron, Item.paper, Item.paper, Item.paper);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (stack.stackTagCompound == null || !stack.getTagCompound().getString("version").equals(TPPITweaks.VERSION))
		{
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			{
				stack.setTagCompound(new NBTTagCompound());
				addTextToBook(stack);
				
				player.inventoryContainer.detectAndSendChanges();
				
				GuiHelper.doBookGUI(player, stack, false);
				return stack;
			}
		}
		else if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			GuiHelper.doBookGUI(player, stack, false);
		return stack;
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return ConfigurationHandler.bookTitle;
	}

	public ItemStack addTextToBook(ItemStack book) {
		
		book.setTagInfo("author", new NBTTagString("author", ConfigurationHandler.bookAuthor));
		book.setTagInfo("title", new NBTTagString("title", ConfigurationHandler.bookTitle));

		NBTTagCompound nbttagcompound = book.getTagCompound();
		NBTTagList bookPages = new NBTTagList("pages");

		for (int i = 0; i < ConfigurationHandler.bookText.size(); i++)
		{
			bookPages.appendTag(new NBTTagString("" + i, ConfigurationHandler.bookText.get(i)));
		}

		nbttagcompound.setTag("pages", bookPages);
		nbttagcompound.setString("version", TPPITweaks.VERSION);
		
		return book;
		
	}
	
	public ItemStack getBook()
	{
		return addTextToBook(new ItemStack(ModItems.tppiBook));
	}
	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		return false;
	}

}