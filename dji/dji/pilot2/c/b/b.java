package dji.pilot2.c.b;

import android.os.Bundle;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.pilot2.c.a.a;
import dji.pilot2.mine.activity.DraftActivity;

public abstract class b {
    private String a;
    private String b;
    private String c;
    private boolean d;
    private boolean e;
    private boolean f;
    private a g;
    private dji.pilot2.c.a.b h;

    public abstract int a();

    protected abstract void b();

    protected abstract void c();

    private b() {
        this.d = false;
        this.e = false;
        this.f = false;
        this.a = "";
        this.b = "";
        this.c = "";
        this.h = new dji.pilot2.c.a.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(String str) {
                Message message = new Message();
                message.what = 3;
                Bundle bundle = new Bundle();
                bundle.putString(dji.pilot2.share.mode.a.m, this.a.i());
                bundle.putString(dji.pilot2.share.mode.a.q, str);
                message.setData(bundle);
                DraftActivity.K.sendMessage(message);
            }

            public void a() {
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString(dji.pilot2.share.mode.a.m, this.a.i());
                message.setData(bundle);
                DraftActivity.K.sendMessage(message);
            }

            public void a(int i) {
                if (i != 100) {
                    Message message = new Message();
                    message.what = 1;
                    Bundle bundle = new Bundle();
                    bundle.putInt(dji.pilot2.share.mode.a.n, i);
                    bundle.putString(dji.pilot2.share.mode.a.m, this.a.i());
                    message.setData(bundle);
                    DraftActivity.K.sendMessage(message);
                    return;
                }
                message = new Message();
                message.what = 6;
                bundle = new Bundle();
                bundle.putString(dji.pilot2.share.mode.a.m, this.a.i());
                message.setData(bundle);
                DraftActivity.K.sendMessage(message);
            }

            public void b() {
                Message message = new Message();
                message.what = 2;
                Bundle bundle = new Bundle();
                bundle.putString(dji.pilot2.share.mode.a.m, this.a.i());
                message.setData(bundle);
                DraftActivity.K.sendMessage(message);
            }

            public void f() {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString(dji.pilot2.share.mode.a.m, this.a.i());
                message.setData(bundle);
                message.what = 5;
                DraftActivity.K.sendMessage(message);
            }
        };
    }

    public b(String str, String str2, String str3) {
        this();
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    protected final void d() {
        this.d = true;
        if (this.g != null) {
            this.g.a(this);
        }
        dji.pilot2.b.a.b().post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.h != null) {
                    this.a.h.a();
                }
            }
        });
    }

    protected final void a(final String str) {
        this.e = true;
        if (this.g != null) {
            this.g.a(this, str);
        }
        dji.pilot2.b.a.b().post(new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                if (this.b.h != null) {
                    this.b.h.a(str);
                }
            }
        });
    }

    protected final void e() {
        this.f = true;
        DJILogHelper.getInstance().LOGI("Lyric", "managerListener: " + this.g);
        if (this.g != null) {
            this.g.b(this);
        }
        dji.pilot2.b.a.b().post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.h != null) {
                    this.a.h.b();
                }
            }
        });
    }

    protected final void f() {
        if (this.g != null) {
            this.g.c(this);
        }
        dji.pilot2.b.a.b().post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.h != null) {
                    this.a.h.f();
                }
            }
        });
    }

    protected final void a(final int i) {
        dji.pilot2.b.a.b().post(new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                if (this.b.h != null) {
                    this.b.h.a(i);
                }
            }
        });
    }

    final void g() {
        b();
    }

    final void h() {
        c();
    }

    public final String i() {
        return this.a;
    }

    public final void b(String str) {
        this.a = str;
    }

    public final String j() {
        return this.b;
    }

    public final void c(String str) {
        this.b = str;
    }

    public final String k() {
        return this.c;
    }

    public final void d(String str) {
        this.c = str;
    }

    public final boolean l() {
        return this.d;
    }

    public final boolean m() {
        return this.e;
    }

    public final boolean n() {
        return this.f;
    }

    final void a(a aVar) {
        this.g = aVar;
    }

    public final void a(dji.pilot2.c.a.b bVar) {
        this.h = bVar;
    }
}
