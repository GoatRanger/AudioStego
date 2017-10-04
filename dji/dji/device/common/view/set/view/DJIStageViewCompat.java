package dji.device.common.view.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;
import dji.pilot.fpv.R;
import java.util.ArrayList;

public class DJIStageViewCompat extends ViewAnimator {
    private final ArrayList<f> a;
    private int b;
    private Context c;
    private e d;
    private d e;
    private Animation f;
    private Animation g;
    private Animation h;
    private Animation i;
    protected LayoutInflater r;

    public interface e {
        void a(int i);

        void a(int i, int i2, int i3);
    }

    public interface a {
        void dispatchOnPause();

        void dispatchOnResume();

        void dispatchOnStart();

        void dispatchOnStop();

        View getView();
    }

    public interface b {
        void a();

        void b();

        void c();

        void d();
    }

    public interface c extends a {
        boolean a();

        boolean b();
    }

    public interface d {
        boolean a();
    }

    private static final class f {
        public int a;
        public int b;
        public a c;

        private f() {
            this.a = 0;
            this.b = 0;
            this.c = null;
        }
    }

    public DJIStageViewCompat(Context context) {
        this(context, null);
    }

    public DJIStageViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ArrayList(4);
        this.b = 0;
        this.c = null;
        this.r = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        if (!isInEditMode()) {
            this.c = context;
            this.r = LayoutInflater.from(this.c);
            a();
        }
    }

    public void stop() {
        if (this.d != null) {
            this.d.a(0);
        }
    }

    public void setOnStageChangeListener(e eVar) {
        this.d = eVar;
    }

    public void setOnOptListener(d dVar) {
        this.e = dVar;
    }

    protected a a(int i, int i2) {
        return (a) this.r.inflate(i, null);
    }

    public boolean canGoBack() {
        return this.b > 1;
    }

    public boolean handleGoBack() {
        f fVar = null;
        if (this.b >= 1) {
            fVar = (f) this.a.get(this.b - 1);
        }
        if (fVar == null || !(fVar.c instanceof c)) {
            return false;
        }
        return ((c) fVar.c).a();
    }

    public boolean handleClose() {
        f fVar = null;
        if (this.b >= 1) {
            fVar = (f) this.a.get(this.b - 1);
        }
        if (fVar == null || !(fVar.c instanceof c)) {
            return false;
        }
        return ((c) fVar.c).b();
    }

    public boolean closeViews() {
        if (this.e != null) {
            return this.e.a();
        }
        dispatchOnStop(true);
        return true;
    }

    public int getCurrentStage() {
        return this.b;
    }

    public int getCurrentStageTitleResId() {
        if (this.b >= 1) {
            return ((f) this.a.get(this.b - 1)).b;
        }
        return 0;
    }

    public a getCurrentStageView() {
        if (this.b >= 1) {
            return ((f) this.a.get(this.b - 1)).c;
        }
        return null;
    }

    public boolean isEmpty() {
        return this.b == 0;
    }

    public void changeFirstStageView(int i, int i2, boolean z) {
        if (!this.a.isEmpty() && ((f) this.a.get(0)).a != i) {
            f fVar;
            for (int i3 = this.b - 1; i3 >= 0; i3--) {
                fVar = (f) this.a.get(i3);
                if (z) {
                    if (i3 == this.b - 1) {
                        fVar.c.dispatchOnPause();
                    }
                    fVar.c.dispatchOnStop();
                }
                removeView(fVar.c.getView());
            }
            this.a.clear();
            this.b = 0;
            fVar = new f();
            fVar.c = a(i, this.b + 1);
            fVar.a = i;
            fVar.b = i2;
            this.a.add(fVar);
            addView(fVar.c.getView(), this.b);
            setDisplayedChild(this.b);
            this.b++;
            if (z) {
                fVar.c.dispatchOnStart();
                fVar.c.dispatchOnResume();
                a(fVar.b);
            }
        }
    }

    public a createStageView(int i, int i2, boolean z) {
        return createStageView(i, i2, z, -2, -2);
    }

    public a createStageView(int i, int i2, boolean z, int i3, int i4) {
        f fVar;
        if (this.a.size() > this.b) {
            fVar = (f) this.a.get(this.b);
        } else {
            fVar = null;
        }
        if (this.b >= 1) {
            f fVar2 = (f) this.a.get(this.b - 1);
            if (fVar2.a == i && i2 == fVar2.b) {
                return fVar2.c;
            }
            fVar2.c.dispatchOnPause();
        }
        if (fVar == null) {
            fVar = new f();
            fVar.c = a(i, this.b + 1);
            fVar.a = i;
            fVar.b = i2;
            this.a.add(fVar);
            addView(fVar.c.getView(), this.b, new LayoutParams(i3, i4));
        } else if (fVar.a != i) {
            removeView(fVar.c.getView());
            fVar.c = a(i, this.b + 1);
            fVar.a = i;
            fVar.b = i2;
            addView(fVar.c.getView(), this.b, new LayoutParams(i3, i4));
        } else {
            fVar.b = i2;
        }
        a aVar = fVar.c;
        if (this.b >= 1) {
            aVar.dispatchOnStart();
            aVar.dispatchOnResume();
        }
        a(z, true);
        setDisplayedChild(this.b);
        this.b++;
        a(fVar.b);
        return aVar;
    }

    public a destoryStageView(boolean z) {
        if (this.b > 1) {
            f fVar;
            if (this.a.size() > this.b - 1) {
                fVar = (f) this.a.get(this.b - 1);
            } else {
                fVar = null;
            }
            if (fVar != null) {
                a aVar = fVar.c;
                aVar.dispatchOnPause();
                aVar.dispatchOnStop();
                a(z, false);
                this.b--;
                setDisplayedChild(this.b - 1);
                fVar = (f) this.a.get(this.b - 1);
                fVar.c.dispatchOnResume();
                a(fVar.b);
                return aVar;
            }
        }
        return null;
    }

    public void dispatchOnStart(boolean z) {
        if (this.b >= 1) {
            f fVar = (f) this.a.get(this.b - 1);
            fVar.c.dispatchOnStart();
            fVar.c.dispatchOnResume();
        }
    }

    public void dispatchOnStop(boolean z) {
        if (this.b >= 1) {
            f fVar;
            for (int i = this.b - 1; i >= 1; i--) {
                fVar = (f) this.a.get(i);
                fVar.c.dispatchOnPause();
                fVar.c.dispatchOnStop();
            }
            fVar = (f) this.a.get(0);
            a(z, false);
            this.b = 1;
            setDisplayedChild(0);
            fVar.c.dispatchOnPause();
            fVar.c.dispatchOnStop();
            a(fVar.b);
        }
    }

    public void clearAllStage() {
        if (this.b >= 1) {
            a(false, false);
            this.b = 1;
            setDisplayedChild(0);
        }
    }

    public void stopAllStage() {
        if (this.b >= 1) {
            for (int i = this.b - 1; i >= 0; i--) {
                f fVar = (f) this.a.get(i);
                if (i == this.b - 1) {
                    fVar.c.dispatchOnPause();
                }
                fVar.c.dispatchOnStop();
            }
        }
    }

    public void startAllStage() {
        if (this.b >= 1) {
            for (int i = 0; i <= this.b - 1; i++) {
                f fVar = (f) this.a.get(i);
                fVar.c.dispatchOnStart();
                if (i == this.b - 1) {
                    fVar.c.dispatchOnResume();
                }
            }
        }
    }

    private void a(boolean z, boolean z2) {
        if (!z) {
            setInAnimation(null);
            setOutAnimation(null);
        } else if (z2) {
            setInAnimation(this.h);
            setOutAnimation(this.g);
        } else {
            setInAnimation(this.f);
            setOutAnimation(this.i);
        }
    }

    private void a() {
        this.f = AnimationUtils.loadAnimation(this.c, R.anim.slide_left_in);
        this.g = AnimationUtils.loadAnimation(this.c, R.anim.slide_left_out);
        this.h = AnimationUtils.loadAnimation(this.c, R.anim.slide_right_in);
        this.i = AnimationUtils.loadAnimation(this.c, R.anim.slide_right_out);
    }

    private void a(int i) {
        if (this.d != null) {
            this.d.a(this.b, i, 0);
        }
    }
}
