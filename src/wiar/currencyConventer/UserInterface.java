package wiar.currencyConventer;

import java.util.Date;
import java.util.Scanner;

public class UserInterface {

    private final CurrencyConverter currencyConverter;
    private final Parser parser;

    public UserInterface(){
        parser = new Parser();
        currencyConverter = new CurrencyConverter();
    }

    public void start(){
        printWelcome();
        double result = getResultFromInputs();
        System.out.println("Результат: " + result);
    }

    private void printWelcome(){
        System.out.println("Вас приветствует конвертер валюты.");
        System.out.println("Данные актуальны на момент: " + new Date());
    }

    private double getResultFromInputs(){
        System.out.print("Введите Вашу валюту: ");
        Currency from = parser.getCurrencyFromUser();
        System.out.print("Введите валюту, в которую нужно перевести: ");
        Currency to = parser.getCurrencyFromUser();
        System.out.print("Введите Вашу сумму: ");
        double amount = parser.getAmountFromUser();
        return currencyConverter.convert(amount, from, to);
    }
}
