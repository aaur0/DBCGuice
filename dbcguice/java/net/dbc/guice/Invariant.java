package net.dbc.guice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation defines an invariant for a type, it means, a condition that
 * must be true during all the lifetime of the instance. When defining an
 * invariant the key "_instantce_" can be used as a reference to the instance.
 * 
 * @param value
 *            The String containing the expression to evaluate to true or false.
 *            Typically has some reference to the instance ("_instance_") by
 *            calling some public member (method or attribute). Context
 *            available for the expression in this annotation includes:
 *            "_instance_" and all the public members of the type where this
 *            annotation is applied.
 * @param desc
 *            Describes the condition human-readable way, example:
 *            "Age must be greater than 0.".
 * 
 * @author raul.bajales@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Invariant {

	String value();

	String desc() default "";
}
