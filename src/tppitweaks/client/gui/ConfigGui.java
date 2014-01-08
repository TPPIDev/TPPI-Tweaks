package tppitweaks.client.gui;

import java.io.InputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import tppitweaks.util.Unzipper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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
		
		this.drawCenteredString(this.fontRenderer, "Config Options", this.width / 2,  10, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "Use hard mode configs?", this.width / 2, 40, 0xFFFFFF);
		
		this.drawCenteredString(this.fontRenderer, "The hard mode config enables all GT nerfs, as well as all", this.width / 2, 110, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "expsnsive versions of recpies", this.width / 2, 124, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, "NOTE: You will have to restart Minecraft in order for the config change to take effect", this.width / 2, 134, 0xFFFFFF);
						
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		switch(button.id) {
			case 13:
				InputStream input = getClass().getResourceAsStream("/assets/tppitweaks/config/hardconfig.zip");
				Unzipper.unzipFiles(input, "config");
				GuiHelper.updateGui.actionPerformed(button);
			    break;
			    
			default:
				GuiHelper.updateGui.actionPerformed(button);
		}

	}

}
