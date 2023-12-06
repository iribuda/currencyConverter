module whz.wiar.i18n {
    requires javafx.controls;
    requires javafx.fxml;


    opens wiar.currencyConverter to javafx.fxml;
    exports wiar.currencyConverter;
}