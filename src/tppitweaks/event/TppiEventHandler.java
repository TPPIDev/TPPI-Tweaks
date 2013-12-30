package tppitweaks.event;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import tppitweaks.client.gui.GuiHelper;
import tppitweaks.client.gui.UpdateGui;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TppiEventHandler
{	
	
	private boolean shouldLoadGUI = true;
	
	@ForgeSubscribe
	public void onPlayerJoin(EntityJoinWorldEvent event)
	{
		if (event.entity != null && event.entity instanceof EntityPlayerMP && !event.entity.getEntityData().getCompoundTag("TPPI").getBoolean("hasBook"))
		{
			System.out.println("adding book");

			EntityPlayer entity = (EntityPlayer) event.entity;

			ItemStack stack = ModItems.tppiBook.getBook();
			entity.inventory.addItemStackToInventory(stack);

			NBTTagCompound tag = new NBTTagCompound();
			tag.setBoolean("hasBook", true);

			entity.getEntityData().setTag("TPPI", tag);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onGui(GuiOpenEvent event)	
	{
		if (shouldLoadGUI && ConfigurationHandler.showDownloadGUI && event.gui instanceof GuiMainMenu)
		{
			event.gui = new UpdateGui(event.gui, true);
			GuiHelper.updateGui = (UpdateGui)event.gui;
			shouldLoadGUI = false;
			
			ConfigurationHandler.stopShowingGUI();
		}
	}
	
}
