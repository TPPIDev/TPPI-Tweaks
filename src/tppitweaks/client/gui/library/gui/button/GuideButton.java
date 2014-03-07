package tppitweaks.client.gui.library.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import tppitweaks.client.gui.library.gui.GuiBase;
import tppitweaks.client.gui.library.gui.element.ElementBase;
import tppitweaks.client.gui.modGuides.GuiGuideBase;
import tppitweaks.config.ConfigurationHandler;

public class GuideButton extends ElementBase
{
	private ResourceLocation myButtons = new ResourceLocation("tppitweaks", ConfigurationHandler.guideSkin == 0 ? "textures/gui/guiGuide.png" : "textures/gui/guiGuide_alt.png");
	private int renderID;

	private boolean clicked = false;

	public final static int LINE_HEIGHT = 11;

	public GuideButton(GuiBase gui, int id, int x, int y)
	{
		super(gui, x, y, id < 8 ? 25 : 31, 18);
		// super(gui, x, y, 25, 18);
		renderID = id;
	}

	@Override
	public void draw()
	{
		draw(this.gui.getMinecraft(), 0, 0);
	}

	@Override
	public void draw(int x, int y)
	{
		draw(this.gui.getMinecraft(), x, y);
	}

	public void draw(Minecraft minecraft, int i, int j)
	{
		if (clicked && !Mouse.isButtonDown(0))
			clicked = false;

		minecraft.getTextureManager().bindTexture(myButtons);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		if (this.renderID <= 7)
			this.drawTexturedModalRect((int) this.posX, this.posY, renderID * 25, clicked ? 197 : 179, this.sizeX, this.sizeY);
		else if (this.renderID == 8)
			this.drawTexturedModalRect(posX, posY, renderID * 25, clicked ? 197 : 179, sizeX, sizeY);
	}

	@Override
	public boolean handleMouseClicked(int x, int y, int mouseButton)
	{
		if (!clicked)
		{
			switch (renderID)
			{
			case 0:
				((GuiGuideBase) gui).showModRange('a', 'c');
				break;
			case 1:
				((GuiGuideBase) gui).showModRange('d', 'f');
				break;
			case 2:
				((GuiGuideBase) gui).showModRange('g', 'i');
				break;
			case 3:
				((GuiGuideBase) gui).showModRange('j', 'l');
				break;
			case 4:
				((GuiGuideBase) gui).showModRange('m', 'o');
				break;
			case 5:
				((GuiGuideBase) gui).showModRange('p', 's');
				break;
			case 6:
				((GuiGuideBase) gui).showModRange('t', 'v');
				break;
			case 7:
				((GuiGuideBase) gui).showModRange('w', 'z');
				break;
			case 8:
				((GuiGuideBase) gui).setDefaultText(false);
				break;
			}
		}
		clicked = Mouse.isButtonDown(0);
		return clicked;
	}
}