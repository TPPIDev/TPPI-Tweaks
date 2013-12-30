package tppitweaks.client.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tppitweaks.TPPITweaks;

public class GuiHelper {
	
	public static void doBookGUI(EntityPlayer player, ItemStack stack, boolean par3) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBook(player, stack, par3));
	}
	
	@SuppressWarnings("unchecked")
	public static void doDownloaderGUI(String playername, World world) {
		for (EntityPlayer player : (List<EntityPlayer>) world.playerEntities)
		{
			if (player.username.equals(playername));//remove this semicolon
				//TODO Do stuff with the guis and stuff
		}
	}	
	
	public static boolean shouldLoadDownloaderGUI() {
		
		//TODO: Grab the config option that'll trigger this event
		
		/*if(config stuff says don't do it) {
			return false;
		}*/
		
		for(boolean b : TPPITweaks.getModFlags()) {
			if(!b) {
				return true;
			}
		}
		return false;
		
	}
	
}