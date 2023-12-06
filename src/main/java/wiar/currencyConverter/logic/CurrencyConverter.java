package wiar.currencyConverter.logic;

import java.util.Optional;

/**
 * Eine Klasse für die Umrechnung von Beträgen zwischen verschiedenen Währungen.
 */
public class CurrencyConverter {
    /**
     * Der Handler für die Behandlung von Währungskursen
     */
    private final ICurrencyRateHandler currencyRateHandler;

    public CurrencyConverter(){
        currencyRateHandler = new CurrencyRateHandler();
    }

    /**
     * Konvertiert einen Betrag von einer Ausgangswährung in eine Zielwährung.
     *
     * @param amount Der zu konvertierende Betrag.
     * @param from Die Ausgangswährung.
     * @param to Die Zielwährung.
     * @return Der konvertierte Betrag in der Zielwährung oder -1.0, wenn der Kurs nicht gefunden wurde.
     */
    public double convert(double amount, Currency from, Currency to){
        Optional<CurrencyRate> currencyRate = currencyRateHandler.get(from, to);

        // Falls ein Währungskurs vorhanden ist, wird der Betrag umgerechnet, andernfalls wird -1.0 zurückgegeben
        return currencyRate.map(rate -> amount * rate.getRate()).orElse(-1.0);
    }
}
