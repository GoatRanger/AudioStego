package com.google.api.client.testing.http;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Beta
public class MockHttpTransport extends HttpTransport {
    private MockLowLevelHttpRequest lowLevelHttpRequest;
    private MockLowLevelHttpResponse lowLevelHttpResponse;
    private Set<String> supportedMethods;

    protected MockHttpTransport(Builder builder) {
        this.supportedMethods = builder.supportedMethods;
        this.lowLevelHttpRequest = builder.lowLevelHttpRequest;
        this.lowLevelHttpResponse = builder.lowLevelHttpResponse;
    }

    public boolean supportsMethod(String str) throws IOException {
        return this.supportedMethods == null || this.supportedMethods.contains(str);
    }

    public LowLevelHttpRequest buildRequest(String str, String str2) throws IOException {
        Preconditions.checkArgument(supportsMethod(str), "HTTP method %s not supported", new Object[]{str});
        if (this.lowLevelHttpRequest != null) {
            return this.lowLevelHttpRequest;
        }
        LowLevelHttpRequest mockLowLevelHttpRequest = new MockLowLevelHttpRequest(str2);
        if (this.lowLevelHttpResponse == null) {
            return mockLowLevelHttpRequest;
        }
        mockLowLevelHttpRequest.setResponse(this.lowLevelHttpResponse);
        return mockLowLevelHttpRequest;
    }

    public final Set<String> getSupportedMethods() {
        return this.supportedMethods == null ? null : Collections.unmodifiableSet(this.supportedMethods);
    }

    public final MockLowLevelHttpRequest getLowLevelHttpRequest() {
        return this.lowLevelHttpRequest;
    }

    @Deprecated
    public static Builder builder() {
        return new Builder();
    }
}
