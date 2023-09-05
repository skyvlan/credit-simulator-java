package org.technicaltest.creditsim.model.domain.enums;

public enum VehicleType{
    MOTOR("motor"),
    MOBIL("mobil");

    private String value;
    VehicleType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
