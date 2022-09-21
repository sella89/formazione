package com.afzaalahmadzeeshan.practice.models;

public class Engineer extends Person {

    @SuppressWarnings("unused")
    public Engineer() {}

    public Engineer(Long id, String name) {
        super(id, name);
    }

    @Override
    @SuppressWarnings("deprecation")
    public String details() {
        return String.format("[Engineer #%d] %s", this.id, this.name);
    }

    public String detailsV2() {
        return String.format("[Engineer #%d] %s", this.id, this.name);
    }
}
