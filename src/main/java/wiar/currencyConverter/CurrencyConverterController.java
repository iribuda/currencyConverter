package wiar.currencyConverter;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import wiar.currencyConverter.logic.Currency;
import wiar.currencyConverter.logic.CurrencyConverter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Steuert die Währungsumrechnung im JavaFX-Benutzeroberflächenkontext.
 */
public class CurrencyConverterController {
    public Label labelAmount;
    public Label labelFrom;
    public Label labelTo;
    public Button btnConvert;
    public Label labelLanguage;
    // Textfeld für die Eingabe des Betrags
    @FXML
    private TextField amountInput;
    // Dropdown-Liste für die Auswahl der Ausgangswährung
    @FXML
    private ComboBox<Currency> fromCurrencyDropdown;
    // Dropdown-Liste für die Auswahl der Zielwährung
    @FXML
    private ComboBox<Currency> toCurrencyDropdown;
    @FXML
    private ComboBox<Locale> localeDropDown;
    private final ArrayList<Locale> locales = new ArrayList<>();
    private ResourceBundle resourceBundle;
    private CurrencyConverterApplication app;

    public CurrencyConverterController(){
        locales.add(new Locale("ru"));
        locales.add(Locale.GERMAN);
        locales.add(Locale.ENGLISH);
    }

    public void initialize(){
        fromCurrencyDropdown.getItems().addAll(Currency.EUR, Currency.USD, Currency.KGS);
        fromCurrencyDropdown.setValue(Currency.USD);

        toCurrencyDropdown.getItems().addAll(Currency.EUR, Currency.USD, Currency.KGS);
        toCurrencyDropdown.setValue(Currency.EUR);

        localeDropDown.getItems().addAll(locales);
        localeDropDown.setValue(locales.get(2));
        //setResourceBundle(locales.get(0));
    }

    private void updateText(){
        labelAmount.setText(resourceBundle.getString("label.amount"));
        labelFrom.setText(resourceBundle.getString("label.from"));
        labelTo.setText(resourceBundle.getString("label.to"));
        labelLanguage.setText(resourceBundle.getString("label.language"));
        btnConvert.setText(resourceBundle.getString("button.convert"));
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


            displayResult(result);
        } catch (NumberFormatException ex) {
            showError(resourceBundle.getString("error.wrong"));
        } catch (Exception ex) {
            showError(resourceBundle.getString("error.convert"));
        }
    }

    // Zeigt das Ergebnis in einem Informationsdialog an
    private void displayResult(String result) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedResult = currencyFormatter.format(Double.parseDouble(result));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(resourceBundle.getString("result.title"));
        alert.setHeaderText(null);
        alert.setContentText(formattedResult);
        alert.showAndWait();
    }

    /**
     * Zeigt eine Fehlermeldung in einem Dialog an.
     *
     * @param message Die Fehlermeldung, die angezeigt werden soll.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(resourceBundle.getString("error"));
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void changeLocale() {
        Locale selectedLocale = localeDropDown.getValue();
        app.changeLocale(selectedLocale);
        resourceBundle = ResourceBundle.getBundle("resources", selectedLocale);
        updateText();
    }

    public void setResourceBundle(ResourceBundle bundle){
        resourceBundle = bundle;
    }

    public void setApp(CurrencyConverterApplication app) {
        this.app = app;
    }
}