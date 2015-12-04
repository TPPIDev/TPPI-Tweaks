package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.TPPITweaks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TweakMekanism {

    public static Block basic = GameRegistry.findBlock("Mekanism", "BasicBlock");
    public static Block cardboardBox = GameRegistry.findBlock("Mekanism", "CardboardBox");
    public static Block machineBlock = GameRegistry.findBlock("Mekanism", "MachineBlock");
    public static Block partTransmitter = GameRegistry.findBlock("Mekanism", "PartTransmitter");
    public static Item atomicDisassembler = GameRegistry.findItem("Mekanism", "AtomicDisassembler");
    public static Item atomicAlloy = GameRegistry.findItem("Mekanism", "AtomicAlloy");
    public static Item reinforcedAlloy = GameRegistry.findItem("Mekanism", "ReinforcedAlloy");
    public static Item teleportationCore = GameRegistry.findItem("Mekanism", "TeleportationCore");
    public static Item speedUpgrade = GameRegistry.findItem("Mekanism", "SpeedUpgrade");
    public static Item energyUpgrade = GameRegistry.findItem("Mekanism", "EnergyUpgrade");
    public static Item controlCircuit = GameRegistry.findItem("Mekanism", "ControlCircuit");
    public static Item energyTablet = GameRegistry.findItem("Mekanism", "EnergyTablet");
    public static Item clump = GameRegistry.findItem("Mekanism", "Clump");
    public static Item electrolyticCore = GameRegistry.findItem("Mekanism", "ElectrolyticCore");
    public static Item compressedDiamond = GameRegistry.findItem("Mekanism", "CompressedDiamond");
    public static Item compressedObsidian = GameRegistry.findItem("Mekanism", "CompressedObsidian");
    public static Item robit = GameRegistry.findItem("Mekanism", "Robit");

    @RecipeRemoval(requiredModids = "Mekanism")
    public static void init() {
        if (ConfigurationHandler.harderDisassemblerRecipe) {
            TweakingRegistry.markItemForRecipeRemoval(atomicDisassembler, -1, TweakingAction.CHANGED, "Changed to ensure", "balance with all other tools");
            TweakingRegistry.markItemForRecipeRemoval(atomicAlloy, -1, TweakingAction.CHANGED, "Changed to further", "balance all of Mekanism");
        }

        TweakingRegistry.addTweakedTooltip(compressedDiamond, 0, TweakingAction.REMOVED, "Removed to further increase", "costs of Mekanism");
        TweakingRegistry.addTweakedTooltip(compressedObsidian, 0, TweakingAction.REMOVED, "Removed to further increase", "costs of Mekanism");

        if (ConfigurationHandler.disableCardboardBox)
            TweakingRegistry.markItemForRecipeRemoval(cardboardBox, -1);

        if (ConfigurationHandler.disableUniversalCables)
            for (int i = 0; i < 4; i++)
                TweakingRegistry.markItemForRecipeRemoval(partTransmitter, i, TweakingAction.REMOVED, "Crashes");

        if (ConfigurationHandler.disableMiner || ConfigurationHandler.nerfMiner)
            TweakingRegistry.markItemForRecipeRemoval(machineBlock, 4, TweakingAction.CHANGED, "Changed to balance better", "with other quarry-like blocks");
    }

    @RecipeAddition(requiredModids = "Mekanism")
    public static void addRecipes() {
        if (ConfigurationHandler.harderDisassemblerRecipe)
            doMaterialNerfs();

        if (ConfigurationHandler.nerfMiner)
            doMinerNerf();

        ArrayList<ItemStack> oreIn = null, dustOut = null;

        if (!(oreIn = OreDictionary.getOres("oreAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            out.stackSize++;
            for (ItemStack i : oreIn)
                addEnrichmentChamberRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("oreVinteum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustVinteum")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            out.stackSize++;
            for (ItemStack i : oreIn)
                addEnrichmentChamberRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("oreYellorite")).isEmpty() && !(dustOut = OreDictionary.getOres("dustYellorium")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            out.stackSize++;
            for (ItemStack i : oreIn)
                addEnrichmentChamberRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("oreRuby")).isEmpty() && !(dustOut = OreDictionary.getOres("dustRuby")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            out.stackSize++;
            for (ItemStack i : oreIn)
                addEnrichmentChamberRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("oreSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustSapphire")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            out.stackSize++;
            for (ItemStack i : oreIn)
                addEnrichmentChamberRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("oreOlivine")).isEmpty() && !(dustOut = OreDictionary.getOres("dustOlivine")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            out.stackSize++;
            for (ItemStack i : oreIn)
                addEnrichmentChamberRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("ingotAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            for (ItemStack i : oreIn)
                addCrusherRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("ingotYellorium")).isEmpty() && !(dustOut = OreDictionary.getOres("dustYellorium")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            for (ItemStack i : oreIn)
                addCrusherRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("gemRuby")).isEmpty() && !(dustOut = OreDictionary.getOres("dustRuby")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            for (ItemStack i : oreIn)
                addCrusherRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("gemSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustSapphire")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            for (ItemStack i : oreIn)
                addCrusherRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("gemGreenSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustGreenSapphire")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            for (ItemStack i : oreIn)
                addCrusherRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("gemOlivine")).isEmpty() && !(dustOut = OreDictionary.getOres("dustOlivine")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            for (ItemStack i : oreIn)
                addCrusherRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("ingotAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            for (ItemStack i : oreIn)
                addCrusherRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("ingotPlatinum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustPlatinum")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            for (ItemStack i : oreIn)
                addCrusherRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("ingotElectrum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustElectrum")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            for (ItemStack i : oreIn)
                addCrusherRecipe(i.copy(), out);
        }

        if (!(oreIn = OreDictionary.getOres("ingotInvar")).isEmpty() && !(dustOut = OreDictionary.getOres("dustInvar")).isEmpty()) {
            ItemStack out = dustOut.get(0).copy();
            for (ItemStack i : oreIn)
                addCrusherRecipe(i.copy(), out);
        }

        addCrusherRecipe(new ItemStack(Items.bone), new ItemStack(Items.dye, 5, 15));
        addCrusherRecipe(new ItemStack(Blocks.red_flower), new ItemStack(Items.dye, 2, 1));
        addCrusherRecipe(new ItemStack(Blocks.yellow_flower), new ItemStack(Items.dye, 2, 11));
    }

    private static void doMaterialNerfs() {
        GameRegistry.addRecipe(new ShapedOreRecipe(getUnchargedItem(atomicDisassembler),
                "AtA",
                "ADA",
                " o ",

                'D', new ItemStack(ModItems.tppiMaterial, 1, 1),
                't', getUnchargedItem(energyTablet),
                'o', "ingotRefinedObsidian",
                'A', atomicAlloy
        ));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(atomicAlloy),
                "oao",
                "aea",
                "oao",

                'o', "ingotRefinedObsidian",
                'e', electrolyticCore,
                'a', reinforcedAlloy
        ));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 1),
                "asa",
                "ete",
                "asa",

                't', teleportationCore,
                's', speedUpgrade,
                'e', energyUpgrade,
                'a', atomicAlloy
        ));
    }

    private static void doMinerNerf() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 3),
                "0c2",
                "314",
                "5c6",

                'c', new ItemStack(controlCircuit, 1, 1),
                '0', setClump(clump, 0),
                '1', setClump(clump, 1),
                '2', setClump(clump, 2),
                '3', setClump(clump, 3),
                '4', setClump(clump, 4),
                '5', setClump(clump, 5),
                '6', setClump(clump, 6)
        ));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machineBlock, 1, 4),
                "AcA",
                "LRL",
                "MCM",

                'A', atomicAlloy,
                'c', new ItemStack(ModItems.tppiMaterial, 1, 3),
                'L', new ItemStack(machineBlock, 1, 15),
                'R', getUnchargedItem(robit),
                'M', new ItemStack(basic, 1, 8),
                'C', getUnchargedItem(atomicDisassembler)
        ));
    }

    private static ItemStack setClump(Item item, int dmg) {
        ItemStack stack = new ItemStack(item);
        stack.setItemDamage(dmg);
        return stack;
    }

    private static ItemStack getUnchargedItem(Item item) {
        ItemStack ret = new ItemStack(item, 1);
        ret.setItemDamage(100);
        return ret;
    }

    public static IIcon getCircuitIcon() {
        return controlCircuit.getIconFromDamage(1);
    }

    @SuppressWarnings("unchecked")
    public static void addCrusherRecipe(ItemStack input, ItemStack output) {
        try {
            Class recipeClass = Class.forName("mekanism.common.recipe.RecipeHandler");
            Method m = recipeClass.getMethod("addCrusherRecipe", ItemStack.class, ItemStack.class);
            m.invoke(null, input, output);
        } catch(Exception e) {
            TPPITweaks.logger.error("Error while adding recipe: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void addEnrichmentChamberRecipe(ItemStack input, ItemStack output) {
        try {
            Class recipeClass = Class.forName("mekanism.common.recipe.RecipeHandler");
            Method m = recipeClass.getMethod("addEnrichmentChamberRecipe", ItemStack.class, ItemStack.class);
            m.invoke(null, input, output);
        } catch(Exception e) {
            System.err.println("Error while adding recipe: " + e.getMessage());
        }
    }
}
