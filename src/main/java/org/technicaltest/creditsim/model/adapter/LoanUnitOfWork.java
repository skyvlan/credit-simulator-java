package org.technicaltest.creditsim.model.adapter;
import org.technicaltest.creditsim.model.domain.interfaces.AbstractUnitOfWork;
public class LoanUnitOfWork implements AbstractUnitOfWork{


    public LoanRepository loan;
    private boolean isCommited;

    public LoanUnitOfWork() {
        this.loan = new LoanRepository();
    }

    @Override
    public void commit() {
        this.isCommited = true;
    }

    @Override
    public void rollback() {
        this.isCommited = false;
        this.loan = new LoanRepository();
    }
}
