package org.exchangeproject.utils;

import org.exchangeproject.api.ExchangeRateClient;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final CurrencyConverter converter;
    private final ExchangeRateClient rateClient;

    public Menu(CurrencyConverter converter, ExchangeRateClient rateClient) {
        this.scanner = new Scanner(System.in);
        this.converter = converter;
        this.rateClient = rateClient;
    }

    public void displayMenu() {
        try {
            int option;
            do {
                System.out.println("\n--- Currency Exchange Menu ---");
                System.out.println("1. Convert Currency");
                System.out.println("2. Show Current Exchange Rates");
                System.out.println("3. Exit");
                System.out.print("Select an option: ");
                option = ScannerUtils.readInt();

                switch (option) {
                    case 1:
                        performConversion();
                        break;
                    case 2:
                        showExchangeRates();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } while (option != 3);
        } finally {
            ScannerUtils.closeScanner();
        }
    }

    private void performConversion() {
        System.out.print("Enter amount to convert: ");
        double amount = ScannerUtils.readDouble();
        System.out.print("Enter source currency (e.g., USD, EUR, UYU): ");
        String fromCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Enter target currency (e.g., USD, EUR, UYU): ");
        String toCurrency = scanner.nextLine().toUpperCase();

        double result = converter.convert(amount, fromCurrency, toCurrency);
        System.out.printf("%.2f %s is equivalent to %.2f %s%n", amount, fromCurrency, result, toCurrency);
    }

    private void showExchangeRates() {
        System.out.println("Current Exchange Rates:");
        System.out.println(rateClient.getRates());
    }
}
