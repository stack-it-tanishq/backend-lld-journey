package com.practice.model;

import com.practice.enums.VehicleCategory;

public class ParkingSlot {

    private final String slotId;
    private final VehicleCategory category;
    private boolean available = true;

    public ParkingSlot(String slotId, VehicleCategory category) {
        this.slotId = slotId;
        this.category = category;
    }

    public String getSlotId() {
        return slotId;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
