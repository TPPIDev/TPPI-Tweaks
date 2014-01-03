package tppitweaks.item;

import tppitweaks.config.ConfigurationHandler;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TPPIMaterial extends Item {

	public TPPIMaterial(int par1) {
		super(par1);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("tppimaterial");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("tppitweaks:tppiProcessor");
	}
	
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return "ME Multi-Core Processor";
	}

}
