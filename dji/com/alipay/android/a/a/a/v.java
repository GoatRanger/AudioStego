package com.alipay.android.a.a.a;

import com.google.api.client.http.UrlEncodedParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

public final class v extends aa {
    private String b;
    private byte[] c;
    private String d = UrlEncodedParser.CONTENT_TYPE;
    private ArrayList<Header> e = new ArrayList();
    private Map<String, String> f = new HashMap();
    private boolean g;

    public v(String str) {
        this.b = str;
    }

    public final String a() {
        return this.b;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a(String str, String str2) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f.put(str, str2);
    }

    public final void a(Header header) {
        this.e.add(header);
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final void a(byte[] bArr) {
        this.c = bArr;
    }

    public final String b(String str) {
        return this.f == null ? null : (String) this.f.get(str);
    }

    public final byte[] b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }

    public final ArrayList<Header> d() {
        return this.e;
    }

    public final boolean e() {
        return this.g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        v vVar = (v) obj;
        if (this.c == null) {
            if (vVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(vVar.c)) {
            return false;
        }
        return this.b == null ? vVar.b == null : this.b.equals(vVar.b);
    }

    public final int hashCode() {
        int i = 1;
        if (this.f != null && this.f.containsKey("id")) {
            i = ((String) this.f.get("id")).hashCode() + 31;
        }
        return (this.b == null ? 0 : this.b.hashCode()) + (i * 31);
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", new Object[]{this.b, this.e});
    }
}
