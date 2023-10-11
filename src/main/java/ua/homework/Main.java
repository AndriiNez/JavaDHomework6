package ua.homework;


import ua.homework.Migrations.DatabaseMigrationsService;
import ua.homework.database.Database;

public class Main {
    public static void main(String[] args)  {

        Database database = Database.getInstance();
       new DatabaseMigrationsService().initDbService(database.getConnectionUrl());
    }
}
