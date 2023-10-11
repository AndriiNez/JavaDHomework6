package ua.homework.Migrations;

import org.flywaydb.core.Flyway;


public class DatabaseMigrationsService {

    public  void initDbService(String connectionUrl) {

        Flyway flyway = Flyway.
                configure().
                dataSource(connectionUrl,null, null).
                load();

        flyway.migrate();
    }
}


