package tppitweaks.event;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tppitweaks.TPPITweaks;
import tppitweaks.client.gui.GuiHelper;
import tppitweaks.client.gui.IRCGui;
import tppitweaks.client.gui.MaricultureGui;
import tppitweaks.client.gui.UpdateGui;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.lib.Reference;
import tppitweaks.recipetweaks.modTweaks.DATweaks;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TPPIEventHandler
{
	private boolean shouldLoadGUI = true;

	public static boolean NBTValOnDeath;
	private String name, version, acronym;

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onGui(GuiOpenEvent event)
	{
		if (event.gui instanceof GuiMainMenu)
		{
			if (shouldLoadGUI && ConfigurationHandler.showDownloadGUI)
			{
				event.gui = new UpdateGui(event.gui, true);
				GuiHelper.updateGui = (UpdateGui) event.gui;
				shouldLoadGUI = false;

				ConfigurationHandler.manuallyChangeConfigValue("B:showDownloadGUI", "true", "false");
			}
			else if (shouldLoadGUI && ConfigurationHandler.showMaricultureGui)
			{
				event.gui = new MaricultureGui();
				shouldLoadGUI = false;
			}
			else if (shouldLoadGUI && ConfigurationHandler.showIRCGui)
			{
				event.gui = new IRCGui();
				shouldLoadGUI = false;
			}
			else
			{
				name = Reference.packName;
				version = Reference.packVersion;
				acronym = Reference.packAcronym;
				
				Field f = null;
				try
				{
					f = FMLCommonHandler.class.getDeclaredField("brandings");
				}
				catch (Exception e)
				{
					e.printStackTrace();
					TPPITweaks.logger.log(Level.WARNING, "Reflection error, " + acronym + " watermark will not be shown");
					return;
				}

				f.setAccessible(true);
				try
				{
					if (f.get(FMLCommonHandler.instance()) == null)
					{
						FMLCommonHandler.instance().computeBranding();
						doThisAgain();
					}
					else
					{
						addStuff(f);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
					TPPITweaks.logger.log(Level.WARNING, "Reflection error, " + acronym + " watermark will not be shown");
				}
			}
		}
	}

	private void doThisAgain()
	{
		Field f = null;
		try
		{
			f = FMLCommonHandler.class.getDeclaredField("brandings");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			TPPITweaks.logger.log(Level.WARNING, "Reflection error, " + acronym + " watermark will not be shown");
		}

		addStuff(f);
	}

	@SuppressWarnings("unchecked")
	private void addStuff(Field f)
	{
		f.setAccessible(true);
		try
		{
			ImmutableList<String> list = (ImmutableList<String>) f.get(FMLCommonHandler.instance());
			List<String> newList = new ArrayList<String>();

			for (String s : list)
			{
				if (s.contains("Feed"))
				{
					// Do nothing
				}
				else if (s.equals(name + " " + version))
				{
					// Do nothing
				}
				else if (s.contains("Forge") && !s.contains(":"))
				{
					String[] sa = s.split(" ");
					String firstLine = sa[0] + " " + sa[1];
					String secondLine = sa[2];
					newList.add(firstLine + ":");
					newList.add("    " + secondLine);
				}
				else
					newList.add(s);
			}

			newList.add(name + " " + version);

			f.set(FMLCommonHandler.instance(), ImmutableList.copyOf(newList));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			TPPITweaks.logger.log(Level.WARNING, "Reflection error, " + acronym + " watermark will not be shown");
		}
	}

	@ForgeSubscribe
	public void onLivingDeath(LivingDeathEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			savePlayerNBT((EntityPlayer) event.entityLiving);
		}
	}

	private void savePlayerNBT(EntityPlayer player)
	{
		TPPITweaks.logger.log(Level.INFO, "getting NBT");

		NBTValOnDeath = player.getEntityData().getCompoundTag("TPPI").getBoolean("hasBook");
	}

	public NBTTagCompound getTag(EntityPlayer entity, boolean useClassVal)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("hasBook", useClassVal ? NBTValOnDeath : true);

		return tag;
	}

	@ForgeSubscribe
	public void onItemTooltip(ItemTooltipEvent event)
	{
		if (Loader.isModLoaded("DimensionalAnchors") && !Loader.isModLoaded("ChickenChunks"))
		{
			DATweaks.addTooltip(event);
		}
	}
}