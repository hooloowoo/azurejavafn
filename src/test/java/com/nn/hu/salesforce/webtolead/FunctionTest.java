package com.nn.hu.salesforce.webtolead;

import com.microsoft.azure.functions.*;
import com.nn.hu.salesforce.webtolead.data.WebToLeadData;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.*;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


/**
 * Unit test for Function class.
 */
public class FunctionTest {
    /**
     * Unit test for HttpTriggerJava method.
     */
    @Test
    public void testHttpTriggerJava() throws Exception {
        // Setup
        @SuppressWarnings("unchecked")
        final HttpRequestMessage<Optional<WebToLeadData>> req = mock(HttpRequestMessage.class);

        // final Map<String, String> queryParams = new HashMap<>();
        // queryParams.put("name", "Azure");
        // doReturn(queryParams).when(req).getQueryParameters();

        WebToLeadData wtlData = new WebToLeadData();
        wtlData.firstName("TEST").lastName("ELEK").emailAddress("example@example.com").zipCode("1075");
        final Optional<WebToLeadData> queryBody = Optional.of(wtlData);
        doReturn(queryBody).when(req).getBody();

        doAnswer(new Answer<HttpResponseMessage.Builder>() {
            @Override
            public HttpResponseMessage.Builder answer(InvocationOnMock invocation) {
                HttpStatus status = (HttpStatus) invocation.getArguments()[0];
                return new HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(status);
            }
        }).when(req).createResponseBuilder(any(HttpStatus.class));

        final ExecutionContext context = mock(ExecutionContext.class);
        doReturn(Logger.getGlobal()).when(context).getLogger();

        // Invoke
        final HttpResponseMessage ret = new WebToLeadService().runJson(req, context);

        // Verify
        assertEquals(HttpStatus.OK, ret.getStatus());
    }
}
