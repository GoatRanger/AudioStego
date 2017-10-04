package com.google.api.client.auth.oauth2;

import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseException.Builder;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Strings;
import java.io.IOException;

public class TokenResponseException extends HttpResponseException {
    private static final long serialVersionUID = 4020689092957439244L;
    private final transient TokenErrorResponse details;

    TokenResponseException(Builder builder, TokenErrorResponse tokenErrorResponse) {
        super(builder);
        this.details = tokenErrorResponse;
    }

    public final TokenErrorResponse getDetails() {
        return this.details;
    }

    public static TokenResponseException from(JsonFactory jsonFactory, HttpResponse httpResponse) {
        IOException e;
        TokenErrorResponse tokenErrorResponse;
        Object obj;
        TokenErrorResponse tokenErrorResponse2 = null;
        Builder builder = new Builder(httpResponse.getStatusCode(), httpResponse.getStatusMessage(), httpResponse.getHeaders());
        Preconditions.checkNotNull(jsonFactory);
        String contentType = httpResponse.getContentType();
        StringBuilder computeMessageBuffer;
        TokenErrorResponse tokenErrorResponse3;
        try {
            if (httpResponse.isSuccessStatusCode() || contentType == null || !HttpMediaType.equalsIgnoreParameters(Json.MEDIA_TYPE, contentType)) {
                contentType = httpResponse.parseAsString();
                computeMessageBuffer = HttpResponseException.computeMessageBuffer(httpResponse);
                if (!Strings.isNullOrEmpty(contentType)) {
                    computeMessageBuffer.append(StringUtils.LINE_SEPARATOR).append(contentType);
                    builder.setContent(contentType);
                }
                builder.setMessage(computeMessageBuffer.toString());
                return new TokenResponseException(builder, tokenErrorResponse2);
            }
            tokenErrorResponse3 = (TokenErrorResponse) new JsonObjectParser(jsonFactory).parseAndClose(httpResponse.getContent(), httpResponse.getContentCharset(), TokenErrorResponse.class);
            try {
                tokenErrorResponse2 = tokenErrorResponse3;
                contentType = tokenErrorResponse3.toPrettyString();
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                tokenErrorResponse = tokenErrorResponse2;
                tokenErrorResponse2 = tokenErrorResponse3;
                obj = tokenErrorResponse;
                computeMessageBuffer = HttpResponseException.computeMessageBuffer(httpResponse);
                if (Strings.isNullOrEmpty(contentType)) {
                    computeMessageBuffer.append(StringUtils.LINE_SEPARATOR).append(contentType);
                    builder.setContent(contentType);
                }
                builder.setMessage(computeMessageBuffer.toString());
                return new TokenResponseException(builder, tokenErrorResponse2);
            }
            computeMessageBuffer = HttpResponseException.computeMessageBuffer(httpResponse);
            if (Strings.isNullOrEmpty(contentType)) {
                computeMessageBuffer.append(StringUtils.LINE_SEPARATOR).append(contentType);
                builder.setContent(contentType);
            }
            builder.setMessage(computeMessageBuffer.toString());
            return new TokenResponseException(builder, tokenErrorResponse2);
        } catch (IOException e3) {
            e = e3;
            tokenErrorResponse3 = tokenErrorResponse2;
            e.printStackTrace();
            tokenErrorResponse = tokenErrorResponse2;
            tokenErrorResponse2 = tokenErrorResponse3;
            obj = tokenErrorResponse;
            computeMessageBuffer = HttpResponseException.computeMessageBuffer(httpResponse);
            if (Strings.isNullOrEmpty(contentType)) {
                computeMessageBuffer.append(StringUtils.LINE_SEPARATOR).append(contentType);
                builder.setContent(contentType);
            }
            builder.setMessage(computeMessageBuffer.toString());
            return new TokenResponseException(builder, tokenErrorResponse2);
        }
    }
}
