package com.example.megasoft.model;

public class YoungestEldestWorker {
    private final String type;
    private final String name;
    private final java.time.LocalDate birthday;

    public YoungestEldestWorker(String type, String name, java.time.LocalDate birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public java.time.LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return type + " - " + name + " (" + birthday + ")";
    }
}
