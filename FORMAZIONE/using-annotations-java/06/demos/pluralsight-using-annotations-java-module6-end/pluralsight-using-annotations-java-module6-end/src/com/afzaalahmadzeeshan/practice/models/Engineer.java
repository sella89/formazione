package com.afzaalahmadzeeshan.practice.models;

import com.afzaalahmadzeeshan.practice.annotations.Version;
import lombok.ToString;
import org.checkerframework.checker.nullness.qual.NonNull;

@Version(1)
@ToString
public class Engineer extends Person {

    private String department;

    @Version(1)
    @SuppressWarnings("unused")
    public Engineer() {}

    @Version(1)
    public Engineer(Long id, String name) {
        super(id, name);
    }

    public void setDepartment(@NonNull String dept) {
        this.department = dept;
    }

    public String getDepartment() {
        return this.department;
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
