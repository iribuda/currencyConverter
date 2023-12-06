package wiar.currencyConverter.logic;
/**
 * Repräsentiert einen Währungskurs zwischen zwei Währungen.
 */
public class CurrencyRate {

    /**
     * Die Ausgangswährung
     */
    private final Currency from;
    /**
     * Die Zielwährung
     */
    private final Currency to;

    /**
     * Der Währungskurs
     */
    private final double rate;

    public CurrencyRate(Currency from, Currency to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public double getRate(){
        return rate;
    }
}
