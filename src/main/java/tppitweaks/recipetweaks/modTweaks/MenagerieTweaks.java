package tppitweaks.recipetweaks.modTweaks;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.event.EntityAnt;
import tppitweaks.event.EntityGhost;
import tppitweaks.event.EntityInfestedVillager;
import tterrag.rtc.RecipeAddition;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MenagerieTweaks
{
    @RecipeAddition(requiredModids="menagerie")
    public void register()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onLivingDrop(LivingDropsEvent event)
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
