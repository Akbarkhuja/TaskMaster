module com.example.homeartifact {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.homeartifact to javafx.fxml;
    exports com.example.homeartifact;
}