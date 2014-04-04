package tppitweaks.recipetweaks.modTweaks;

import mcp.mobius.betterbarrels.BetterBarrels;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import powercrystals.minefactoryreloaded.setup.Machine;
import cpw.mods.fml.common.registry.GameRegistry;
import factorization.shared.Core;

public class JABBATweaks
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(BetterBarrels.barrelID, -1);
	}

	public static void addRecipes()
	{
		// This only works for oak barrels
		GameRegistry.addRecipe(new ShapedOreRecipe(BetterBarrels.blockBarrel, 
				"wBw",
				"wUw",
				"www",

				'w', "plankWood",
				'B', new ItemStack(Core.registry.daybarrel, 1, 2720),
				'U', Machine.Unifier.getItemStack()
		));
	}
}
