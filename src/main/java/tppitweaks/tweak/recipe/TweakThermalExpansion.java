package tppitweaks.tweak.recipe;

import cofh.thermalexpansion.util.crafting.FurnaceManager;
import cofh.thermalexpansion.util.crafting.PulverizerManager;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class TweakThermalExpansion {

    public static Block device = GameRegistry.findBlock("ThermalExpansion", "Device");
    public static Block cell = GameRegistry.findBlock("ThermalExpansion", "Cell");
    public static Block cache = GameRegistry.findBlock("ThermalExpansion", "Cache");
    public static Block tank = GameRegistry.findBlock("ThermalExpansion", "Tank");
    public static Block strongbox = GameRegistry.findBlock("ThermalExpansion", "Strongbox");
    public static Item satchel = GameRegistry.findItem("ThermalExpansion", "satchel");
    public static Item florb = GameRegistry.findItem("ThermalExpansion", "florb");
    public static Item material = GameRegistry.findItem("ThermalExpansion", "material");

    @RecipeRemoval(requiredModids = "ThermalExpansion")
    public static void init() {
        if (ConfigurationHandler.harderActivatorRecipe)
            TweakingRegistry.markItemForRecipeRemoval(device, 2, TweakingAction.CHANGED, "Recipe requires steel", "to make this a later game item");

        if (ConfigurationHandler.nerfTECaches) {
            TweakingRegistry.markItemForRecipeRemoval(cache, 1, TweakingAction.CHANGED, "Added Bin to basic recipes.");
            TweakingRegistry.markItemForRecipeRemoval(cache, 2, TweakingAction.CHANGED, "Added Bin to basic recipes.");
        }

        TweakingRegistry.markItemForRecipeRemoval(material, 513, TweakingAction.NOTE, "Recipe edited to be", "ore dictionary.");
        TweakingRegistry.markItemForRecipeRemoval(florb, 0, TweakingAction.NOTE, "Recipe edited to be", "ore dictionary");
        TweakingRegistry.markItemForRecipeRemoval(florb, 1, TweakingAction.NOTE, "Recipe edited to be", "ore dictionary");
    }

    @RecipeAddition(requiredModids = "ThermalExpansion")
    public static void addRecipes() {
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.paper, 3),
                "dustWood", "dustWood", "dustWood"
        ));

        if (!OreDictionary.getOres("itemRubber").isEmpty())
            FurnaceManager.addOreDictRecipe("resinIC2", OreDictionary.getOres("itemRubber").get(0).copy());

        if (ConfigurationHandler.harderActivatorRecipe) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(device, 1, 2),
                    "scs",
                    "tpt",
                    "sns",

                    's', OreDictionary.getOres("ingotSteel").isEmpty() ? "ingotIron" : "ingotSteel",
                    'p', Blocks.piston,
                    't', "ingotTin",
                    'c', Blocks.chest,
                    'n', material
            ));
        }

        if (OreDictionary.getOres("dustRuby").size() != 0)
            cofh.thermalexpansion.util.crafting.PulverizerManager.addIngotNameToDustRecipe(2400, "gemRuby", OreDictionary.getOres("dustRuby").get(0));

        if (OreDictionary.getOres("dustTinyWood").size() != 0) {
            ItemStack res = OreDictionary.getOres("dustTinyWood").get(0).copy();
            res.stackSize = 2;
            PulverizerManager.addIngotNameToDustRecipe(1000, "stickWood", res);
        }

        if (ConfigurationHandler.nerfTECaches) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(cache, 1, 1),
                    " t ",
                    "tbt",
                    " t ",
                    't', "ingotTin",
                    'b', Loader.isModLoaded("Mekanism") ? new ItemStack(TweakMekanism.basic, 1, 6) : "logWood"
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(cache, 1, 2),
                    " i ",
                    "ici",
                    " i ",
                    'i', "ingotInvar",
                    'c', new ItemStack(cache, 1, 1)
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(cache, 1, 2),
                    "iti",
                    "tbt",
                    "iti",
                    'i', "ingotInvar",
                    't', "ingotTin",
                    'b', Loader.isModLoaded("Mekanism") ? new ItemStack(TweakMekanism.basic, 1, 6) : "logWood"
            ));
        }

        OreDictionary.registerOre("dustWood", new ItemStack(material, 1, 512));
        OreDictionary.registerOre("pulpWood", new ItemStack(material, 1, 512));
        OreDictionary.registerOre("itemSlag", new ItemStack(material, 1, 514));
        OreDictionary.registerOre("magmaCream", Items.magma_cream);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(material, 1, 513),
                "sss",
                "s s",
                "sss",

                's', "dustWood"
        ));

        GameRegistry.addRecipe(new ShapelessOreRecipe(florb, "dustWood", "itemSlag", "slimeball"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(florb, 1, 1), "dustWood", "itemSlag", "slimeball", "dustBlaze"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(florb, 1, 1), "dustWood", "itemSlag", "magmaCream"));
    }
}
