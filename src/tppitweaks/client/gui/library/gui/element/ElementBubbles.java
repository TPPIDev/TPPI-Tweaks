package tppitweaks.client.gui.library.gui.element;

import java.util.List;

import org.lwjgl.opengl.GL11;

import tppitweaks.client.gui.library.gui.GuiBase;

public class ElementBubbles extends ElementProgressBar
{
    boolean showTooltip;

    public ElementBubbles(GuiBase parent, int x, int y)
    {
        this(parent, x, y, 0, 100, false);
    }

    public ElementBubbles(GuiBase parent, int x, int y, int max)
    {
        this(parent, x, y, 0, max, false);
    }

    public ElementBubbles(GuiBase parent, int x, int y, int progress, int max, boolean tooltip)
    {
        super(parent, x, y, progress, max);
        sizeX = 10;
        sizeY = 28;
        showTooltip = tooltip;
    }

    @Override
    public void draw()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        gui.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(posX, posY, 16, 26, sizeX, sizeY);
        
        if (!isDisabled())
        {
            int height = 0;

            if (currentProgress > 0)
            {
                height = Math.round((float)currentProgress * sizeY / maxProgress);
            }

            drawTexturedModalRect(posX, posY + sizeY - height, 16 + sizeX, 26 + sizeY - height, sizeX + 1, height);
        }
    }

    @Override
    public void addTooltip(List<String> list)
    {
        if (showTooltip)
        {
            super.addTooltip(list);
        }
    }
}
