package com.example.megasoft;

import com.example.megasoft.model.LongestProject;
import com.example.megasoft.model.MaxProjectCountClient;
import com.example.megasoft.model.MaxSalaryWorker;
import com.example.megasoft.model.ProjectPrice;
import com.example.megasoft.model.YoungestEldestWorker;
import com.example.megasoft.model.WorkerSalary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxSalaryWorker> findMaxSalaryWorkers() {
        List<MaxSalaryWorker> result = new ArrayList<>();
        String sql = readSql("find_max_salary_worker.sql");

        try (PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                result.add(new MaxSalaryWorker(rs.getString("NAME"), rs.getInt("SALARY")));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to find max salary workers", e);
        }
        return result;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sql = readSql("find_max_projects_client.sql");

        try (PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                result.add(new MaxProjectCountClient(rs.getString("NAME"), rs.getInt("PROJECT_COUNT")));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to find clients with most projects", e);
        }
        return result;
    }

    public List<LongestProject> findLongestProjects() {
        List<LongestProject> result = new ArrayList<>();
        String sql = readSql("find_longest_project.sql");

        try (PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                result.add(new LongestProject(rs.getString("NAME"), rs.getInt("MONTH_COUNT")));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to find longest projects", e);
        }
        return result;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        List<YoungestEldestWorker> result = new ArrayList<>();
        String sql = readSql("find_youngest_eldest_workers.sql");

        try (PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                result.add(new YoungestEldestWorker(
                        rs.getString("TYPE"),
                        rs.getString("NAME"),
                        rs.getDate("BIRTHDAY").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to find youngest and eldest workers", e);
        }
        return result;
    }

    public List<ProjectPrice> printProjectPrices() {
        List<ProjectPrice> result = new ArrayList<>();
        String sql = readSql("print_project_prices.sql");

        try (PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                result.add(new ProjectPrice(rs.getString("NAME"), rs.getLong("PRICE")));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to calculate project prices", e);
        }
        return result;
    }

    // Two parameters are passed separately, rather than inserted into the SQL text.
    public List<WorkerSalary> findWorkersBySalaryRange(int minSalary, int maxSalary) {
        if (minSalary < 0 || maxSalary < minSalary) {
            throw new IllegalArgumentException("Incorrect salary range");
        }

        List<WorkerSalary> result = new ArrayList<>();
        String sql = readSql("find_workers_by_salary_range.sql");

        try (PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql)) {
            statement.setInt(1, minSalary);
            statement.setInt(2, maxSalary);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    result.add(new WorkerSalary(rs.getString("NAME"), rs.getInt("SALARY")));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to find workers by salary range", e);
        }

        return result;
    }

    private String readSql(String fileName) {
        try {
            return Files.readString(Path.of("sql", fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read SQL file: " + fileName, e);
        }
    }
}
