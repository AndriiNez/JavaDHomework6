package ua.homework.database;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class Database {
    private static final Database INSTANCE = new Database();
    private static final String CONNECTION_URL = "jdbc:h2:./test";
    private static HikariDataSource dataSource;


    private Database() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(CONNECTION_URL);


        dataSource = new HikariDataSource(config);
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public String getConnectionUrl() {
        return CONNECTION_URL;
    }

    public void close() {
        dataSource.close();
    }
}