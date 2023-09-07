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
        System.out.println(message);
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
        System.out.println(message);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        else{
            throw new IllegalArgumentException("Input tidak valid");
        }
    }

    @Override
    public String inputString(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public VehicleType inputVehicleType() {
        String vehicleType = "";
        try{
            vehicleType = this.inputString("Masukkan jenis kendaraan (mobil|motor): ");
        }
        catch (IllegalArgumentException e){
            this.output("Jenis kendaraan tidak valid\n");
            this.inputVehicleType();
        }


        if (vehicleType.equals("mobil")) {
            return VehicleType.MOBIL;
        } else if (vehicleType.equals("motor")) {
            return VehicleType.MOTOR;
        } else {
            this.output("Jenis kendaraan tidak valid\n");
            this.inputVehicleType();
            return null;
        }

    }

    public VehicleCondition inputVehicleCondition() {
        String vehicleCondition = "";
        try{
             vehicleCondition = this.inputString("Masukkan kondisi kendaraan (baru|bekas): ");
        }
        catch (IllegalArgumentException e){
            this.output("Kondisi kendaraan tidak valid\n");
            this.inputVehicleCondition();
        }
        if(vehicleCondition.equals("baru")){
            return VehicleCondition.BARU;
        }
        else if (vehicleCondition.equals("bekas")){
            return VehicleCondition.BEKAS;
        }
        else{
            this.output("Kondisi kendaraan tidak valid\n");
            this.inputVehicleCondition();
            return null;
        }

    }

    public int inputVehicleYear() {
        int year = 0;
        try{
            year = this.inputInt("Masukkan tahun kendaraan: ");
        }
        catch (IllegalArgumentException e){
            this.output("Tahun kendaraan tidak valid\n");
            this.inputVehicleYear();
        }

        if (year < 1000 || year > 9999){
            this.output("Tahun kendaraan tidak valid\n");
            this.inputVehicleYear();
            return 0;
        }
        else{
            return year;
        }

    }

    public int inputTenor() {
        int tenor = 0;
        try{
            tenor = this.inputInt("Masukkan tenor: ");
        }
        catch (IllegalArgumentException e){
            this.output("Tenor tidak valid\n");
            this.inputTenor();
        }

        if (tenor < 1 || tenor > 6){
            this.output("Tenor tidak valid\n");
            this.inputTenor();
            return 0;
        }
        else{
           return tenor;
        }
    }


    public float inputTotalLoan(){
        float totalLoan = 0;
        try{
            totalLoan = this.inputFloat("Masukkan total pinjaman: ");
        }
        catch (IllegalArgumentException e){
            this.output("Total pinjaman tidak valid\n");
            this.inputTotalLoan();
        }
        if (totalLoan >= 1000000000){
            this.output("Total pinjaman tidak valid\n");
            this.inputTotalLoan();
            return 0;
        }
        else{
            return totalLoan;
        }
    }


    public float inputDownPayment(){
        float downPayment = 0;
        try{
            downPayment = this.inputFloat("Masukkan uang muka: ");
        }
        catch (IllegalArgumentException e){
            this.output("Uang muka tidak valid\n");
            this.inputDownPayment();
        }

        if (downPayment < 0){
            this.output("Uang muka tidak valid\n");
            this.inputDownPayment();
            return 0;
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
