package com.google.api.client.json;

import com.google.api.client.http.HttpMediaType;
import com.google.api.client.util.Charsets;
import com.loopj.android.http.RequestParams;

public class Json {
    public static final String MEDIA_TYPE = new HttpMediaType(RequestParams.APPLICATION_JSON).setCharsetParameter(Charsets.UTF_8).build();
}
