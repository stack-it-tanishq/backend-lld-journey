package com.practice.model;

import java.util.Objects;

public class Theatre {

    private final int theatreId;
    private final String theatreName;
    private final City city;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Theatre theatre = (Theatre) o;
        return theatreId == theatre.theatreId && Objects.equals(theatreName, theatre.theatreName) && Objects.equals(city, theatre.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theatreId, theatreName, city);
    }

    public Theatre(int theatreId, String theatreName, City city) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
        this.city = city;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public City getCity() {
        return city;
    }
}
