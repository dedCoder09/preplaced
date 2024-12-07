package org.example.entity;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSlot {

    private static int counter = 1;
    Integer id;
    VehicleType vehicleType;
    AtomicBoolean isAvailable;

    public ParkingSlot(VehicleType vehicleType) {
        this.id = counter++;
        this.vehicleType = vehicleType;
        this.isAvailable = new AtomicBoolean(true);
    }

    public Integer getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Boolean isAvailable() {
        return isAvailable.get();
    }

    public void park() {
        isAvailable.set(false);
    }

    public void unpark() {
        isAvailable.set(true);
    }

}
