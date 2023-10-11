package ua.homework.Client;

public class Client {
    private long id;
    private String name;


    public Client(String name, long id) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
