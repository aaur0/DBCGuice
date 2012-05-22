package net.dbc.guice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Contains a condition evaluated before the method execution. Typically this
 * condition have some reference to the arguments of the method. Also in the
 * condition any alias defined for the arguments should be used.
 * 
 * @see Alias
 * 
 * @param value
 *            The String containing the expression to evaluate to true or false.
 *            Typically has some reference to any argument of the method where
 *            this annotation is applied, those references includes any argument
 *            alias or "arg" + the argument index. Context available for the
 *            expression in this annotation includes: any argument alias or
 *            "arg" + the argument index, and all the public members of the type
 *            where this annotation is applied (using "_instance_" key).
 * @param desc
 *            Describes the condition human-readable way, example:
 *            "Age must be greater than 0.".
 * 
 * @author raul.bajales@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Precondition {

	String value();

	String desc() default "";
}
