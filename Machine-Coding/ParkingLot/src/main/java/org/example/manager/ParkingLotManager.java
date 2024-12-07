package org.example.manager;

import org.example.entity.*;
import org.example.strategy.RandomAllocationStrategy;
import org.example.strategy.SequentialAllocationStrategy;
import org.example.strategy.SlotAllocationStrategy;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotManager {
    private static volatile ParkingLotManager INSTANCE;
    private static Map<String, ParkingLot> parkingLotMap;

    private ParkingLotManager(){
        parkingLotMap = new HashMap<>();
    }

    public static ParkingLotManager getINSTANCE(){
        if(INSTANCE == null){
            synchronized (ParkingLotManager.class){
                if(INSTANCE == null){
                    INSTANCE = new ParkingLotManager();
                }
            }
        }
        return INSTANCE;
    }

    public static void save(ParkingLot parkingLot){
        parkingLotMap.put(parkingLot.getName(), parkingLot);
    }

    public static ParkingLot getParkingLot(String parkingLotName){
        return parkingLotMap.get(parkingLotName);
    }

    public static synchronized boolean parkVehicle(String parkingLotName, String regNumber, String vehicleTypeString){

        ParkingLot parkingLot = parkingLotMap.get(parkingLotName);
        VehicleType vehicleType = VehicleType.valueOf(vehicleTypeString.toUpperCase());
        Vehicle vehicle = new Vehicle(regNumber,vehicleType);

        SlotAllocationStrategy slotAllocationStrategy;

        if(vehicleType.equals(VehicleType.BIKE) || vehicleType.equals(VehicleType.CAR)){
            slotAllocationStrategy = new RandomAllocationStrategy();
        }else{
            slotAllocationStrategy = new SequentialAllocationStrategy();
        }

        ParkingSlot parkingSlot = slotAllocationStrategy.allocateSlot(parkingLot,vehicle,)
    }
}
