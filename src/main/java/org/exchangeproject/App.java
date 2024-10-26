package org.exchangeproject;

import org.exchangeproject.api.ExchangeRateClient;
import org.exchangeproject.utils.CurrencyConverter;
import org.exchangeproject.utils.Menu;
import org.exchangeproject.model.ExchangeRates;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        ExchangeRateClient rateClient = new ExchangeRateClient();
        ExchangeRates rates = new ExchangeRates(rateClient.getRates()); // Asume that rateClient.getRates() returns a Map<String, Double>
        CurrencyConverter converter = new CurrencyConverter((Map<String, Double>) rates);

        Menu menu = new Menu(converter, rateClient);
        menu.displayMenu();
    }
}
