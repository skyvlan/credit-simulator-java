package org.technicaltest.creditsim.model.service;

import org.technicaltest.creditsim.model.adapter.LoanUnitOfWork;
import org.technicaltest.creditsim.model.domain.entities.Loan;
import org.technicaltest.creditsim.model.domain.entities.Vehicle;
import org.technicaltest.creditsim.model.domain.enums.VehicleCondition;
import org.technicaltest.creditsim.model.domain.enums.VehicleType;

public class LoanInputService {
    private LoanUnitOfWork uow;

    public LoanInputService(LoanUnitOfWork uow){
        this.uow = uow;
    }

    public void InputLoan(float totalLoan, int tenor, float downPayment){
        this.uow.loan.setLoan(new Loan( totalLoan,  tenor,  downPayment));
    }

    public void InputVehicle(String vehicleType, String vehicleCondition, int vehicleYear){
        this.uow.loan.setVehicle(new Vehicle(VehicleType.valueOf(vehicleType), VehicleCondition.valueOf(vehicleCondition), vehicleYear));
    }


}
