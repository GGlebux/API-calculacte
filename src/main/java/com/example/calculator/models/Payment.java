package com.example.calculator.models;

public class Payment {
    private Double salary;
    private Integer vacation;
    private String date;

    public Payment() {}

    public Payment(Double salary, Integer vacation, String date) {
        this.salary = salary;
        this.vacation = vacation;
        this.date = date;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setVacation(Integer vacation) {
        this.vacation = vacation;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getSalary() {
        return salary;
    }

    public Integer getVacation() {
        return vacation;
    }

    public String getDate() {
        return date;
    }
}
