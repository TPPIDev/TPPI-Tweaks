package tppitweaks.util;

import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tppitweaks.TPPITweaks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.event.TPPIEventHandler;
import tppitweaks.item.ModItems;
import cpw.mods.fml.common.IPlayerTracker;

public class TPPIPlayerTracker implements IPlayerTracker
{

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		addBook(player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		// Do Nothing
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{
		TPPIEventHandler.NBTValOnDeath = player.getEntityData().getCompoundTag("TPPI").getBoolean("hasBook");
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
		TPPITweaks.logger.log(Level.INFO, "adding NBT: " + TPPIEventHandler.NBTValOnDeath);
		player.getEntityData().setTag("TPPI", TPPITweaks.eventHandler.getTag(player, true));
		
		addBook(player);
	}
	
	private boolean addBook(EntityPlayer player)
	{
		if (player != null && ConfigurationHandler.doSpawnBook && !player.getEntityData().getCompoundTag("TPPI").getBoolean("hasBook") && !player.worldObj.isRemote)
		{
			TPPITweaks.logger.log(Level.INFO, "Adding book");

			player.getEntityData().setTag("TPPI", TPPITweaks.eventHandler.getTag(player, false));
			
			ItemStack stack = ModItems.tppiBook.getGuide();
			player.inventory.addItemStackToInventory(stack);
			return true;
		}
		
		return false;
	}

}
