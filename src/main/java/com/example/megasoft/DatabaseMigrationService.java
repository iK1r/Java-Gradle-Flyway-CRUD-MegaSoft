package com.example.megasoft;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.output.MigrateResult;

public class DatabaseMigrationService {

    public static void main(String[] args) {
        migrate();
    }

    public static void migrate() {
        // The Database singleton creates the local data directory on first use.
        Database.getInstance();

        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:./data/megasoft", "sa", "")
                .locations("classpath:db/migration")
                .load();

        MigrateResult result = flyway.migrate();
        System.out.println("Flyway migrations executed now: " + result.migrationsExecuted);
        for (MigrationInfo migration : flyway.info().applied()) {
            System.out.println(migration.getScript() + " - " + migration.getState());
        }
    }
}
