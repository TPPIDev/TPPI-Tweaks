package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class MalisisTweaks
{
    @RecipeRemoval(requiredModids = "malisisdoors")
    public static void removeRecipes()
    {
        Item curtains = GameRegistry.findItem("malisisdoors", "item.curtain");
        Block rustyLadder = GameRegistry.findBlock("malisisdoors", "rustyLadder");

        TweakingRegistry.markItemForRecipeRemoval(curtains, 0, TweakingAction.CHANGED, "Recipe changed to force red wool", "for compat with Extra Utils");
        
        TweakingRegistry.markItemForRecipeRemoval(rustyLadder, 0, TweakingAction.CHANGED, "Recipe change to remove conflict", "with Electrical Age wire.");
    }

    @RecipeAddition(requiredModids = "malisisdoors")
    public static void addRecipes()
    {
        Item curtains = GameRegistry.findItem("malisisdoors", "item.curtain");
        Block rustyLadder = GameRegistry.findBlock("malisisdoors", "rustyLadder");

        GameRegistry.addRecipe(new ShapedOreRecipe(curtains, "xx", "xx", "xx", 'x', "blockWoolRed"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rustyLadder, 2), "xxx", "   ", "xxx", 'x', "ingotIron"));
    }
}
