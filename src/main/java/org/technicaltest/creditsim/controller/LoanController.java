package org.technicaltest.creditsim.controller;

import org.technicaltest.creditsim.model.adapter.LoanUnitOfWork;
import org.technicaltest.creditsim.model.domain.entities.LoanOutput;
import org.technicaltest.creditsim.model.domain.enums.VehicleCondition;
import org.technicaltest.creditsim.model.domain.enums.VehicleType;
import org.technicaltest.creditsim.model.service.LoanInputService;
import org.technicaltest.creditsim.model.service.LoanService;
import org.technicaltest.creditsim.view.ConsoleView;

import java.util.Calendar;
import java.util.List;

public class LoanController {

    private LoanUnitOfWork uow;
    private LoanService loanService;
    private LoanInputService loanInputService;
    private ConsoleView view;

    public LoanController(LoanUnitOfWork uow, LoanService loanService, LoanInputService loanInputService){
        this.uow = uow;
        this.loanService = loanService;
        this.loanInputService = loanInputService;
        this.view = new ConsoleView();
    }

    public void runMenu(){
        this.view.output("Menu");
        this.view.output("1. Masukkan data pinjaman");
        this.view.output("2. Load data pinjaman existing");
        this.view.output("3. Exit");
        int menu = this.view.inputInt("Pilih menu: ");
        switch (menu){
            case 1:
                this.runInputCalc();
                break;
            case 2:
                this.runCalcFromAPI();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                this.view.output("Menu tidak tersedia");
                this.runMenu();
                break;
        }
    }

    public void runCalcFromAPI(){
        return;
    }

    public void runInputCalc(){
        VehicleType vehicleType = this.view.inputVehicleType();
        VehicleCondition vehicleCondition = this.view.inputVehicleCondition();
        int vehicleYear = this.view.inputVehicleYear();
        while(vehicleCondition == VehicleCondition.BARU && vehicleYear < Calendar.getInstance().get(Calendar.YEAR) - 1){
                this.view.output("Tahun kendaraan baru tidak boleh lebih dari 1 tahun");
                vehicleYear = this.view.inputVehicleYear();
            }
        float totalLoan = this.view.inputTotalLoan();
        int tenor = this.view.inputTenor();
        float downPayment = this.view.inputDownPayment();
        while (downPayment > totalLoan){
            this.view.output("Uang muka tidak boleh lebih besar dari total pinjaman");
            downPayment = this.view.inputDownPayment();
        }
        while(vehicleCondition == VehicleCondition.BARU && downPayment / totalLoan < 0.35){
            this.view.output("Uang muka untuk kendaraan baru tidak boleh kurang dari 35%");
            downPayment = this.view.inputDownPayment();
        }
        while(vehicleCondition == VehicleCondition.BEKAS && downPayment / totalLoan < 0.25){
            this.view.output("Uang muka untuk kendaraan bekas tidak boleh kurang dari 25%");
            downPayment = this.view.inputDownPayment();
        }
        this.loanInputService = new LoanInputService(this.uow);
        this.loanInputService.InputVehicle(vehicleType.toString(), vehicleCondition.toString(), vehicleYear);
        this.loanInputService.InputLoan(totalLoan, tenor, downPayment);
        this.loanService = new LoanService(this.uow);
        List<LoanOutput> calculation = this.loanService.calculateLoan();
        this.view.outputLoan(calculation);
        this.runMenu();

    }




}
