module com.example.canvasdrawing {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.canvasdrawing to javafx.fxml;
    exports com.example.canvasdrawing;
}