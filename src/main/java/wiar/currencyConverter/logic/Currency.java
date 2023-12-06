package wiar.currencyConverter.logic;

/**
 * Die Aufzählung für die Währung.
 */
public enum Currency {
    KGS("KGS", "Сом"),
    USD("USD", "Доллар"),
    EUR("EUR", "Евро");

    /**
     * Name der Währung auf russische Sprache
     */
    private final String name;

    /**
     * Code der Währung nach dem ISO-Standard
     */
    private final String ISOcode;

    Currency(String ISOcode, String name){
        this.ISOcode = ISOcode;
        this.name = name;
    }

    public String getISOcode() {
        return ISOcode;
    }

    public String getName(){
        return name;
    }
}
