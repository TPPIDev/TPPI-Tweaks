package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class AM2Tweaks
{
	public static void addRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(am2.items.ItemsCommonProxy.spawnEgg, 1, 12), new Object[] { "CCC", "CPC", "CCC", 'C', new ItemStack(am2.items.ItemsCommonProxy.essence, 1, 5), 'P',
			new ItemStack(am2.blocks.BlocksCommonProxy.aum) });
	}
}
