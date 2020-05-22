package utils;

import lombok.extern.log4j.Log4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Log4j
public class HttpUtils {
    static public void printResponse(HttpURLConnection con) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            log.info("Response: " + response + "\n");
        } catch (IOException e) {
            log.error("Cannot print the response: " + e.getMessage() + "\n");
        }
    }

    public static void printRequestIfWorks(HttpURLConnection con, int responseCode) {
        if (responseCode == HttpURLConnection.HTTP_OK) {
            printResponse(con);
        } else {
            log.error("Request did not worked");
        }
    }
}

