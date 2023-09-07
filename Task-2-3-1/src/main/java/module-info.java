module io.github.xman145.task231 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens io.github.xman145.task231 to javafx.fxml;
    exports io.github.xman145.task231;
}