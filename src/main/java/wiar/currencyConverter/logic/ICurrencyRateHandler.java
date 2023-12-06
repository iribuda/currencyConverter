package wiar.currencyConverter.logic;

import java.util.Optional;

/**
 *  Schnittstelle für die Behandlung von Währungskursen
 */
public interface ICurrencyRateHandler {
    Optional<CurrencyRate> get(Currency from, Currency to);

}
