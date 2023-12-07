package wiar.currencyConverter;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import wiar.currencyConverter.logic.Currency;
import wiar.currencyConverter.logic.CurrencyConverter;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Steuert die Währungsumrechnung im JavaFX-Benutzeroberflächenkontext.
 */
public class CurrencyConverterController {
    // Textfeld für die Eingabe des Betrags
    @FXML
    private TextField amountInput;
    // Dropdown-Liste für die Auswahl der Ausgangswährung
    @FXML
    private ComboBox<Currency> fromCurrencyDropdown;
    // Dropdown-Liste für die Auswahl der Zielwährung
    @FXML
    private ComboBox<Currency> toCurrencyDropdown;

    public void initialize(){
        fromCurrencyDropdown.getItems().addAll(Currency.EUR, Currency.USD, Currency.KGS);
        fromCurrencyDropdown.setValue(Currency.USD);

        toCurrencyDropdown.getItems().addAll(Currency.EUR, Currency.USD, Currency.KGS);
        toCurrencyDropdown.setValue(Currency.EUR);
    }

    /**
     * Verarbeitet die Währungsumrechnung, basierend auf den Benutzereingaben.
     */
    @FXML
    public void handleConversion() {
        try {
            double amount = Double.parseDouble(amountInput.getText());
            Currency fromCurrency = fromCurrencyDropdown.getValue();
            Currency toCurrency = toCurrencyDropdown.getValue();

            CurrencyConverter currencyConverter = new CurrencyConverter();
            // Führt die Währungsumrechnung durch und formatiert das Ergebnis
            String result = String.format("%.2f", currencyConverter.convert(amount, fromCurrency, toCurrency));

            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
            result = currencyFormatter.format(amount) + " " + fromCurrency.getName() + " равно " + result + " " + toCurrency.getName();

            // Zeigt das Ergebnis in einem Informationsdialog an
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Результат");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.showAndWait();
        } catch (NumberFormatException ex) {
            showError("Неверное значение. Введите число!");
        } catch (Exception ex) {
            showError("Возникла ошибка.");
        }
    }

    /**
     * Zeigt eine Fehlermeldung in einem Dialog an.
     *
     * @param message Die Fehlermeldung, die angezeigt werden soll.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}