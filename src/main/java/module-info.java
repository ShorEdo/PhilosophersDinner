module com.example.philosophersdinner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.philosophersdinner to javafx.fxml;
    exports com.example.philosophersdinner;
}