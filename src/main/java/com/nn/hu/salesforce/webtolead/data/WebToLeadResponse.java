package com.nn.hu.salesforce.webtolead.data;

import java.util.Optional;

import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebToLeadResponse {
    
    private String responseMessage;
    private Boolean hasError;
    private Boolean hasValidationError;

    public WebToLeadResponse() {
        setResponseMessage("");
        setHasError(false);
        setHasValidationError(false);
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Boolean isHasError() {
        return this.hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public Boolean isHasValidationError() {
        return this.hasValidationError;
    }

    public void setHasValidationError(Boolean hasValidationError) {
        this.hasValidationError = hasValidationError;
    }

    public HttpStatus getStatusCode() {
        HttpStatus statusCode;
        if(this.hasError) {
            if(this.hasValidationError) {
                statusCode = HttpStatus.BAD_REQUEST;
            } else {
                statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            // TODO: Future should be CREATED;
            statusCode = HttpStatus.OK;
        }
        return statusCode;
    }


    public static WebToLeadResponse getNullErrorResponse() {
        WebToLeadResponse wtlresp = new WebToLeadResponse();
        wtlresp.setHasError(true);
        wtlresp.setHasValidationError(true);
        wtlresp.setResponseMessage("No data present");
        return wtlresp;
    }

    public static WebToLeadResponse getRequiredFieldMissingErrorResponse() {
        WebToLeadResponse wtlresp = new WebToLeadResponse();
        wtlresp.setHasError(true);
        wtlresp.setHasValidationError(true);
        wtlresp.setResponseMessage("Required field(s) missing.");
        return wtlresp;
    }

    public static WebToLeadResponse getOkResponse() {
        WebToLeadResponse wtlresp = new WebToLeadResponse();
        wtlresp.setResponseMessage("Lead processed. ");
        return wtlresp;
    }

    public static WebToLeadResponse getInternalServerErrorResponse() {
        WebToLeadResponse wtlresp = new WebToLeadResponse();
        wtlresp.setHasError(true);
        wtlresp.setResponseMessage("Internal Server Error");
        return wtlresp;
    }

    public String getMessageAsJSON() {
        return "{\"Message\": \"" + responseMessage + "\"}";
    }
}
