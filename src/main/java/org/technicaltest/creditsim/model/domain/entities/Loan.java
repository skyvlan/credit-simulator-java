package org.technicaltest.creditsim.model.domain.entities;

public class Loan {



    private float total_loan;

    private int tenor;
    private float down_payment;

    public Loan(float total_loan, int tenor, float down_payment) {
        this.total_loan = total_loan;
        this.tenor = tenor;
        this.down_payment = down_payment;
    }

    public float getTotal_loan() {
        return total_loan;
    }

    public void setTotal_loan(float total_loan) {
        this.total_loan = total_loan;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public float getDown_payment() {
        return down_payment;
    }

    public void setDown_payment(float down_payment) {
        this.down_payment = down_payment;
    }



}
