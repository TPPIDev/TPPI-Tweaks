package tppitweaks.client.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tppitweaks.TPPITweaks;
import tppitweaks.config.ConfigurationHandler;

public class IRCGui extends GuiScreen
{
	private boolean configState;
	private static final ResourceLocation bg = new ResourceLocation("tppitweaks", "textures/gui/TPPI.png");
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		this.buttonList.add(new GuiButton(0, this.width / 2 - 144, this.height / 2 + 40, 140, 20, "Enable this Feature"));
		this.buttonList.add(new GuiButton(1, this.width / 2 + 4, this.height / 2 + 40, 140, 20, "Disable this Feature"));
		this.buttonList.add(new GuiButton(2, this.width / 2 - 144, this.height / 2 + 68, 140, 20, "Exit the Game"));
		this.buttonList.add(new GuiButton(3, this.width / 2 + 4, this.height / 2 + 68, 140, 20, "Continue to the Game"));
		this.buttonList.add(new GuiButton(4, this.width / 2 - 144, this.height / 2 + 96, 288, 20, "Don't Show this Again"));

		((GuiButton) this.buttonList.get(2)).enabled = false;
		configState = Boolean.parseBoolean(ConfigurationHandler.manuallyGetConfigValue("EiraIRC.cfg", "B:autoConnect"));
		((GuiButton) this.buttonList.get(0)).enabled = !configState;
		((GuiButton) this.buttonList.get(1)).enabled = configState;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		drawCustomBackground(0);
		
		this.drawCenteredString(this.mc.fontRenderer, "TPPI has included an IRC chat mod!", this.width / 2, this.height / 2 - 105, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "Anyone playing the pack is able to live chat", this.width / 2, this.height / 2 - 90, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "with anyone else simultaneously.", this.width / 2, this.height / 2 - 80, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "If you do not desire this feature,", this.width / 2, this.height / 2 - 65, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "this is your chance to opt-out.", this.width / 2, this.height / 2 - 55, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "To do this, hit \"Disable this Feature\",", this.width / 2, this.height / 2 - 40, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "and then restart the game.", this.width / 2, this.height / 2 - 30, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "If you wish to leave this feature enabled,", this.width / 2, this.height / 2 - 15, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "simply hit \"Continue to the Game\"", this.width / 2, this.height / 2 - 5, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "\"Don't show this again\" will prevent this GUI", this.width / 2, this.height / 2 + 10, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, "from showing in the future.", this.width / 2, this.height / 2 + 20, 0xFFFFFF);
		super.drawScreen(par1, par2, par3);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void actionPerformed(GuiButton button)
	{
		switch (button.id)
		{
		case 0:
			button.enabled = false;
			if (ConfigurationHandler.manuallyChangeConfigValue("EiraIRC.cfg", "B:autoConnect", "false", "true"))
			{
				((GuiButton) buttonList.get(3)).enabled = configState;
				((GuiButton) buttonList.get(2)).enabled = !configState;
			}
			((GuiButton) buttonList.get(1)).enabled = true;
			break;
		case 1:
			button.enabled = false;
			if (ConfigurationHandler.manuallyChangeConfigValue("EiraIRC.cfg", "B:autoConnect", "true", "false"))
			{
				((GuiButton) buttonList.get(3)).enabled = !configState;
				((GuiButton) buttonList.get(2)).enabled = configState;
			}
			((GuiButton) buttonList.get(0)).enabled = true;
			break;
		case 2:
			TPPITweaks.logger.info("Shutting down!");
			Minecraft.getMinecraft().shutdown();
			break;
		case 3:
			this.mc.displayGuiScreen(null);
			break;
		case 4:
			dontShowAgain();
			for (GuiButton b : (List<GuiButton>) buttonList)
			{
				if (b.id != 3 && b.id != 2)
					b.enabled = false;
			}
			break;
		default:
			return;
		}
	}
	
	@Override
	protected void keyTyped(char par1, int par2)
	{
		; // do nothing
	}

	private void dontShowAgain()
	{
		TPPITweaks.logger.info("Disabling IRC GUI...");
		ConfigurationHandler.manuallyChangeConfigValue("B:showIRCGui", "true", "false");
	}

	private void drawCustomBackground(int par1)
	{
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		this.mc.getTextureManager().bindTexture(bg);
		GL11.glColor4f(0.18f, 0.18f, 0.18f, 1.0F);
		this.drawTexturedModalRect(0, 0, 240, 240, this.width, this.height);
	}
}
