package org.example.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    String name;
    int noOfEntryGates;
    int noOfExitGates;
    Map<String, List<ParkingSlot>> parkingSlotsByType;

    public ParkingLot(String name, int noOfEntryGates, int noOfExitGates) {
        this.name = name;
        this.noOfEntryGates = noOfEntryGates;
        this.noOfExitGates = noOfExitGates;
        this.parkingSlotsByType = new ConcurrentHashMap<>();
    }

    public void addSlots(VehicleType vehicleType, int count){
        parkingSlotsByType.putIfAbsent(vehicleType.name(), Collections.synchronizedList(new ArrayList<>()));
        for(int i=0;i<count;i++){
            parkingSlotsByType.get(vehicleType.name()).add(new ParkingSlot(vehicleType));
        }
    }


}
