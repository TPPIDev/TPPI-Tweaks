package tppitweaks.tweak.recipe;

import binnie.core.machines.ItemMachine;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeAddition.EventTime;

public class TweakAdvancedGenetics {
    private static final String key = "power-system";
    private static NBTTagCompound energyType = new NBTTagCompound();

    static {
        energyType.setInteger(key, 0);
    }

    @RecipeAddition(requiredModids = "Genetics", time = EventTime.POST_INIT)
    public static void addRecipes() {
        GameRegistry.addRecipe(new IRecipe() {
            private ItemStack output;

            @Override
            public boolean matches(InventoryCrafting inv, World world) {
                int count = 0;
                ItemStack input = null;

                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack checkStack = inv.getStackInSlot(i);
                    if (checkStack != null && checkStack.getItem() instanceof ItemMachine) {
                        count++;
                    }
                    input = count == 1 && checkStack != null ? checkStack : input;
                }

                if (count == 1) {
                    ItemStack out = input.copy();
                    out.stackSize = 1;

                    if (out.getTagCompound() == null) {
                        out.setTagCompound(new NBTTagCompound());
                    }

                    out = setEnergyType(out, (out.getTagCompound().getInteger(key) + 1) % 3);
                    this.output = out;
                } else {
                    this.output = null;
                }

                return count == 1 && output != null;
            }

            @Override
            public ItemStack getCraftingResult(InventoryCrafting inv) {
                return output.copy();
            }

            @Override
            public int getRecipeSize() {
                return 1;
            }

            @Override
            public ItemStack getRecipeOutput() {
                return output;
            }
        });
    }

    private static ItemStack setEnergyType(ItemStack stack, int type) {
        ItemStack ret = stack.copy();
        energyType.setInteger(key, type);
        ret.setTagCompound(copy(energyType));
        return ret;
    }

    private static NBTTagCompound copy(NBTTagCompound tag) {
        return (NBTTagCompound) tag.copy();
    }
}
