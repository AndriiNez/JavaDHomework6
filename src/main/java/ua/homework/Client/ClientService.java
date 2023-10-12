package ua.homework.Client;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientService {
    private Connection connection;


    public ClientService(Connection connection) throws SQLException {
        this.connection = connection;

    }

    public long create(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null!");
        } else if (name.length() < 2 || name.length() > 1000) {
            throw new IllegalArgumentException("Name cannot be less than 2 characters or more than 1000 characters");
        }
        try (PreparedStatement createSt = connection.
                prepareStatement("INSERT INTO client (name) VALUES (?)")) {
            createSt.setString(1, name);
            createSt.executeUpdate();
            long id;
            try (PreparedStatement selectMaxIdSt = connection.
                    prepareStatement("SELECT max(id)AS maxId From client");
                 ResultSet rs = selectMaxIdSt.executeQuery()) {
                rs.next();
                id = rs.getLong("maxId");
            }

            return id;
        } catch (SQLException e) {
            try {
                throw new SQLException("Failed to create client", e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public String getById(long id) throws SQLException {
        String name = "";
        try (PreparedStatement getById = connection.
                prepareStatement("SELECT name FROM client WHERE id = ?")) {


            getById.setLong(1, id);

            try (ResultSet rs = getById.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                name = rs.getString("name");
            }
        } catch (SQLException e) {

        }
        return name;
    }

    public void setName(long id, String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null!");
        } else if (name.length() < 2 || name.length() > 1000) {
            throw new IllegalArgumentException("Name cannot be less than 2 characters or more than 1000 characters");
        }

        try (PreparedStatement setNameUpdateSt = connection.
                prepareStatement("UPDATE client SET name = ? WHERE id = ?")) {
            setNameUpdateSt.setString(1, name);
            setNameUpdateSt.setLong(2, id);
            setNameUpdateSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) throws SQLException {
        try (PreparedStatement deleteSt = connection.
                prepareStatement("DELETE FROM client WHERE id = ?")) {


            deleteSt.setLong(1, id);
            deleteSt.executeUpdate();
        }
    }


    public List<Client> listAll() {
        List<Client> result = new ArrayList<>();
        try (PreparedStatement getAllClientSt = connection.
                prepareStatement("SELECT * FROM client")) {
            ResultSet resultSet = getAllClientSt.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Client client = new Client(name, id);
                result.add(client);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}