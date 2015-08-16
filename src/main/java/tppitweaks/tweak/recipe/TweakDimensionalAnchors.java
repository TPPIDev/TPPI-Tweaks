package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;

public class TweakDimensionalAnchors {

    public static ItemStack anchor = new ItemStack(GameRegistry.findBlock("DimensionalAnchors", "chunkloader"));

    @RecipeRemoval(requiredModids = "DimensionalAnchors")
    public static void init() {
        if (ConfigurationHandler.tweakDA)
            TweakingRegistry.markItemForRecipeRemoval(anchor.getItem(), -1);
    }

    @RecipeAddition(requiredModids = "DimensionalAnchors")
    public static void addRecipes() {
        if (ConfigurationHandler.tweakDA)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(anchor.getItem(), 1, 0),
                    "ded",
                    "oIo",
                    "gog",

                    'd', "gemDiamond",
                    'e', Items.ender_pearl,
                    'o', Blocks.obsidian,
                    'I', "blockIron",
                    'g', "ingotGold"
            ));
    }

    public static void addTooltip(ItemTooltipEvent event) {
        if (event.itemStack.getItem() == anchor.getItem())
            event.toolTip.add("\u00A7oA chunk loader");
    }
}
