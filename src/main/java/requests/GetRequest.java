package requests;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import static utils.Constants.URL_GET;
import static utils.Constants.USER_AGENT;
import static utils.HttpUtils.printRequestIfWorks;

@Log4j
public class GetRequest {
    public static void sendGet(String hostname, int port) {
        try {
            URL obj = new URL(URL_GET);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            log.info("GET Response Code: [" + responseCode + "]\n");

            printRequestIfWorks(con, responseCode);
        } catch (IOException e) {
            log.error("Cannot perform the GET request: " + e.getMessage() + "\n");
        }
    }


}

