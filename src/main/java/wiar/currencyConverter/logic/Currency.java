package wiar.currencyConverter.logic;

/**
 * Die Aufzählung für die Währung.
 */
public enum Currency {
    KGS("KGS", "Som"),
    USD("USD", "Dollar"),
    EUR("EUR", "Euro");

    /**
     * Name der Währung auf englische Sprache
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
