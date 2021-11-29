package com.example.coinfliptracker;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "coinflipdata";
        String databaseUser = "FlipExpert";
        String databasePassword = "BasicPassword123!";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch(Exception e){

            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }

}
