package wiar.currencyConverter;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import wiar.currencyConverter.logic.Currency;
import wiar.currencyConverter.logic.CurrencyConverter;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Steuert die Währungsumrechnung im JavaFX-Benutzeroberflächenkontext.
 */
public class CurrencyConverterController {
    @FXML
    private Label labelAmount;
    @FXML
    private Label labelFrom;
    @FXML
    private Label labelTo;
    @FXML
    private Button btnConvert;
    @FXML
    private Label labelLanguage;
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
    private ResourceBundle resourceBundle;
    private CurrencyConverterApplication app;
    public CurrencyConverterController(){
    }

    public void initialize(){
        fromCurrencyDropdown.getItems().addAll(Currency.EUR, Currency.USD, Currency.KGS);
        fromCurrencyDropdown.setValue(Currency.USD);

        toCurrencyDropdown.getItems().addAll(Currency.EUR, Currency.USD, Currency.KGS);
        toCurrencyDropdown.setValue(Currency.EUR);

        ArrayList<Locale> locales = new ArrayList<>();
        locales.add(new Locale("ru"));
        locales.add(Locale.GERMAN);
        locales.add(Locale.ENGLISH);

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
            double amountResult = currencyConverter.convert(amount, fromCurrency, toCurrency);
            NumberFormat numberFormatter = NumberFormat.getNumberInstance(resourceBundle.getLocale());
            String fromCurrencyText = MessageFormat.format(fromCurrency.formatAmount("currency.result.", amount), numberFormatter.format(amount));
            String toCurrencyText = MessageFormat.format(toCurrency.formatAmount("currency.", amountResult), numberFormatter.format(amountResult));
            String formattedText = fromCurrencyText + toCurrencyText;
            displayResult(formattedText);
        } catch (NumberFormatException ex) {
            showError(resourceBundle.getString("error.wrong"));
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            showError(resourceBundle.getString("error.convert"));
        }
    }

    // Zeigt das Ergebnis in einem Informationsdialog an
    private void displayResult(String result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(resourceBundle.getString("title.result"));
        alert.setHeaderText(null);
        alert.setContentText(result);
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
        Locale.setDefault(selectedLocale);
        resourceBundle = ResourceBundle.getBundle("resources", Locale.getDefault());
        app.changeLocale();
        updateText();
    }

    public void setResourceBundle(ResourceBundle bundle){
        resourceBundle = bundle;
    }

    public void setApp(CurrencyConverterApplication app) {
        this.app = app;
    }
}