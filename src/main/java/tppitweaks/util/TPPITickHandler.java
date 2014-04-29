package tppitweaks.util;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StringUtils;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TPPITickHandler implements ITickHandler
{
	public static Minecraft mc = FMLClientHandler.instance().getClient();
	private final String CAPE_URL = "https://dl.dropboxusercontent.com/s/f89qsarfhni5l3x/TPPIcape.png";
	private Map<String, CapeBufferDownload> downloads = new HashMap<String, CapeBufferDownload>();
	
	private List<String> devs = Arrays.asList(new String[]{ "tterrag1098", "esKaayY", "wha_ha_ha", "JDGBOLT", "afflucky" });
	
	public static String[] AbstractClientPlayer_downloadImageCape = new String[] {"downloadImageCape", "field_110315_c", "c"};
	public static String[] AbstractClientPlayer_locationCape = new String[] {"locationCape", "field_110313_e", "e"};
	public static String[] AbstractClientPlayer_getDownloadImage = new String[] {"getDownloadImage", "func_110301_a", "a"};
	
	//private int ticksElapsed = 0;

	@SuppressWarnings("unchecked")
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		if (ConfigurationHandler.allowCapes && mc.theWorld != null && mc.theWorld.getWorldTime() % 20 == 0 && !isPaused())
		{
			for (EntityPlayer entityPlayer : (List<EntityPlayer>) mc.theWorld.playerEntities)
			{
				if (entityPlayer instanceof AbstractClientPlayer)
				{
					AbstractClientPlayer player = (AbstractClientPlayer) entityPlayer;

					if (player != null)
					{
						if (devs.contains(StringUtils.stripControlCodes(player.username)))
						{
							CapeBufferDownload download = downloads.get(player.username);

							if (download == null)
							{
								download = new CapeBufferDownload(player.username, CAPE_URL);
								downloads.put(player.username, download);

								download.start();
							}
							else
							{
								if (!download.downloaded)
								{
									continue;
								}

								TPPITweaksUtils.setPrivateValue(player, download.getImage(), AbstractClientPlayer.class, AbstractClientPlayer_downloadImageCape);
								TPPITweaksUtils.setPrivateValue(player, download.getResourceLocation(), AbstractClientPlayer.class, AbstractClientPlayer_locationCape);
							}
						}
					}
				}
			}
		//ticksElapsed++;
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		// Do nothing
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel()
	{
		return "tppiTickHandler";
	}

	private boolean isPaused()
	{
		if (FMLClientHandler.instance().getClient().isSingleplayer() && !FMLClientHandler.instance().getClient().getIntegratedServer().getPublic())
		{
			GuiScreen screen = FMLClientHandler.instance().getClient().currentScreen;

			if (screen != null && screen.doesGuiPauseGame())
			{
				return true;
			}
		}

		return false;
	}
}
