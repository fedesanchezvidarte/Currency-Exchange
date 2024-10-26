package org.exchangeproject.utils;

import java.util.Map;

public class CurrencyConverter {
    private final Map<String, Double> rates;

    public CurrencyConverter(Map<String, Double> rates) {
        this.rates = rates;
    }

    /**
     * Converts an amount from one currency to another.
     *
     * @param amount the amount to convert
     * @param fromCurrency the currency code to convert from (e.g., "USD")
     * @param toCurrency the currency code to convert to (e.g., "EUR")
     * @return the converted amount
     */
    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (!rates.containsKey(fromCurrency) || !rates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency code");
        }
        double fromRate = rates.get(fromCurrency);
        double toRate = rates.get(toCurrency);
        return (amount / fromRate) * toRate;
    }
}
