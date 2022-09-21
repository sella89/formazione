package com.afzaalahmadzeeshan.practice.models;

import com.afzaalahmadzeeshan.practice.annotations.Environment;
import com.afzaalahmadzeeshan.practice.annotations.Version;

@Environment("staging")
@Version(1)
public class Person {
    protected Long id;
    protected String name;

    public Person() {}

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String greet() {
        return "Hi, I am " + this.name;
    }

    @Deprecated
    public String details() {
        return String.format("[%d] %s", this.id, this.name);
    }

    public String detailsV2() {
        return String.format("[Person #%d] %s", this.id, this.name);
    }
}
