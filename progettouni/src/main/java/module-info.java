module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens app.main to javafx.fxml;
    exports app.main;
}
