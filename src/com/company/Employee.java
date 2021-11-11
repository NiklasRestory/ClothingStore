package com.company;

public class Employee extends Person {
    private int salary;
    private String position;

    public Employee(String name, String address, String gender, int salary, String position) {
        super(name, address, gender);
        this.salary = salary;
        this.position = position;
    }
}
