package wiar.currencyConverter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import wiar.currencyConverter.logic.Currency;
import wiar.currencyConverter.logic.CurrencyConverter;

public class CurrencyConverterApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        CurrencyConverter currencyConverter = new CurrencyConverter();

        primaryStage.setTitle("Конвертер валюты");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label amount = new Label("Сумма:");
        grid.add(amount, 0, 0);

        TextField amountInput = new TextField();
        grid.add(amountInput, 1, 0);

        Label from = new Label("Из:");
        grid.add(from, 0, 1);

        ComboBox<Currency> fromCurrencyDropdown = new ComboBox<>();
        fromCurrencyDropdown.getItems().addAll(Currency.EUR, Currency.USD, Currency.KGS);
        fromCurrencyDropdown.setValue(Currency.USD);
        grid.add(fromCurrencyDropdown, 0, 2);

        Label to = new Label("В:");
        grid.add(to, 1, 1);

        ComboBox<Currency> toCurrencyDropdown = new ComboBox<>();
        toCurrencyDropdown.getItems().addAll(Currency.EUR, Currency.USD, Currency.KGS);
        toCurrencyDropdown.setValue(Currency.EUR);
        grid.add(toCurrencyDropdown, 1, 2);

        Button btn = new Button("Посчитать");

        CurrencyConverterController controller = new CurrencyConverterController(currencyConverter, amountInput, fromCurrencyDropdown, toCurrencyDropdown);
        btn.setOnAction(e -> controller.handleConversion());

        grid.add(btn, 0, 3);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}