package tppitweaks.block;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
    public static Block tppiBlock;

    public static Block teTank;
    public static Block teStrongbox;
    public static Block teCache;
    public static Block teDevice;
    public static Block teEnergyCell;

    public static Block eioReservoir;
    public static Block eioFusedQuartz;

    public static Block exuQuarry;
    public static Block exuDecor1;
    public static Block exuGenerator;
    public static Block exuPortal;
    public static Block exuPump;
    public static Block exuCurtain;
    public static Block exuSpikeDiamond;
    public static Block exuSpikeGold;
    public static Block exuCompressed;

    public static Block obBlockBreaker;
    public static Block obBlockPlacer;
    public static Block obTrophy;

    public static Block sfmManager;
    public static Block sfmCable;
    public static Block sfmCableRelay;
    public static Block sfmCableOutput;
    public static Block sfmCableInput;
    public static Block sfmCableIntake;
    public static Block sfmCableBreaker;

    public static Block mekBasicBlock;

    public static void initBlocks()
    {
        tppiBlock = new TPPIBlock();
        GameRegistry.registerBlock(tppiBlock, "tppi_block");

        teTank = GameRegistry.findBlock("ThermalExpansion", "Tank");
        teStrongbox = GameRegistry.findBlock("ThermalExpansion", "Strongbox");
        teCache = GameRegistry.findBlock("ThermalExpansion", "Cache");
        teDevice = GameRegistry.findBlock("ThermalExpansion", "Device");
        teEnergyCell = GameRegistry.findBlock("ThermalExpansion", "Cell");

        eioReservoir = GameRegistry.findBlock("EnderIO", "blockReservoir");
        eioFusedQuartz = GameRegistry.findBlock("EnderIO", "blockFusedQuartz");

        exuQuarry = GameRegistry.findBlock("ExtraUtilities", "enderQuarry");
        exuDecor1 = GameRegistry.findBlock("ExtraUtilities", "decorativeBlock1");
        exuGenerator = GameRegistry.findBlock("ExtraUtilities", "generator");
        exuPortal = GameRegistry.findBlock("ExtraUtilities", "dark_portal");
        exuPump = GameRegistry.findBlock("ExtraUtilities", "enderThermicPump");
        exuCurtain = GameRegistry.findBlock("ExtraUtilities", "curtains");
        exuQuarry = GameRegistry.findBlock("ExtraUtilities", "enderQuarry");
        exuSpikeDiamond = GameRegistry.findBlock("ExtraUtilities", "spike_base_diamond");
        exuSpikeGold = GameRegistry.findBlock("ExtraUtilities", "spike_base_gold");
        exuCompressed = GameRegistry.findBlock("ExtraUtilities", "cobblestone_compressed");

        obBlockBreaker = GameRegistry.findBlock("OpenBlocks", "blockBreaker");
        obBlockPlacer = GameRegistry.findBlock("OpenBlocks", "blockPlacer");
        obTrophy = GameRegistry.findBlock("OpenBlocks", "trophy");

        sfmManager = GameRegistry.findBlock("StevesFactoryManager", "BlockMachineManagerName");
        sfmCable = GameRegistry.findBlock("StevesFactoryManager", "BlockCableName");
        sfmCableRelay = GameRegistry.findBlock("StevesFactoryManager", "BlockCableRelayName");
        sfmCableOutput = GameRegistry.findBlock("StevesFactoryManager", "BlockCableOutputName");
        sfmCableInput = GameRegistry.findBlock("StevesFactoryManager", "BlockCableInputName");
        sfmCableIntake = GameRegistry.findBlock("StevesFactoryManager", "BlockCableIntakeName");
        sfmCableBreaker = GameRegistry.findBlock("StevesFactoryManager", "BlockCableBreakerName");

        mekBasicBlock = GameRegistry.findBlock("Mekanism", "BasicBlock");
    }

    public static void registerRecipes()
    {
        OreDictionary.registerOre("blockCompressedRedstone", tppiBlock);

        /* @formatter:off */
		if(OreDictionary.getOres("blockRedstone").isEmpty()) {

			GameRegistry.addShapedRecipe(new ItemStack(tppiBlock),
                "RRR",
                "RRR",
                "RRR",

                'R', Blocks.redstone_block
            );

		} else {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tppiBlock),
                "RRR",
                "RRR",
                "RRR",

                'R', "blockRedstone"
            ));

		}
		/* @formatter:on */

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.redstone_block, 9), "blockCompressedRedstone"));
    }

}
