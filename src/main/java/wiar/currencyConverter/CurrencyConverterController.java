package wiar.currencyConverter;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import wiar.currencyConverter.logic.Currency;
import wiar.currencyConverter.logic.CurrencyConverter;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyConverterController {
    private CurrencyConverter currencyConverter;
    private TextField amountInput;
    private ComboBox<Currency> fromCurrencyDropdown;
    private ComboBox<Currency> toCurrencyDropdown;

    public CurrencyConverterController(CurrencyConverter currencyConverter, TextField amountInput, ComboBox<Currency> fromCurrencyDropdown, ComboBox<Currency> toCurrencyDropdown) {
        this.currencyConverter = currencyConverter;
        this.amountInput = amountInput;
        this.fromCurrencyDropdown = fromCurrencyDropdown;
        this.toCurrencyDropdown = toCurrencyDropdown;
    }

    public void handleConversion() {
        try {
            double amount = Double.parseDouble(amountInput.getText());
            Currency fromCurrency = fromCurrencyDropdown.getValue();
            Currency toCurrency = toCurrencyDropdown.getValue();

            String result = Double.toString(currencyConverter.convert(amount, fromCurrency, toCurrency));

            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
            result = currencyFormatter.format(amount) + " " + fromCurrency.getName() + " is equal to " + result;

            // Display the result
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Conversion Result");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.showAndWait();
        } catch (NumberFormatException ex) {
            showError("Invalid input. Please enter a valid number.");
        } catch (Exception ex) {
            showError("An error occurred during the conversion.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}