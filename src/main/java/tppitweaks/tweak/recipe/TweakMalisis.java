package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class TweakMalisis {

    public static Block rustyLadder = GameRegistry.findBlock("malisisdoors", "rustyLadder");
    public static Item curtainsItem = GameRegistry.findItem("malisisdoors", "item.curtain");

    @RecipeRemoval(requiredModids = "malisisdoors")
    public static void removeRecipes() {
        TweakingRegistry.markItemForRecipeRemoval(curtainsItem, 0, TweakingAction.CHANGED, "Recipe changed to force red wool", "for compat with Extra Utils");

        TweakingRegistry.markItemForRecipeRemoval(rustyLadder, 0, TweakingAction.CHANGED, "Recipe change to remove conflict", "with Electrical Age wire.");
    }

    @RecipeAddition(requiredModids = "malisisdoors")
    public static void addRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(curtainsItem,
                "xx", "xx", "xx",
                'x', "blockWoolRed"
        ));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rustyLadder, 2),
                "xxx",
                "   ",
                "xxx",
                'x', "ingotIron"
        ));
    }
}
