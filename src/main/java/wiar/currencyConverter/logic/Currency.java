package wiar.currencyConverter.logic;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Die Aufzählung für die Währung.
 */
public enum Currency {
    KGS("KGS"),
    USD("USD"),
    EUR("EUR");

    /**
     * Code der Währung nach dem ISO-Standard
     */
    private final String ISOcode;

    Currency(String ISOcode){
        this.ISOcode = ISOcode;
    }

    public String getISOcode() {
        return ISOcode;
    }


    public String formatAmount(String key, double amount) {
        ResourceBundle bundle = ResourceBundle.getBundle("resources", Locale.getDefault());
        return MessageFormat.format(bundle.getString(key+getISOcode()), amount);
    }
}
