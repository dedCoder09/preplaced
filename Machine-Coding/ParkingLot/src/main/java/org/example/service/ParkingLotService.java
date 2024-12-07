package org.example.service;

import org.example.entity.*;
import org.example.manager.ParkingLotManager;

import java.util.List;

public class ParkingLotService {

    public void createParkingLot(String name, String slots, int entryGates, int exitGates){

        ParkingLot parkingLot = new ParkingLot(name);

        String[] slotArray = slots.split(",");
        int totalSlotCount = 0;
        for(String slot : slotArray){
            String[] slotCountAndType = slot.split(" ");
            int count = Integer.valueOf(slotCountAndType[0]);
            String slotType = slotCountAndType[1];
            for(int i=0;i<count;i++){
                totalSlotCount++;
                VehicleType vehicleType = VehicleType.valueOf(slotType.toUpperCase());
                ParkingSlot parkingSlot = new ParkingSlot(totalSlotCount,vehicleType);

                parkingLot.getParkingSlotList().add(parkingSlot);
            }
        }

        int totalGates = 0;

        for(int i=0;i<entryGates;i++){
            totalGates++;
            Gate gate = new Gate(totalGates, GateType.ENTRY);
            parkingLot.getGateList().add(gate);
        }

        for(int i=0;i<exitGates;i++){
            totalGates++;
            Gate gate = new Gate(totalGates,GateType.EXIT);
            parkingLot.getGateList().add(gate);
        }

        ParkingLotManager.getINSTANCE().save(parkingLot);
    }

    public void displayAvailableSlots(String parkingLotName){
        ParkingLot parkingLot = ParkingLotManager.getINSTANCE().getParkingLot(parkingLotName);

        List<ParkingSlot> parkingSlotList = parkingLot.getParkingSlotList();

        int availableBikeSlot = 0, availableCarSlot = 0, availableBicycleSlot = 0;
        for(ParkingSlot parkingSlot : parkingSlotList){
            if(parkingSlot.getAvailable()){
                if(parkingSlot.getVehicleType().equals(VehicleType.BIKE)) availableBikeSlot++;
                else if(parkingSlot.getVehicleType().equals(VehicleType.BICYCLE)) availableBicycleSlot++;
                else availableCarSlot++;
            }
        }

        int totalAvailableSlots = availableBicycleSlot + availableBikeSlot + availableCarSlot;

        System.out.println("Total Available: " + totalAvailableSlots);
        System.out.println("Car " + availableCarSlot + " Bike " + availableBikeSlot + " Bicycle " + availableBicycleSlot);

    }
}
