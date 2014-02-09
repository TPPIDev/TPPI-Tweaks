package codechicken.nei.api;

import java.util.List;

import codechicken.nei.VisiblityData;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

public interface INEIGuiHandler
{
	public int getItemSpawnSlot(GuiContainer gui, ItemStack item);
	
	public List<TaggedInventoryArea> getInventoryAreas(GuiContainer gui);

	public boolean handleDragNDrop(GuiContainer gui, int mousex, int mousey, ItemStack draggedStack, int button);
	
	public boolean hideItemPanelSlot(GuiContainer gui, int x, int y, int w, int h);

	public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility);
}
