package com.at.utils.annotations;


import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// If path contains dynamic parameters simply use {} to indicate such. For example: @Url("/p/{productCode}")

@Retention(RUNTIME)
@Target(TYPE)
public @interface PageUrl {

    String value() default "";
}
