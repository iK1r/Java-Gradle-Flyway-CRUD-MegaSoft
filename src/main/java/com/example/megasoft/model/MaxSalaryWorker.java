package com.example.megasoft.model;

public class MaxSalaryWorker {
    private final String name;
    private final int salary;

    public MaxSalaryWorker(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " - $" + salary;
    }
}
