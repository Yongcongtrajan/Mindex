package com.mindex.challenge.data;

import java.util.Date;


public class Compensation {
    private Employee employee;
    private double salary;
    private Date effectiveDate;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Compensation() {}
}
