package com.alipay.sdk.f.a;

import android.content.Context;
import com.alipay.sdk.f.b;
import com.alipay.sdk.f.d;
import com.loopj.android.http.RequestParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

public final class c extends d {
    public static final String t = "log_v";

    protected final List<Header> a(boolean z, String str) {
        List<Header> arrayList = new ArrayList();
        arrayList.add(new BasicHeader(d.a, String.valueOf(z)));
        arrayList.add(new BasicHeader(d.d, RequestParams.APPLICATION_OCTET_STREAM));
        arrayList.add(new BasicHeader(d.g, "CBC"));
        return arrayList;
    }

    protected final String c() throws JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put(d.i, "/sdk/log");
        hashMap.put(d.j, "1.0.0");
        HashMap hashMap2 = new HashMap();
        hashMap2.put(t, "1.0");
        return d.a(hashMap, hashMap2);
    }

    protected final JSONObject a() throws JSONException {
        return null;
    }

    protected final String a(String str, JSONObject jSONObject) {
        return str;
    }

    public final b a(Context context, String str) throws Throwable {
        return a(context, str, "http://mcgw.alipay.com/sdklog.do", true);
    }
}
