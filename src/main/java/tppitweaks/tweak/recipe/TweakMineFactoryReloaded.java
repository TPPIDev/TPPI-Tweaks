package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class TweakMineFactoryReloaded {

    public static Block machine1 = GameRegistry.findBlock("MineFactoryReloaded", "machine.1");
    public static Block machineBlock = GameRegistry.findBlock("MineFactoryReloaded", "machineblock");
    public static Item plasticCup = GameRegistry.findItem("MineFactoryReloaded", "plastic.cup");

    @RecipeRemoval(requiredModids = "MineFactoryReloaded")
    public static void init() {
        if (ConfigurationHandler.buffUnifierRecipe)
            TweakingRegistry.markItemForRecipeRemoval(machine1, 8, TweakingAction.CHANGED, "Cheapened for use", "in JABBA barrels");
        if (ConfigurationHandler.disablePlasticCups)
            TweakingRegistry.markItemForRecipeRemoval(plasticCup, -1, TweakingAction.REMOVED, "Has liquid dupes.");
    }

    @RecipeAddition(requiredModids = "MineFactoryReloaded")
    public static void addRecipes() {
        if (ConfigurationHandler.buffUnifierRecipe) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machineBlock, 3),
                    "RRR",
                    "SSS",

                    'R', "sheetPlastic",
                    'S', "stone"
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machine1, 1, 8),
                    "RRR",
                    "rCr",
                    " M ",

                    'R', "sheetPlastic",
                    'r', Items.redstone,
                    'C', Items.comparator,
                    'M', machineBlock
            ));
        }
    }
}
