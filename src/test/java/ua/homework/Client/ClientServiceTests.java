package ua.homework.Client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.homework.Migrations.DatabaseMigrationsService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

class ClientServiceTests {
    private Connection connection;
    private ClientService service;

    @BeforeEach
    public void beforeEach() throws SQLException {
        final String connectionUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

        new DatabaseMigrationsService().initDbService(connectionUrl);

        connection = DriverManager.
                getConnection(connectionUrl);
        service = new ClientService(connection);
    }

    @AfterEach
    public void afterEach() throws SQLException {
        connection.close();

    }

    @Test
    public void testThanClientCreateCorectliAndGetById() throws SQLException {
        long id = service.create("Ololo");

        Assertions.assertEquals("Ololo", service.getById(id));
    }

    @Test
    public void testThanClientCreateSetNameNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.create(null);
        });
    }

    @Test
    public void testThanClientCreateSetNameLess2Characters() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.create("O");
        });
    }

    @Test
    public void testThanSetNewNameWithId() throws SQLException {
        service.setName(4, "Rolton");

        Assertions.assertEquals("Rolton", service.getById(4));
    }

    @Test
    public void testThanSetNewNameWithIdNameNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.setName(3, null);
        });
    }

    @Test
    public void testThanSetNewNameWithIdSetNameLess2Characters() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.setName(3, null);
        });
    }

    @Test
    public void testThanDeleteClientByID() throws SQLException {
        service.deleteById(3);
        Assertions.assertEquals(null, service.getById(3));
    }

    @Test
    public void testThanGetListAllClient() {
        List<Client> clients = service.listAll();
        for (Client client : clients) {
            System.out.println("id: " + client.getId() + " , Name: " + client.getName());
        }
    }

}