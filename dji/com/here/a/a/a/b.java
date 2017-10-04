package com.here.a.a.a;

import com.sina.weibo.sdk.component.WidgetRequestParam;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class b extends i {
    private String b;
    private boolean c;
    private Boolean d;
    private Integer e;

    public b(String str, String str2, String str3, String str4, boolean z) {
        super(str, str2, str3);
        if (str4 == null || str4.isEmpty()) {
            throw new IllegalArgumentException("Search text can't be null.");
        }
        this.b = str4;
        this.c = z;
    }

    protected String a() {
        return "coverage/v1/search";
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap(super.b());
        hashMap.put("chinaconfig", this.c ? "1" : "0");
        hashMap.put(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, this.b);
        if (this.d != null) {
            hashMap.put("details", this.d.booleanValue() ? "1" : "0");
        }
        if (this.e != null) {
            hashMap.put("max", String.valueOf(this.e));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public b a(Boolean bool) {
        this.d = bool;
        return this;
    }

    public b a(Integer num) {
        if (num == null || num.intValue() >= 1) {
            this.e = num;
            return this;
        }
        throw new IllegalArgumentException("Max results should be greater that zero.");
    }

    public Integer c() {
        return this.e;
    }
}
