package com.afzaalahmadzeeshan.practice.annotations;

import java.lang.annotation.*;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Versions.class)
public @interface Version {
    int value();
    String author() default "Afzaal Ahmad Zeeshan";
    String licence() default "MIT";

    String[] environments() default { "production", "development" };
}
