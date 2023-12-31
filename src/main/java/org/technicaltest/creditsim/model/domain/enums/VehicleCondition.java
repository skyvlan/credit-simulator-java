package org.technicaltest.creditsim.model.domain.enums;

public enum VehicleCondition {
    BARU("baru"),
    BEKAS("bekas");

    private final String value;
    VehicleCondition(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
