module com.example.coinfliptracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.coinfliptracker to javafx.fxml;
    exports com.example.coinfliptracker;
}