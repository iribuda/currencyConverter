package wiar.currencyConverter.logic;

import java.util.Optional;

public interface ICurrencyRateHandler {

    Optional<CurrencyRate> get(Currency from, Currency to);

}
