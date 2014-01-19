package tppitweaks.event;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tppitweaks.client.gui.GuiHelper;
import tppitweaks.client.gui.UpdateGui;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TPPIEventHandler
{	
	
	private boolean shouldLoadGUI = true;
	
	public static boolean NBTValOnDeath;
	
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
	
	@ForgeSubscribe
	public void onLivingDeath(LivingDeathEvent event)
	{
		EntityPlayer entityPlayer;
		if (event.entityLiving instanceof EntityPlayer)
		{
			System.out.println("getting NBT");
			entityPlayer = (EntityPlayer) event.entityLiving;
			
			NBTValOnDeath = entityPlayer.getEntityData().getCompoundTag("TPPI").getBoolean("hasBook");
		}
	}
	
	public NBTTagCompound getTag(EntityPlayer entity, boolean useClassVal)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("hasBook", useClassVal ? NBTValOnDeath : true);
		
		return tag;
	}
	
	@ForgeSubscribe
	public void onItemTooltip(ItemTooltipEvent event)
	{
		if (event.itemStack.getItem().itemID == mods.immibis.chunkloader.DimensionalAnchors.instance.block.blockID)
		{
			event.toolTip.add("\u00A7oA chunk loader");
		}
	}
}
