package org.technicaltest.creditsim.model.adapter;

import org.technicaltest.creditsim.model.domain.entities.Loan;
import org.technicaltest.creditsim.model.domain.entities.Vehicle;
import org.technicaltest.creditsim.model.domain.interfaces.AbstractRepo;

public class LoanRepository implements AbstractRepo {
    private Loan loanEntity;
    private Vehicle vehicleEntity;


    @Override
    public Loan getLoan() {
        return this.loanEntity;
    }

    @Override
    public Vehicle getVehicle() {
        return this.vehicleEntity;
    }

    @Override
    public void setLoan(Loan loan) {
        this.loanEntity = loan;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicleEntity = vehicle;
    }

}

