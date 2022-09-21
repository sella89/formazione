package com.afzaalahmadzeeshan.practice.models;

import com.afzaalahmadzeeshan.practice.annotations.Version;

@Version(1)
public class Engineer extends Person {

    private String department;

    @Version(1)
    @SuppressWarnings("unused")
    public Engineer() {}

    @Version(1)
    public Engineer(Long id, String name) {
        super(id, name);
    }

    @Version(1)
    @Override
    @SuppressWarnings("deprecation")
    public String details() {
        return String.format("[Engineer #%d] %s", this.id, this.name);
    }

    public String detailsV2() {
        return String.format("[Engineer #%d] %s", this.id, this.name);
    }
}
