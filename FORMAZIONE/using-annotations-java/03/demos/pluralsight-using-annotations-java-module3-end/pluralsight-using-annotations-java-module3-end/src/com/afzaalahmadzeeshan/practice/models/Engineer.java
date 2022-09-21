package com.afzaalahmadzeeshan.practice.models;

public class Engineer extends Person {
    public Engineer() {}

    public Engineer(Long id, String name) {
        super(id, name);
    }
    @Override
    public String details() {
        return String.format("[Engineers #%d] %s", this.id, this.name);
    }
}
