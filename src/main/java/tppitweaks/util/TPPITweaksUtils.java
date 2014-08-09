package tppitweaks.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;
import tppitweaks.TPPITweaks;
import tppitweaks.lib.Reference;

/**
 * @author AidanBrady, butchered by tterrag
 */
public final class TPPITweaksUtils
{
	public static final ForgeDirection[] SIDE_DIRS = new ForgeDirection[] {ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.WEST, ForgeDirection.EAST};
	
	/**
	 * Checks if Minecraft is running in offline mode.
	 * @return if mod is running in offline mode.
	 */
	public static boolean isOffline()
	{
		try {
			new URL("http://www.apple.com").openConnection().connect();
			return true;
		} catch (IOException e)
		{
			return false;
		}
	}

    /**
     * Retrieves a private value from a defined class and field.
     * @param obj - the Object to retrieve the value from, null if static
     * @param c - Class to retrieve field value from
     * @param fields - possible names of field to iterate through
     * @return value as an Object, cast as necessary
     */
    @SuppressWarnings("rawtypes")
	public static Object getPrivateValue(Object obj, Class c, String[] fields)
    {
    	for(String field : fields)
    	{
	    	try {
		    	Field f = c.getDeclaredField(field);
		    	f.setAccessible(true);
		    	return f.get(obj);
	    	} catch(Exception e) {
	    		continue;
	    	}
    	}
    	
    	return null;
    }
    
    /**
     * Sets a private value from a defined class and field to a new value.
     * @param obj - the Object to perform the operation on, null if static
     * @param value - value to set the field to
     * @param c - Class the operation will be performed on
     * @param fields - possible names of field to iterate through
     */
    @SuppressWarnings("rawtypes")
	public static void setPrivateValue(Object obj, Object value, Class c, String[] fields)
    {
    	for(String field : fields)
    	{
	    	try {
	    		Field f = c.getDeclaredField(field);
	    		f.setAccessible(true);
	    		f.set(obj, value);
	    	} catch(Exception e) {
	    		continue;
	    	}
    	}
    }
    
    /**
     * Retrieves a private method from a class, sets it as accessible, and returns it.
     * @param c - Class the method is located in
     * @param methods - possible names of the method to iterate through
     * @param params - the Types inserted as parameters into the method
     * @return private method
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Method getPrivateMethod(Class c, String[] methods, Class... params)
    {
    	for(String method : methods)
    	{
    		try {
    			Method m = c.getDeclaredMethod(method, params);
    			m.setAccessible(true);
    			return m;
    		} catch(Exception e) {
    			continue;
    		}
    	}
    	
    	return null;
    }
    
    /**
     * Gets a ResourceLocation with a defined resource type and name.
     * @param type - type of resource to retrieve
     * @param name - simple name of file to retrieve as a ResourceLocation
     * @return the corresponding ResourceLocation
     */
    public static ResourceLocation getResource(ResourceType type, String name)
    {
    	return new ResourceLocation("tppitweaks", type.getPrefix() + name);
    }
    
    /**
     * Splits a string of text into a list of new segments, using the splitter "!n."
     * @param s - string to split
     * @return split string
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String> splitLines(String s)
    {
    	ArrayList ret = new ArrayList();
    	
    	String[] split = s.split("!n");
    	ret.addAll(Arrays.asList(split));
    	
    	return ret;
    }
    
    public static enum ResourceType
    {
    	GUI("gui"),
		GUI_ELEMENT("gui/elements"),
    	SOUND("sound"),
    	RENDER("render"),
    	TEXTURE_BLOCKS("textures/blocks"),
    	TEXTURE_ITEMS("textures/items"),
    	MODEL("models"),
    	INFUSE("infuse");
    	
    	private String prefix;
    	
    	private ResourceType(String s)
    	{
    		prefix = s;
    	}
    	
    	public String getPrefix()
    	{
    		return prefix + "/";
    	}
    }
    
    public static boolean disableMod(String partOfName, String extension)
	{
		boolean hasChanged = false;
		for (File f : getMods(partOfName))
		{
			TPPITweaks.logger.info("Disabling: " + f.getName());
			if (!f.getName().contains(extension))
			{
				f.renameTo(new File(f.getAbsolutePath() + extension));
				System.out.println(f.getAbsolutePath() + "   " + extension);
				hasChanged = true;
			}
			else
			{
				TPPITweaks.logger.info(partOfName + " was already disabled!");
			}
		}
		return hasChanged;
	}

	public static boolean enableMod(String partOfName, String extensionToRemove)
	{
		boolean hasChanged = false;
		for (File f : getMods(partOfName))
		{
			TPPITweaks.logger.info("Enabling: " + f.getName());
			if (f.getName().contains(extensionToRemove))
			{
				f.renameTo(new File(f.getAbsolutePath().replace(extensionToRemove, "")));
				hasChanged = true;
			}
			else
			{
				TPPITweaks.logger.info(partOfName + " was already enabled!");
			}
		}
		return hasChanged;
	}

	/**
	 * Finds all mods that contain the passed string in their filename that exist in the /mods folder
	 * @return A list of Files that are mod jars (or zips)
	 */
	public static ArrayList<File> getMods(String partOfName)
	{
		ArrayList<File> files = new ArrayList<File>();
		File mods;

		mods = Reference.modsFolder;

		String[] fileNames = mods.list();
		ArrayList<String> foundNames = new ArrayList<String>();

		for (String s : fileNames)
		{
			if (s.toLowerCase().contains(partOfName.toLowerCase()))
				foundNames.add(s);
		}

		for (String s : foundNames)
			files.add(new File(mods, s));

		return files;
	}
}
