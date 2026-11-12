package com.example.megasoft;

import com.example.megasoft.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final Connection connection = Database.getInstance().getConnection();

    public long create(String name) {
        String checkedName = validateName(name);
        String sql = "INSERT INTO client (name) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, checkedName);
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
                throw new IllegalStateException("Database did not return a client ID");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot create client", e);
        }
    }

    public String getById(long id) {
        validateId(id);
        String sql = "SELECT name FROM client WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next() ? rs.getString("name") : null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot find client", e);
        }
    }

    public void setName(long id, String name) {
        validateId(id);
        String checkedName = validateName(name);
        String sql = "UPDATE client SET name = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, checkedName);
            statement.setLong(2, id);
            if (statement.executeUpdate() == 0) {
                throw new IllegalArgumentException("Client not found: " + id);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot update client", e);
        }
    }

    public void deleteById(long id) {
        validateId(id);
        String sql = "DELETE FROM client WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                throw new IllegalArgumentException("Client not found: " + id);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot delete client", e);
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id, name FROM client ORDER BY id";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                clients.add(new Client(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot list clients", e);
        }
        return clients;
    }

    private static void validateId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Client ID must be positive");
        }
    }

    private static String validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Client name cannot be null");
        }
        String value = name.trim();
        if (value.length() < 2 || value.length() > 1000) {
            throw new IllegalArgumentException("Client name must contain 2 to 1000 characters");
        }
        return value;
    }
}
