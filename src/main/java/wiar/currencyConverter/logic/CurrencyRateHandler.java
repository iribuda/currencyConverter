package wiar.currencyConverter.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementierung des Währungskurs-Handlers, der die ICurrencyRateHandler-Schnittstelle umsetzt
 */
public class CurrencyRateHandler implements ICurrencyRateHandler{
    /**
     * Eine Map zur Speicherung von Währungskursen, wobei der Schlüssel im Format "Währung-Währung" ist
     */
    private final Map<String, CurrencyRate> currencyRateMap = new HashMap<>();

    public CurrencyRateHandler(){
        setCurrencyRateMap();
    }

    /**
     * Implementierung der Methode aus der Schnittstelle zur Abrufung eines Währungskurses
     * @param from Die Ausgangswährung
     * @param to Die Zielwährung
     * @return Ein Optional, das den Währungskurs zwischen from und to enthält,
     *         oder Optional.empty(), wenn kein Währungskurs gefunden wurde.
     */
    @Override
    public Optional<CurrencyRate> get(Currency from, Currency to) {
        return Optional.ofNullable(currencyRateMap.get(from.getISOcode() + "-" + to.getISOcode()));
    }

    /**
     * Private Hilfsmethode zum Hinzufügen eines Währungskurses zur Map
     *
     * @param from Die Ausgangswährung
     * @param to Die Zielwährung
     * @param rate Der Währungskurs
     */
    private void add(Currency from, Currency to, double rate){
        CurrencyRate currencyRate = new CurrencyRate(from, to, rate);
        currencyRateMap.put(from.getISOcode() + "-" + to.getISOcode() , currencyRate);
    }

    /**
     * Private Hilfsmethode zum Initialisieren der Währungskurs-Map mit festen Werten
     */
    private void setCurrencyRateMap(){
        add(Currency.KGS, Currency.USD, 0.01);
        add(Currency.USD, Currency.KGS, 89.31);
        add(Currency.KGS, Currency.EUR, 0.01);
        add(Currency.EUR, Currency.KGS, 96.37);
        add(Currency.USD, Currency.EUR, 0.92);
        add(Currency.EUR, Currency.USD, 1.07);
    }
}
