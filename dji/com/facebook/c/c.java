package com.facebook.c;

import android.net.Uri;
import com.alipay.sdk.b.b;
import dji.pilot.usercenter.protocol.d;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class c {
    public static final Set<String> a;
    public static final Set<String> b;
    public static final Set<String> c;
    public final Uri d;
    public final String e;
    public final String f;
    public final Uri g;

    static {
        Set hashSet = new HashSet();
        hashSet.add("image/*");
        hashSet.add("image/jpeg");
        hashSet.add("image/png");
        hashSet.add("image/gif");
        hashSet.add("image/webp");
        hashSet.add("video/*");
        hashSet.add("video/mp4");
        hashSet.add("audio/*");
        hashSet.add("audio/mpeg");
        b = Collections.unmodifiableSet(hashSet);
        hashSet = new HashSet();
        hashSet.add("content");
        hashSet.add("android.resource");
        hashSet.add(d.A);
        a = Collections.unmodifiableSet(hashSet);
        hashSet = new HashSet();
        hashSet.add("http");
        hashSet.add(b.a);
        c = Collections.unmodifiableSet(hashSet);
    }

    c(d dVar) {
        this.d = dVar.a();
        this.e = dVar.b();
        this.f = dVar.c();
        this.g = dVar.d();
        if (this.d == null) {
            throw new NullPointerException("Must provide non-null uri");
        } else if (this.e == null) {
            throw new NullPointerException("Must provide mimeType");
        } else if (!a.contains(this.d.getScheme())) {
            throw new IllegalArgumentException("Unsupported URI scheme: " + this.d.getScheme());
        } else if (!b.contains(this.e)) {
            throw new IllegalArgumentException("Unsupported mime-type: " + this.e);
        } else if (this.g != null && !c.contains(this.g.getScheme())) {
            throw new IllegalArgumentException("Unsupported external uri scheme: " + this.g.getScheme());
        }
    }

    public static d a(Uri uri, String str) {
        return new d(uri, str);
    }
}
