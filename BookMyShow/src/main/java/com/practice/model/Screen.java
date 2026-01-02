package com.practice.model;

public class Screen {

    private final int screenId;
    private final int numberOfSeats;
    private final Theatre theatre;

    public Screen(int screenId, int numberOfSeats, Theatre theatre) {
        this.screenId = screenId;
        this.numberOfSeats = numberOfSeats;
        this.theatre = theatre;
    }

    public int getScreenId() {
        return screenId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public Theatre getTheatre() {
        return theatre;
    }
}
