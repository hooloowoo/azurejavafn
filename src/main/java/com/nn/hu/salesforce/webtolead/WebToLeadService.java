package com.nn.hu.salesforce.webtolead;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.nn.hu.salesforce.webtolead.data.WebToLeadData;
import com.nn.hu.salesforce.webtolead.data.WebToLeadResponse;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * Azure Functions with HTTP Trigger.
 */
public class WebToLeadService {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("WebToLeadService")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.POST},
                route = "v1/webtolead",
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<WebToLeadData>> request,
            final ExecutionContext context) {
                
                Logger serviceLogger = context.getLogger();
                
                serviceLogger.info("Function invoked: webtolead");
                
                HttpResponseMessage wtlResponseMessage = null;
                WebToLeadResponse wtlResponse = null;
                try {
                    serviceLogger.info("Executing webtolead service function...");
                    WebToLeadHandler wtlHandler = new WebToLeadHandler(context, request);
                    wtlResponse = wtlHandler.execute();
                    
                } catch(Exception ex) {
                    serviceLogger.severe("Unexpected event occured: " + ex.getMessage());
                    wtlResponse = WebToLeadResponse.getInternalServerErrorResponse();
        
                } finally {
                    wtlResponseMessage = wtlResponse.getHttpResponseForRequest(request);
        
                }
        
                return wtlResponseMessage;
    }
}
