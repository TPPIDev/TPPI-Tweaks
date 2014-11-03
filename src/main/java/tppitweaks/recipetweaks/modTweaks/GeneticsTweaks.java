package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeAddition.EventTime;
import binnie.core.machines.ItemMachine;
import cpw.mods.fml.common.registry.GameRegistry;

public class GeneticsTweaks
{
    private static NBTTagCompound energyType = new NBTTagCompound();
    private static final String key = "power-system";
    
    static {
        energyType.setInteger(key, 0);
    }

    @RecipeAddition(requiredModids = "Genetics", time = EventTime.POST_INIT)
    public static void addRecipes()
    {
        GameRegistry.addRecipe(new IRecipe()
        {
            private ItemStack out = null;
            
            @Override
            public boolean matches(InventoryCrafting inv, World w)
            {
                out = null;
                
                for (int i = 0; i < inv.getSizeInventory(); i++)
                {
                    ItemStack s = inv.getStackInSlot(i);
                    
                    if (out != null && s != null)
                    {
                        out = null;
                        break;
                    }
                    
                    if (s != null && s.getItem() instanceof ItemMachine)
                    {
                        out = s.copy();
                        out = setEnergyType(out, (out.getTagCompound().getInteger(key) + 1) % 3);
                    }
                }
                
                return out != null;
            }
            
            @Override
            public int getRecipeSize()
            {
                return 1;
            }
            
            @Override
            public ItemStack getRecipeOutput()
            {
                return out.copy();
            }
            
            @Override
            public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
            {
                return out.copy();
            }
        });
    }
    
    private static ItemStack setEnergyType(ItemStack stack, int type)
    {
        ItemStack ret = stack.copy();
        energyType.setInteger(key, type);
        ret.setTagCompound(copy(energyType));
        return ret;
    }

    private static NBTTagCompound copy(NBTTagCompound tag)
    {
        return (NBTTagCompound) tag.copy();
    }
}
