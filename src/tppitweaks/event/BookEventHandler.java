package tppitweaks.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import tppitweaks.item.TPPIBook;
import cpw.mods.fml.common.FMLCommonHandler;

public class BookEventHandler {
	
	@ForgeSubscribe
	public void onPlayerJoin(EntityJoinWorldEvent event)
	{
		if (event.entity != null && event.entity instanceof EntityPlayerMP && FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			System.out.println("adding book");
			
			EntityPlayer entity = (EntityPlayer) event.entity;
			
			entity.inventory.addItemStackToInventory(TPPIBook.tppiBook);
		}
	}
}
