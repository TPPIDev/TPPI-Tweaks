package tppitweaks.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TPPIMaterial extends Item {

	public Icon uncookedIcon;
	
	public TPPIMaterial(int par1) {
		super(par1);
		setCreativeTab(CreativeTabs.tabMisc);
		setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	private String[] unlocNames = {"multicoreProcessor", "multicoreProcessorUncooked", "tppiChunkLoader"};
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons = new Icon[3];
		icons[0] = par1IconRegister.registerIcon("tppitweaks:tppiProcessor");
		icons[1] = par1IconRegister.registerIcon("tppitweaks:tppiProcessorUncooked");
		icons[2] = par1IconRegister.registerIcon("tppitweaks:tppiChunkloader");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return unlocNames[par1ItemStack.getItemDamage()];
	}
	
	@Override
	public Icon getIconFromDamage(int dmg) {
		return icons[dmg];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < 3; i++)
			list.add(new ItemStack(this.itemID, 1, i));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (par1ItemStack.getItemDamage() == 3)
		{
			par3List.add("This is not a real chunkloader.");
			par3List.add("You are probably looking for a");
			par3List.add("Dimensinal Anchor instead.");
		}
	}
}
