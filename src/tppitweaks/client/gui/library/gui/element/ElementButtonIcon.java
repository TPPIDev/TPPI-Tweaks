package tppitweaks.client.gui.library.gui.element;

import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

import tppitweaks.client.gui.library.gui.GuiBase;

public class ElementButtonIcon extends ElementButton
{
    Icon icon;

    public ElementButtonIcon(GuiBase parent, int x, int y, String id, String hover, Icon icon)
    {
        super(parent, x, y, 20, id, null, hover);
        this.icon = icon;
    }

    public ElementButtonIcon(GuiBase parent, int x, int y, String id, Icon icon)
    {
        super(parent, x, y, 20, id, null);
        this.icon = icon;
    }
    
    @Override
    public void draw()
    {
        super.draw();
        
        if (texture != null)
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            gui.getTextureManager().bindTexture(texture);
            gui.drawIcon(icon, posX + sizeX / 2 - 8, posY + sizeY / 2 - 8, 1);
        }
    }
}
