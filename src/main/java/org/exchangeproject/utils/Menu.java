package org.exchangeproject.utils;

import org.exchangeproject.api.ExchangeRateClient;

import java.util.Scanner;

/**
 * The Menu class provides an interactive command-line interface for users to
 * perform currency conversions and view exchange rates.
 */
public class Menu {
    private final Scanner scanner; // Scanner to read user input.
    private final CurrencyConverter converter; // Converter to handle currency conversion.
    private final ExchangeRateClient rateClient; // Client to fetch exchange rates.

    /**
     * Constructs a Menu with the specified currency converter and rate client.
     * @param converter A CurrencyConverter for performing currency conversions.
     * @param rateClient An ExchangeRateClient to fetch current exchange rates.
     */
    public Menu(CurrencyConverter converter, ExchangeRateClient rateClient) {
        this.scanner = new Scanner(System.in);
        this.converter = converter;
        this.rateClient = rateClient;
    }

    /**
     * Displays the menu repeatedly until the user chooses to exit. This menu
     * allows the user to convert currencies, view current exchange rates, or exit the program.
     */
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
            ScannerUtils.closeScanner(); // Ensures the scanner is closed after the menu loop.
        }
    }

    /**
     * Handles currency conversion functionality by prompting the user for details
     * and displaying the result.
     */
    private void performConversion() {
        System.out.print("Enter amount to convert: ");
        double amount = ScannerUtils.readDouble();
        System.out.print("Enter source currency (e.g., USD, EUR, UYU): ");
        String fromCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Enter target currency (e.g., USD, EUR, UYU): ");
        String toCurrency = scanner.nextLine().toUpperCase();

        double result = converter.convert(amount, fromCurrency, toCurrency);
        System.out.printf("%.2f %s --- %.2f %s%n", amount, fromCurrency, result, toCurrency);
    }

    /**
     * Displays the current exchange rates fetched from the ExchangeRateClient.
     */
    private void showExchangeRates() {
        System.out.println("Current Exchange Rates:");
        System.out.println(rateClient.getRates());
    }
}
