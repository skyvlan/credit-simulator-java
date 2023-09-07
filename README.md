# credit-simulator-java

# Credit Simulator
## Description
- This is a technical assesment for recruitment purposes. The goal is to create a credit simulator that will calculate the monthly payment of a loan.
- The project was made using Python 3.8 and was ported into Java 8.
- The project is using MVC architecture and Domain Driven Design.
- The project is using a CLI interface.
## Requirements
- Java 8


## How To Run
1. Clone the repository
2. Build the Java Project
3. Run the application
```bash
java -jar  credit-simulator-java.jar
```
you can also use files as input
```bash
java -jar  credit-simulator-java.jar <file_name>
```
the file should contain following json format to be parsable:
```json
{
    "vehicleModel":{
        "vehicleCondition" : "Baru",
        "vehicleType" : "Mobil",
        "tahunMobil" : "2019",
        "jumlahDownPayment" : "25000000",
        "jumlahPinjaman" : "100000000",
        "tenorCicilan":"5"
    },
    "responseCode":"00",
    "responseMessage":"Succeed"
}
```

