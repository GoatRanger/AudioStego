package com.amap.api.mapcore.util;

import com.loopj.android.http.AsyncHttpClient;
import java.util.HashMap;
import java.util.Map;

public class ed extends fw {
    private byte[] a;
    private String b = "1";

    public ed(byte[] bArr) {
        this.a = (byte[]) bArr.clone();
    }

    public ed(byte[] bArr, String str) {
        this.a = (byte[]) bArr.clone();
        this.b = str;
    }

    private String e() {
        Object a = dx.a(ea.a);
        byte[] bArr = new byte[(a.length + 50)];
        System.arraycopy(this.a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        return ds.a(bArr);
    }

    public Map<String, String> c() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(AsyncHttpClient.HEADER_CONTENT_TYPE, "application/zip");
        hashMap.put("Content-Length", String.valueOf(this.a.length));
        return hashMap;
    }

    public Map<String, String> b() {
        return null;
    }

    public String a() {
        return String.format(ea.b, new Object[]{"1", this.b, "1", "open", e()});
    }

    public byte[] a_() {
        return this.a;
    }
}
