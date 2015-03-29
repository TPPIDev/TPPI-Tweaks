package tppitweaks.core;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class CoreMethods
{
	public static int getFuelBurnFurnace(ItemStack item)
	{
		return (int) (getFurnaceBurnTime(item) / 1.5);
	}

	public static int getFuelBurnSurvivalist(ItemStack item)
	{
		return getFurnaceBurnTime(item) / 200;
	}

	public static int getFurnaceBurnTime(ItemStack item)
	{
		if (item == null)
		{
			return 0;
		}
		if (item.getItem() == Items.lava_bucket)
		{
			return 0;
		}
		return TileEntityFurnace.getItemBurnTime(item);
	}
}
