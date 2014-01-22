package tppitweaks.recipetweaks.modTweaks;

import ic2.core.Ic2Items;

import java.util.ListIterator;

import com.sun.imageio.plugins.common.I18N;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.StatCollector;
import bluedart.core.recipes.MisshapenDartCrafting;
import bluedart.core.recipes.ShapedDartCrafting;

public class DCTweaks {

	public static boolean dartCheck(boolean canTweak, IRecipe r) {
		
		//Don't think we need try/catch here due to how it's set up in canRemoveRecipe
		return canTweak && r instanceof ShapedDartCrafting && r.getRecipeOutput().itemID == Ic2Items.energyCrystal.itemID;
	}

	public static void removeRecipe(ListIterator<IRecipe> listIterator)
	{
		while (listIterator.hasNext())
		{
			IRecipe r = listIterator.next();
			if (r.getRecipeOutput() != null && (r.getRecipeOutput().itemID == Ic2Items.energyCrystal.itemID))
			{
				System.out.println(r.getRecipeOutput().itemID);
				listIterator.remove();
			}
		}
	}

	public static void init()
	{
		//TweakerBase.recipesToRemove.put(((Item)Ic2Items.energyCrystal.getItem()).itemID - 256, -1);		
	}
	
}
