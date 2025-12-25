package com.practice.model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Elevator {

  private final PriorityQueue<Integer> floorsQueue;
  private int currentFloor;
  private Direction direction;

    public Elevator(int currentFloor, Direction direction){
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.floorsQueue = new PriorityQueue<>(
                Comparator.comparingInt(a -> Math.abs(a - currentFloor))
        );
    }

    public void addRequest(int floorNo, Direction direction){
        floorsQueue.offer(floorNo);
    }

    public void step(){
        if(floorsQueue.isEmpty()){
            direction = Direction.IDLE;
            return;
        }
        int nextFloor = floorsQueue.peek();
        if(nextFloor > currentFloor){
            currentFloor++;
            direction = Direction.UP;
        } else if (nextFloor < currentFloor){
            currentFloor--;
            direction = Direction.DOWN;
        } else {
            floorsQueue.poll();
            direction = Direction.IDLE;
        }

    }

    public void selectDestination(int destinationFloor){
        floorsQueue.offer(destinationFloor);
    }

    public PriorityQueue<Integer> getFloorsQueue() {
        return floorsQueue;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

}
