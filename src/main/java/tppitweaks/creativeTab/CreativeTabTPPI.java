package tppitweaks.creativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import tppitweaks.item.ModItems;
import tppitweaks.lib.Reference;

public class CreativeTabTPPI extends CreativeTabs
{
	public CreativeTabTPPI(int id)
	{
		super(id, Reference.TAB_NAME);
	}
	
	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(ModItems.tppiMaterial, 1, 1);
	}
	
	@Override
	public String getTabLabel() {
		return Reference.TAB_NAME;
	}
}
