package tppitweaks.client.gui.library.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import tppitweaks.client.gui.library.gui.GuiBase;
import tppitweaks.client.gui.library.gui.element.ElementBase;

public class SmallButton extends ElementBase
{
	private ResourceLocation myButtons = new ResourceLocation("tppitweaks", "textures/gui/guiGuide.png");
	private int renderID;
	
	private boolean clicked = false;

	public final static int LINE_HEIGHT = 11;

	public SmallButton(GuiBase gui, int id, int x, int y)
	{
		super(gui, x, y, 25, 18);
		renderID = id;
	}

	@Override
	public void draw()
	{
		draw(this.gui.mc, 0, 0);
	}

	@Override
	public void draw(int x, int y)
	{
		draw(this.gui.mc, x, y);
	}

	public void draw(Minecraft minecraft, int i, int j)
	{
		if (clicked && !Mouse.isButtonDown(0))
			clicked = false;
		
		minecraft.getTextureManager().bindTexture(myButtons);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		if (this.renderID <= 7)
			this.drawTexturedModalRect((int) this.posX, this.posY, renderID * 25, clicked ? 197 : 179, this.sizeX, this.sizeY);
	}
	
	@Override
	public boolean handleMouseClicked(int x, int y, int mouseButton)
	{
		clicked = Mouse.isButtonDown(0);
		return clicked;
	}
}
