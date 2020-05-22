package requests;

import lombok.extern.log4j.Log4j;

import static utils.Constants.POST_PARAMS;
import static utils.Constants.URL_POST;
import static utils.Constants.USER_AGENT;
import static utils.HttpUtils.printRequestIfWorks;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

@Log4j
public class PostRequest {
    public static void sendPost(String hostname, int port) {
        try {
            URL obj = new URL(URL_POST);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);

            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);

            con.setDoOutput(true);
            writeStream(con);

            int responseCode = con.getResponseCode();
            log.info("POST Response Code: [" + responseCode + "]\n");

            printRequestIfWorks(con, responseCode);
        } catch (IOException e) {
            log.error("Cannot send the POST request: " + e.getMessage() + "\n");
        }
    }

    private static void writeStream(HttpURLConnection con) {
        try {
            OutputStream os = con.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            log.error("Cannot write: " + e.getMessage() + "\n");
        }
    }
}

