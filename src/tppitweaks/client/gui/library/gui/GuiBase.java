package tppitweaks.client.gui.library.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import tppitweaks.client.gui.library.container.ContainerBase;
import tppitweaks.client.gui.library.gui.element.ElementBase;
import tppitweaks.client.gui.library.gui.element.ElementFakeItemSlot;
import tppitweaks.client.gui.library.gui.tab.TabBase;
import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;

/**
 * Base class for a modular GUIs. Works with Elements {@link ElementBase} and
 * Tabs {@link TabBase} which are both modular elements.
 * 
 * @author King Lemming
 * 
 */
public abstract class GuiBase extends GuiContainer implements INEIGuiHandler
{
	protected boolean drawInventory = true;
	protected int mouseX = 0;
	protected int mouseY = 0;

	protected int lastIndex = -1;

	protected String name;
	protected ResourceLocation texture;
	public ArrayList<TabBase> tabs = new ArrayList<TabBase>();
	protected ArrayList<ElementBase> elements = new ArrayList<ElementBase>();
	protected List<String> tooltip = new LinkedList<String>();

	public GuiBase()
	{
		super(new ContainerBase());
	}

	public GuiBase(Container container)
	{
		super(container);
	}

	public GuiBase(Container container, ResourceLocation texture)
	{
		super(container);
		this.texture = texture;
	}

	public GuiBase(ResourceLocation texture)
	{
		this(new ContainerBase(), texture);
	}

	public ElementBase addElement(ElementBase element)
	{
		elements.add(element);
		return element;
	}

	public TabBase addTab(TabBase tab)
	{
		tabs.add(tab);
		if (TabTracker.getOpenedLeftTab() != null && tab.getClass().equals(TabTracker.getOpenedLeftTab()))
		{
			tab.setFullyOpen();
		}
		else if (TabTracker.getOpenedRightTab() != null && tab.getClass().equals(TabTracker.getOpenedRightTab()))
		{
			tab.setFullyOpen();
		}
		return tab;
	}

	/**
	 * Draws the elements for this GUI.
	 */
	protected void drawElements()
	{
		for (ElementBase element : elements)
		{
			if (element.isVisible())
			{
				element.draw();
			}
		}
	}

	/**
	 * Simple method used to draw a fluid of arbitrary size.
	 */
	public void drawFluid(int x, int y, FluidStack fluid, int width, int height)
	{
		if (fluid == null || fluid.getFluid() == null)
		{
			return;
		}

		getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		int colour = fluid.getFluid().getColor(fluid);
		GL11.glColor3ub((byte) (colour >> 16 & 0xFF), (byte) (colour >> 8 & 0xFF), (byte) (colour & 0xFF));

		drawTiledTexture(x, y, fluid.getFluid().getIcon(fluid), width, height);
	}

	protected void drawBackgroundTexture()
	{
		if (texture != null)
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(texture);
			drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		mouseX = x - guiLeft;
		mouseY = y - guiTop;

		updateElements();
		drawBackgroundTexture();
		drawElements();
		drawTabs();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		fontRenderer.drawString(StatCollector.translateToLocal(name), getCenteredOffset(StatCollector.translateToLocal(name)), 6, 0x404040);

		if (drawInventory)
		{
			fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 3, 0x404040);
		}

		TabBase tab = getTabAtPosition(x - guiLeft, y - guiTop);

		if (tab != null)
		{
			List<String> list = new ArrayList<String>();
			tab.addTooltip(list);

			if (!list.isEmpty())
			{
				drawTooltip(list);
				return;
			}
		}

		ElementBase element = getElementAtPosition(x - guiLeft, y - guiTop);

		if (element != null && !element.isDisabled())
		{
			List<String> list = new ArrayList<String>();
			element.addTooltip(list);

			if (!list.isEmpty())
			{
				drawTooltip(list);
				return;
			}
		}
	}

	public void drawIcon(Icon icon, int x, int y, int spriteSheet)
	{
		if (spriteSheet == 0)
		{
			getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		}
		else
		{
			getTextureManager().bindTexture(TextureMap.locationItemsTexture);
		}

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
		drawTexturedModelRectFromIcon(x, y, icon, 16, 16);
	}

	public void drawItemStack(ItemStack stack, int x, int y)
	{
		if (stack != null)
		{
			RenderHelper.enableGUIStandardItemLighting();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			itemRenderer.renderItemAndEffectIntoGUI(getFontRenderer(), getTextureManager(), stack, x, y);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.disableStandardItemLighting();
		}
	}

	public void drawScaledTexturedModelRectFromIcon(int x, int y, Icon icon, int width, int height)
	{
		if (icon == null)
		{
			return;
		}

		double minU = icon.getMinU();
		double maxU = icon.getMaxU();
		double minV = icon.getMinV();
		double maxV = icon.getMaxV();

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, zLevel, minU, minV + (maxV - minV) * height / 16F);
		tessellator.addVertexWithUV(x + width, y + height, zLevel, minU + (maxU - minU) * width / 16F, minV + (maxV - minV) * height / 16F);
		tessellator.addVertexWithUV(x + width, y + 0, zLevel, minU + (maxU - minU) * width / 16F, minV);
		tessellator.addVertexWithUV(x + 0, y + 0, zLevel, minU, minV);
		tessellator.draw();
	}

	public void drawSizedTexturedModalRect(int x, int y, int u, int v, int width, int height, float texW, float texH)
	{
		float texU = 1 / texW;
		float texV = 1 / texH;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, zLevel, (u + 0) * texU, (v + height) * texV);
		tessellator.addVertexWithUV(x + width, y + height, zLevel, (u + width) * texU, (v + height) * texV);
		tessellator.addVertexWithUV(x + width, y + 0, zLevel, (u + width) * texU, (v + 0) * texV);
		tessellator.addVertexWithUV(x + 0, y + 0, zLevel, (u + 0) * texU, (v + 0) * texV);
		tessellator.draw();
	}

	/**
	 * Draws the tabs for this GUI. Handles Tab open/close animation.
	 */
	protected void drawTabs()
	{
		int yPosRight = 4;
		int yPosLeft = 4;

		for (TabBase tab : tabs)
		{
			tab.update();
			if (!tab.isVisible())
			{
				continue;
			}
			if (tab.side == 0)
			{
				tab.draw(guiLeft, guiTop + yPosLeft);
				yPosLeft += tab.currentHeight;
			}
			else
			{
				tab.draw(guiLeft + xSize, guiTop + yPosRight);
				yPosRight += tab.currentHeight;
			}
		}
	}

	public void drawTiledTexture(int x, int y, Icon icon, int width, int height)
	{
		int i = 0;
		int j = 0;

		int drawHeight = 0;
		int drawWidth = 0;

		for (i = 0; i < width; i += 16)
		{
			for (j = 0; j < height; j += 16)
			{
				drawWidth = Math.min(width - i, 16);
				drawHeight = Math.min(height - j, 16);
				drawScaledTexturedModelRectFromIcon(x + i, y + j, icon, drawWidth, drawHeight);
			}
		}

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
	}

	public void drawTooltip(List<String> list)
	{
		drawTooltipHoveringText(list, mouseX, mouseY, fontRenderer);
		tooltip.clear();
	}

	protected void drawTooltipHoveringText(List<String> list, int x, int y, FontRenderer font)
	{
		if (list == null || list.isEmpty())
		{
			return;
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		int k = 0;
		Iterator<String> iterator = list.iterator();

		while (iterator.hasNext())
		{
			String s = iterator.next();
			int l = font.getStringWidth(s);

			if (l > k)
			{
				k = l;
			}
		}

		int i1 = x + 12;
		int j1 = y - 12;
		int k1 = 8;

		if (list.size() > 1)
		{
			k1 += 2 + (list.size() - 1) * 10;
		}
		if (i1 + k > width)
		{
			i1 -= 28 + k;
		}
		if (j1 + k1 + 6 > height)
		{
			j1 = height - k1 - 6;
		}

		zLevel = 300.0F;
		itemRenderer.zLevel = 300.0F;
		int l1 = -267386864;
		drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
		drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
		drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
		drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
		drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
		int i2 = 1347420415;
		int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
		drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
		drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
		drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
		drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

		for (int k2 = 0; k2 < list.size(); ++k2)
		{
			String s1 = (String) list.get(k2);
			font.drawStringWithShadow(s1, i1, j1, -1);

			if (k2 == 0)
			{
				j1 += 2;
			}
			j1 += 10;
		}

		zLevel = 0.0F;
		itemRenderer.zLevel = 0.0F;
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}

	protected int getCenteredOffset(String string)
	{
		return this.getCenteredOffset(string, xSize);
	}

	protected int getCenteredOffset(String string, int xWidth)
	{
		return (xWidth - fontRenderer.getStringWidth(string)) / 2;
	}

	protected ElementBase getElementAtPosition(int mX, int mY)
	{
		for (ElementBase element : elements)
		{
			if (element.isVisible() && element.intersectsWith(mX, mY))
			{
				return element;
			}
		}

		return null;
	}

	public FontRenderer getFontRenderer()
	{
		return fontRenderer;
	}

	public int getGuiLeft()
	{
		return guiLeft;
	}

	public int getGuiTop()
	{
		return guiTop;
	}

	public RenderItem getItemRenderer()
	{
		return itemRenderer;
	}

	public int getMouseX()
	{
		return mouseX;
	}

	public int getMouseY()
	{
		return mouseY;
	}

	protected TabBase getTabAtPosition(int mX, int mY)
	{
		int xShift = 0;
		int yShift = 4;

		for (TabBase tab : tabs)
		{
			if (!tab.isVisible() || tab.side == 1)
			{
				continue;
			}

			tab.currentShiftX = xShift;
			tab.currentShiftY = yShift;

			if (tab.intersectsWith(mX, mY, xShift, yShift))
			{
				return tab;
			}

			yShift += tab.currentHeight;
		}

		xShift = xSize;
		yShift = 4;

		for (TabBase tab : tabs)
		{
			if (!tab.isVisible() || tab.side == 0)
			{
				continue;
			}

			tab.currentShiftX = xShift;
			tab.currentShiftY = yShift;

			if (tab.intersectsWith(mX, mY, xShift, yShift))
			{
				return tab;
			}

			yShift += tab.currentHeight;
		}

		return null;
	}

	public TextureManager getTextureManager()
	{
		return mc.renderEngine;
	}

	public boolean isItemStackAllowedInFakeSlot(ElementFakeItemSlot slot, ItemStack stack)
	{
		return true;
	}

	public void handleElementButtonClick(String buttonName, int mouseButton)
	{

	}

	public void handleElementFakeSlotItemChange(ElementFakeItemSlot slot)
	{

	}

	@Override
	public void initGui()
	{
		super.initGui();

		tabs.clear();
		elements.clear();
		buttonList.clear();

		addElements();
		addTabs();
	}

	public void addElements()
	{

	}

	public void addTabs()
	{

	}

	/**
	 * Passthrough method for tab use.
	 */
	public void mouseClicked(int mouseButton)
	{
		super.mouseClicked(guiLeft + mouseX, guiTop + mouseY, mouseButton);
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton)
	{
		super.mouseClicked(x, y, mouseButton);

		TabBase tab = getTabAtPosition(mouseX, mouseY);

		if (tab != null && tab.isVisible() && !tab.handleMouseClicked(mouseX, mouseY, mouseButton))
		{
			for (TabBase other : tabs)
			{
				if (other != tab && other.open && other.side == tab.side)
				{
					other.toggleOpen();
				}
			}

			tab.toggleOpen();
		}

		ElementBase element = getElementAtPosition(mouseX, mouseY);

		if (element != null)
		{
			if (element.isVisible())
			{
				element.handleMouseClicked(mouseX, mouseY, mouseButton);
			}
		}
	}

	@Override
	protected void mouseClickMove(int mX, int mY, int lastClick, long timeSinceClick)
	{
		Slot slot = getSlotAtPosition(mX, mY);
		ItemStack itemstack = mc.thePlayer.inventory.getItemStack();

		lastIndex = -1;
		super.mouseClickMove(mX, mY, lastClick, timeSinceClick);

	}

	protected void updateElements()
	{
		for (ElementBase element : elements)
		{
			element.update();
		}
	}

	public Minecraft getMinecraft()
	{
		return mc;
	}

	@Override
	public boolean handleDragNDrop(GuiContainer gui, int mousex, int mousey, ItemStack draggedStack, int button)
	{
		return false;
	}

	@Override
	public List<TaggedInventoryArea> getInventoryAreas(GuiContainer gui)
	{
		return null;
	}

	@Override
	public int getItemSpawnSlot(GuiContainer gui, ItemStack item)
	{
		return 0;
	}

	@Override
	public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility)
	{
		return null;
	}
}
