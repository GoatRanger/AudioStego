package cn.sharesdk.framework.b.b;

import android.content.Context;
import android.text.TextUtils;
import cn.sharesdk.framework.b.a.e;

public class g extends c {
    private static int a;
    private static long b;

    protected String a() {
        return "[RUN]";
    }

    protected int b() {
        return 5000;
    }

    protected int c() {
        return 5;
    }

    public boolean a(Context context) {
        e a = e.a(context);
        a = a.g("insertRunEventCount");
        b = a.f("lastInsertRunEventTime");
        return super.a(context);
    }

    public void b(Context context) {
        super.b(context);
        e a = e.a(context);
        a.a("lastInsertRunEventTime", Long.valueOf(b));
        a.a("insertRunEventCount", a);
    }

    protected long d() {
        return (long) a;
    }

    protected long e() {
        return b;
    }

    protected void f() {
        a++;
    }

    protected void a(long j) {
        b = j;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        return stringBuilder.toString();
    }
}
