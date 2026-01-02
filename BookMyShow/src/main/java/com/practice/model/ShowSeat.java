package com.practice.model;

import java.util.Map;

public class ShowSeat {
    private final int showSeatId;
    private final Seat seat;
    private SeatStatus status;

    public ShowSeat(int showSeatId, Seat seat, SeatStatus status) {
        this.showSeatId = showSeatId;
        this.seat = seat;
        status = SeatStatus.AVL;
    }

    public int getShowSeatId() {
        return showSeatId;
    }

    public Seat getSeat() {
        return seat;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public void holdSeat(){
        this.status = SeatStatus.HLD;
    }

    public void confirmSeat(){
        this.status = SeatStatus.CNF;
    }

    public void freeSeat(){
        this.status = SeatStatus.AVL;
    }
}
