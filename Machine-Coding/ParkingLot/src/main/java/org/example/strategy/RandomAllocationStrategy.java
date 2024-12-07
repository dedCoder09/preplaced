package org.example.strategy;

import org.example.entity.Gate;
import org.example.entity.ParkingLot;
import org.example.entity.ParkingSlot;
import org.example.entity.Vehicle;

public class RandomAllocationStrategy implements SlotAllocationStrategy{
    @Override
    public ParkingSlot allocateSlot(ParkingLot parkingLot, Vehicle vehicle, Gate gate) {
        return null;
    }
}
