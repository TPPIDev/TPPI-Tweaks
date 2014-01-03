package tppitweaks.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import appeng.api.Materials;
import cpw.mods.fml.common.registry.GameRegistry;
import tppitweaks.config.ConfigurationHandler;

public class ModItems {
	
	public static TPPIBook tppiBook;
	public static TPPIMaterial tppiMaterial;
	
	public static void initItems() {
		tppiBook = new TPPIBook(ConfigurationHandler.bookID);
		tppiMaterial = new TPPIMaterial(ConfigurationHandler.materialID);
	}
	
	public static void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(tppiBook.getBook(), Item.ingotIron, Item.paper, Item.paper, Item.paper);
		GameRegistry.addRecipe(new ItemStack(ModItems.tppiMaterial, 1, 0),
            new Object[] {
							"CSC",
							"SPS",
							"CSC",
                                                    
							'C', Materials.matFluxDust.copy(),
							'P', new ItemStack(Item.dyePowder, 1, 4),
							'S', Materials.matProcessorAdvanced.copy(),
			});
	}

}
