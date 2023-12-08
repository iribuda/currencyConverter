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

    /**
     * Die Methode formatiert den angegebenen Betrag gemäß der lokalen Währungseinstellung.
     * Sie verwendet das Ressourcenbündel, um das entsprechende Formatierungsmuster abzurufen
     * und den Betrag zu formatieren.
     *
     * @param key Der Schlüssel, der auf das Formatierungsmuster im Ressourcenbündel zeigt.
     * @param amount Der zu formatierende Betrag.
     * @return Ein formatierter String, der den Betrag gemäß der lokalen Währungseinstellung darstellt.
     */
    public String formatAmount(String key, double amount) {
        ResourceBundle bundle = ResourceBundle.getBundle("resources", Locale.getDefault());
        return MessageFormat.format(bundle.getString(key+getISOcode()), amount);
    }
}
