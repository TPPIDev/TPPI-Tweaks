package tppitweaks.lib;

import java.io.File;

public class Reference {
	public static final String CHANNEL = "tppitweaks";
	public static final String TAB_NAME = "tabTPPI";
	
	public static File modsFolder;
	
	public static final String DEPENDENCIES = 
			"before:ThaumicTinkerer;"
			+ "after:Thaumcraft;"
			+ "after:TwilightForest;"
			+ "after:AppliedEnergistics;"
			+ "after:StevesFactoryManager;"
			+ "after:DimensionalAnchors;"
			+ "after:ThermalExpansion;"
			+ "after:ExtraUtilities;"
			+ "after:EnderStorage;"
			+ "after:BigReactors;"
			+ "after:BuildCraft|Core;"
			+ "after:Mekanism;"
			+ "after:xreliquary;"
			+ "after:NotEnoughItems;"
			+ "after:Railcraft;"
			+ "after:IC2;"
			+ "after:MineFactoryReloaded;"
			+ "after:JABBA;"
			+ "after:EnderIO;"
			+ "required-after:recipetweakingcore";
	
	public static String packName = "Test Pack Please Ignore";
	public static String packVersion = "0.2.2";
	public static String packAcronym = "TPPI";
}
