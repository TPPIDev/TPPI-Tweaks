package tppitweaks.tweak.recipe;

public class TweakAdvancedGenetics {
//    private static final String key = "power-system";
//    private static NBTTagCompound energyType = new NBTTagCompound();
//    private static Class itemMachine;
//
//    static {
//        energyType.setInteger(key, 0);
//
//        try {
//            itemMachine = Class.forName("binnie.core.machines.ItemMachine", false, ClassLoader.getSystemClassLoader());
//        } catch (ClassNotFoundException e) {
//            TPPITweaks.logger.error("Binnie's mods were found but a required class was not.");
//        }
//    }
//
//    @RecipeAddition(requiredModids = "Genetics", time = EventTime.POST_INIT)
//    public static void addRecipes() {
//        GameRegistry.addRecipe(new IRecipe() {
//            private ItemStack output;
//
//            @Override
//            public boolean matches(InventoryCrafting inv, World world) {
//                int count = 0;
//                ItemStack input = null;
//
//                for (int i = 0; i < inv.getSizeInventory(); i++) {
//                    ItemStack checkStack = inv.getStackInSlot(i);
//                    if (checkStack != null && checkStack.getItem().getClass().isInstance(itemMachine)) {
//                        count++;
//                    }
//                    input = count == 1 && checkStack != null ? checkStack : input;
//                }
//
//                if (count == 1) {
//                    ItemStack out = input.copy();
//                    out.stackSize = 1;
//
//                    if (out.getTagCompound() == null) {
//                        out.setTagCompound(new NBTTagCompound());
//                    }
//
//                    out = setEnergyType(out, (out.getTagCompound().getInteger(key) + 1) % 3);
//                    this.output = out;
//                } else {
//                    this.output = null;
//                }
//
//                return count == 1 && output != null;
//            }
//
//            @Override
//            public ItemStack getCraftingResult(InventoryCrafting inv) {
//                return output.copy();
//            }
//
//            @Override
//            public int getRecipeSize() {
//                return 1;
//            }
//
//            @Override
//            public ItemStack getRecipeOutput() {
//                return output;
//            }
//        });
//    }
//
//    private static ItemStack setEnergyType(ItemStack stack, int type) {
//        ItemStack ret = stack.copy();
//        energyType.setInteger(key, type);
//        ret.setTagCompound(copy(energyType));
//        return ret;
//    }
//
//    private static NBTTagCompound copy(NBTTagCompound tag) {
//        return (NBTTagCompound) tag.copy();
//    }
}
