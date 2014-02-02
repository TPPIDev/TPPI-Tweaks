package tppitweaks.client.gui.modGuides;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
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
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;

public class GuiGuideBase extends GuiBase implements INEIGuiHandler
{
	protected static Map<String, GuiMod> mods = new TreeMap<String, GuiMod>();

	protected String title, body;

	protected static final int LENGTH = 180;

	public GuiGuideBase()
	{
		super(new ResourceLocation("tppitweaks", "textures/gui/guiGuide.png"));
		this.xSize = 256;
		this.ySize = 178;

		setDefaultText(true);
	}

	public static void initMap()
	{
		for (String s : TxtParser.getSupportedMods(FileLoader.getSupportedModsFile()))
		{
			ArrayList<String> pages = TxtParser.parseFileMods(FileLoader.getSupportedModsFile(), s);
			String text = "";
			for (String page : pages)
			{
				page = page.replace("~", " ");
				page = page.replace("  ", " ");
				page = page.replace("  ", " ");
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
		ElementScrollPanel panel = new ElementScrollPanel(this, this.xSize / 6, this.ySize / 9, this.xSize, (int) (this.ySize / 1.35));
		List<String> lines = getLines();

		int length = 0;
		for (int i = 0; i < lines.size(); i++)
		{
			panel.addElement(new ElementText(this, 0, (length * 10), lines.get(i), null, 0x282828, false));
			length++;

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
		if (body.startsWith("<"))
		{
			body = "No information for this mod yet! " + "Use /tppi mods [modname] to get a link to a helpful webpage for this mod, " + "or contribute some documentation on the github!";
		}
		FontRenderer render = this.mc.fontRenderer;
		String[] paragraphs = body.split("\n");
		List<String[]> words = new ArrayList<String[]>();
		for (String ss : paragraphs)
		{
			words.add(ss.split(" "));
		}
		String currentLine = "";
		for (String[] ss : words)
		{
			String tab = "    ";
			for (String s : ss)
			{
				if (s.endsWith(" "))
				{
					s = s.substring(0, s.length() - 1);
				}
				System.out.print(s + " ");
				if (render.getStringWidth(currentLine + s + " ") < this.xSize - (this.xSize - LENGTH))
					currentLine += s + " ";
				else
				{
					lines.add(currentLine);
					currentLine = s + " ";
				}
				tab = "";
			}
			System.out.println();
			lines.add(currentLine);
			currentLine = "";
			lines.add("");
		}
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
		this.addElement(new GuideButton(this, 8, 114, 157));
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
				lines.add(m.title);
				modids.add(m.modid);
			}
		}

		initPanel(lines, modids);

		mods.keySet();
	}

	@Override
	public int getItemSpawnSlot(GuiContainer gui, ItemStack item)
	{
		return -1;
	}

	@Override
	public List<TaggedInventoryArea> getInventoryAreas(GuiContainer gui)
	{
		return null;
	}

	@Override
	public boolean handleDragNDrop(GuiContainer gui, int mousex, int mousey, ItemStack draggedStack, int button)
	{
		return false;
	}

	public void setDefaultText(boolean startup)
	{
		title = "Main menu";
		body = "Welcome to the TPPI Documentation System, your source documentation for all mods in this pack. To start, click on a button signifying the letter the mod starts with, then click on the mod name to read the documentation related to it that we have available. To return to this menu, press the home button at any time.";
		if (!startup)
			initPanel();
	}
}
