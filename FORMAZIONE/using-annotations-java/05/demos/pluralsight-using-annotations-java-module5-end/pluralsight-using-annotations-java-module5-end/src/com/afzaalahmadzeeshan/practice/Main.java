package com.afzaalahmadzeeshan.practice;

import com.afzaalahmadzeeshan.practice.annotations.Environment;
import com.afzaalahmadzeeshan.practice.annotations.Version;
import com.afzaalahmadzeeshan.practice.annotations.Versions;
import com.afzaalahmadzeeshan.practice.models.Engineer;
import com.afzaalahmadzeeshan.practice.models.Person;

public class Main {

    public static void main(String[] args) {
        Person engineer = new Engineer(1L, "Afzaal Ahmad Zeeshan");

        var annotations = engineer.getClass().getAnnotations();
        System.out.printf("%d annotations found.%n", annotations.length);

        // Read the annotation details
        for (var annotation : annotations) {
            if (annotation instanceof Version) {
                System.out.println("Found Version annotation on type.");
                var version = (Version) annotation;
                System.out.printf("Version #%d%n", version.value());
            } else  if (annotation instanceof Versions) {
                System.out.println("Found Versions annotation on type.");
                var versions = (Versions) annotation;
                for (var version : versions.value()) {
                    System.out.printf("Version #%d%n", version.value());
                }
            } else if (annotation instanceof Environment) {
                var environment = (Environment) annotation;
                System.out.printf("Found %s environment annotation%n", environment.value());
            }
        }

        var annotation = engineer.getClass().getAnnotation(Environment.class);
        if (annotation != null) {
            System.out.println("Engineer has Environment annotation.");
        } else {
            System.out.println("Engineer does not have Environment annotation.");
        }
    }
}
