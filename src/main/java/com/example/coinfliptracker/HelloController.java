package com.example.coinfliptracker;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    Double totalHeads;
    Double totalTails;
    @FXML
    private TextField totalFlips;
    @FXML
    private TextField FlipChance;
    @FXML
    private void OneHeads(){
        AddFlips( 1,1);
    }
    @FXML
    private void OneTails(){
        AddFlips(1, 0);
    }
    @FXML
    private void ClearFlips(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String dbQuery = "DELETE flips FROM flips WHERE flips = 1;";
        String dbQuery2 = "DELETE flips FROM flips WHERE flips = 0;";
        try{
            Statement statement = connectDB.createStatement();
            statement.execute(dbQuery);
            statement.execute(dbQuery2);
            updateTextFields();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    private void AddFlips( Integer quantity, Integer isHeads){
        totalFlips.setText(quantity.toString());
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String dbQuery = "INSERT INTO flips(flips) VALUES (" +isHeads.toString() +")";
        try{
            Statement statement = connectDB.createStatement();
            statement.execute(dbQuery);
            updateTextFields();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    private void updateTextFields(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String getHeads = "SELECT * FROM flips WHERE flips = 1;";
        String getTails = "SELECT * FROM flips WHERE flips = 0;";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getHeads);
            totalHeads = 0.0;

            while(queryResult.next()){
                //selectReciever.getItems().addAll(queryResult.getString(1));
                totalHeads++;
            }
            queryResult = statement.executeQuery(getTails);
            totalTails = 0.0;

            while(queryResult.next()){
                //selectReciever.getItems().addAll(queryResult.getString(1));
                totalTails++;
            }
            totalFlips.setText(totalHeads.toString() + " Heads and " + totalTails.toString() + " Tails");
            Double chanceOfFlip = ((totalHeads/(totalTails + totalHeads))*100);
            FlipChance.setText(chanceOfFlip.toString());

        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}