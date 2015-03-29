package tppitweaks.client.gui;

import java.io.File;
import java.io.FileInputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import tppitweaks.util.Unzipper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Deprecated
public class ConfigGui extends GuiScreen
{

	public ConfigGui()
	{

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		this.buttonList.add(new GuiButton(13, this.width / 2 - 100, 60, 200, 20, "Enable Hard Mode"));
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, 200, 200, 20, "Continue"));
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();

		this.drawCenteredString(this.fontRendererObj, "Config Options", this.width / 2, 10, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, "Use hard mode configs?", this.width / 2, 40, 0xFFFFFF);

		this.drawCenteredString(this.fontRendererObj, "The hard mode config enables all GT nerfs, as well as all", this.width / 2, 114, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, "expsnsive versions of recpies", this.width / 2, 124, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, "NOTE: You will have to restart Minecraft in order for the", this.width / 2, 134, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, "config change to take effect", this.width / 2, 144, 0xFFFFFF);

		super.drawScreen(par1, par2, par3);
	}

	@Override
	public void actionPerformed(GuiButton button)
	{
		switch (button.id)
		{
		case 13:
			try
			{
				File file = new File("config/TPPI/config/hardconfig.zip");
				Unzipper.unzipFiles(new FileInputStream(file), "config");
				break;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
