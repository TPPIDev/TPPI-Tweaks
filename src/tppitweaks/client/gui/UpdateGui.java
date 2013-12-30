package tppitweaks.client.gui;

import java.awt.Desktop;
import java.net.URI;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class UpdateGui extends GuiScreen
{
	private GuiScreen parentScreen;
	private boolean isTCLoaded, isTFLoaded;
	private Desktop desktop;

	public UpdateGui(GuiScreen parentScreen)
	{
		this(parentScreen, new boolean[] { false, false });
	}

	public UpdateGui(GuiScreen parentScreen, boolean[] flags)
	{
		this.parentScreen = parentScreen;
		isTCLoaded = flags[0];
		isTFLoaded = flags[1];

		desktop = Desktop.getDesktop();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		// Unsure exactly what this does but...it seems necessary
		Keyboard.enableRepeatEvents(true);

		this.buttonList.clear();
		if (!isTCLoaded)
			this.buttonList.add(new GuiButton(0, this.width / 2 - 150, this.height / 4 + 60, 300, 20, "Download Thaumcraft 4"));
		if (!isTFLoaded)
			this.buttonList.add(new GuiButton(1, this.width / 2 - 150, this.height / 4 + 85, 300, 20, "Download Twilight Forest"));

		this.buttonList.add(new GuiButton(2, this.width / 2 - 150, this.height / 4 + 110, 300, 20, "Close the game, to allow the new mods to load."));
		this.buttonList.add(new GuiButton(3, this.width / 2 - 150, this.height / 4 + 135, 300, 20, "Skip this, do NOT exit the game."));
	}

	@Override
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.enabled)
		{
			try
			{
				switch (button.id)
				{
				case 0:
					desktop.browse(new URI("http://adf.ly/1311628/thaumcraft-4"));
					this.mc.displayGuiScreen(new InstructionsGui(this));
					break;
				case 1:
					desktop.browse(new URI("http://adf.ly/Zvi5J"));
					this.mc.displayGuiScreen(new InstructionsGui(this));
					break;
				case 2:
					System.exit(0);
					break;
				case 3:
					this.mc.displayGuiScreen(this.parentScreen);
					break;
				default:
					System.out.println("Where did you find this button?");
					break;
				}
			}
			catch (Exception e)
			{
				System.err.println("Error opening webpage, please contact TPPI Team.");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		drawScreen(par1, par2, par3, true);
	}

	public void drawScreen(int par1, int par2, float par3, boolean draw)
	{
		if (draw)
		{
			this.drawDefaultBackground();

			this.drawCenteredString(this.fontRenderer, "This is the first time you are starting TestPackPleaseIgnore. ", this.width / 2, 20, 0xFFFFFF);
			this.drawCenteredString(this.fontRenderer, "If you would like to download either of these two mods,", this.width / 2, 40, 0xFFFFFF);
			this.drawCenteredString(this.fontRenderer, "please click the appropriate buttons.", this.width / 2, 50, 0xFFFFFF);
			this.drawCenteredString(this.fontRenderer, "This option will not show again unless enabled in the config.", this.width / 2, 60, 0xFFFFFF);
			this.drawCenteredString(this.fontRenderer, "Alternatively, use the command \"/tppi download\" to show this GUI.", this.width / 2, 80, 0xFFFFFF);
		}

		super.drawScreen(par1, par2, par3);
	}
}
