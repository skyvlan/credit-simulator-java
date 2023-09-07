package org.technicaltest.creditsim.controller;

import org.json.JSONObject;
import org.technicaltest.creditsim.model.adapter.LoanUnitOfWork;
import org.technicaltest.creditsim.model.domain.entities.LoanOutput;
import org.technicaltest.creditsim.model.domain.enums.VehicleCondition;
import org.technicaltest.creditsim.model.domain.enums.VehicleType;
import org.technicaltest.creditsim.model.service.LoanInputService;
import org.technicaltest.creditsim.model.service.LoanService;
import org.technicaltest.creditsim.view.ConsoleView;
import org.technicaltest.creditsim.helper.Requests;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import org.json.JSONException;
import org.json.JSONObject;

public class LoanController {

    private final LoanUnitOfWork uow;
    private LoanService loanService;
    private LoanInputService loanInputService;
    private final ConsoleView view;

    public LoanController() {
        this.uow = new LoanUnitOfWork();
        this.view = new ConsoleView();
    }

    public void runMenu() {
        this.view.output("Menu");
        this.view.output("1. Masukkan data pinjaman");
        this.view.output("2. Load data pinjaman existing");
        this.view.output("3. Exit");
        int menu = this.view.inputInt("Pilih menu: ");
        switch (menu) {
            case 1:
                this.runInputCalc();
                break;
            case 2:
                try {
                    this.runCalcFromAPI("http://www.mocky.io/v2/5d06e6ae3000005300051d16");
                } catch (IOException e) {
                    this.view.output("Error: " + e.getMessage());
                    this.runMenu();
                }
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

    public void runCalcFromAPI(String url) throws IOException {
        Requests requests = new Requests(url);
        String response = requests.Get();
        response = response.replace("\"responseCode\":\"00\",", "");
        response = response.replace("\"responseMessage\":\"Succeed\"", "");
        JSONObject jsonObject = new JSONObject(response);
        JSONObject vehicle = jsonObject.getJSONObject("vehicleModel");
        String vehicleType = vehicle.getString("vehicleType").toUpperCase();
        String vehicleCondition = vehicle.getString("vehicleCondition").toUpperCase();
        int vehicleYear = vehicle.getInt("tahunMobil");
        float totalDownPayment = vehicle.getFloat("jumlahDownPayment");
        float totalLoan = vehicle.getFloat("jumlahPinjaman");
        int tenor = vehicle.getInt("tenorCicilan");
        this.loanInputService = new LoanInputService(this.uow);
        this.loanInputService.InputVehicle(vehicleType, vehicleCondition, vehicleYear);
        this.loanInputService.InputLoan(totalLoan, tenor, totalDownPayment);
        this.loanService = new LoanService(this.uow);
        List<LoanOutput> calculation = this.loanService.calculateLoan();
        this.view.outputLoan(calculation);
    }

    public void runInputCalc() {
        VehicleType vehicleType = this.view.inputVehicleType();
        VehicleCondition vehicleCondition = this.view.inputVehicleCondition();
        int vehicleYear = this.view.inputVehicleYear();
        while (vehicleCondition == VehicleCondition.BARU && vehicleYear < Calendar.getInstance().get(Calendar.YEAR) - 1) {
            this.view.output("Tahun kendaraan baru tidak boleh lebih dari 1 tahun");
            vehicleYear = this.view.inputVehicleYear();
        }
        float totalLoan = this.view.inputTotalLoan();
        int tenor = this.view.inputTenor();
        float downPayment = this.view.inputDownPayment();
        while (downPayment > totalLoan) {
            this.view.output("Uang muka tidak boleh lebih besar dari total pinjaman");
            downPayment = this.view.inputDownPayment();
        }
        while (vehicleCondition == VehicleCondition.BARU && downPayment / totalLoan < 0.35) {
            this.view.output("Uang muka untuk kendaraan baru tidak boleh kurang dari 35%");
            downPayment = this.view.inputDownPayment();
        }
        while (vehicleCondition == VehicleCondition.BEKAS && downPayment / totalLoan < 0.25) {
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

    public void runFromFile(String filePath) throws JSONException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder data = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            try {
                JSONObject jsonObject = new JSONObject(data.toString());
                JSONObject vehicle = jsonObject.getJSONObject("vehicleModel");
                String vehicleType = vehicle.getString("vehicleType").toUpperCase();
                String vehicleCondition = vehicle.getString("vehicleCondition").toUpperCase();
                int vehicleYear = vehicle.getInt("tahunMobil");
                float totalDownPayment = vehicle.getFloat("jumlahDownPayment");
                float totalLoan = vehicle.getFloat("jumlahPinjaman");
                int tenor = vehicle.getInt("tenorCicilan");
                this.loanInputService = new LoanInputService(this.uow);
                this.loanInputService.InputVehicle(vehicleType, vehicleCondition, vehicleYear);
                this.loanInputService.InputLoan(totalLoan, tenor, totalDownPayment);
                this.loanService = new LoanService(this.uow);
                List<LoanOutput> calculation = this.loanService.calculateLoan();
                this.view.outputLoan(calculation);
                this.runMenu();
            } catch (JSONException e) {
                this.view.output("Error: " + e.getMessage());
                this.runMenu();
            }

        } catch (IOException e) {
            this.view.output("Error: " + e.getMessage());
            this.runMenu();
        }


    }
}
