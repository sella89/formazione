package com.afzaalahmadzeeshan.practice;

import com.afzaalahmadzeeshan.practice.models.Engineer;
import com.afzaalahmadzeeshan.practice.models.Person;

public class Main {

    public static void main(String[] args) {
        Person engineer = new Engineer(1L, "Afzaal Ahmad Zeeshan");
        System.out.println(engineer.details());
    }
}
