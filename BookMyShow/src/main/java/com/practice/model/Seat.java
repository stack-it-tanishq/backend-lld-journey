package com.practice.model;

import java.util.List;

public class Seat {

    private final int row;
    private final int col;
    private final SeatType seatType;

    public Seat(int row, int col, SeatType seatType){
        this.col = col;
        this.row = row;
        this.seatType = seatType;
    }

}
