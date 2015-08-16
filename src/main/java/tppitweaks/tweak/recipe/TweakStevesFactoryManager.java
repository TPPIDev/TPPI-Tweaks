package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class TweakStevesFactoryManager {

    public static Block blockManager = GameRegistry.findBlock("StevesFactoryManager", "BlockMachineManagerName");
    public static Block blockCable = GameRegistry.findBlock("StevesFactoryManager", "BlockCableName");
    public static Block blockCableRelay = GameRegistry.findBlock("StevesFactoryManager", "BlockCableRelayName");
    public static Block blockCableInput = GameRegistry.findBlock("StevesFactoryManager", "BlockCableInputName");
    public static Block blockCableOutput = GameRegistry.findBlock("StevesFactoryManager", "BlockCableOutputName");
    public static Block blockCableIntake = GameRegistry.findBlock("StevesFactoryManager", "BlockCableIntakeName");
    public static Block blockCableBreaker = GameRegistry.findBlock("StevesFactoryManager", "BlockCableBreakerName");

    @RecipeRemoval(requiredModids = {"appliedenergistics2", "StevesFactoryManager"})
    public static void init() {
        if (ConfigurationHandler.tweakSFM) {
            TweakingRegistry.markItemForRecipeRemoval(blockManager, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
            TweakingRegistry.markItemForRecipeRemoval(blockCable, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
            TweakingRegistry.markItemForRecipeRemoval(blockCableRelay, 8, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
            TweakingRegistry.markItemForRecipeRemoval(blockCableInput, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
            TweakingRegistry.markItemForRecipeRemoval(blockCableOutput, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
            TweakingRegistry.markItemForRecipeRemoval(blockCableIntake, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
            TweakingRegistry.markItemForRecipeRemoval(blockCableBreaker, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
        }
    }

    @RecipeAddition(requiredModids = {"appliedenergistics2", "StevesFactoryManager"})
    public static void addRecipes() {
        if (ConfigurationHandler.tweakSFM) {

            Item ae2Material = GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial");
            Item ae2Part = GameRegistry.findItem("appliedenergistics2", "item.ItemMultiPart");

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockManager),
                    "III",
                    "aRf",
                    "SSS",

                    'R', new ItemStack(ModItems.tppiMaterial),
                    'f', new ItemStack(ae2Material, 1, 43),
                    'a', new ItemStack(ae2Material, 1, 44),
                    'I', "ingotIron",
                    'S', "stone"
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCable, 8),
                    "GPG",
                    "IRI",
                    "GPG",

                    'R', "dustFluix",
                    'G', "blockGlass",
                    'I', "ingotIron",
                    'P', Blocks.heavy_weighted_pressure_plate
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCableRelay, 1, 8),
                    "fBf",
                    "BIB",
                    "fBf",

                    'B', "blockLapis",
                    'I', blockCableRelay,
                    'f', "dustFluix"
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCableInput),
                    " r ",
                    "rIr",
                    " r ",

                    'r', "dustRedstone",
                    'I', blockCable
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCableOutput),
                    "rtr",
                    "rIr",
                    "rrr",

                    'r', "dustRedstone",
                    't', new ItemStack(ae2Part, 1, 280),
                    'I', blockCable
            ));

            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(blockCableIntake, 1, 0),
                    blockCable,
                    Blocks.hopper,
                    Blocks.dropper,
                    new ItemStack(ae2Part, 1, 440)
            ));

            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(blockCableIntake, 1, 8),
                    new ItemStack(blockCableIntake, 1, 0),
                    new ItemStack(ae2Material, 1, 23)
            ));

            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(blockCableBreaker),
                    blockCable,
                    Items.iron_pickaxe,
                    Blocks.dispenser,
                    new ItemStack(ae2Part, 1, 440)
            ));
        }
    }
}

