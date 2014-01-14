package tppitweaks.creativeTab;

import net.minecraft.creativetab.CreativeTabs;
import tppitweaks.item.ModItems;
import tppitweaks.lib.Reference;

public class CreativeTabTPPI extends CreativeTabs
{
	public CreativeTabTPPI(int id)
	{
		super(id, Reference.TAB_NAME);
	}

	@Override
	public int getTabIconItemIndex()
	{
		return ModItems.tppiBook.itemID;
	}

	@Override
	public String getTabLabel()
	{
		return Reference.TAB_NAME;
	}
}
