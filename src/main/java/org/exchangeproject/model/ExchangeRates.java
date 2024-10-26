package org.exchangeproject.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class acts as a model to store exchange rates.
 * It includes methods to update the rates from an API and
 * provide them to other parts of the application.
 */
public class ExchangeRates {
    private Map<String, Double> rates;

    public ExchangeRates(Map<String, Double> initialRates) {
        this.rates = new HashMap<>(initialRates); // Correctly initializes the rates map with the passed values.
    }

    /**
     * Updates the stored exchange rates with new data.
     * @param newRates A map of exchange rates where the key is the currency code and the value is the rate.
     */
    public void updateRates(Map<String, Double> newRates) {
        rates.clear();
        rates.putAll(newRates);
    }

    /**
     * Retrieves the exchange rate for a specific currency.
     * @param currencyCode The currency code for which to obtain the exchange rate.
     * @return The exchange rate for the specified currency, or null if it is not available.
     */
    public Double getRate(String currencyCode) {
        return rates.get(currencyCode);
    }

    /**
     * Returns an immutable map of all exchange rates.
     * @return An immutable map of all exchange rates.
     */
    public Map<String, Double> getAllRates() {
        return Collections.unmodifiableMap(rates);
    }
}
