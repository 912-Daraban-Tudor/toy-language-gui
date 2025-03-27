module com.example {
    requires javafx.controls;

    requires javafx.fxml;

    requires transitive javafx.graphics;
    opens com.example to javafx.fxml;
    opens com.example.GUI to javafx.fxml;
    exports com.example;
    exports com.example.GUI to javafx.fxml;
}
