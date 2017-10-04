package com.dji.a.d;

import android.content.Context;
import android.os.AsyncTask;
import com.dji.a.f.g;
import com.dji.a.f.i;
import com.sina.weibo.sdk.statistic.LogBuilder;
import java.util.HashMap;

public class a {
    private static final Object e = new Object();
    private static a f = null;
    private static final Object g = new Object();
    private Context a;
    private String b;
    private String c;
    private Boolean d;
    private com.dji.a.b h;

    private class a extends AsyncTask<Void, Void, Boolean> {
        final /* synthetic */ a a;
        private String b;
        private byte[] c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Boolean) obj);
        }

        public a(a aVar, String str, byte[] bArr) {
            this.a = aVar;
            this.b = str;
            this.c = bArr;
        }

        protected Boolean a(Void... voidArr) {
            com.dji.a.e.a aVar = new com.dji.a.e.a(this.b);
            try {
                aVar.a(com.dji.a.b.a.INIT_DATA);
                boolean a = aVar.a(this.c, this.a.h);
                if (com.dji.a.a.b) {
                    com.dji.a.a.c.a(com.dji.a.a.a, "SendBaseInfo finish result is " + a);
                }
                return Boolean.valueOf(a);
            } catch (Exception e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }

        protected void a(Boolean bool) {
            this.a.a(bool.booleanValue());
        }
    }

    private static final class b {
        private static final a a = new a();
    }

    public static a a() {
        return b.a;
    }

    private a() {
        this.c = null;
        this.d = null;
    }

    public void a(Context context, String str) {
        this.a = context;
        this.b = str;
        this.h = com.dji.a.a.a();
    }

    public boolean b() {
        boolean booleanValue;
        synchronized (e) {
            if (this.d == null) {
                this.d = Boolean.valueOf(i.a().c());
            }
            if (com.dji.a.a.b) {
                com.dji.a.a.c.a(com.dji.a.a.a, "mIsBaseInfoSent is " + this.d);
            }
            booleanValue = this.d.booleanValue();
        }
        return booleanValue;
    }

    public void c() {
        synchronized (g) {
            if (f == null) {
                f = new a(this, this.h.c(), g.a(new HashMap[]{f()}));
                f.execute(new Void[0]);
            }
        }
    }

    public String d() {
        if (this.a != null) {
            return com.dji.a.f.a.b(this.a);
        }
        return "";
    }

    public synchronized String e() {
        if (this.c == null) {
            this.c = i.a().b();
            if (this.c == null) {
                this.c = com.dji.a.f.a.a(this.a);
                i.a().c(this.c);
            }
        }
        return this.c;
    }

    private HashMap<String, Object> f() {
        HashMap<String, Object> hashMap = new HashMap(32);
        String e = e();
        if (com.dji.a.a.b) {
            com.dji.a.a.c.a(com.dji.a.a.a, "djia uuid=<<<<<<" + e + ">>>>>>");
        }
        hashMap.put("uuid", e);
        hashMap.put("mbrand", com.dji.a.f.a.a());
        hashMap.put("mmodel", com.dji.a.f.a.b());
        hashMap.put("msize", com.dji.a.f.a.c(this.a));
        hashMap.put("ostype", Integer.valueOf(com.dji.a.f.a.c()));
        hashMap.put("osver", com.dji.a.f.a.d());
        hashMap.put("mcc", com.dji.a.f.a.d(this.a) + "");
        hashMap.put("country", com.dji.a.f.a.f());
        hashMap.put("lang", com.dji.a.f.a.e());
        hashMap.put(LogBuilder.KEY_CHANNEL, com.dji.a.f.a.g());
        hashMap.put("Token", this.b);
        return hashMap;
    }

    private void a(boolean z) {
        synchronized (g) {
            f = null;
            if (z) {
                this.d = Boolean.valueOf(true);
                i.a().a(true);
            }
        }
    }
}
