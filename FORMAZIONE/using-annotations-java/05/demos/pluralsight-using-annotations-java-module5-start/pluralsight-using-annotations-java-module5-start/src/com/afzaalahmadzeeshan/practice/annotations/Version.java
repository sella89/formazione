package com.afzaalahmadzeeshan.practice.annotations;

public @interface Version {
    int value();
    String author() default "Afzaal Ahmad Zeeshan";
    String licence() default "MIT";

    String[] environments() default { "production", "development" };
}
