package org.technicaltest.creditsim.model.domain.interfaces;

import org.technicaltest.creditsim.model.domain.entities.Loan;
import org.technicaltest.creditsim.model.domain.entities.Vehicle;

public interface AbstractRepo {
    Loan getLoan();
    Vehicle getVehicle();
    void setLoan(Loan loan);
    void setVehicle(Vehicle vehicle);
}
