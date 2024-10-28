package org.exchangeproject.utils;

import java.util.Scanner;

public class ScannerUtils {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Gets the scanner instance.
     *
     * @return the scanner instance.
     */
    public static Scanner getScanner() {
        return scanner;
    }

    /**
     * Safely reads an Integer from the console. If the input is not a valid integer,
     * prompts the user to enter a valid number.
     *
     * @return the integer entered by the user.
     */
    public static int readInt() {
        while (true) {
            System.out.print("Please enter a number: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. That is not an integer.");
            }
        }
    }

    /**
     * Safely reads a Double from the console. If the input is not a valid double,
     * prompts the user to enter a valid number.
     *
     * @return the double entered by the user.
     */
    public static double readDouble() {
        while (true) {
            System.out.print("Please enter a decimal number: ");
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. That is not a decimal number.");
            }
        }
    }

    /**
     * Closes the scanner.
     */
    public static void closeScanner() {
        scanner.close();
    }
}
