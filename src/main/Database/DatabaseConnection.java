package main.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getconnection(){
        String dbName = "airlineSystem";
        String dbUser = "root";
        String dbPassword= "";
        String url = "jdbc:mysql://localhost/"+dbName;
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
