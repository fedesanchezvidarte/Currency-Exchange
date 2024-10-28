package org.exchangeproject;

import org.exchangeproject.api.ExchangeRateClient;
import org.exchangeproject.model.ExchangeRates;
import org.exchangeproject.utils.CurrencyConverter;
import org.exchangeproject.utils.Menu;

public class App {
    public static void main(String[] args) {
        ExchangeRateClient rateClient = new ExchangeRateClient();
        ExchangeRates rates = new ExchangeRates(rateClient.getRates());
        CurrencyConverter converter = new CurrencyConverter(rates.getAllRates());

        Menu menu = new Menu(converter, rateClient);
        menu.displayMenu();
    }
}
