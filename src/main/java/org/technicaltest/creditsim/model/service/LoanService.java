package org.technicaltest.creditsim.model.service;

import org.technicaltest.creditsim.model.adapter.LoanUnitOfWork;
import org.technicaltest.creditsim.model.domain.entities.Loan;
import org.technicaltest.creditsim.model.domain.entities.LoanOutput;
import org.technicaltest.creditsim.model.domain.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class LoanService {

    private Loan loan;
    private Vehicle vehicle;
    private static final float BASE_INTEREST_CAR_RATE = 0.08f;
    private static final float BASE_INTEREST_MOTORCYCLE_RATE = 0.09f;

    public LoanService(LoanUnitOfWork uow){
        this.loan = uow.loan.getLoan();
        this.vehicle = uow.loan.getVehicle();
    }

    public List<LoanOutput> calculateLoan(){
        List<LoanOutput> loanOutputList = new ArrayList<>();
        float interestRate = 0f;
        if (this.vehicle.getVehicleType().toString().equals("mobil")){
             interestRate = BASE_INTEREST_CAR_RATE;
        }
        else{
             interestRate = BASE_INTEREST_MOTORCYCLE_RATE;
        }
        float baseLoan = this.loan.getTotal_loan() - this.loan.getDown_payment();
        float yearlyInstallment = 0f;
        float loan = 0f;
        for (int i = 0; i < this.loan.getTenor(); i++){
            if(i == 0){
                loan = baseLoan + (baseLoan * interestRate);
                yearlyInstallment = loan / this.loan.getTenor();
                float monthlyInstallment = yearlyInstallment / 12;
                loanOutputList.add(new LoanOutput(i+1, monthlyInstallment,interestRate * 100));
            }
            else{
                if(i % 2 == 0){
                    interestRate += 0.005f;
                }
                else{
                    interestRate += 0.001f;
                }
                float remainingLoan = loan - yearlyInstallment;
                loan = remainingLoan + (remainingLoan * interestRate);
                yearlyInstallment = loan / (this.loan.getTenor() - i);
                float monthlyInstallment = yearlyInstallment / 12;
                loanOutputList.add(new LoanOutput(i+1, monthlyInstallment,interestRate * 100));
            }
        }
        return loanOutputList;
    }

}
