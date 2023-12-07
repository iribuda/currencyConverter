module app {
    requires javafx.controls;
    requires javafx.fxml;


    opens wiar.currencyConverter to javafx.fxml;
    exports wiar.currencyConverter;
}