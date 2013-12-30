package tppitweaks.client.gui;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class UpdateGui extends GuiScreen
{
	private GuiScreen parentScreen;
	private Desktop desktop;
	private boolean noShow = true;
	
	private List<ModDownload> mods = new ArrayList<ModDownload>();

	public UpdateGui(GuiScreen parentScreen)
	{
		this.parentScreen = parentScreen;

		desktop = Desktop.getDesktop();
		
		mods.add(new ModDownload("Thaumcraft", "http://adf.ly/1311628/thaumcraft-4", "Thaumcraft"));
		mods.add(new ModDownload("Twilight Forest", "http://adf.ly/Zvi5J", "TwilightForest"));
		mods.add(new ModDownload("Testing Testing", "http://google.com", "TEST"));
		
		for (ModDownload m : mods)
		{
			if (!Loader.isModLoaded(m.modid))
				noShow = false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		if (noShow)
		{
			System.out.println("not opening GUI");
			//this.mc.displayGuiScreen(this.parentScreen);
		//	return;
		}
		
		// Unsure exactly what this does but...it seems necessary
		Keyboard.enableRepeatEvents(true);

		this.buttonList.clear();
		
		int index = 0;
		for (ModDownload m : mods)
		{
			if (!Loader.isModLoaded(m.modid))
				this.buttonList.add(new GuiButton(index, this.width / 2 - 150, this.height / 4 + (19 * (1 + (index + 1))), 300, 20, "Download " + m.name));
			index++;
		}
		
		this.buttonList.add(new GuiButton(10, this.width / 2 - 150, this.height / 4 + 135, 300, 20, "Close the game, to allow the new mods to load."));
		this.buttonList.add(new GuiButton(11, this.width / 2 - 150, this.height / 4 + 160, 300, 20, "Skip this, do NOT exit the game."));
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
				if (button.id < 10)
					this.mc.displayGuiScreen(new InstructionsGui(this, mods.get(button.id)));
				else if (button.id == 10)
					System.exit(0);
				else
					this.mc.displayGuiScreen(this.parentScreen);
					System.out.println(this.parentScreen.toString());
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
