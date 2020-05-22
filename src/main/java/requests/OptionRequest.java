package requests;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import static utils.Constants.URL_POST;

@Log4j
public class OptionRequest {
    public static void sendOptions(String hostname, int port) {
        try {
            URL obj = new URL(URL_POST);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
            con.setRequestMethod("OPTIONS");
            System.out.println(con.getHeaderField("Allow"));
        } catch (IOException e) {
            log.error("Failed to send Options: " + e.getMessage() + "\n");
        }
    }
}

