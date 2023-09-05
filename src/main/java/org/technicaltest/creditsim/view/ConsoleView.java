package org.technicaltest.creditsim.view;
import org.technicaltest.creditsim.model.domain.entities.LoanOutput;
import org.technicaltest.creditsim.model.domain.enums.VehicleCondition;
import org.technicaltest.creditsim.model.domain.enums.VehicleType;
import org.technicaltest.creditsim.model.domain.entities.LoanOutput;

import java.util.Scanner;
import java.util.List;
public class ConsoleView implements BaseView{


    @Override
    public void output(String message) {
        System.out.println(message);
    }

    @Override
    public float inputFloat(String message) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextFloat()) {
            return scanner.nextFloat();
        }
        else{
            System.out.println("Input tidak valid");
            throw new IllegalArgumentException("Input tidak valid");
        }

    }

    @Override
    public int inputInt(String message) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        else{
            System.out.println("Input tidak valid");
            throw new IllegalArgumentException("Input tidak valid");
        }
    }

    @Override
    public String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public VehicleType inputVehicleType() {
        this.output("Masukkan jenis kendaraan (mobil|motor): ");
        Scanner scanner = new Scanner(System.in);
        String vehicleType = scanner.nextLine().toLowerCase();
        if(vehicleType.equals("mobil")){
            return VehicleType.MOBIL;
        }
        else if (vehicleType.equals("motor")){
            return VehicleType.MOTOR;
        }
        else{
            this.output("Jenis kendaraan tidak valid\n");
            return null;
        }

    }

    public VehicleCondition inputVehicleCondition() {
        this.output("Masukkan kondisi kendaraan (baru|bekas): ");
        Scanner scanner = new Scanner(System.in);
        String vehicleCondition = scanner.nextLine().toLowerCase();
        if(vehicleCondition.equals("baru")){
            return VehicleCondition.BARU;
        }
        else if (vehicleCondition.equals("bekas")){
            return VehicleCondition.BEKAS;
        }
        else{
            this.output("Kondisi kendaraan tidak valid\n");
            return null;
        }

    }

    public int inputVehicleYear() {
        this.output("Tahun kendaraan: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        if (scanner.nextInt() < 1000 || scanner.nextInt() > 9999){
            this.output("Tahun kendaraan tidak valid");
            return -1;
        }
        else{
            this.output("Tahun kendaraan tidak valid");
            return -1;
        }

    }

    public int inputTenor() {
        this.output("Masukkan Tenor Pinjaman: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        if (scanner.nextInt() < 1 || scanner.nextInt() > 6){
            this.output("Tenor tidak valid");
            return -1;
        }
        else{
            this.output("Tenor tidak valid");
            return -1;
        }
    }


    public float inputTotalLoan(){
        float totalLoan = this.inputFloat("Masukkan total pinjaman: ");
        if (totalLoan >= 1000000000){
            this.output("Total pinjaman tidak valid");
            return -1;
        }
        else{
            return totalLoan;
        }
    }


    public float inputDownPayment(){
        float downPayment = this.inputFloat("Masukkan uang muka: ");
        if (downPayment < 0){
            this.output("Uang muka tidak valid");
            return -1;
        }
        else{
            return downPayment;
        }
    }


    public void outputLoan(List<LoanOutput> loanOutputList){
        for(LoanOutput loanOutput : loanOutputList){
            this.output("Tahun " + loanOutput.getYear() + " : Rp. " + loanOutput.getInstallment() + " Suku Bunga : " + loanOutput.getInterest() + "%");
        }
    }





}
