package com.nn.hu.salesforce.webtolead;

import java.util.Optional;
import java.util.logging.Logger;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.nn.hu.salesforce.webtolead.data.WebToLeadData;
import com.nn.hu.salesforce.webtolead.data.WebToLeadResponse;

public class WebToLeadHandler {

    private Logger wtlLogger;
    // private ExecutionContext wtfContext;
    private HttpRequestMessage<Optional<WebToLeadData>> httpRequest;

    public WebToLeadHandler(ExecutionContext context, HttpRequestMessage<Optional<WebToLeadData>> request) {
        // this.wtfContext = context;
        this.wtlLogger = context.getLogger();
        this.httpRequest = request;

        wtlLogger.info("WebToLead handler initialized...");
    }

    public WebToLeadResponse execute() {
        wtlLogger.info("WebToLeadHandler execute...");

        WebToLeadData wtlData = this.httpRequest.getBody().orElse(null);

        if(wtlData == null) {
            return WebToLeadResponse.getNullErrorResponse();
        }

        if(validateData(wtlData)) {
            // DO STUFF
            wtlLogger.info("WebToLeadHandler validation passed...");
            wtlLogger.info("WebToLeadHandler DO STUFF...");
            return WebToLeadResponse.getOkResponse();
        } else {
            wtlLogger.warning("WebToLeadHandler validation failed...");
            return WebToLeadResponse.getRequiredFieldMissingErrorResponse();
        }
    }

    private Boolean validateData(WebToLeadData wtlData) {
        Boolean retStatus = true;

        if(wtlData.getFirstName().isBlank()) {
            this.wtlLogger.warning("Empty firstname...");
            retStatus = false;
        }

        if(wtlData.getLastName().isBlank()) {
            this.wtlLogger.warning("Empty lastname...");
            retStatus = false;
        }

        if(wtlData.getEmailAddress().isBlank()) {
            this.wtlLogger.warning("Empty email address...");
            retStatus = false;
        }

        if(wtlData.getZipCode().isBlank()) {
            this.wtlLogger.warning("Empty zip code...");
            retStatus = false;
        }

        return retStatus;
    }
    
}
