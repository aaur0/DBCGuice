package net.dbc.guice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Contains a condition to evaluate the return value of the method. In the
 * condition to evaluate the reserved word "_returnValue_" is used to reference
 * the return value. Also in the condition any alias defined for the arguments
 * should be used.
 * 
 * @see Alias
 * 
 * @param value
 *            The String containing the expression to evaluate to true or false,
 *            typically has some reference to the return value ("_returnValue_")
 *            when defining a postcondition. Context available for the
 *            expression in this annotation includes: "_returnValue_" and all
 *            the public members of the type where this annotation is applied
 *            (using "_instance_" key).
 * @param desc
 *            Describes the condition human-readable way, example: "Age must be
 *            greater than 0.".
 * 
 * @author raul.bajales@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD })
public @interface Postcondition {

	String value();

	String desc() default "";
}
