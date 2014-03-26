package tppitweaks.client.gui.library.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import tppitweaks.client.gui.library.gui.element.ElementBase;
import tppitweaks.client.gui.library.gui.element.ElementFakeItemSlot;

public interface IGuiBase
{
    public ElementBase addElement(ElementBase element);

    public void addElements();

    public void addTabs();

    public void drawBackgroundTexture();

    public void drawElements();

    public void drawTabs();

    public ElementBase getElementAtPosition(int mouseX, int mouseY);

    public FontRenderer getFontRenderer();

    public int getGuiLeft();

    public int getGuiTop();

    public int getHeight();

    public RenderItem getItemRenderer();

    public Minecraft getMinecraft();

    public int getMouseX();

    public int getMouseY();

    public TextureManager getTextureManager();

    public int getWidth();

    public float getZLevel();

    public void handleElementButtonClick(String buttonName, int mouseButton);

    public void handleElementFakeSlotItemChange(ElementFakeItemSlot slot);

    public boolean isItemStackAllowedInFakeSlot(ElementFakeItemSlot slot, ItemStack stack);

    public void setZLevel(float zlevel);
}
