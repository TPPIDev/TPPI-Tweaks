package tppitweaks.recipetweaks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as one that removes recipes
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RecipeRemoval
{
	/**
	 * The modids that must be loaded for this method to execute
	 */
	String[] requiredModids() default {};
}
