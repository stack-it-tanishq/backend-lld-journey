package com.practice.service;

import com.practice.enums.VehicleCategory;
import com.practice.model.ParkingSlot;
import com.practice.model.Ticket;
import com.practice.model.Vehicle;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {

    private final List<ParkingSlot> slots;
    private Map<UUID, Ticket> activeTickets = new HashMap<>();
    private static final int HOURLY_RATE = 20;

    public ParkingLot(List<ParkingSlot> slots){
        this.slots = slots;
    }

    public Ticket parkVehicle(Vehicle vehicle) {

        Optional<ParkingSlot> slot = findNextFreeSlot(vehicle.getCategory());
        if(slot.isEmpty()){
            return null;
        }
        Ticket ticket = new Ticket(UUID.randomUUID(), vehicle, slot.get(), LocalDateTime.now());
        slot.get().markOccupy();
        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    private Optional<ParkingSlot> findNextFreeSlot(VehicleCategory vehicleCategory){
        return slots.stream().filter(slot -> slot.isAvailable() && slot.canFit(vehicleCategory)).findFirst();
    }

    public double exitVehicle(UUID ticketId) throws Exception{
        Ticket ticket = activeTickets.get(ticketId);
        if(ticket==null){
            throw new Exception("Invalid ticket");
        }
        ticket.close();
        ticket.getSlot().markFree();
        activeTickets.remove(ticketId);
        return calculateAmount(ticket.getEntryTime(), ticket.getExitTime());
    }

    private double calculateAmount(LocalDateTime entry, LocalDateTime exit){
        return ((Duration.between(entry, exit)).toHours()+1)*HOURLY_RATE;
    }

}
