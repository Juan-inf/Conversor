package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.ExchangeRateData;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyService {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/a33297308b66c29e0de8872f/latest/USD";

    /**
     * Obtiene las tasas de cambio desde la API ExchangeRate-API.
     *
     * @return Un objeto ExchangeRateData con las tasas de cambio.
     */
    public ExchangeRateData fetchExchangeRates() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) { // Código HTTP OK
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                Gson gson = new Gson();
                ExchangeRateData data = gson.fromJson(reader, ExchangeRateData.class);
                reader.close();
                return data;
            } else {
                System.out.println("Error al conectar con la API. Código de respuesta: " + connection.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }
        return null;
    }
}
