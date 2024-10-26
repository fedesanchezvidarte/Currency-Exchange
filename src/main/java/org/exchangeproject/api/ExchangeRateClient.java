package org.exchangeproject.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeRateClient {
    private static final Logger logger = Logger.getLogger(ExchangeRateClient.class.getName());
    private static final String API_KEY = System.getenv("OPEN_EXCHANGE_RATES_API_KEY");
    private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=" + API_KEY;

    private final OkHttpClient client;
    private final Gson gson;

    public ExchangeRateClient() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public Map<String, Double> getRates() {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                logger.log(Level.SEVERE, "Unexpected code: {0}", response);
                return null; // Early return on failure
            }

            if (response.body() == null) {
                logger.log(Level.SEVERE, "Response body was null");
                return null; // Early return on failure
            }

            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            Map<String, Object> responseBody = gson.fromJson(response.body().string(), type);
            return safelyCastToMapStringDouble(responseBody.get("rates"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to retrieve exchange rates", e);
            return null; // Return null to indicate failure
        }
    }

    private Map<String, Double> safelyCastToMapStringDouble(Object object) {
        if (object instanceof Map<?, ?> rawMap) {
            Map<String, Double> map = new HashMap<>();
            for (Map.Entry<?, ?> entry : rawMap.entrySet()) {
                if (entry.getKey() instanceof String && entry.getValue() instanceof Number) {
                    map.put((String) entry.getKey(), ((Number) entry.getValue()).doubleValue());
                }
            }
            return map;
        }
        return null;
    }
}
