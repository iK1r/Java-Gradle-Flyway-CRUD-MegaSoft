package com.example.megasoft.model;

public class WorkerSalary {
    private final String name;
    private final int salary;

    public WorkerSalary(String name, int salary) {
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
