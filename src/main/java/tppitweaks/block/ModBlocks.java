package tppitweaks.block;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static Block tppiBlock;

    public static void initBlocks() {
        tppiBlock = new TPPIBlock();
        GameRegistry.registerBlock(tppiBlock, "tppi_block");
    }

    public static void registerRecipes() {
        OreDictionary.registerOre("blockCompressedRedstone", tppiBlock);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tppiBlock),

				"RRR",
				"RRR",
				"RRR",

				'R', OreDictionary.getOres("blockRedstone").isEmpty() ? new ItemStack(tppiBlock) : "blockRedstone"
		));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.redstone_block, 9), "blockCompressedRedstone"));
    }
}
