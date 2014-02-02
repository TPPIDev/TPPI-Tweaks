package tppitweaks.recipetweaks.modTweaks;

public class DCandIC2Tweaks
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(ic2.core.Ic2Items.energyCrystal.itemID, -1);
	}
}
