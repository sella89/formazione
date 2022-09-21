package com.afzaalahmadzeeshan.practice;

import com.afzaalahmadzeeshan.practice.models.Engineer;

public class Main {

    public static void main(String[] args) {
        Engineer engineer = new Engineer(1L, "Afzaal Ahmad Zeeshan");

        if (getDept() != null) {
            engineer.setDepartment(getDept());
        }
        System.out.println(engineer);
    }

    private static String getDept() {
        String value = "";
        return value;
    }
}
