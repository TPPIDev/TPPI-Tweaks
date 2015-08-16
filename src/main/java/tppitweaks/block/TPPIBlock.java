package tppitweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import tppitweaks.TPPITweaks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TPPIBlock extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public TPPIBlock() {
        super(Material.iron);
        setHardness(5.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setBlockName("redstoneCompressed");
        setBlockTextureName("tppitweaks:redstoneCompressed");
        setCreativeTab(TPPITweaks.creativeTab);
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return 15;
    }

}
