package com.google.api.client.testing.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.util.Set;

@Beta
public class MockHttpTransport$Builder {
    MockLowLevelHttpRequest lowLevelHttpRequest;
    MockLowLevelHttpResponse lowLevelHttpResponse;
    Set<String> supportedMethods;

    public MockHttpTransport build() {
        return new MockHttpTransport(this);
    }

    public final Set<String> getSupportedMethods() {
        return this.supportedMethods;
    }

    public final MockHttpTransport$Builder setSupportedMethods(Set<String> set) {
        this.supportedMethods = set;
        return this;
    }

    public final MockHttpTransport$Builder setLowLevelHttpRequest(MockLowLevelHttpRequest mockLowLevelHttpRequest) {
        Preconditions.checkState(this.lowLevelHttpResponse == null, "Cannnot set a low level HTTP request when a low level HTTP response has been set.");
        this.lowLevelHttpRequest = mockLowLevelHttpRequest;
        return this;
    }

    public final MockLowLevelHttpRequest getLowLevelHttpRequest() {
        return this.lowLevelHttpRequest;
    }

    public final MockHttpTransport$Builder setLowLevelHttpResponse(MockLowLevelHttpResponse mockLowLevelHttpResponse) {
        Preconditions.checkState(this.lowLevelHttpRequest == null, "Cannot set a low level HTTP response when a low level HTTP request has been set.");
        this.lowLevelHttpResponse = mockLowLevelHttpResponse;
        return this;
    }

    MockLowLevelHttpResponse getLowLevelHttpResponse() {
        return this.lowLevelHttpResponse;
    }
}
