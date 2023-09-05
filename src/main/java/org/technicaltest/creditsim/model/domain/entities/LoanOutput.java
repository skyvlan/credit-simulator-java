package org.technicaltest.creditsim.model.domain.entities;



public class LoanOutput {

    private int year;
    private float installment;
    private float interest;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getInstallment() {
        return installment;
    }

    public void setInstallment(float installment) {
        this.installment = installment;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public LoanOutput(int year, float installment, float interest){
        this.year = year;
        this.installment = installment;
        this.interest = interest;
    }



}

