package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;

public class DCTweaks {

	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(((Item)bluedart.item.DartItem.forceShears).itemID, 0);
	}
}
