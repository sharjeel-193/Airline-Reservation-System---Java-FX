package main.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getconnection(){
        String dbName = "zs6x3hYOhN";
        String dbUser = "zs6x3hYOhN";
        String dbPassword= "EcgCqNbXAq";
        String url = "jdbc:mysql://remotemysql.com/"+dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
