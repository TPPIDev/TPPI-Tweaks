package tppitweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import tppitweaks.TPPITweaks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TPPIBlock extends Block {
	
	@SideOnly(Side.CLIENT)
	private Icon icon;
	
	public TPPIBlock(int par1) {
		super(par1, Material.iron);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundMetalFootstep);
		setUnlocalizedName("redstoneCompressed");
		setCreativeTab(TPPITweaks.creativeTab);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		icon = register.registerIcon("tppitweaks:redstoneCompressed");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		return icon;
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
