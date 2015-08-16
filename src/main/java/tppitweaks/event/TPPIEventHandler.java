package tppitweaks.event;

import java.util.Random;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.WorldEvent;
import tppitweaks.tweak.aspect.TweakVanilla;
import tppitweaks.client.gui.IRCGui;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.tweak.recipe.TweakDimensionalAnchors;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TPPIEventHandler
{
    private boolean shouldLoadGUI = true;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onGui(GuiOpenEvent event)
    {
        if (event.gui instanceof GuiMainMenu)
        {
        	if (shouldLoadGUI && ConfigurationHandler.showIRCGui)
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
    public void worldLoaded(WorldEvent.Load event){
    	if (Loader.isModLoaded("Thaumcraft"))
    		TweakVanilla.onWorldLoad();
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event)
    {
        if (Loader.isModLoaded("DimensionalAnchors") && !Loader.isModLoaded("ChickenChunks"))
        {
            TweakDimensionalAnchors.addTooltip(event);
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
}