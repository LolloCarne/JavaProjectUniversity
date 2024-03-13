module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    opens com.example to javafx.fxml;
    opens com.Helper to com.google.gson;
    opens com.DTO to com.google.gson;
    exports com.example;
}


