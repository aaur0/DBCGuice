package net.dbc.guice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A Postcondition that asserts that the return value is not null.
 * 
 * @author JohnnyK3@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface NeverReturnsNull {
	String value() default "_returnValue_ != null";

	String desc() default "This method should never return null";
}
