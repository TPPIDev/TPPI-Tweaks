package tppitweaks.client.gui.modGuides;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import tppitweaks.client.gui.library.gui.GuiBase;
import tppitweaks.client.gui.library.gui.button.GuideButton;
import tppitweaks.client.gui.library.gui.element.ElementBase;
import tppitweaks.client.gui.library.gui.element.ElementScrollBar;
import tppitweaks.client.gui.library.gui.element.ElementScrollPanel;
import tppitweaks.client.gui.library.gui.element.ElementText;
import tppitweaks.client.gui.library.gui.element.ElementTextClickable;
import tppitweaks.command.CommandTPPI;
import tppitweaks.util.FileLoader;
import tppitweaks.util.TxtParser;
import codechicken.nei.VisiblityData;

public class GuiGuideBase extends GuiBase
{
	protected static Map<String, GuiMod> mods = new TreeMap<String, GuiMod>();

	protected String title, body;

	protected static final int LENGTH = 140;

	public GuiGuideBase()
	{
		super(new ResourceLocation("tppitweaks", "textures/gui/guiGuide.png"));
		drawInventory = false;
		this.xSize = 256;
		this.ySize = 178;

		title = "Main menu";
		body = "This is a test. This is a test. This is a test. This is a test. ";
	}

	public static void initMap()
	{
		for (String s : TxtParser.getSupportedMods(FileLoader.getSupportedModsFile()))
		{
			ArrayList<String> pages = TxtParser.parseFileMods(FileLoader.getSupportedModsFile(), s);
			String text = "";
			for (String page : pages)
			{
				text += page + " ";
			}
			mods.put(s, new GuiMod(CommandTPPI.getProperName(s), text, s));
		}
	}

	@Override
	public void initGui()
	{
		super.initGui();
		initButtons();
		initPanel();
	}

	// Initializes the panel by first clearing all panels/scrollbars from the
	// element list and then re-adding with updated text
	protected void initPanel()
	{
		System.out.println("init panel");

		ElementScrollPanel panel = new ElementScrollPanel(this, this.xSize / 6, this.ySize / 9, this.xSize, (int) (this.ySize / 1.35));
		List<String> lines = getLines();

		for (int i = 0; i < lines.size(); i++)
		{
			panel.addElement(new ElementText(this, 0, (i * 10), lines.get(i), null, 0x282828, false));
		}

		ArrayList<ElementBase> elementsNew = new ArrayList<ElementBase>();

		for (ElementBase e : elements)
		{
			if (!(e instanceof ElementScrollBar || e instanceof ElementScrollPanel))
				elementsNew.add(e);
		}

		this.elements = elementsNew;

		this.addElement(panel);
		this.addElement(new ElementScrollBar(this, 217, 15, 6, 139, panel));
	}

	private void initPanel(List<String> lines, List<String> modids)
	{
		System.out.println("init panel");

		ElementScrollPanel panel = new ElementScrollPanel(this, this.xSize / 6, this.ySize / 9, this.xSize, (int) (this.ySize / 1.35));

		for (int i = 0; i < lines.size(); i++)
		{
			panel.addElement(new ElementTextClickable(this, 0, i * 10, lines.get(i), null, 0x282828, modids.get(i)));
		}

		ArrayList<ElementBase> elementsNew = new ArrayList<ElementBase>();

		for (ElementBase e : elements)
		{
			if (!(e instanceof ElementScrollBar || e instanceof ElementScrollPanel))
				elementsNew.add(e);
		}

		this.elements = elementsNew;

		this.addElement(panel);
		this.addElement(new ElementScrollBar(this, 217, 15, 6, 139, panel));
	}

	// Splits the body text into correctly sized lines based on the LENGTH
	// constant
	private List<String> getLines()
	{
		List<String> lines = new ArrayList<String>();
		lines.add(title);
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

	// No NEI, too crowded
	@Override
	public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility)
	{
		currentVisibility.showNEI = false;
		return currentVisibility;
	}

	private void initButtons()
	{
		this.addElement(new GuideButton(this, 0, 2, 10));
		this.addElement(new GuideButton(this, 1, 2, 40));
		this.addElement(new GuideButton(this, 2, 2, 70));
		this.addElement(new GuideButton(this, 3, 2, 100));
		this.addElement(new GuideButton(this, 4, 228, 10));
		this.addElement(new GuideButton(this, 5, 228, 40));
		this.addElement(new GuideButton(this, 6, 228, 70));
		this.addElement(new GuideButton(this, 7, 228, 100));
	}

	@Override
	public boolean hideItemPanelSlot(GuiContainer gui, int x, int y, int w, int h)
	{
		return true;
	}

	// Sets the body/title and reinitializs the text
	public void modifyGui(int buttonID)
	{
		if (buttonID <= 7)

			this.body = "This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. This is a test. ";
		this.initPanel();
	}

	// TODO shows the info for the given modid
	public void displayModInfo(String modid)
	{
		this.title = mods.get(modid).title;
		this.body = mods.get(modid).body;
		initPanel();
	}
	
	public void showModRange(char start, char end)
	{
		List<String> lines = new ArrayList<String>(), modids = new ArrayList<String>();
		
		for (GuiMod m : mods.values())
		{
			if (m.modid.toLowerCase().charAt(0) >= start && m.modid.toLowerCase().charAt(0) <= end)
			{
				lines.add(m.body);
				modids.add(m.modid);
			}
		}
		
		initPanel(lines, modids);
	}
}
