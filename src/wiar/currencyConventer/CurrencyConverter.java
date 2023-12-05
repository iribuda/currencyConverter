package wiar.currencyConventer;

import java.util.Optional;

public class CurrencyConverter {
    private final ICurrencyRateHandler currencyRateHandler;

    public CurrencyConverter(){
        currencyRateHandler = new CurrencyRateHandler();
    }

    public double convert(double amount, Currency from, Currency to){
        Optional<CurrencyRate> currencyRate = currencyRateHandler.get(from, to);
        return currencyRate.map(rate -> amount * rate.getRate()).orElse(-1.0);
    }
}
