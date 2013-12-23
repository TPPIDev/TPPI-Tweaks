package tppitweaks.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import tppitweaks.TPPITweaks;
import tppitweaks.item.TPPIBook;

public class BookEventHandler {
		
	@ForgeSubscribe
	public void onPlayerJoin(EntityJoinWorldEvent event)
	{
		if (event.entity != null && event.entity instanceof EntityPlayerMP && !event.entity.getEntityData().getCompoundTag("TPPI").getBoolean("hasBook"))
		{
			System.out.println("adding book");
			
			EntityPlayer entity = (EntityPlayer) event.entity;
			
			entity.inventory.addItemStackToInventory(TPPIBook.getBook());
			
			NBTTagCompound tag = new NBTTagCompound();
			tag.setBoolean("hasBook", true);
			
			entity.getEntityData().setTag("TPPI", tag);
		}
	}
}
