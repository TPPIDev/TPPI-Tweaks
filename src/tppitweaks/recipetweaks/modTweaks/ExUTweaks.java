package tppitweaks.recipetweaks.modTweaks;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class ExUTweaks
{
	
	public static void init() {
		if(ConfigurationHandler.nerfEnderQuarry) {
			TweakerBase.markItemForRecipeRemoval(extrautils.ExtraUtils.enderQuarry.blockID, -1);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void fixRecipes()
	{
		Iterator<IRecipe> iter = CraftingManager.getInstance().getRecipeList().listIterator();
		while (iter.hasNext())
		{
			IRecipe recipe = iter.next();

			ItemStack stack = recipe.getRecipeOutput();
			if (stack != null && stack.getItem() == extrautils.ExtraUtils.unstableIngot && stack.stackSize == 9)
			{
				iter.remove();
			}
			if (recipe != null && stack != null && stack.getItem().itemID == extrautils.ExtraUtils.decorative1Id && stack.getItemDamage() == 1)
			{
				ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;
				if (shapedRecipe.recipeItems.length > 0 && shapedRecipe.recipeItems[0] != null && recipe instanceof ShapedRecipes
						&& ((ItemStack) shapedRecipe.recipeItems[0]).getItem() == extrautils.ExtraUtils.unstableIngot)
				{
					iter.remove();
				}
			}
		}

		GameRegistry.addShapelessRecipe(new ItemStack(extrautils.ExtraUtils.unstableIngot, 9), new ItemStack(extrautils.ExtraUtils.decorative1, 1, 5));
		GameRegistry.addRecipe(new ItemStack(extrautils.ExtraUtils.decorative1, 1, 5), new Object[] { "iii", "iii", "iii",

		'i', new ItemStack(extrautils.ExtraUtils.unstableIngot) });
	}

	public static void addRecipes() {
		if(ConfigurationHandler.nerfEnderQuarry) {
				
			ItemStack enderSlave = new ItemStack(extrautils.ExtraUtils.portal);
			ItemStack octoCobble = new ItemStack(extrautils.ExtraUtils.cobblestoneCompr, 1, 7);
		    
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 3), new Object[] { "AWS", "PBP", "GEG", 
		    	Character.valueOf('A'), extrautils.ExtraUtils.destructionPickaxe, 
		    	Character.valueOf('W'), extrautils.ExtraUtils.buildersWand, 
		    	Character.valueOf('S'), extrautils.ExtraUtils.erosionShovel, 
		    	Character.valueOf('P'), octoCobble, 
		    	Character.valueOf('E'), enderSlave, 
		    	Character.valueOf('G'), extrautils.ExtraUtils.cursedEarth,
		    	Character.valueOf('B'), Block.fenceIron }));
		    
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 3), new Object[] { "SWA", "PBP", "GEG", 
		    	Character.valueOf('A'), extrautils.ExtraUtils.destructionPickaxe, 
		    	Character.valueOf('W'), extrautils.ExtraUtils.buildersWand, 
		    	Character.valueOf('S'), extrautils.ExtraUtils.erosionShovel, 
		    	Character.valueOf('P'), octoCobble, 
		    	Character.valueOf('E'), enderSlave, 
		    	Character.valueOf('G'), extrautils.ExtraUtils.cursedEarth,
		    	Character.valueOf('B'), Block.fenceIron }));
		    
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(extrautils.ExtraUtils.enderQuarry), new Object[] { "EsE", "CDC", "pPp", Character.valueOf('E'), new ItemStack(extrautils.ExtraUtils.decorative1, 1, 1), Character.valueOf('s'), "treeSapling", Character.valueOf('M'), new ItemStack(extrautils.ExtraUtils.decorative1, 1, 8), Character.valueOf('C'), new ItemStack(extrautils.ExtraUtils.decorative1, 1, 11), Character.valueOf('D'), new ItemStack(extrautils.ExtraUtils.decorative1, 1, 12), Character.valueOf('P'), new ItemStack(ModItems.tppiMaterial, 1, 3), Character.valueOf('p'), extrautils.ExtraUtils.enderThermicPump == null ? new ItemStack(extrautils.ExtraUtils.decorative1, 1, 12) : extrautils.ExtraUtils.enderThermicPump }));
		    
		}
	}
}
