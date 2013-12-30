package tppitweaks.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tppitweaks.TPPITweaks;

public class GuiHelper {
	
	public static void doBookGUI(EntityPlayer player, ItemStack stack, boolean par3) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBook(player, stack, par3));
	}
	
	public static void doDownloaderGUI() {
		Minecraft.getMinecraft().displayGuiScreen(new UpdateGui(Minecraft.getMinecraft().currentScreen));
	}		
}