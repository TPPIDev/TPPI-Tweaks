package tppitweaks.client.gui.modGuides;

import net.minecraft.util.ResourceLocation;
import tppitweaks.client.gui.library.gui.GuiBase;
import tppitweaks.client.gui.library.gui.element.ElementBase;
import tppitweaks.client.gui.library.gui.tab.TabBase;

public class Tab extends TabBase
{
	public Tab(GuiBase gui, boolean left, int yPos)
	{
		super(gui);
		this.side = left ? 0 : 1;
		this.posY = yPos;
		this.posX = left ? 20 : 400;
	}
	
	@Override
	public ElementBase setTexture(String texture, int texW, int texH)
	{
		super.setTexture("", texW, texH);
		this.texture = new ResourceLocation("tppitweaks", "textures/gui/" + texture);
		return this;
	}
}
