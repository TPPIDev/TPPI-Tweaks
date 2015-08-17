package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.TPPITweaks;
import tppitweaks.block.ModBlocks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeAddition.EventTime;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

import java.lang.reflect.Field;
import java.util.HashMap;

public class TweakExtraUtilities {

    public static Block enderQuarry = GameRegistry.findBlock("ExtraUtilities", "enderQuarry");
    public static Block enderThermicPump = GameRegistry.findBlock("ExtraUtilities", "enderThermicPump");
    public static Block portal = GameRegistry.findBlock("ExtraUtilities", "dark_portal");
    public static Block decorative1 = GameRegistry.findBlock("ExtraUtilities", "decorativeBlock1");
    public static Block generator = GameRegistry.findBlock("ExtraUtilities", "generator");
    public static Block curtain = GameRegistry.findBlock("ExtraUtilities", "curtains");
    public static Block compressed = GameRegistry.findBlock("ExtraUtilities", "cobblestone_compressed");
    public static Block diamondSpike = GameRegistry.findBlock("ExtraUtilities", "spike_base_diamond");
    public static Item angelRing = GameRegistry.findItem("ExtraUtilities", "angelRing");
    public static Item soul = GameRegistry.findItem("ExtraUtilities", "mini-soul");
    public static Item destructionPickaxe = GameRegistry.findItem("ExtraUtilities", "destructionpickaxe");
    public static Item erosionShovel = GameRegistry.findItem("ExtraUtilities", "erosionShovel");
    public static Item buildersWand = GameRegistry.findItem("ExtraUtilities", "builderswand");
    public static Item unstableIngot = GameRegistry.findItem("ExtraUtilities", "unstableingot");

    @RecipeRemoval(requiredModids = "ExtraUtilities")
    public static void init() {
        if (ConfigurationHandler.nerfEnderQuarry) {
            TweakingRegistry.markItemForRecipeRemoval(enderQuarry, -1, TweakingAction.CHANGED, "Recipe changed to better", "balance with BC quarry recipe");
            TweakingRegistry.markItemForRecipeRemoval(decorative1, 11, TweakingAction.CHANGED, "Recipe changed for ender quarry");
        }

        if (ConfigurationHandler.nerfRedstoneGen)
            TweakingRegistry.markItemForRecipeRemoval(generator, 4, TweakingAction.CHANGED, "Recipe requires 2xcompressed redstone", "because it produces tonnes of RF");

        if (ConfigurationHandler.nerfEnderGen && !OreDictionary.getOres("blockEnderium").isEmpty())
            TweakingRegistry.markItemForRecipeRemoval(generator, 3, TweakingAction.CHANGED, "Recipe requires enderium", "because it produces tonnes of RF");

        if (ConfigurationHandler.nerfAngelRings)
            TweakingRegistry.markItemForRecipeRemoval(angelRing, -1, TweakingAction.CHANGED, "Recipe balanced around other", "mod's creative flight abilities");

        TweakingRegistry.markItemForRecipeRemoval(curtain, 0, TweakingAction.CHANGED, "Recipe changed to force black wool", "for compat with Malisis' Doors");

        if (ConfigurationHandler.harderDiamondSpikeRecipe)
            TweakingRegistry.markItemForRecipeRemoval(diamondSpike, 0, TweakingAction.CHANGED, "Recipe balanced to account", "for more valuable drops.");

        if (ConfigurationHandler.enableSoulFragmentRecipes)
            TweakingRegistry.addTweakedTooltip(soul, 0, TweakingAction.NOTE, "Added secondary recipes for souls, which can", "be auto-crafted to not lose health.");
    }

    @SuppressWarnings("unchecked")
    @RecipeAddition(requiredModids = "ExtraUtilities")
    public static void addRecipes() {
        if (ConfigurationHandler.nerfEnderQuarry) {
            ItemStack quadDirt = new ItemStack(compressed, 1, 11);
            ItemStack enderObs = new ItemStack(decorative1, 1, 1);
            ItemStack magicalWood = new ItemStack(decorative1, 1, 8);
            ItemStack enderCore = new ItemStack(decorative1, 1, 11);
            ItemStack DECM = new ItemStack(decorative1, 1, 12);


            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 2),
                    "AWS",
                    "PBP",
                    "GEG",

                    'A', destructionPickaxe,
                    'W', buildersWand,
                    'S', erosionShovel,
                    'P', portal,
                    'B', Blocks.iron_bars,
                    'G', enderCore,
                    'E', Loader.isModLoaded("ThermalExpansion") ? new ItemStack(TweakThermalExpansion.cell, 1, 4) : Items.ender_pearl
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 2),
                    "SWA",
                    "PBP",
                    "GEG",

                    'A', destructionPickaxe,
                    'W', buildersWand,
                    'S', erosionShovel,
                    'P', portal,
                    'B', Blocks.iron_bars,
                    'G', enderCore,
                    'E', Loader.isModLoaded("ThermalExpansion") ? new ItemStack(TweakThermalExpansion.cell, 1, 4) : Items.ender_pearl
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(enderQuarry,
                    "EQE",
                    "CDC",
                    "pPp",

                    'E', enderObs,
                    'Q', quadDirt,
                    'M', magicalWood,
                    'C', enderCore,
                    'D', DECM,
                    'P', new ItemStack(ModItems.tppiMaterial, 1, 2),
                    'p', enderThermicPump == null ? new ItemStack(decorative1, 1, 12) : enderThermicPump
            ));

            GameRegistry.addRecipe(enderCore,
                    "ABA",
                    "BEB",
                    "ABA",

                    'A', magicalWood,
                    'B', DECM,
                    'E', Items.ender_eye
            );
        }

        if (ConfigurationHandler.nerfRedstoneGen) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(generator, 1, 4),
                    "RRR",
                    "RGR",
                    "DFD",

                    'R', ModBlocks.tppiBlock,
                    'G', new ItemStack(generator, 1, 2),
                    'D', Items.redstone,
                    'F', Blocks.furnace
            ));
        }
        if (ConfigurationHandler.nerfEnderGen && !OreDictionary.getOres("blockEnderium").isEmpty()) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(generator, 1, 3),
                    "PPP",
                    "EBE",
                    "DFD",

                    'P', Items.ender_pearl,
                    'E', Items.ender_eye,
                    'B', "blockEnderium",
                    'D', Items.redstone,
                    'F', Blocks.furnace
            ));
        }
        if (ConfigurationHandler.nerfAngelRings) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(angelRing, 1, 0),
                    "GUG",
                    "NSN",
                    "WBW",

                    'G', new ItemStack(Blocks.glass),
                    'U', new ItemStack(unstableIngot),
                    'N', new ItemStack(Items.nether_star),
                    'S', new ItemStack(soul),
                    'W', Loader.isModLoaded("xreliquary") ? new ItemStack(GameRegistry.findItem("xreliquary", "mob_ingredient"), 1, 5) : new ItemStack(Items.feather),
                    'B', Loader.isModLoaded("TConstruct") ? new ItemStack(GameRegistry.findItem("TConstruct", "buckets"), 1, 1) : new ItemStack(Blocks.gold_block)
            ));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(angelRing, 1, 1),
                    "FUF",
                    "NSN",
                    "WBW",

                    'F', new ItemStack(Items.feather),
                    'U', new ItemStack(unstableIngot),
                    'N', new ItemStack(Items.nether_star),
                    'S', new ItemStack(soul),
                    'W', Loader.isModLoaded("xreliquary") ? new ItemStack(GameRegistry.findItem("xreliquary", "mob_ingredient"), 1, 5) : new ItemStack(Items.feather),
                    'B', Loader.isModLoaded("TConstruct") ? new ItemStack(GameRegistry.findItem("TConstruct", "buckets"), 1, 1) : new ItemStack(Blocks.gold_block)
            ));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(angelRing, 1, 2),
                    "DUP",
                    "NSN",
                    "WBW",

                    'D', new ItemStack(Items.dye, 1, 5),
                    'P', new ItemStack(Items.dye, 1, 9),
                    'U', new ItemStack(unstableIngot),
                    'N', new ItemStack(Items.nether_star),
                    'S', new ItemStack(soul),
                    'W', Loader.isModLoaded("xreliquary") ? new ItemStack(GameRegistry.findItem("xreliquary", "mob_ingredient"), 1, 5) : new ItemStack(Items.feather),
                    'B', Loader.isModLoaded("TConstruct") ? new ItemStack(GameRegistry.findItem("TConstruct", "buckets"), 1, 1) : new ItemStack(Blocks.gold_block)
            ));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(angelRing, 1, 3),
                    "LUL",
                    "NSN",
                    "WBW",

                    'L', new ItemStack(Items.leather),
                    'U', new ItemStack(unstableIngot),
                    'N', new ItemStack(Items.nether_star),
                    'S', new ItemStack(soul),
                    'W', Loader.isModLoaded("xreliquary") ? new ItemStack(GameRegistry.findItem("xreliquary", "mob_ingredient"), 1, 5) : new ItemStack(Items.feather),
                    'B', Loader.isModLoaded("TConstruct") ? new ItemStack(GameRegistry.findItem("TConstruct", "buckets"), 1, 1) : new ItemStack(Blocks.gold_block)
            ));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(angelRing, 1, 4),
                    "GUG",
                    "NSN",
                    "WBW",

                    'G', new ItemStack(Items.gold_nugget),
                    'U', new ItemStack(unstableIngot),
                    'N', new ItemStack(Items.nether_star),
                    'S', new ItemStack(soul),
                    'W', Loader.isModLoaded("xreliquary") ? new ItemStack(GameRegistry.findItem("xreliquary", "mob_ingredient"), 1, 5) : new ItemStack(Items.feather),
                    'B', Loader.isModLoaded("TConstruct") ? new ItemStack(GameRegistry.findItem("TConstruct", "buckets"), 1, 1) : new ItemStack(Blocks.gold_block)
            ));
        }

        try {
            Field f = OreDictionary.class.getDeclaredField("oreIDs");
            f.setAccessible(true);
            HashMap<String, Integer> temp = (HashMap<String, Integer>) f.get(null);
            temp.remove("nuggetUnstable");
            f.set(null, temp);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        if (ConfigurationHandler.fixExURecipes) {
            try {
                TPPITweaks.logger.info("You made me do this, RwTema, know that ;_;");
                TPPITweaks.logger.info("Fixing ExtraUtils OreDict registrations by hacky reflection");
                Field f = OreDictionary.class.getDeclaredField("oreIDs");
                f.setAccessible(true);
                HashMap<String, Integer> temp1 = (HashMap<String, Integer>) f.get(null);
                temp1.remove("blockUnstable");
                temp1.remove("burntquartz");
                temp1.remove("icestone");
                f.set(null, temp1);
            } catch (Throwable t) {
                t.printStackTrace();
            }

            OreDictionary.registerOre("blockUnstable", new ItemStack(decorative1, 1, 5));
            OreDictionary.registerOre("burntquartz", new ItemStack(decorative1, 1, 2));
            OreDictionary.registerOre("icestone", new ItemStack(decorative1, 1, 3));
        }

        if (Loader.isModLoaded("gregtech_addon"))
            TPPITweaks.logger.info("Stahp, greg, I know. Blame Tema.");

        GameRegistry.addRecipe(new ShapedOreRecipe(curtain,
                "xx", "xx", "xx",
                'x', "blockWoolBlack"
        ));

        if (ConfigurationHandler.harderDiamondSpikeRecipe) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("ExtraUtilities", "spike_base_diamond"), 3, 0),
                    " D ",
                    "DSD",
                    "SBS",
                    'D', new ItemStack(Items.diamond_sword),
                    'S', new ItemStack(GameRegistry.findItem("ExtraUtilities", "spike_base_gold")),
                    'B', Loader.isModLoaded("RandomThings") ? new ItemStack(GameRegistry.findItem("RandomThings", "spiritBinder")) : new ItemStack(Items.nether_star)
            ));
        }

        if (ConfigurationHandler.enableSoulFragmentRecipes) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 5),
                    "GSG",
                    "SDS",
                    "GSG",
                    'G', Loader.isModLoaded("TConstruct") ? new ItemStack(TweakTConstruct.heartCanister, 1, 5) : new ItemStack(Blocks.emerald_block),
                    'S', new ItemStack(Items.nether_star),
                    'D', new ItemStack(Blocks.dragon_egg)
            ));

            if (Loader.isModLoaded("StevesCarts")) {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 5),
                        "GSG",
                        "SBS",
                        "GSG",
                        'G', Loader.isModLoaded("TConstruct") ? new ItemStack(TweakTConstruct.heartCanister, 1, 5) : new ItemStack(Blocks.emerald_block),
                        'S', new ItemStack(Items.nether_star),
                        'B', new ItemStack(GameRegistry.findItem("StevesCarts", "BlockMetalStorage"), 1, 2)
                ));
            }
        }
    }

    @RecipeAddition(requiredModids = "ExtraUtilities", time = EventTime.WORLD_LOAD)
    public static void doPostLoadRecipeAdditions() {
        ItemStack stableIngot = new ItemStack(unstableIngot);
        stableIngot.stackTagCompound = new NBTTagCompound();
        stableIngot.stackTagCompound.setBoolean("stable", true);

        GameRegistry.addRecipe(new ItemStack(decorative1, 1, 5), "iii", "iii", "iii", 'i', unstableIngot);
        GameRegistry.addRecipe(stableIngot, "nnn", "nnn", "nnn", 'n', new ItemStack(unstableIngot, 1, 1));
    }

    public static IIcon getSoulFragmentIcon() {
        return soul.getIconFromDamage(0);
    }
}
