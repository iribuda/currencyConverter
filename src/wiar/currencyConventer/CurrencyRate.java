package wiar.currencyConventer;

public class CurrencyRate {

    private final Currency from;
    private final Currency to;
    private final double rate;

    public CurrencyRate(Currency from, Currency to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public String getFromCurrencyCode(){
        return from.getISOcode();
    }

    public String getToCurrencyCode(){
        return to.getISOcode();
    }

    public double getRate(){
        return rate;
    }
}
