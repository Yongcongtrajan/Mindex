package com.mindex.challenge.data;


public class ReportingStructure {
    private String firstName;
    private String LastName;
    private int numberOfReports;

    public ReportingStructure(String firstName,String lastName,int numberOfReports){

        this.numberOfReports = numberOfReports;
        this.firstName = firstName;
        this.LastName = lastName;
    }

    public ReportingStructure() {
    }


    public int getNumberOfReports() {
        return numberOfReports;
    }



    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }




}
