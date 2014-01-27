package tppitweaks.client.gui;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.apache.commons.io.FileUtils;

import tppitweaks.lib.Reference;
import tppitweaks.util.DesktopApi;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class InstructionsGui extends GuiScreen
{

	ModDownload mod;

	public InstructionsGui(ModDownload modDownload)
	{
		mod = modDownload;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		this.buttonList.add(new GuiButton(11, this.width / 2 - 100, this.height / 2 - 60, 200, 20, "Download"));
		this.buttonList.add(new GuiButton(12, this.width / 2 - 100, this.height / 2, 200, 20, "Find Mod"));
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 + 70, 200, 20, "Continue"));
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();

		this.drawCenteredString(this.fontRenderer, mod.name, this.width / 2, this.height / 2 - 115, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "1. Click the button below to download " + mod.name + ",", this.width / 2, this.height / 2 - 95, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "or press continue at the bottom to skip installation.", this.width / 2, this.height / 2 - 85, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "(for adfly, wait 5 seconds then click \"SKIP AD\" in the upper right)", this.width / 2, this.height / 2 - 75, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "Select the file from your computer and it will be added to the mods directory.", this.width / 2, this.height / 2 - 15, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "3. Press the button below to continue.", this.width / 2, this.height / 2 + 55, 0xFFFFFF);

		super.drawScreen(par1, par2, par3);
	}

	@Override
	public void actionPerformed(GuiButton button)
	{
		switch (button.id)
		{
		case 11:
			try
			{
				DesktopApi.browse(new URI(mod.link));
			}
			catch (Exception e1)
			{
				System.err.println("Failed to reach " + mod.name + "'s download page!");
				e1.printStackTrace();
			}
			break;

		case 12:
			try
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			final JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(FileUtils.getUserDirectory());
			fc.showOpenDialog(new JFrame());
			File mod = fc.getSelectedFile();
			try
			{
				if (mod != null)
					FileUtils.copyFile(mod, new File(Reference.modsFolder.getCanonicalPath() + "/" + mod.getName()));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			break;

		default:
			GuiHelper.updateGui.actionPerformed(button);
		}

	}

}
