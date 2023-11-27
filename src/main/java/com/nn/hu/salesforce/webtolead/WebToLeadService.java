package com.nn.hu.salesforce.webtolead;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.nn.hu.salesforce.webtolead.data.WebToLeadData;

import java.util.Optional;

public class WebToLeadService {


    @FunctionName("FromRedirect")
    public HttpResponseMessage redirect(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "v1/webtolead")
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        return request.createResponseBuilder(HttpStatus.FOUND).header("Location","https://webtoleadnn.z13.web.core.windows.net/").build();

    }


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
