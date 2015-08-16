package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

import java.util.Iterator;

public class TweakOpenBlocks {

    public static Block blockBreaker = GameRegistry.findBlock("OpenBlocks", "blockBreaker");
    public static Block blockPlacer = GameRegistry.findBlock("OpenBlocks", "blockPlacer");
    public static Block trohpy = GameRegistry.findBlock("OpenBlocks", "trophy");

    @RecipeRemoval(requiredModids = "OpenBlocks")
    public static void init() {
        if (ConfigurationHandler.eloraamBreakersAndDeployers) {
            if (blockBreaker != null)
                TweakingRegistry.markItemForRecipeRemoval(blockBreaker, -1, TweakingAction.CHANGED, "Recipe changed to bring back", "RP2-like recipes");

            if (blockPlacer != null)
                TweakingRegistry.markItemForRecipeRemoval(blockPlacer, -1, TweakingAction.CHANGED, "Recipe changed to bring back", "RP2-like recipes");
        }

        if (ConfigurationHandler.disablePigmenTrophyDrop) {
            MinecraftForge.EVENT_BUS.register(new TweakOpenBlocks());
        }
    }

    @RecipeAddition(requiredModids = "OpenBlocks")
    public static void addRecipes() {
        if (ConfigurationHandler.eloraamBreakersAndDeployers) {
            if (blockBreaker != null)
                GameRegistry.addRecipe(new ShapedOreRecipe(blockBreaker,
                        "CAC", "CPC", "CRC",
                        'C', "cobblestone",
                        'A', Items.iron_pickaxe,
                        'P', Blocks.piston,
                        'R', "dustRedstone"
                ));

            if (blockPlacer != null)
                GameRegistry.addRecipe(new ShapedOreRecipe(blockPlacer,
                        "CAC", "CPC", "CRC",
                        'C', "cobblestone",
                        'A', Blocks.chest,
                        'P', Blocks.piston,
                        'R', "dustRedstone"
                ));
        }
    }

    @SubscribeEvent
    public void onLivingDrop(LivingDropsEvent event) {
        if (event.entityLiving instanceof EntityPigZombie) {
            Iterator<EntityItem> iter = event.drops.iterator();
            while (iter.hasNext()) {
                ItemStack stack = iter.next().getEntityItem();
                if (stack != null && stack.getItem() == Item.getItemFromBlock(trohpy))
                    iter.remove();
            }
        }
    }
}
