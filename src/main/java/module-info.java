module com.example.cassebriques {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.cassebriques to javafx.fxml;
    exports com.example.cassebriques;
}