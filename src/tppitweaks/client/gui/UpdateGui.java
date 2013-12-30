package tppitweaks.client.gui;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class UpdateGui extends GuiScreen
{
	private GuiScreen parentScreen;

	public UpdateGui(GuiScreen parentScreen)
	{
		this.parentScreen = parentScreen;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		// Unsure exactly what this does but...it seems necessary
		Keyboard.enableRepeatEvents(true);

		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 100, 350, 20, "Download Thaumcraft 4"));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120, 350, 20, "Download Twilight Forest"));
		this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 140, 350, 20, "Close the game, to allow the new mods to load."));
		this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 160, 350, 20, "Skip this, do NOT exit the game."));
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
			switch (button.id)
			{
			case 0:
				// TODO Download TC4
				break;
			case 1:
				// TODO Download TF
				break;
			case 2:
				System.exit(0);
				break;
			case 3:
				this.mc.displayGuiScreen(this.parentScreen);
			}
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();

		this.drawCenteredString(
				this.fontRenderer,
				"This is the first time you are starting TestPackPleaseIgnore. "
				+ "\nIf you would like to download either of these two mods,"
				+ "\nplease click the appropriate buttons."
				+ "\nThis option will not show again unless enabled in the config.",
				this.width / 2, 20, 0xFFFFFF);
		
		super.drawScreen(par1, par2, par3);
	}
}
