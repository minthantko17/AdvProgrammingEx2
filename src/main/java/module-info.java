module se233.chapter2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se233.chapter2 to javafx.fxml;
    exports se233.chapter2;
}