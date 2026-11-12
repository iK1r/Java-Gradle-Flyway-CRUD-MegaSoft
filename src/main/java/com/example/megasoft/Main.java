package com.example.megasoft;

public class Main {
    public static void main(String[] args) {
        DatabaseMigrationService.migrate();

        DatabaseQueryService service = new DatabaseQueryService();

        System.out.println("=== Workers with maximum salary ===");
        service.findMaxSalaryWorkers().forEach(System.out::println);

        System.out.println("\n=== Clients with most projects ===");
        service.findMaxProjectsClient().forEach(System.out::println);

        System.out.println("\n=== Longest projects ===");
        service.findLongestProjects().forEach(System.out::println);

        System.out.println("\n=== Youngest and eldest workers ===");
        service.findYoungestEldestWorkers().forEach(System.out::println);

        System.out.println("\n=== Workers with salary from $3000 to $5000 ===");
        service.findWorkersBySalaryRange(3000, 5000).forEach(System.out::println);

        System.out.println("\n=== Project prices ===");
        service.printProjectPrices().forEach(System.out::println);
    }
}
