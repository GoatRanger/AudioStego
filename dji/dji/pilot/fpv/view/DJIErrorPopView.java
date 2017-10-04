package dji.pilot.fpv.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.dji.frame.c.l;
import dji.log.DJILogHelper;
import dji.log.ErrorPopLogHelper;
import dji.midware.data.manager.P3.p;
import dji.pilot.R;
import dji.pilot.fpv.control.g;
import dji.pilot.publics.widget.j;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIErrorPopView extends DJILinearLayout implements j {
    private static final String c = DJIErrorPopView.class.getSimpleName();
    private static final int d = 4096;
    private static final long e = 4000;
    private static final int[] f = new int[]{R.id.a01, R.id.a02, R.id.a03, R.id.a04, R.id.a05, R.id.a06};
    private g g = null;
    private h h = null;
    private OnClickListener i = null;
    private final a[] j = new a[f.length];
    private int k = 0;
    private int l = 0;
    private Animation m = null;
    private Animation n = null;
    private Animation o = null;
    private Animation p = null;
    private Animation q = null;
    private Animation r = null;
    private Animation[] s = new Animation[(f.length - 1)];
    private AnimationListener t = null;
    private boolean u = false;
    private int v = -1;
    private int w = 0;
    private int x = 0;

    public static final class b {
        public d a = d.WARNING;
        public int b = 0;
        public String c = null;
        public int d = 0;
        public String e = null;
        public c f = c.AUTODISAPPEAR;
        public f g = f.INSERT;
        public long h = DJIErrorPopView.e;

        public static b a(d dVar, int i, int i2, c cVar, f fVar) {
            b bVar = new b();
            bVar.a = dVar;
            bVar.b = i;
            bVar.d = i2;
            bVar.f = cVar;
            bVar.g = fVar;
            return bVar;
        }

        public static void b(d dVar, int i, int i2, c cVar, f fVar) {
            b bVar = new b();
            bVar.a = dVar;
            bVar.b = i;
            bVar.d = i2;
            bVar.f = cVar;
            bVar.g = fVar;
            dji.thirdparty.a.c.a().e(bVar);
        }

        public static void a(d dVar, String str, String str2, c cVar, f fVar) {
            b bVar = new b();
            bVar.a = dVar;
            bVar.c = str;
            bVar.e = str2;
            bVar.f = cVar;
            bVar.g = fVar;
            dji.thirdparty.a.c.a().e(bVar);
        }

        public static void a(d dVar, String str, String str2, c cVar, f fVar, long j) {
            b bVar = new b();
            bVar.a = dVar;
            bVar.c = str;
            bVar.e = str2;
            bVar.f = cVar;
            bVar.g = fVar;
            bVar.h = j;
            dji.thirdparty.a.c.a().e(bVar);
        }

        public void a(b bVar) {
            this.a = bVar.a;
            this.b = bVar.b;
            this.d = bVar.d;
            this.c = bVar.c;
            this.e = bVar.e;
            this.f = bVar.f;
            this.g = bVar.g;
        }

        private void a() {
            this.a = d.WARNING;
            this.b = 0;
            this.d = 0;
            this.c = null;
            this.e = null;
            this.f = c.AUTODISAPPEAR;
            this.g = f.INSERT;
        }

        public boolean equals(Object obj) {
            boolean equals = super.equals(obj);
            if (!equals && (obj instanceof b)) {
                b bVar = (b) obj;
                if (bVar.f == c.STATIC) {
                    if (bVar.b == this.b) {
                        return true;
                    }
                    return false;
                } else if (bVar.b == this.b && TextUtils.equals(bVar.c, this.c) && bVar.d == this.d && TextUtils.equals(bVar.e, this.e)) {
                    return true;
                }
            }
            return equals;
        }

        public int hashCode() {
            int i = ((this.b + 527) * 31) + this.d;
            if (this.c != null) {
                i = (i * 31) + this.c.hashCode();
            }
            if (this.e != null) {
                return (i * 31) + this.e.hashCode();
            }
            return i;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(32);
            stringBuilder.append("titleId[").append(String.valueOf(this.b)).append(dji.pilot.usercenter.protocol.d.H);
            stringBuilder.append("descId[").append(String.valueOf(this.d)).append(dji.pilot.usercenter.protocol.d.H);
            stringBuilder.append("titleStr[").append(this.c).append(dji.pilot.usercenter.protocol.d.H);
            stringBuilder.append("descStr[").append(this.e).append(dji.pilot.usercenter.protocol.d.H);
            return stringBuilder.toString();
        }
    }

    public enum c {
        AUTODISAPPEAR,
        PUSH,
        STATIC
    }

    public enum d {
        NOTIFY,
        WARNING,
        DANGEROUS,
        NONE
    }

    public enum f {
        INSERT,
        REMOVE
    }

    public DJIErrorPopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ProductView);
            this.x = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
            a(context);
        }
    }

    private void a(Object obj) {
        if (obj instanceof String) {
            try {
                int parseInt = Integer.parseInt((String) obj);
                if (parseInt != -1) {
                    a(parseInt);
                }
            } catch (NumberFormatException e) {
            }
        }
    }

    private void a(Context context) {
        this.i = new 1(this);
        this.m = AnimationUtils.loadAnimation(context, R.anim.a9);
        if (this.x == 0) {
            this.n = AnimationUtils.loadAnimation(context, R.anim.bp);
            this.o = AnimationUtils.loadAnimation(context, R.anim.bt);
            this.p = AnimationUtils.loadAnimation(context, R.anim.a7);
            this.q = AnimationUtils.loadAnimation(context, R.anim.a8);
        } else if (this.x == 1) {
            this.n = AnimationUtils.loadAnimation(context, R.anim.bi);
            this.o = AnimationUtils.loadAnimation(context, R.anim.bt);
            this.p = AnimationUtils.loadAnimation(context, R.anim.a7);
            this.q = AnimationUtils.loadAnimation(context, R.anim.a8);
        }
        this.n.setFillBefore(true);
        this.o.setFillBefore(true);
        this.p.setFillBefore(true);
        this.q.setFillBefore(true);
        this.g = new g(this, this.n);
        setLayoutAnimation(this.g);
        this.h = new h(this);
    }

    private void a(a aVar) {
        aVar.b = (DJIImageView) aVar.a.findViewById(R.id.zw);
        aVar.c = (DJITextView) aVar.a.findViewById(R.id.zx);
        aVar.d = (DJITextView) aVar.a.findViewById(R.id.zy);
        aVar.e = (DJIImageView) aVar.a.findViewById(R.id.zz);
        aVar.e.setOnClickListener(this.i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            for (int i = 0; i < f.length; i++) {
                this.j[i] = new a(null);
                this.j[i].a = (DJILinearLayout) findViewById(f[i]);
                a(this.j[i]);
            }
        }
    }

    public void dispatchOnCreate() {
        dji.thirdparty.a.c.a().a((Object) this);
    }

    public void dispatchOnDestroy() {
        dji.thirdparty.a.c.a().d((Object) this);
    }

    private final int b() {
        int i = 0;
        int length = f.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.j[i2].a()) {
                i++;
            }
        }
        return i;
    }

    private boolean a(b bVar) {
        boolean z = true;
        int b = b();
        if (bVar.f == c.STATIC) {
            int length = f.length;
            b = 0;
            while (b < length) {
                if (this.j[b].a() && this.j[b].g.equals(bVar) && this.j[b].a.getVisibility() == 0) {
                    break;
                }
                b++;
            }
            z = false;
            return z;
        } else if (b < 5 && this.j[b].g.equals(bVar) && this.j[b].a.getVisibility() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void c() {
        int length = f.length;
        for (int i = 0; i < length; i++) {
            this.j[i].a.clearAnimation();
        }
    }

    private void b(b bVar) {
        int i;
        f();
        c();
        this.k = this.k >= f.length ? f.length : this.k + 1;
        this.w = b();
        this.j[this.k - 1].a.show();
        for (i = this.k - 1; i > this.w; i--) {
            this.j[i].f = this.j[i - 1].f;
            this.j[i].g.a(this.j[i - 1].g);
            this.j[i].b();
        }
        i = d();
        DJILogHelper.getInstance().LOGD(c, "seqNum[" + i + dji.pilot.usercenter.protocol.d.H, false, true);
        this.j[this.w].f = i;
        this.j[this.w].g.a(bVar);
        this.j[this.w].b();
        startLayoutAnimation();
        if (bVar.f == c.AUTODISAPPEAR) {
            this.h.sendMessageDelayed(this.h.obtainMessage(4096, i, 0), bVar.h);
        }
        dji.thirdparty.a.c.a().e(e.a);
    }

    private int d() {
        if (this.l >= 32767) {
            this.l = 0;
        } else {
            this.l++;
        }
        return this.l;
    }

    private void c(b bVar) {
        a aVar;
        for (int i = 0; i < this.k; i++) {
            a aVar2 = this.j[i];
            if (aVar2.a.getVisibility() == 0 && aVar2.g.equals(bVar)) {
                aVar = aVar2;
                break;
            }
        }
        aVar = null;
        if (aVar != null) {
            a(aVar.f);
        }
    }

    private void a(int i) {
        int i2 = 0;
        a aVar = null;
        int i3 = 0;
        while (i3 < this.k) {
            a aVar2 = this.j[i3];
            if (aVar2.a.getVisibility() == 0 && aVar2.f == i) {
                aVar = aVar2;
                break;
            }
            i3++;
        }
        DJILogHelper.getInstance().LOGD(c, "seqNum[" + i + "]child[" + (aVar == null ? -1 : aVar.f) + "]length[" + this.k + "]index[" + i3 + dji.pilot.usercenter.protocol.d.H);
        if (aVar != null) {
            f();
            c();
            if (i3 == this.k - 1) {
                aVar.f = -1;
                aVar.g.a();
                a(aVar.a);
            } else {
                if (aVar.a()) {
                    aVar.g.f = c.AUTODISAPPEAR;
                }
                b(i3);
            }
            if (this.k != 0) {
                i2 = this.k - 1;
            }
            this.k = i2;
        }
    }

    private void a(DJILinearLayout dJILinearLayout) {
        if (dJILinearLayout != null) {
            Animation animation = this.p;
            dJILinearLayout.hide();
            dJILinearLayout.startAnimation(animation);
            dji.thirdparty.a.c.a().e(e.b);
        }
    }

    private void e() {
        Context context = getContext();
        this.t = new 2(this);
        if (this.x == 0) {
            this.r = AnimationUtils.loadAnimation(context, R.anim.bq);
        } else if (this.x == 1) {
            this.r = AnimationUtils.loadAnimation(context, R.anim.bk);
        }
        this.r.setFillBefore(true);
        this.r.setAnimationListener(this.t);
        for (int i = 1; i < this.s.length; i++) {
            this.s[i] = AnimationUtils.loadAnimation(context, R.anim.bu);
            this.s[i].setFillBefore(true);
        }
    }

    private void b(int i) {
        if (this.r == null) {
            e();
        }
        this.v = i;
        this.u = true;
        this.j[i].a.startAnimation(this.r);
        int length = this.k == f.length ? f.length - 1 : this.k;
        for (int i2 = i + 1; i2 < length; i2++) {
            a aVar = this.j[i2];
            if (aVar.a.getVisibility() == 0) {
                aVar.a.startAnimation(this.s[i2]);
            }
        }
    }

    private void f() {
        if (this.u) {
            int length = this.k == f.length ? f.length - 1 : this.k;
            int i = this.v + 1;
            while (i <= length && this.j[i].a.getVisibility() == 0) {
                this.j[i - 1].a.clearAnimation();
                this.j[i - 1].f = this.j[i].f;
                this.j[i - 1].g.a(this.j[i].g);
                this.j[i - 1].b();
                i++;
            }
            this.j[i - 1].a.clearAnimation();
            this.j[i - 1].f = -1;
            this.j[i - 1].g.a();
            this.j[i - 1].a.hide();
            this.v = -1;
            this.u = false;
            dji.thirdparty.a.c.a().e(e.b);
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.ConnectLose) {
            g();
        }
    }

    private void g() {
        c();
        for (int i = 0; i < this.k; i++) {
            a aVar = this.j[i];
            if (aVar.a.getVisibility() == 0) {
                aVar.f = -1;
                aVar.g.a();
                aVar.a.hide();
            }
        }
        this.k = 0;
    }

    public void onEventMainThread(b bVar) {
        if (bVar == null) {
            return;
        }
        if (bVar.g == f.INSERT) {
            if ((!l.a(bVar.e) || !l.a(bVar.c) || bVar.d > 0 || bVar.b > 0) && !a(bVar)) {
                b(bVar);
                String str = "";
                if (l.a(bVar.c) && bVar.b > 0) {
                    str = getResources().getString(bVar.b);
                } else if (!l.a(bVar.c)) {
                    str = bVar.c;
                }
                String str2 = "";
                if (l.a(bVar.e) && bVar.d > 0) {
                    str2 = getResources().getString(bVar.d);
                } else if (!l.a(bVar.e)) {
                    str2 = bVar.e;
                }
                ErrorPopLogHelper.getInstance(getContext()).logStr(str, str2);
                if (bVar.a == d.WARNING || bVar.a == d.DANGEROUS) {
                    g.getInstance().b(bVar);
                } else if (bVar.a == d.NOTIFY) {
                    g.getInstance().a(bVar);
                }
            }
        } else if (bVar.g == f.REMOVE) {
            c(bVar);
        }
    }
}
