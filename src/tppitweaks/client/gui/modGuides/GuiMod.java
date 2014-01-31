package tppitweaks.client.gui.modGuides;

import java.util.ArrayList;
import java.util.List;

import tppitweaks.client.gui.library.gui.button.GuideButton;
import tppitweaks.client.gui.library.gui.element.ElementScrollBar;
import tppitweaks.client.gui.library.gui.element.ElementScrollPanel;
import tppitweaks.client.gui.library.gui.element.ElementText;
import tppitweaks.client.gui.library.gui.tab.TabBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class GuiMod extends GuiGuideBase
{
	private String modName, body;
		
	public GuiMod(String modName, String body)
	{
		super();
		this.modName = modName;
		this.body = body;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		super.drawGuiContainerForegroundLayer(x, y);
	}
}
