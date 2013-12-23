package tppitweaks.event;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import tppitweaks.item.SpecialRecipes;

public class BookEventHandler {
	
	@ForgeSubscribe
	public void onPlayerJoin(EntityJoinWorldEvent event)
	{
		if (event.entity != null && event.entity instanceof EntityPlayerMP && FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			System.out.println("adding book");
			
			EntityPlayer entity = (EntityPlayer) event.entity;
			
			entity.inventory.addItemStackToInventory(SpecialRecipes.stack);
		}
	}
}
