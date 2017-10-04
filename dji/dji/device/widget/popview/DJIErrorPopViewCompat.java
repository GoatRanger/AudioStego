package dji.device.widget.popview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LayoutAnimationController.AnimationParameters;
import com.dji.frame.c.l;
import dji.log.DJILogHelper;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;

public class DJIErrorPopViewCompat extends DJILinearLayout implements b {
    private static final String c = DJIErrorPopViewCompat.class.getSimpleName();
    private static final int d = 4096;
    private static final long e = 5000;
    private static final int[] f = new int[]{R.id.fpv_error_pop_ly1, R.id.fpv_error_pop_ly2, R.id.fpv_error_pop_ly3, R.id.fpv_error_pop_ly4, R.id.fpv_error_pop_ly5, R.id.fpv_error_pop_ly6};
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

    private static final class a {
        public DJIRelativeLayout a;
        public DJIImageView b;
        public DJITextView c;
        public DJITextView d;
        public DJIImageView e;
        public int f;
        public final b g;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = 0;
            this.g = new b();
        }

        public final boolean a() {
            return this.g.f == c.STATIC;
        }

        public void b() {
            switch (this.g.a) {
                case NOTIFY:
                    this.b.setImageResource(R.drawable.osd_error_tips_notify_icon);
                    this.b.show();
                    break;
                case WARNING:
                    this.b.setImageResource(R.drawable.osd_error_tips_warning_icon);
                    this.b.show();
                    break;
                default:
                    this.b.go();
                    break;
            }
            if (this.g.f == c.STATIC) {
                this.e.show();
                this.e.setTag(String.valueOf(this.f));
            } else {
                this.e.go();
                this.e.setTag(null);
            }
            if (this.g.b != 0) {
                this.c.setText(this.g.b);
                this.c.show();
            } else if (dji.device.common.a.b.a(this.g.c)) {
                this.c.go();
            } else {
                this.c.setText(this.g.c);
                this.c.show();
            }
            if (this.g.d != 0) {
                this.d.setText(this.g.d);
                this.d.show();
            } else if (dji.device.common.a.b.a(this.g.e)) {
                this.d.go();
            } else {
                this.d.setText(this.g.e);
                this.d.show();
            }
        }
    }

    public static final class b {
        public d a = d.WARNING;
        public int b = 0;
        public String c = null;
        public int d = 0;
        public String e = null;
        public c f = c.AUTODISAPPEAR;
        public f g = f.INSERT;

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
            if (equals || !(obj instanceof b)) {
                return equals;
            }
            b bVar = (b) obj;
            if (bVar.b == this.b && bVar.d == this.d) {
                return true;
            }
            return equals;
        }

        public int hashCode() {
            return ((this.b + 527) * 31) + this.d;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(32);
            stringBuilder.append("titleId[").append(String.valueOf(this.b)).append(dji.pilot.usercenter.protocol.d.H);
            stringBuilder.append("descId[").append(String.valueOf(this.d)).append(dji.pilot.usercenter.protocol.d.H);
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
        NONE
    }

    public enum e {
        ADD,
        REMOVE
    }

    public enum f {
        INSERT,
        REMOVE
    }

    private final class g extends LayoutAnimationController {
        final /* synthetic */ DJIErrorPopViewCompat a;
        private long b = 0;
        private long c = 0;

        public g(DJIErrorPopViewCompat dJIErrorPopViewCompat, Animation animation) {
            this.a = dJIErrorPopViewCompat;
            super(animation, 0.0f);
            this.c = dJIErrorPopViewCompat.n.getDuration();
        }

        public void setAnimation(Animation animation) {
        }

        public Animation getAnimation() {
            return super.getAnimation();
        }

        public void start() {
            this.b = AnimationUtils.currentAnimationTimeMillis();
        }

        public boolean isDone() {
            return AnimationUtils.currentAnimationTimeMillis() > this.b + this.c;
        }

        protected long getDelayForView(View view) {
            int indexOfChild = this.a.indexOfChild(view);
            if (indexOfChild < this.a.w) {
                super.setAnimation(this.a.m);
            } else if (indexOfChild == this.a.w) {
                super.setAnimation(this.a.n);
            } else if (indexOfChild == DJIErrorPopViewCompat.f.length - 1) {
                super.setAnimation(this.a.q);
                view.setVisibility(4);
                this.a.k = this.a.k == 0 ? 0 : this.a.k - 1;
            } else {
                super.setAnimation(this.a.o);
            }
            if (this.a.k - 1 == indexOfChild) {
                this.a.w = this.a.b();
            }
            return 0;
        }

        protected int getTransformedIndex(AnimationParameters animationParameters) {
            return super.getTransformedIndex(animationParameters);
        }
    }

    private static final class h extends Handler {
        private final WeakReference<DJIErrorPopViewCompat> a;

        public h(DJIErrorPopViewCompat dJIErrorPopViewCompat) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIErrorPopViewCompat);
        }

        public void handleMessage(Message message) {
            DJIErrorPopViewCompat dJIErrorPopViewCompat = (DJIErrorPopViewCompat) this.a.get();
            if (dJIErrorPopViewCompat != null) {
                switch (message.what) {
                    case 4096:
                        dJIErrorPopViewCompat.a(message.arg1);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIErrorPopViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
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
        this.i = new OnClickListener(this) {
            final /* synthetic */ DJIErrorPopViewCompat a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (view.getId() == R.id.fpv_error_pop_item_close_img) {
                    this.a.a(view.getTag());
                }
            }
        };
        this.m = AnimationUtils.loadAnimation(context, R.anim.error_no_action);
        if (this.x == 0) {
            this.n = AnimationUtils.loadAnimation(context, R.anim.slide_right_in);
            this.o = AnimationUtils.loadAnimation(context, R.anim.slide_top_in);
            this.p = AnimationUtils.loadAnimation(context, R.anim.error_disappear);
            this.q = AnimationUtils.loadAnimation(context, R.anim.error_last_disappear);
        } else if (this.x == 1) {
            this.n = AnimationUtils.loadAnimation(context, R.anim.slide_left_in);
            this.o = AnimationUtils.loadAnimation(context, R.anim.slide_top_in);
            this.p = AnimationUtils.loadAnimation(context, R.anim.error_disappear);
            this.q = AnimationUtils.loadAnimation(context, R.anim.error_last_disappear);
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
        aVar.b = (DJIImageView) aVar.a.findViewById(R.id.fpv_error_pop_item_icon);
        aVar.c = (DJITextView) aVar.a.findViewById(R.id.fpv_error_pop_item_title_tv);
        aVar.d = (DJITextView) aVar.a.findViewById(R.id.fpv_error_pop_item_desc_tv);
        aVar.e = (DJIImageView) aVar.a.findViewById(R.id.fpv_error_pop_item_close_img);
        aVar.e.setOnClickListener(this.i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            for (int i = 0; i < f.length; i++) {
                this.j[i] = new a();
                this.j[i].a = (DJIRelativeLayout) findViewById(f[i]);
                a(this.j[i]);
            }
        }
    }

    public void dispatchOnCreate() {
        dji.thirdparty.a.c.a().a(this);
    }

    public void dispatchOnDestroy() {
        dji.thirdparty.a.c.a().d(this);
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
        if (bVar.f != c.STATIC) {
            return false;
        }
        int length = f.length;
        int i = 0;
        while (i < length) {
            if (this.j[i].a() && this.j[i].g.equals(bVar) && this.j[i].a.isShown()) {
                return true;
            }
            i++;
        }
        return false;
    }

    private void c() {
        int length = f.length;
        for (int i = 0; i < length; i++) {
            this.j[i].a.clearAnimation();
        }
    }

    private void b(b bVar) {
        if (!a(bVar)) {
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
            DJILogHelper.getInstance().LOGD(c, "seqNum[" + i + dji.pilot.usercenter.protocol.d.H);
            this.j[this.w].f = i;
            this.j[this.w].g.a(bVar);
            this.j[this.w].b();
            startLayoutAnimation();
            if (bVar.f == c.AUTODISAPPEAR) {
                this.h.sendMessageDelayed(this.h.obtainMessage(4096, i, 0), 5000);
            }
            dji.thirdparty.a.c.a().e(e.ADD);
        }
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

    private void a(DJIRelativeLayout dJIRelativeLayout) {
        if (dJIRelativeLayout != null) {
            Animation animation = this.p;
            dJIRelativeLayout.hide();
            dJIRelativeLayout.startAnimation(animation);
            dji.thirdparty.a.c.a().e(e.REMOVE);
        }
    }

    private void e() {
        Context context = getContext();
        this.t = new AnimationListener(this) {
            final /* synthetic */ DJIErrorPopViewCompat a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.f();
            }
        };
        if (this.x == 0) {
            this.r = AnimationUtils.loadAnimation(context, R.anim.slide_right_out);
        } else if (this.x == 1) {
            this.r = AnimationUtils.loadAnimation(context, R.anim.slide_left_out);
        }
        this.r.setFillBefore(true);
        this.r.setAnimationListener(this.t);
        for (int i = 1; i < this.s.length; i++) {
            this.s[i] = AnimationUtils.loadAnimation(context, R.anim.slide_top_out);
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
            dji.thirdparty.a.c.a().e(e.REMOVE);
        }
    }

    public void onEventMainThread(b bVar) {
        if (bVar == null) {
            return;
        }
        if (bVar.g == f.INSERT) {
            if (!l.a(bVar.e) || !l.a(bVar.c) || bVar.d > 0 || bVar.b > 0) {
                b(bVar);
                if (bVar.a == d.WARNING) {
                    a.getInstance().b(bVar);
                } else if (bVar.a == d.NOTIFY) {
                    a.getInstance().a(bVar);
                }
            }
        } else if (bVar.g == f.REMOVE) {
            c(bVar);
        }
    }
}
