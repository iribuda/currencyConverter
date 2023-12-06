package wiar.currencyConverter.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CurrencyRateHandler implements ICurrencyRateHandler{

    private final Map<String, CurrencyRate> currencyRateMap = new HashMap<>();

    public CurrencyRateHandler(){
        setCurrencyRateMap();
    }

    @Override
    public Optional<CurrencyRate> get(Currency from, Currency to) {
        return Optional.ofNullable(currencyRateMap.get(from.getISOcode() + "-" + to.getISOcode()));
    }

    private void add(Currency from, Currency to, double rate){
        CurrencyRate currencyRate = new CurrencyRate(from, to, rate);
        currencyRateMap.put(from.getISOcode() + "-" + to.getISOcode() , currencyRate);
    }

    private void setCurrencyRateMap(){
        add(Currency.KGS, Currency.USD, 0.01);
        add(Currency.USD, Currency.KGS, 89.31);
        add(Currency.KGS, Currency.EUR, 0.01);
        add(Currency.EUR, Currency.KGS, 96.37);
        add(Currency.USD, Currency.EUR, 0.92);
        add(Currency.EUR, Currency.USD, 1.07);
    }
}
