package com.alibaba.sdk.android.initialization;

import org.json.JSONObject;

public interface InitializationHandler<T> {
    Object createRequestParameters();

    String getRequestParameterKey();

    int getRequestServiceType();

    String getResponseValueKey();

    void handleResponseError(int i, String str);

    T handleResponseValue(JSONObject jSONObject);
}
