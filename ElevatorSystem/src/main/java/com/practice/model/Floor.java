package com.practice.model;

public class Floor {

    private final int number;

    public Floor(int number){
        this.number = number;
    }

    public void callElevator(Elevator elevator){
        elevator.addRequest(number);
    }
}
