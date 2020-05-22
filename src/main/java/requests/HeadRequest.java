package requests;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static utils.Constants.URL_GET;

@Log4j
public class HeadRequest {
    public static void sendHead(String hostname, int port) {
        System.setProperty("http.proxyHost", hostname);
        System.setProperty("http.proxyPort", String.valueOf(port));

        HttpClient client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create(URL_GET)).method("HEAD",
                HttpRequest.BodyPublishers.noBody()).build();

        HttpResponse<Void> response = getHttpResponse(client, request);

        HttpHeaders headers = response.headers();
        log.info("Getting the request\n");
        headers.map().forEach((key, values) -> {
            System.out.printf("%s: %s%n", key, values);
        });
    }

    private static HttpResponse<Void> getHttpResponse(HttpClient client, HttpRequest request) {
        HttpResponse<Void> httpResponse = null;
        try {
            httpResponse = client.send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            log.error("Cannot get the response: " + e.getMessage() + "\n");
        }
        return httpResponse;
    }
}

