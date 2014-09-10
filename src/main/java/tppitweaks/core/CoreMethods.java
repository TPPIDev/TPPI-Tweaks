package tppitweaks.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		return null; // screw it
	}

	public static int getFurnaceBurnTime(ItemStack item)
	{
		if (item == null)
		{
			return 0;
		}
		if (item.itemID == Item.bucketLava.itemID)
		{
			return 0;
		}
		return TileEntityFurnace.getItemBurnTime(item);
	}
}
