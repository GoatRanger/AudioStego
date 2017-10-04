package cn.sharesdk.framework.b.b;

import android.content.Context;
import android.text.TextUtils;

public class e extends c {
    private static int b;
    private static long c;
    public long a;

    protected String a() {
        return "[EXT]";
    }

    protected int b() {
        return 5000;
    }

    protected int c() {
        return 5;
    }

    public boolean a(Context context) {
        cn.sharesdk.framework.b.a.e a = cn.sharesdk.framework.b.a.e.a(context);
        b = a.g("insertExitEventCount");
        c = a.f("lastInsertExitEventTime");
        return super.a(context);
    }

    public void b(Context context) {
        super.b(context);
        cn.sharesdk.framework.b.a.e a = cn.sharesdk.framework.b.a.e.a(context);
        a.a("lastInsertExitEventTime", Long.valueOf(c));
        a.a("insertExitEventCount", b);
    }

    protected long d() {
        return (long) b;
    }

    protected long e() {
        return c;
    }

    protected void f() {
        b++;
    }

    protected void a(long j) {
        c = j;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        stringBuilder.append('|').append(Math.round(((float) this.a) / 1000.0f));
        return stringBuilder.toString();
    }
}
