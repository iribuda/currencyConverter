package wiar.currencyConverter;

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
    // Objekt für die Durchführung von Währungsumrechnungen
    private CurrencyConverter currencyConverter;
    // Textfeld für die Eingabe des Betrags
    private TextField amountInput;
    // Dropdown-Liste für die Auswahl der Ausgangswährung
    private ComboBox<Currency> fromCurrencyDropdown;
    // Dropdown-Liste für die Auswahl der Zielwährung
    private ComboBox<Currency> toCurrencyDropdown;

    public CurrencyConverterController(CurrencyConverter currencyConverter, TextField amountInput, ComboBox<Currency> fromCurrencyDropdown, ComboBox<Currency> toCurrencyDropdown) {
        this.currencyConverter = currencyConverter;
        this.amountInput = amountInput;
        this.fromCurrencyDropdown = fromCurrencyDropdown;
        this.toCurrencyDropdown = toCurrencyDropdown;
    }

    /**
     * Verarbeitet die Währungsumrechnung, basierend auf den Benutzereingaben.
     */
    public void handleConversion() {
        try {
            double amount = Double.parseDouble(amountInput.getText());
            Currency fromCurrency = fromCurrencyDropdown.getValue();
            Currency toCurrency = toCurrencyDropdown.getValue();

            // Führt die Währungsumrechnung durch und formatiert das Ergebnis
            String result = Double.toString(currencyConverter.convert(amount, fromCurrency, toCurrency));

            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
            result = currencyFormatter.format(amount) + " " + fromCurrency.getName() + " is equal to " + result;

            // Zeigt das Ergebnis in einem Informationsdialog an
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Conversion Result");
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