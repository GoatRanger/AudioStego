package com.e;

import com.loopj.android.http.AsyncHttpClient;
import java.util.HashMap;
import java.util.Map;

public class di extends ao {
    private byte[] a;
    private String b = "1";

    public di(byte[] bArr) {
        this.a = (byte[]) bArr.clone();
    }

    public di(byte[] bArr, String str) {
        this.a = (byte[]) bArr.clone();
        this.b = str;
    }

    private String e() {
        Object a = dd.a(df.a);
        byte[] bArr = new byte[(a.length + 50)];
        System.arraycopy(this.a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        return cz.a(bArr);
    }

    public Map<String, String> a() {
        return null;
    }

    public Map<String, String> b() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(AsyncHttpClient.HEADER_CONTENT_TYPE, "application/zip");
        hashMap.put("Content-Length", String.valueOf(this.a.length));
        return hashMap;
    }

    public String c() {
        return String.format(df.b, new Object[]{"1", this.b, "1", "open", e()});
    }

    public byte[] d() {
        return this.a;
    }
}
