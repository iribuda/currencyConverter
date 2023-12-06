package wiar.currencyConverter.logic;

import java.util.Scanner;

public class Parser {
    private final Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    public Currency getCurrencyFromUser(){
        while(true){
            try{
                String currencyCode = scanner.nextLine().toUpperCase();
                return Currency.valueOf(currencyCode);
            }catch (IllegalArgumentException e){
                System.out.println("Неверный код валюты! Попробуйте еще раз.");
            }
        }
    }

    public double getAmountFromUser(){
        while(true){
            try{
                double amount = Double.parseDouble(scanner.nextLine());
                if (CurrencyInputValidator.isValidAmount(amount)){
                    return amount;
                }
                else{
                    System.out.println("Вы ввели значение, которое меньше или равно 0! Попробуйте еще раз");
                }
            }catch (NumberFormatException e){
                System.out.println("Введите число!");
            }
        }
    }
}
