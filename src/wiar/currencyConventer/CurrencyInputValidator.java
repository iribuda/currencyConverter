package wiar.currencyConventer;

public class CurrencyInputValidator {
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
