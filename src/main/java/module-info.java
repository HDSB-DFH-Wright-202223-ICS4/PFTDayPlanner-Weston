module loginassignment {
    requires javafx.controls;
    requires javafx.fxml;

    opens loginassignment to javafx.fxml;
    exports loginassignment;
}
