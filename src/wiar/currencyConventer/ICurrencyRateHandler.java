package wiar.currencyConventer;

import java.util.Optional;

public interface ICurrencyRateHandler {

    Optional<CurrencyRate> get(Currency from, Currency to);

}
