package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import java.util.Locale;

public class r {
    public static final int a = 0;
    private static final String b = "https";
    private static final String c = "graph.facebook.com";
    private static final String d = "%s/picture";
    private static final String e = "height";
    private static final String f = "width";
    private static final String g = "migration_overrides";
    private static final String h = "{october_2012:true}";
    private Context i;
    private Uri j;
    private b k;
    private boolean l;
    private Object m;

    public static class a {
        private Context a;
        private Uri b;
        private b c;
        private boolean d;
        private Object e;

        public a(Context context, Uri uri) {
            ai.a((Object) uri, "imageUri");
            this.a = context;
            this.b = uri;
        }

        public a a(b bVar) {
            this.c = bVar;
            return this;
        }

        public a a(Object obj) {
            this.e = obj;
            return this;
        }

        public a a(boolean z) {
            this.d = z;
            return this;
        }

        public r a() {
            return new r();
        }
    }

    public interface b {
        void a(s sVar);
    }

    public static Uri a(String str, int i, int i2) {
        ai.a(str, "userId");
        int max = Math.max(i, 0);
        int max2 = Math.max(i2, 0);
        if (max == 0 && max2 == 0) {
            throw new IllegalArgumentException("Either width or height must be greater than 0");
        }
        Builder path = new Builder().scheme("https").authority(c).path(String.format(Locale.US, d, new Object[]{str}));
        if (max2 != 0) {
            path.appendQueryParameter("height", String.valueOf(max2));
        }
        if (max != 0) {
            path.appendQueryParameter("width", String.valueOf(max));
        }
        path.appendQueryParameter(g, h);
        return path.build();
    }

    private r(a aVar) {
        this.i = aVar.a;
        this.j = aVar.b;
        this.k = aVar.c;
        this.l = aVar.d;
        this.m = aVar.e == null ? new Object() : aVar.e;
    }

    public Context a() {
        return this.i;
    }

    public Uri b() {
        return this.j;
    }

    public b c() {
        return this.k;
    }

    public boolean d() {
        return this.l;
    }

    public Object e() {
        return this.m;
    }
}
