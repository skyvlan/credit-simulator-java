package org.technicaltest.creditsim;
import org.technicaltest.creditsim.controller.LoanController;
import org.technicaltest.creditsim.model.domain.entities.Loan;


public class Main {
    public static void main(String[] args)
    {
        LoanController loanController = new LoanController();
        System.out.println(args.length);
        if(args.length > 1){
            loanController.runFromFile(args[1]);
        }
        else{
            loanController.runMenu();
        }


    }


}
