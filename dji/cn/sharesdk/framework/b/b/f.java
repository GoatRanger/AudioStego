package cn.sharesdk.framework.b.b;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.utils.d;
import com.here.odnp.debug.DebugFile;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;

public class f extends c {
    private static int p;
    private static long q;
    public int a;
    public String b;
    public String c;
    public a d = new a();
    public String n;
    public String[] o;

    public static class a {
        public String a = "";
        public String b;
        public ArrayList<String> c = new ArrayList();
        public ArrayList<String> d = new ArrayList();
        public ArrayList<String> e = new ArrayList();
        public ArrayList<Bitmap> f = new ArrayList();
        public HashMap<String, Object> g;

        public String toString() {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(this.b)) {
                this.b = this.b.trim().replaceAll("\r", "");
                this.b = this.b.trim().replaceAll("\n", "");
                this.b = this.b.trim().replaceAll(DebugFile.EOL, "");
            }
            hashMap.put("text", this.b);
            hashMap.put("url", this.c);
            if (this.d != null && this.d.size() > 0) {
                hashMap.put("imgs", this.d);
            }
            if (this.g != null) {
                hashMap.put("attch", new Hashon().fromHashMap(this.g));
            }
            return new Hashon().fromHashMap(hashMap);
        }
    }

    protected String a() {
        return "[SHR]";
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|').append(this.a);
        stringBuilder.append('|').append(this.b);
        stringBuilder.append('|').append(TextUtils.isEmpty(this.c) ? "" : this.c);
        String str = "";
        if (this.o != null && this.o.length > 0) {
            str = "[\"" + TextUtils.join("\",\"", this.o) + "\"]";
        }
        stringBuilder.append('|').append(str);
        stringBuilder.append('|');
        if (this.d != null) {
            try {
                str = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.d.toString()), 0);
                if (str.contains("\n")) {
                    str = str.replace("\n", "");
                }
                stringBuilder.append(str);
            } catch (Throwable th) {
                d.a().d(th);
            }
        }
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.n)) {
            try {
                str = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.n), 0);
                if (!TextUtils.isEmpty(str) && str.contains("\n")) {
                    str = str.replace("\n", "");
                }
                stringBuilder.append(str);
            } catch (Throwable th2) {
                d.a().w(th2);
            }
        }
        return stringBuilder.toString();
    }

    protected int b() {
        return 5000;
    }

    protected int c() {
        return 30;
    }

    protected long d() {
        return (long) p;
    }

    protected long e() {
        return q;
    }

    protected void f() {
        p++;
    }

    protected void a(long j) {
        q = j;
    }
}
