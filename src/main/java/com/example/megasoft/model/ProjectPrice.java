package com.example.megasoft.model;

public class ProjectPrice {
    private final String name;
    private final long price;

    public ProjectPrice(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}
