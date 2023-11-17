package com.nn.hu.salesforce.webtolead;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.nn.hu.salesforce.webtolead.data.WebToLeadData;

import java.util.Optional;

public class WebToLeadService {

    @FunctionName("WebToLeadServiceJson")
    public HttpResponseMessage runJson(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.POST},
                route = "v1/webtolead/json",
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<WebToLeadData>> request,
            final ExecutionContext context) {

            WebToLeadHandler<WebToLeadData> wtlHandler = new WebToLeadHandler<>(context, request);
            return wtlHandler.handleRequest();
    }

    @FunctionName("WebToLeadService")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    route = "v1/webtolead",
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        WebToLeadHandler<String> wtlHandler = new WebToLeadHandler<>(context, request);
        return wtlHandler.handleRequest();
    }


}
