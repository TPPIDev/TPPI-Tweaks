package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;

public class DCandIC2Tweaks
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(((ItemStack)ic2.core.Ic2Items.energyCrystal).itemID, -1);
	}
}
