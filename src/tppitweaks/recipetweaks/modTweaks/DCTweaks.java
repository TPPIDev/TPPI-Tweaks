package tppitweaks.recipetweaks.modTweaks;

public class DCTweaks {

	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(ic2.core.Ic2Items.energyCrystal.itemID, -1);
	}
}
