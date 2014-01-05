package tppitweaks.util;

import net.minecraft.entity.player.EntityPlayer;
import tppitweaks.TPPITweaks;
import tppitweaks.event.TPPIEventHandler;
import cpw.mods.fml.common.IPlayerTracker;

public class TPPIPlayerTracker implements IPlayerTracker
{

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		// Do nothing
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
		}
	}

}
