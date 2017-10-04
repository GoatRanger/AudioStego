package com.facebook.internal;

import android.app.Activity;
import android.util.Log;
import com.facebook.f;
import com.facebook.h;
import com.facebook.i;
import com.facebook.k;
import com.facebook.o;
import com.tencent.android.tpush.common.Constants;
import java.util.List;

public abstract class j<CONTENT, RESULT> implements i<CONTENT, RESULT> {
    protected static final Object a = new Object();
    private static final String b = "FacebookDialog";
    private final Activity c;
    private final o d;
    private List<a> e;
    private int f;

    protected abstract class a {
        final /* synthetic */ j a;

        public abstract boolean a(CONTENT content);

        public abstract b b(CONTENT content);

        protected a(j jVar) {
            this.a = jVar;
        }

        public Object a() {
            return j.a;
        }
    }

    protected abstract void a(f fVar, h<RESULT> hVar);

    protected abstract List<a> c();

    protected abstract b d();

    protected j(Activity activity, int i) {
        ai.a((Object) activity, Constants.FLAG_ACTIVITY_NAME);
        this.c = activity;
        this.d = null;
        this.f = i;
    }

    protected j(o oVar, int i) {
        ai.a((Object) oVar, "fragmentWrapper");
        this.d = oVar;
        this.c = null;
        this.f = i;
        if (oVar.c() == null) {
            throw new IllegalArgumentException("Cannot use a fragment that is not attached to an activity");
        }
    }

    public final void a(f fVar, h<RESULT> hVar) {
        if (fVar instanceof f) {
            a((f) fVar, (h) hVar);
            return;
        }
        throw new k("Unexpected CallbackManager, please use the provided Factory.");
    }

    public final void a(f fVar, h<RESULT> hVar, int i) {
        a(i);
        a(fVar, (h) hVar);
    }

    protected void a(int i) {
        if (o.b(i)) {
            throw new IllegalArgumentException("Request code " + i + " cannot be within the range reserved by the Facebook SDK.");
        }
        this.f = i;
    }

    public int a() {
        return this.f;
    }

    public boolean a(CONTENT content) {
        return a((Object) content, a);
    }

    protected boolean a(CONTENT content, Object obj) {
        boolean z = obj == a;
        for (a aVar : e()) {
            if ((z || ah.a(aVar.a(), obj)) && aVar.a(content)) {
                return true;
            }
        }
        return false;
    }

    public void b(CONTENT content) {
        b(content, a);
    }

    protected void b(CONTENT content, Object obj) {
        b c = c(content, obj);
        if (c == null) {
            String str = "No code path should ever result in a null appCall";
            Log.e(b, str);
            if (o.d()) {
                throw new IllegalStateException(str);
            }
        } else if (this.d != null) {
            i.a(c, this.d);
        } else {
            i.a(c, this.c);
        }
    }

    protected Activity b() {
        if (this.c != null) {
            return this.c;
        }
        if (this.d != null) {
            return this.d.c();
        }
        return null;
    }

    private b c(CONTENT content, Object obj) {
        Object obj2;
        b b;
        if (obj == a) {
            obj2 = 1;
        } else {
            obj2 = null;
        }
        for (a aVar : e()) {
            if ((obj2 != null || ah.a(aVar.a(), obj)) && aVar.a(content)) {
                try {
                    b = aVar.b(content);
                    break;
                } catch (k e) {
                    b = d();
                    i.a(b, e);
                }
            }
        }
        b = null;
        if (b != null) {
            return b;
        }
        b = d();
        i.a(b);
        return b;
    }

    private List<a> e() {
        if (this.e == null) {
            this.e = c();
        }
        return this.e;
    }
}
