module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind; // Aggiunto questo
    requires com.fasterxml.jackson.core;     // Aggiunto questo
    requires com.fasterxml.jackson.annotation; // Aggiunto questo
    opens com.example to javafx.fxml;
    opens com.Helper to com.fasterxml.jackson.databind, com.fasterxml.jackson.core, com.fasterxml.jackson.annotation; // Aggiunto questo
    opens com.DTO to com.fasterxml.jackson.databind, com.fasterxml.jackson.core, com.fasterxml.jackson.annotation; // Aggiunto questo
    exports com.example;
}


