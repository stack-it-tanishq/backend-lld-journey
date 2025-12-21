package com.practice.model;

import com.practice.enums.VehicleCategory;

public class ParkingSlot {

    private final int slotId;
    private final VehicleCategory category;
    private boolean available = true;

    public ParkingSlot(int slotId, VehicleCategory category) {
        this.slotId = slotId;
        this.category = category;
    }

    public boolean canFit(VehicleCategory category){
        return this.category == category;
    }

    public int getSlotId() {
        return slotId;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markOccupy() {
        this.available = false;
    }

    public void markFree() {
        this.available = true;
    }
}
