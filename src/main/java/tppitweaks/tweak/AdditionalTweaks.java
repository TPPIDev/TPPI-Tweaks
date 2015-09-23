package tppitweaks.tweak;

import java.util.List;

import cpw.mods.fml.common.Loader;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;

public class AdditionalTweaks {

	public static void doOreDictTweaks() {
		// cross registering alumin(um/ium)
		for (ItemStack s : OreDictionary.getOres("dustAluminium"))
			OreDictionary.registerOre("dustAluminum", s);
		
		// fixing zinc smelting
		List<ItemStack> dirtyZincs = OreDictionary.getOres("dustImpureZinc");
		for (ItemStack stack : dirtyZincs) {
			ItemStack newStack = stack.copy();
			for (ItemStack stack1 : OreDictionary.getOres("ingotZinc"))
				GameRegistry.addSmelting(newStack, stack1.copy(), 0.1F);
		}

		if (Loader.isModLoaded("ExtraTrees"))
			OreDictionary.registerOre("dustWood", new ItemStack(GameRegistry.findItem("ExtraTrees", "misc"), 1, 1));
	}
	
	public static void addMiscRecipes() {
		GameRegistry.addRecipe(new ShapelessOreRecipe(Items.flint_and_steel, "nuggetSteel", Items.flint));
		GameRegistry.addRecipe(new ShapelessOreRecipe(Items.flint_and_steel, "ingotIron", Items.flint));
	}
}
