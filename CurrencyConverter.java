import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;

public class CurrencyConverter {
    private static final String API_KEY = "";//Your API_KEY
    private static final String API_URL = "https://api.currency.com/exchangerates/latest", private static final String API_URL = "https://v6.exchangerate-api.com/v6/"+API_KEY+"/latest/";
    public static void main(String[] args) throws ParseException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the base currency (e.g., USD): ");
            String baseCurrency = reader.readLine();
            System.out.print("Enter the target currency (e.g., EUR): ");
            String targetCurrency = reader.readLine();
            System.out.print("Enter the amount to convert: ");
            double amount = Double.parseDouble(reader.readLine())
            double conversionResult = convertCurrency(baseCurrency, targetCurrency, amount);
            System.out.println(amount + + baseCurrency" is equal to " + conversionResult + " " + targetCurrency);
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double convertCurrency(String baseCurrency, String targetCurrency, double amount) throws IOException, ParseException {
        String url = API_URL+baseCurrency;
        String url API_URL;
        URL apiURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            JSONParser parser = new JSONParser();
            JSONObject exchangeRates = (JSONObject) parser.parse(response.toString());
            if (exchangeRates.containsKey("conversion_rates")) {
                JSONObject rates = (JSONObject) exchangeRates.get("conversion_rates");
                if (rates.containsKey(targetCurrency)) {
                    double exchangeRate = (double) rates.get(targetCurrency);
                    return amount*exchangeRate;
                } else {
                    System.out.println("Target currency not found in rates.");
                }
            }else{
                System.out.println("Rates not found in the API response.");
            }else{ 
                System.out.println("Failed to fetch data from the API. Response code: " + responseCode);
            }
            return 0.0; 
        }
    }
}
 
