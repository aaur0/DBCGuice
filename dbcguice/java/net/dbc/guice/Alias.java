package net.dbc.guice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation defines an alias for a method argument, so a more fancy name can be
 * used when expressing a condition, otherwise the String "arg[n]" must be used, where
 * "[n]" starts from 0 and is related to the order of the argument in the method signature. 
 * This is needed because at least until Java 1.5 the argument names are not saved in the 
 * .class file when a java class is compiled, so it can't be reached by reflection unless
 * the "-g" (to save debug info) option is used when compiling, but this is not a good 
 * restriction.<br><br>
 * 
 * @author raul.bajales@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Alias {
	
	String value();
}
