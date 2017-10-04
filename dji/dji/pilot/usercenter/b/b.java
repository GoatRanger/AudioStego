package dji.pilot.usercenter.b;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.Iterator;

public class b {
    private static int a = 0;
    private static c d;
    private ArrayList<b> b = new ArrayList();
    private int c = 0;

    public static class a {
        public int a;
        private View b;
        private AnimationSet c;
        private AnimationSet d;
        private d e;
        private d f;
        private long g;
        private long h;
        private Interpolator i = new LinearInterpolator();
        private Interpolator j = new e();

        public void a(Interpolator interpolator, Interpolator interpolator2) {
            this.i = interpolator;
            this.j = interpolator2;
        }

        public void a(AnimationSet animationSet, View view, long j, long j2) {
            this.b = view;
            this.c = animationSet;
            this.g = j;
            this.h = j2;
            this.c.setStartOffset(j);
            this.e = b.e(view);
            this.f = b.f(view);
        }

        public void a(AnimationSet animationSet) {
            this.d = animationSet;
        }

        private AnimationSet a(boolean z, long j) {
            Animation animation;
            if (z) {
                animation = this.c;
                animation.setInterpolator(this.j);
                animation.setStartOffset(this.h + j);
            } else {
                animation = this.d;
                animation.setInterpolator(this.i);
                if (this.a != 0) {
                    animation.setStartOffset(this.g + j);
                }
            }
            if (this.a != 0) {
                animation.setDuration((long) this.a);
            }
            animation.setAnimationListener(this.f);
            this.b.startAnimation(animation);
            return animation;
        }

        private AnimationSet b(boolean z, long j) {
            Animation animation;
            if (z) {
                animation = this.d;
                animation.setInterpolator(this.j);
                animation.setStartOffset(this.h + j);
            } else {
                animation = this.c;
                animation.setInterpolator(this.i);
                if (this.a != 0) {
                    animation.setStartOffset(this.g + j);
                }
            }
            if (this.a != 0) {
                animation.setDuration((long) this.a);
            }
            animation.setAnimationListener(this.e);
            this.b.startAnimation(animation);
            return animation;
        }
    }

    public static class b {
        protected ArrayList<a> a = new ArrayList();
        public long b;

        public a a(int i) {
            return (a) this.a.get(i);
        }

        public void a(a aVar) {
            this.a.add(aVar);
        }
    }

    public interface c {
        void a();

        void b();
    }

    static class d implements AnimationListener {
        private View a;
        private boolean b;

        public d(boolean z, View view) {
            this.b = z;
            this.a = view;
        }

        public void onAnimationStart(Animation animation) {
            if (b.a == 0 && b.d != null) {
                b.d.a();
            }
            b.g();
            if (this.b) {
                b.g(this.a);
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            b.h();
            if (b.a == 0 && b.d != null) {
                b.d.b();
            }
            if (!this.b) {
                b.h(this.a);
            }
        }
    }

    public static class e extends LinearInterpolator {
        public float getInterpolation(float f) {
            return Math.abs(super.getInterpolation(f) - 1.0f);
        }
    }

    static /* synthetic */ int g() {
        int i = a;
        a = i + 1;
        return i;
    }

    static /* synthetic */ int h() {
        int i = a;
        a = i - 1;
        return i;
    }

    private static d e(View view) {
        return new d(true, view);
    }

    private static d f(View view) {
        return new d(false, view);
    }

    public static void a() {
        a = 0;
        d = null;
    }

    public b(c cVar) {
        d = cVar;
    }

    public int b() {
        return this.c;
    }

    public void a(b bVar) {
        this.b.add(bVar);
    }

    public void a(int i) {
        c((this.c + 1) + i);
    }

    public void b(int i) {
        c((this.c - 1) - i);
    }

    public void c() {
        c(this.c - 1);
    }

    public void d() {
        c(this.c + 1);
    }

    private void c(int i) {
        int size = this.b.size();
        int i2 = size - 1;
        if (size <= 0 || i > i2 || i < 0) {
            d.b();
        } else if (this.c == i) {
            i();
            d.b();
        } else {
            int i3 = this.c;
            this.c = i;
            a(i3 < this.c, (b) this.b.get(i3), (b) this.b.get(this.c));
        }
    }

    private void i() {
        Iterator it = ((b) this.b.get(this.c)).a.iterator();
        while (it.hasNext()) {
            g(((a) it.next()).b);
        }
    }

    private void a(boolean z, b bVar, b bVar2) {
        Iterator it = bVar.a.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(!z, 0);
        }
        Iterator it2 = bVar2.a.iterator();
        while (it2.hasNext()) {
            ((a) it2.next()).b(!z, z ? bVar2.b : bVar.b);
        }
    }

    private static void g(View view) {
        if (!view.isShown()) {
            view.setVisibility(0);
        }
    }

    private static void h(View view) {
        if (view.isShown()) {
            view.setVisibility(8);
        }
    }
}
