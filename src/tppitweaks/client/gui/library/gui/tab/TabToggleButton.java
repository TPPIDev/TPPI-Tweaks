package tppitweaks.client.gui.library.gui.tab;

import net.minecraft.item.ItemStack;
import tppitweaks.client.gui.library.gui.GuiBase;

public class TabToggleButton extends TabBase
{
    int closedColour = 0x666666, openColour = 0xBBBBBB;

    public TabToggleButton(GuiBase gui, ItemStack item)
    {
        this(gui, 0, item.getUnlocalizedName(), item.getDisplayName(), item);
    }
    
    public TabToggleButton(GuiBase gui, int side, ItemStack item)
    {
        this(gui, side, item.getUnlocalizedName(), item.getDisplayName(), item);
    }
    
    public TabToggleButton(GuiBase gui, String ID, String name, ItemStack item)
    {
        this(gui, 0, ID, name, item);
    }

    public TabToggleButton(GuiBase gui, int side, String ID, String name, ItemStack item)
    {
        super(gui, side);
        this.name = name;
        this.maxWidth = Math.min(maxWidth, gui.getFontRenderer().getStringWidth(name) + 30);
        this.backgroundColor = closedColour;
        this.titleColour = 0xFFFFFF;
        this.stack = item;
        this.ID = ID;
        
        if (stack == null)
        {
            maxWidth -= 20;
        }
    }

    @Override
    public void setFullyOpen()
    {
        super.setFullyOpen();
        this.backgroundColor = openColour;
    }
    
    @Override
    public void toggleOpen()
    {
        if (open)
        {
            open = false;
            backgroundColor = closedColour;
        }
        else
        {
            open = true;
            gui.handleElementButtonClick(ID, 0);
        }
    }
    
    @Override
    public boolean handleMouseClicked(int x, int y, int mouseButton)
    {
        if (isFullyOpened())
        {
            return true;
        }
        
        return false;
    }
}
