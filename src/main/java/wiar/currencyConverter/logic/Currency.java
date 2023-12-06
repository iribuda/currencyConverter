package wiar.currencyConverter.logic;

public enum Currency {

    KGS("KGS", "Som"),
    USD("USD", "Dollar"),
    EUR("EUR", "Euro");

    private final String name;
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
