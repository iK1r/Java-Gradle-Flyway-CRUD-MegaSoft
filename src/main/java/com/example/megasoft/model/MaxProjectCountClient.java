package com.example.megasoft.model;

public class MaxProjectCountClient {
    private final String name;
    private final int projectCount;

    public MaxProjectCountClient(String name, int projectCount) {
        this.name = name;
        this.projectCount = projectCount;
    }

    public String getName() {
        return name;
    }

    public int getProjectCount() {
        return projectCount;
    }

    @Override
    public String toString() {
        return name + " - projects: " + projectCount;
    }
}
