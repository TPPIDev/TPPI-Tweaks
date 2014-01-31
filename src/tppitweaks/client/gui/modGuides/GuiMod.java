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
	
	private static final int LENGTH = 140;
	
	public GuiMod(String modName, String body)
	{
		super();
		this.modName = modName;
		this.body = body;
	}
	
	private void initPanel()
	{
		ElementScrollPanel panel = new ElementScrollPanel(this, this.xSize / 6, this.ySize / 9, this.xSize, (int) (this.ySize / 1.35));
		List<String> lines = getLines();
		
		for (int i = 0; i < lines.size(); i++)
		{
			panel.addElement(new ElementText(this, 0, (i * 10), lines.get(i), null, 0x282828, false));
		}		
		
		this.addElement(panel);
		this.addElement(new ElementScrollBar(this, 217, 15, 6, 139, panel));
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		initPanel();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		super.drawGuiContainerForegroundLayer(x, y);
	}
	
	private List<String> getLines()
	{
		List<String> lines = new ArrayList<String>();
		lines.add(modName);
		lines.add("");
		FontRenderer render = this.mc.fontRenderer;
		String[] words = body.split(" ");
		String currentLine = "";
		for (String s : words)
		{
			currentLine += s + " ";
			if (render.getStringWidth(currentLine) > this.xSize - (this.xSize - LENGTH))
			{
				lines.add(currentLine);
				currentLine = "";
			}
		}
		lines.add(currentLine);
		return lines;
	}
}
