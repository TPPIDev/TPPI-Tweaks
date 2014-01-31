package tppitweaks.client.gui.library.gui.tab;

import net.minecraft.item.Item;
import tppitweaks.client.gui.library.gui.GuiBase;
import tppitweaks.client.gui.library.gui.element.ElementRedstoneFlux;

public class TabRedstoneFlux extends TabBase
{
    protected ElementRedstoneFlux elementFlux;

    public TabRedstoneFlux(GuiBase gui, ElementRedstoneFlux flux)
    {
        this(gui, 1, flux);
    }

    public TabRedstoneFlux(GuiBase gui, int side, ElementRedstoneFlux flux)
    {
        super(gui, side);
        this.elementFlux = flux;
        this.name = "Energy";
        this.backgroundColor = 0xDD6600;
        this.icon = Item.redstone.getIconFromDamage(0);
        this.titleColour = 0xDDDD00;
        this.maxHeight = 75;
    }

    @Override
    public void draw()
    {
        super.draw();
        
        if (isFullyOpened() && elementFlux != null)
        {
            gui.getFontRenderer().drawStringWithShadow("Maximum Power:", posX + 10, posY + 20, 0xAAAAAA);
            gui.getFontRenderer().drawString(elementFlux.getMaximum() + " RF", posX + 17, posY + 32, 0x000000);
            
            gui.getFontRenderer().drawStringWithShadow("Energy Stored:", posX + 10, posY + 45, 0xAAAAAA);
            gui.getFontRenderer().drawString(elementFlux.getProgress() + " RF", posX + 17, posY + 57, 0x000000);
        }
    }
}
