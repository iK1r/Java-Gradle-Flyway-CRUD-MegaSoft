package com.example.megasoft;

import java.util.List;
import com.example.megasoft.model.Client;

public class ClientServiceDemo {
    public static void main(String[] args) {
        DatabaseMigrationService.migrate();
        ClientService service = new ClientService();

        int before = service.listAll().size();
        long id = service.create("Test Client");
        System.out.println("Created client ID: " + id);
        check("Test Client".equals(service.getById(id)), "Create/GetById check failed");

        service.setName(id, "Updated Client");
        System.out.println("Updated client: " + service.getById(id));
        check("Updated Client".equals(service.getById(id)), "Update check failed");

        List<Client> clients = service.listAll();
        System.out.println("Clients after creation: " + clients.size());
        check(clients.stream().anyMatch(client -> client.getId() == id), "ListAll check failed");
        check(clients.size() == before + 1, "Incorrect client count after create");

        service.deleteById(id);
        check(service.getById(id) == null, "Delete check failed");
        check(service.listAll().size() == before, "Incorrect client count after delete");
        System.out.println("Deleted temporary client ID: " + id);

        expectInvalid(() -> service.create("A"));
        expectInvalid(() -> service.create(" "));
        expectInvalid(() -> service.create("X".repeat(1001)));
        expectInvalid(() -> service.setName(id, ""));
        expectInvalid(() -> service.getById(0));
        System.out.println("All CRUD checks passed! Validation rejects invalid input.");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void expectInvalid(Runnable task) {
        try {
            task.run();
            throw new AssertionError("Invalid input was accepted");
        } catch (IllegalArgumentException expected) {
            System.out.println("Validation OK: " + expected.getMessage());
        }
    }
}
