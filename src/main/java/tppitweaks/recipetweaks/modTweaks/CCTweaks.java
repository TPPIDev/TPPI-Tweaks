package tppitweaks.recipetweaks.modTweaks;


public class CCTweaks
{
//	@RecipeRemoval(requiredModids = "ComputerCraft", time = EventTime.POST_INIT)
	public static void removeRecipes()
	{
/*		TweakingRegistry.markItemForRecipeRemoval(ComputerCraft.computerBlockID, 0);
		TweakingRegistry.markItemForRecipeRemoval(ComputerCraft.computerBlockID, 16384);
		TweakingRegistry.markItemForRecipeRemoval(CCTurtle.turtleBlockID, 0);
		
		TPPITweaks.logger.info("Removed IDs: " + ComputerCraft.computerBlockID + ", " + CCTurtle.turtleBlockID);
		
		removeAdvancedTurtle();*/
	}

	@SuppressWarnings({ "unused" })
	private static void removeAdvancedTurtle()
	{
		/*Iterator<IRecipe> iter = CraftingManager.getInstance().getRecipeList().iterator();
		while (iter.hasNext())
		{
			IRecipe recipe = iter.next();
			if (recipe instanceof ShapedRecipes)
			{
				ShapedRecipes sr = (ShapedRecipes) recipe;
				ItemStack stack = sr.getRecipeOutput();
				Item item = Item.itemsList[CCTurtle.turtleAdvancedBlockID];
				if (stack != null && item == stack.getItem() && stack.stackTagCompound != null && stack.stackTagCompound.equals(new NBTTagCompound()))
				{
					RecipeTweakingCore.log("Removing advanced turtle recipe");
					iter.remove();
				}
			}
		}*/
	}
}
