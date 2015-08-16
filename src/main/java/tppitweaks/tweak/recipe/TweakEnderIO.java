package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.config.Config;
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

public class TweakEnderIO {

    public static Block reservoir = GameRegistry.findBlock("EnderIO", "blockReservoir");
    public static Block fusedQuartz = GameRegistry.findBlock("EnderIO", "blockFusedQuartz");
    public static Item capacitor = GameRegistry.findItem("EnderIO", "itemBasicCapacitor");

    @RecipeRemoval(requiredModids = "EnderIO")
    public static void init() {
        if (ConfigurationHandler.makeEIOHardModeEasier && Config.useHardRecipes) {
            TweakingRegistry.markItemForRecipeRemoval(reservoir, -1, TweakingAction.CHANGED, "Easy 'hardmode' recipe");
            TweakingRegistry.markItemForRecipeRemoval(capacitor, 0, TweakingAction.CHANGED, "Easy 'hardmode' recipe");
        }

    }

    @RecipeAddition(requiredModids = "EnderIO")
    public static void addRecipes() {
        if (ConfigurationHandler.makeEIOHardModeEasier && Config.useHardRecipes) {
            GameRegistry.addRecipe(new ShapedOreRecipe(reservoir,
                    "GGG",
                    "QcQ",
                    "GGG",

                    'G', new ItemStack(fusedQuartz, 1, 1),
                    'Q', new ItemStack(fusedQuartz),
                    'c', Items.cauldron
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(capacitor,
                    " CR",
                    "CGC",
                    "RC ",

                    'C', "ingotCopper",
                    'R', "dustRedstone",
                    'G', "ingotGold"
            ));
        }
    }
}
