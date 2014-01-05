package tppitweaks.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tppitweaks.TPPITweaks;
import tppitweaks.event.TPPIEventHandler;
import tppitweaks.item.ModItems;
import cpw.mods.fml.common.IPlayerTracker;

public class TPPIPlayerTracker implements IPlayerTracker
{

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		if (!player.getEntityData().getCompoundTag("TPPI").getBoolean("hasBook"))
		{
			player.getEntityData().setTag("TPPI", TPPITweaks.eventHandler.getTag(player, false));
			
			ItemStack stack = ModItems.tppiBook.getBook();
			player.inventory.addItemStackToInventory(stack);
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		// Do Nothing
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{
		// Do Nothing
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
		System.out.println("adding NBT: " + TPPIEventHandler.NBTValOnDeath);
		player.getEntityData().setTag("TPPI", TPPITweaks.eventHandler.getTag(player, true));
		
		if (player != null && !player.getEntityData().getCompoundTag("TPPI").getBoolean("hasBook"))
		{
			System.out.println("adding book");

			player.getEntityData().setTag("TPPI", TPPITweaks.eventHandler.getTag(player, false));
			
			ItemStack stack = ModItems.tppiBook.getBook();
			player.inventory.addItemStackToInventory(stack);
		}
	}

}
