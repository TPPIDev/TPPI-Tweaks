package tppitweaks.recipetweaks;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.TPPITweaks;
import tppitweaks.recipetweaks.RecipeAddition.EventTime;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeTweaks
{
	public static boolean recipesInitialized;

	@SuppressWarnings("unchecked")
	private static void doOreDictTweaks()
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
				FurnaceRecipes.smelting().addSmelting(newStack.itemID, newStack.getItemDamage(), stack1.copy(), 0.1F);
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
			TPPITweaks.logger.severe("Fixing EnderIO via reflection failed!");
			t.printStackTrace();
		}
	}
	
	private static void addMiscRecipes()
	{
		GameRegistry.addRecipe(new ShapelessOreRecipe(Item.flintAndSteel, "nuggetSteel", Item.flint));
		GameRegistry.addRecipe(new ShapelessOreRecipe(Item.flintAndSteel, "ingotIron", Item.flint));
	}
	
	public static void removeRecipes()
	{
		try
		{
			ClassPath classpath = ClassPath.from(RecipeTweaks.class.getClassLoader());
			Set<ClassInfo> classes = classpath.getTopLevelClasses("tppitweaks.recipetweaks.modTweaks");
			
			for (ClassInfo c : classes)
			{
				for (Method m : loadClassSafe(c))
				{
					RecipeRemoval r = m.getAnnotation(RecipeRemoval.class);
					if (r != null && allModsLoaded(r.requiredModids()))
					{
						TPPITweaks.logger.info("Removing recipes in method " + m.getName() + " in class " + c.getName());
						m.invoke(null, new Object[]{});
					}
				}
			}
		}
		catch (Throwable t)
		{
			TPPITweaks.logger.severe("Could not perform recipe removals. This is a serious error!");
			throw new RuntimeException(t);
		}
		
		TweakingRegistry.removeRecipes();
	}
	
	public static void addRecipes(EventTime time)
	{
		try
		{
			ClassPath classpath = ClassPath.from(RecipeTweaks.class.getClassLoader());
			Set<ClassInfo> classes = classpath.getTopLevelClasses("tppitweaks.recipetweaks.modTweaks");
			
			for (ClassInfo c : classes)
			{
				for (Method m : loadClassSafe(c))
				{
					RecipeAddition r = m.getAnnotation(RecipeAddition.class);
					if (r != null && allModsLoaded(r.requiredModids()) && r.time() == time)
					{
						TPPITweaks.logger.info("Adding recipes in method " + m.getName() + " in class " + c.getName());
						m.invoke(null, new Object[]{});
					}
				}
			}
		}
		catch (Throwable t)
		{
			TPPITweaks.logger.severe("Could not perform recipe additions. This is a serious error!");
			throw new RuntimeException(t);
		}
	}
	
	private static Method[] loadClassSafe(ClassInfo c)
	{
		try
		{
			Class<?> clazz = c.load();
			return clazz.getDeclaredMethods();
		}
		catch (Throwable t)
		{
			TPPITweaks.logger.info(String.format("Class %s threw an error, skipping...", c.getName()));
			return new Method[]{};
		}
	}
	
	public static void doRemainingTweaks(EventTime time)
	{
		switch(time)
		{
		case INIT:
			addMiscRecipes();
			break;
		case POST_INIT:
			doOreDictTweaks();
			break;
		default:
			break;
		}
	}
	
	private static boolean allModsLoaded(String[] modids)
	{
		for (String s : modids)
		{
			if (!Loader.isModLoaded(s))
				return false;
		}
		return true;
	}
}
