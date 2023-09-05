package org.technicaltest.creditsim.model.domain.entities;

import org.technicaltest.creditsim.model.domain.enums.VehicleCondition;
import org.technicaltest.creditsim.model.domain.enums.VehicleType;

public class Vehicle {



    private VehicleType vehicleType;
    private VehicleCondition vehicleCondition;
    private int vehicleYear;

    public Vehicle(VehicleType vehicleType, VehicleCondition vehicleCondition, int vehicleYear) {
        this.vehicleType = vehicleType;
        this.vehicleCondition = vehicleCondition;
        this.vehicleYear = vehicleYear;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleCondition getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(VehicleCondition vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }
}
