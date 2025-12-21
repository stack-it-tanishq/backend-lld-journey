package com.practice.model;

import com.practice.enums.VehicleCategory;

public class Vehicle {

    private final String number;
    private final VehicleCategory category;

    Vehicle(String number, VehicleCategory category){
        this.number = number;
        this.category = category;
    }

    public String getNumber(){
        return number;
    }

    public VehicleCategory getCategory(){
        return category;
    }
}
