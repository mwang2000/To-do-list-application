package network;

import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;

import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;

public class Network {
    public static void printWebPage() throws IOException {
        BufferedReader br = null;
        try {
//            String apikey = "?api_key=13d26819247e9fe85bc28e5de6931138da552730";
//            String calendarific = "https://calendarific.com/api/v2";
//            String theURL = calendarific + apikey;
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID="
                    + "3ca388d3cefa876a0952d3886830e2d9");
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            String string = "";
            while ((line = br.readLine()) != null) {
                string = string + line + System.lineSeparator();
            }
            System.out.println("Weather in Vancouver:\n" + User.parseString(string));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}
