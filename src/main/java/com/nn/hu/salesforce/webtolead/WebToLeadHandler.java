package com.nn.hu.salesforce.webtolead;

import com.google.gson.Gson;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.nn.hu.salesforce.webtolead.data.WebToLeadData;
import com.nn.hu.salesforce.webtolead.data.WebToLeadResponse;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class WebToLeadHandler<T> {

    private final Logger logger;
    private final HttpRequestMessage<Optional<T>> httpRequest;

    public WebToLeadHandler(ExecutionContext context, HttpRequestMessage<Optional<T>> request) {
        this.logger = context.getLogger();
        this.httpRequest = request;
        logger.info("WebToLead handler initialized...");
    }

    public HttpResponseMessage handleRequest() {
        T wtlData = httpRequest.getBody().orElse(null);
        WebToLeadResponse wtlResponse = null;
        HttpResponseMessage wtlResponseMessage = null;
        WebToLeadData data = null;
        if (wtlData == null) {
            wtlResponse = WebToLeadResponse.getNullErrorResponse();
        } else {
            data = wtlData instanceof WebToLeadData ? (WebToLeadData) wtlData : getDataFromString((String) wtlData);
            logger.info("Function invoked: webtolead");
            try {
                logger.info("Executing webtolead service function...");
                wtlResponse = execute();

            } catch (Exception ex) {
                logger.severe("Unexpected event occured: " + ex.getMessage());
                wtlResponse = WebToLeadResponse.getInternalServerErrorResponse();

            }
        }
        if (wtlResponse != null) {
            wtlResponseMessage = httpRequest.createResponseBuilder(wtlResponse.getStatusCode())
                .body(wtlResponse.getMessageAsJSON())
                .header("Content-Type", "application/json")
                .build();
        }
        return wtlResponseMessage;
    }

    private WebToLeadData getDataFromString(String str) {

        // URLEncodedUtils.parse(str, StandardCharsets.UTF_8); The Apache method is much more complex without any benefit

        String decodedStr = URLDecoder.decode(str, StandardCharsets.UTF_8);
        List<String> parameters = Stream.of(decodedStr.split("&")).map(String::new).toList();
        Map<String, String> keyValues = new HashMap<>();
        for (String kv : parameters) {
            String[] keypair = kv.split("=");
            keyValues.put(keypair[0], keypair[1]);
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(keyValues);
        return gson.fromJson(jsonString, WebToLeadData.class);
    }

    public WebToLeadResponse execute() {
        logger.info("WebToLeadHandler execute...");
        T wtlData = httpRequest.getBody().orElse(null);
        if (wtlData == null) {
            return WebToLeadResponse.getNullErrorResponse();
        }
        WebToLeadData data = wtlData instanceof WebToLeadData ? (WebToLeadData) wtlData : getDataFromString((String) wtlData); // TODO: check if not WebToLeadData and not String
        if (validateWebToLeadData(data)) {
            logger.info("WebToLeadHandler validation passed...");
            logger.info("WebToLeadHandler DO STUFF...");
            return WebToLeadResponse.getOkResponse();
        } else {
            logger.warning("WebToLeadHandler validation failed...");
            return WebToLeadResponse.getRequiredFieldMissingErrorResponse();
        }
    }

    private Boolean validateWebToLeadData(WebToLeadData wtlData) {
        boolean retStatus = true;

        if(wtlData.getFirstName().isBlank()) {
            logger.warning("Empty firstname...");
            retStatus = false;
        }

        if(wtlData.getLastName().isBlank()) {
            logger.warning("Empty lastname...");
            retStatus = false;
        }

        if(wtlData.getEmailAddress().isBlank()) {
            logger.warning("Empty email address...");
            retStatus = false;
        }

        if(wtlData.getZipCode().isBlank()) {
            logger.warning("Empty zip code...");
            retStatus = false;
        }

        return retStatus;
    }
    
}
