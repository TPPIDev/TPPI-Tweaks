package tppitweaks.recipetweaks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as one that removes a recipe
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RecipeAddition
{
	public enum EventTime { INIT, POST_INIT, PLAYER_JOIN }
	
	/**
	 * The event at which this method is to be executed
	 */
	EventTime time() default EventTime.POST_INIT;
	
	/**
	 * The modids that must be loaded for this method to execute
	 */
	String[] requiredModids() default {};
}
