package network;

import java.io.*;

import org.json.JSONObject;
import java.net.URL;

// from deliverable 10 instructions
public class Network {
    private static String string;

    // MODIFIES: this
    // EFFECTS: reads from online weather API and returns parts as a string
    public static String printWebPage() {
        BufferedReader br = null;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID="
                    + "3ca388d3cefa876a0952d3886830e2d9");
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            string = "";
            while ((line = br.readLine()) != null) {
                string = string + line + System.lineSeparator();
            }
            string = "Weather in Vancouver:\n" + parseString(string);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            finishReader(br);
            return string;
        }
    }

    // EFFECTS: closes BufferedReader
    private static void finishReader(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // REQUIRES: given string is in the format of a JSON file
    // EFFECTS: parses the given string for temperatures and humidity, and returns the string to be printed for user
    private static String parseString(String sb) {
        JSONObject obj = new JSONObject(sb);
        String temperature = "temperature: " + (obj.getJSONObject("main").getInt("temp") - 273) + "°C";
        String humidity = "humidity: " + obj.getJSONObject("main").getInt("humidity") + "%";
        String minTemp = "low of: " + (obj.getJSONObject("main").getInt("temp_min") - 273) + "°C";
        String maxTemp = "high of: " + (obj.getJSONObject("main").getInt("temp_max") - 273) + "°C";
        return "<html><BR>" + temperature + "<BR>" + humidity + "<BR>" + minTemp + "<BR>" + maxTemp + "</html>";
    }
}
