package tppitweaks.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;
import cpw.mods.fml.common.registry.GameRegistry;

public class TPPIBook extends ItemEditableBook{
	
	public TPPIBook(int par1) {
		super(par1);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("tppibook");
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("tppitweaks:tppiBook");
	}

	public static void registerRecipes() {
		GameRegistry.addShapelessRecipe(getBook(), Item.book, Item.coal);	
	}
	
	public static ItemStack getBook() {
		ItemStack tppiBook = new ItemStack(ModItem.tppiBook);
		tppiBook.setTagInfo("author", new NBTTagString("author", "The TPPI Team"));
		tppiBook.setTagInfo("title", new NBTTagString("title", "TPPI Worldgen Field Guide"));
		return tppiBook;
	}
	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return false;
	}
	
}