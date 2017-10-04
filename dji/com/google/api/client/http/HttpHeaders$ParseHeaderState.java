package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.ClassInfo;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

final class HttpHeaders$ParseHeaderState {
    final ArrayValueMap arrayValueMap;
    final ClassInfo classInfo;
    final List<Type> context;
    final StringBuilder logger;

    public HttpHeaders$ParseHeaderState(HttpHeaders httpHeaders, StringBuilder stringBuilder) {
        this.context = Arrays.asList(new Type[]{httpHeaders.getClass()});
        this.classInfo = ClassInfo.of(r0, true);
        this.logger = stringBuilder;
        this.arrayValueMap = new ArrayValueMap(httpHeaders);
    }

    void finish() {
        this.arrayValueMap.setValues();
    }
}
