package tppitweaks.recipetweaks.modTweaks;

import ic2.core.Ic2Items;
import net.minecraft.item.crafting.IRecipe;
import bluedart.core.recipes.ShapedDartCrafting;

public class DCTweaks {

	public static boolean dartCheck(boolean canTweak, IRecipe r) {
		
		//Don't think we need try/catch here due to how it's set up in canRemoveRecipe
		return canTweak && r instanceof ShapedDartCrafting && r.getRecipeOutput().itemID == Ic2Items.energyCrystal.itemID;
		
	}
	
}
