package tppitweaks.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static Block tppiBlock;
	
	public static void initBlocks() {
		tppiBlock = new TPPIBlock(ConfigurationHandler.blockID);
		GameRegistry.registerBlock(tppiBlock, "tppi_block");
	}
	
	public static void registerRecipes() {
		if(OreDictionary.getOres("blockRedstone").isEmpty()) {
			
			GameRegistry.addShapedRecipe(new ItemStack(tppiBlock),
	            new Object[] {
							"RRR",
							"RRR",
							"RRR",
                                                    
							'R', Block.blockRedstone
				});
			
		}else {
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tppiBlock),
	            new Object[] {
							"RRR",
							"RRR",
							"RRR",
                                                    
							'R', "blockRedstone"
				}));
			
		}
	}
	
}
