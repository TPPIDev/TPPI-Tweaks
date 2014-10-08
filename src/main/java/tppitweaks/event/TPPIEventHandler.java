package tppitweaks.event;

import com.rwtema.menagerie.entities.Ents.EntityAnt;
import com.rwtema.menagerie.entities.Ents.EntityGhost;
import com.rwtema.menagerie.entities.Ents.EntityInfestedVillager;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tppitweaks.client.gui.IRCGui;
import tppitweaks.client.gui.MaricultureGui;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.modTweaks.DATweaks;
import tterrag.rtc.RecipeAddition;

import java.util.Random;

public class TPPIEventHandler
{
    private boolean shouldLoadGUI = true;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onGui(GuiOpenEvent event)
    {
        if (event.gui instanceof GuiMainMenu)
        {
            if (shouldLoadGUI && ConfigurationHandler.showMaricultureGui)
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
                ObfuscationReflectionHelper.setPrivateValue(GuiMainMenu.class, (GuiMainMenu) event.gui, getRandTPPISplash(), "splashText", "field_73975_c");
            }
        }
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event)
    {
        if (Loader.isModLoaded("DimensionalAnchors") && !Loader.isModLoaded("ChickenChunks"))
        {
            DATweaks.addTooltip(event);
        }
    }

    /**
     * Automatically figures out the number of splash text lines in the lang file, returns a random one.
     */
    private String getRandTPPISplash()
    {
        int max = 1;
        String base = "tppi.splash.text.";
        while (!StatCollector.translateToLocal(base + max).equals(base + max))
        {
            max++;
        }
        max--;

        int rand = new Random().nextInt(max) + 1;

        return StatCollector.translateToLocal("tppi.splash.text." + rand);
    }

	@RecipeAddition(requiredModids="menagerie")
	@SubscribeEvent
	public void onEntityLivingDrops(LivingDropsEvent event)
	{
		Random rand = new Random();

		if (event.entityLiving instanceof EntityInfestedVillager && event.source.getEntity() instanceof EntityPlayer && rand.nextInt(100) <= 10 && ConfigurationHandler.addDarkMenagerieMobDrops)
			event.entityLiving.entityDropItem(new ItemStack(Items.emerald), 1);

		if (event.entityLiving instanceof EntityAnt && ConfigurationHandler.addDarkMenagerieMobDrops)
		{
			event.entityLiving.entityDropItem(new ItemStack(Items.string, rand.nextInt(3), 0), 1);
			if (rand.nextInt(100) <= 10 && event.source.getEntity() instanceof EntityPlayer)
				event.entityLiving.entityDropItem(new ItemStack(Items.blaze_powder), 1);
		}

		if (event.entityLiving instanceof EntityGhost && event.source.getEntity() instanceof EntityPlayer && ConfigurationHandler.addDarkMenagerieMobDrops)
		{
			if (rand.nextInt(100) <= 10)
				event.entityLiving.entityDropItem(new ItemStack(Items.diamond), 1);
			if (rand.nextInt(1000) <= 1)
				event.entityLiving.entityDropItem(new ItemStack(Items.diamond_sword, 1, rand.nextInt(Items.diamond_sword.getMaxDamage())), 1);
		}
	}
}