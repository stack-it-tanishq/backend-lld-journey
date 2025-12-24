package com.practice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Building {

    private final Map<Integer, Floor> floors;
    private final List<Elevator> elevators;

    public Building(Map<Integer, Floor> floors, int numOfElevators){
        this.floors = floors;
        elevators = new ArrayList<>();
        for (int i = 0; i < numOfElevators; i++) {
            elevators.add(new Elevator(0, Direction.IDLE));
        }
    }

    public void callElevator(int floorNo){
        Floor callingFloor = floors.get(floorNo);
        if(callingFloor == null){
            throw new NullPointerException();
        }
        callingFloor.callElevator(selectElevator());
    }

    public void step() {
        for(Elevator elevator: elevators){
            elevator.step();
        }
    }

    private Elevator selectElevator(){
        if(elevators.isEmpty()){
            throw new IllegalArgumentException() ;
        }
        for (Elevator elevator : elevators) {
            if (elevator.getDirection() == Direction.IDLE) {
                return elevator;
            }
        }
        return elevators.getFirst();
    }

}
