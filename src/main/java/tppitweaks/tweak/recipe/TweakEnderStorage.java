package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class TweakEnderStorage {

    public static Block enderChest = GameRegistry.findBlock("EnderStorage", "enderChest");
    public static Item enderPouch = GameRegistry.findItem("EnderStorage", "enderPouch");

    @RecipeRemoval(requiredModids = {"EnderStorage", "ThermalExpansion"})
    public static void init() {
        if (ConfigurationHandler.enderPouchNerf)
            TweakingRegistry.markItemForRecipeRemoval(enderPouch, -1, TweakingAction.CHANGED, "Recipe requires resonant satchel", "to match chest & tank recipes.");

        for (int i = 0; i < 16; i++) {
            if (ConfigurationHandler.enderChestNerf)
                TweakingRegistry.markItemForRecipeRemoval(enderChest, getFreqFromColours(i, i, i), TweakingAction.CHANGED, "Recipe requires resonant strongbox", "because it is able to teleport things.");

            if (ConfigurationHandler.enderTankNerf)
                TweakingRegistry.markItemForRecipeRemoval(enderChest, getFreqFromColours(i, i, i) + 4096, TweakingAction.CHANGED, "Recipe requires resonant tank", "because it is able to teleport things.");
        }
    }

    @RecipeAddition(requiredModids = {"EnderStorage", "ThermalExpansion"})
    public static void addRecipes() {
        ItemStack resonantTank = new ItemStack(TweakThermalExpansion.tank, 1, 4);
        ItemStack resonantChest = new ItemStack(TweakThermalExpansion.strongbox, 1, 4);
        ItemStack resonantSatchel = new ItemStack(TweakThermalExpansion.satchel, 1, 4);

        for (int i = 0; i < 16; i++) {
            if (ConfigurationHandler.enderChestNerf) {
                GameRegistry.addRecipe(new ItemStack(enderChest, 2, getFreqFromColours(i, i, i)),
                        "bWb",
                        "OCO",
                        "bOb",

                        'b', Items.blaze_rod,
                        'C', resonantChest,
                        'O', Blocks.obsidian,
                        'W', new ItemStack(Blocks.wool, 1, i)
                );
            }

            if (ConfigurationHandler.enderTankNerf) {
                GameRegistry.addRecipe(new ItemStack(enderChest, 2, getFreqFromColours(i, i, i) + 4096),
                        "bWb",
                        "OCO",
                        "bOb",

                        'b', Items.blaze_rod,
                        'C', resonantTank,
                        'O', Blocks.obsidian,
                        'p', Items.ender_pearl,
                        'W', new ItemStack(Blocks.wool, 1, i)
                );
            }

            if (ConfigurationHandler.enderPouchNerf) {
                GameRegistry.addRecipe(new ItemStack(enderPouch, 1, getFreqFromColours(i, i, i)),
                        "pWp",
                        "lSl",
                        "plp",

                        'p', Items.blaze_powder,
                        'l', Items.leather,
                        'W', new ItemStack(Blocks.wool, 1, i),
                        'S', resonantSatchel
                );
            }
        }
    }

    // Borrowed from CB to remove the local lib requirement
    private static int getFreqFromColours(int colour1, int colour2, int colour3) {
        return ((colour1 & 15) << 8) + ((colour2 & 15) << 4) + (colour3 & 15);
    }
}
