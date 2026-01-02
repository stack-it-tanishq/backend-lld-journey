package com.practice.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Show {
    private final int showId;
    private final Map<Integer, ShowSeat> showSeats;
    private final Screen screen;
    private final LocalDateTime showStartTime;
    private final LocalDateTime showEndTime;
    private final Movie movie;
    private final ReentrantLock lock;

    public int getShowId() {
        return showId;
    }

    public Map<Integer, ShowSeat> getShowSeats() {
        return showSeats;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    public LocalDateTime getShowEndTime() {
        return showEndTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public Show(int showId, Map<Integer,ShowSeat> showSeats, LocalDateTime showStartTime, LocalDateTime showEndTime, Movie movie, Screen screen) {
        this.showId = showId;
        this.showSeats = showSeats;
        this.showStartTime = showStartTime;
        this.showEndTime = showEndTime;
        this.movie = movie;
        this.screen = screen;
        this.lock = new ReentrantLock();
    }

    public boolean holdSeats(List<Integer> seatIds) throws Exception {

        lock.lock();
        if(!checkFreeSeats(seatIds)){
            return false;
        }
        try{
            for(Integer seatId : seatIds){
                ShowSeat showSeat = showSeats.get(seatId);
                showSeat.holdSeat();
            }
        } catch (Exception e){
            return false;
        } finally {
            lock.unlock();
        }
        return true;

    }

    public void releaseSeats(List<Integer> seatIds) throws Exception {
        lock.lock();

        try{
            for(Integer seatId : seatIds){
                if(showSeats.get(seatId).getStatus() == SeatStatus.HLD){
                    showSeats.get(seatId).freeSeat();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private boolean checkFreeSeats(List<Integer> seatIds) {
        for(Integer seatId : seatIds) {
            if(!showSeats.containsKey(seatId) || showSeats.get(seatId).getStatus() != SeatStatus.AVL) {
                return false;
            }
        }
        return true;
    }
}
