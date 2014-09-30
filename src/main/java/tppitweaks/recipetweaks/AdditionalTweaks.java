package tppitweaks.recipetweaks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.TPPITweaks;
import cpw.mods.fml.common.registry.GameRegistry;

public class AdditionalTweaks
{
	@SuppressWarnings("unchecked")
	public static void doOreDictTweaks()
	{
		// cross registering alumin(um/ium)
		for (ItemStack s : OreDictionary.getOres("dustAluminium"))
		{
			OreDictionary.registerOre("dustAluminum", s);
		}
		
		// fixing zinc smelting
		List<ItemStack> dirtyZincs = OreDictionary.getOres("dustImpureZinc");
		for (ItemStack stack : dirtyZincs)
		{
			ItemStack newStack = stack.copy();
			for (ItemStack stack1 : OreDictionary.getOres("ingotZinc"))
				GameRegistry.addSmelting(newStack, stack1.copy(), 0.1F);
		}
		
		// fixing fused quartz
		try
		{
			int id = OreDictionary.getOreID("glassHardened");
			Field f = OreDictionary.class.getDeclaredField("oreStacks");
			f.setAccessible(true);
			HashMap<Integer, ArrayList<ItemStack>> temp = (HashMap<Integer, ArrayList<ItemStack>>) f.get(null);
			ArrayList<ItemStack> glasses = (ArrayList<ItemStack>) temp.get(id).clone();
			for (int i = 0; i < glasses.size(); i++)
				if (glasses.get(i).getUnlocalizedName().toLowerCase().contains("fused"))
					glasses.remove(i);
			temp.remove(id);
			temp.put(id, glasses);
			f.set(null, temp);
		}
		catch (Throwable t)
		{
			TPPITweaks.logger.error("Fixing EnderIO via reflection failed!");
			t.printStackTrace();
		}
	}
	
	public static void addMiscRecipes()
	{
		GameRegistry.addRecipe(new ShapelessOreRecipe(Items.flint_and_steel, "nuggetSteel", Items.flint));
		GameRegistry.addRecipe(new ShapelessOreRecipe(Items.flint_and_steel, "ingotIron", Items.flint));
	}
}
