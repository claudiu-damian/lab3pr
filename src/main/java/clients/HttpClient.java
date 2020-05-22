package clients;

import lombok.extern.log4j.Log4j;
import requests.GetRequest;
import requests.HeadRequest;
import requests.OptionRequest;
import requests.PostRequest;

import static utils.Constants.PROXY_HOSTNAME;
import static utils.Constants.PROXY_PORT;

@Log4j
public class HttpClient {
    public static void main(String[] args) {
        log.info("GET request: \n");
        GetRequest.sendGet(PROXY_HOSTNAME, PROXY_PORT);
        log.info(System.lineSeparator());

        log.info("POST request:\n");
        PostRequest.sendPost(PROXY_HOSTNAME, PROXY_PORT);
        log.info(System.lineSeparator());

        log.info("HEAD request:\n");
        HeadRequest.sendHead(PROXY_HOSTNAME, PROXY_PORT);
        log.info(System.lineSeparator());

        log.info("OPTIONS request:\n");
        OptionRequest.sendOptions(PROXY_HOSTNAME, PROXY_PORT);
    }
}

