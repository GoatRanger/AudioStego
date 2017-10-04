package dji.pilot.fpv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;
import dji.pilot.R;
import java.util.ArrayList;

public class DJIStageView extends ViewAnimator {
    protected LayoutInflater a;
    private final ArrayList<f> b;
    private int c;
    private Context d;
    private e e;
    private d f;
    private Animation g;
    private Animation h;
    private Animation i;
    private Animation j;

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
        void d();

        void e();

        void f();

        void g();
    }

    public interface d {
        boolean b();
    }

    public interface c extends a {
        boolean a();

        boolean b();
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

    public DJIStageView(Context context) {
        this(context, null);
    }

    public DJIStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new ArrayList(4);
        this.c = 0;
        this.d = null;
        this.a = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        if (!isInEditMode()) {
            this.d = context;
            this.a = LayoutInflater.from(this.d);
            a();
        }
    }

    public void stop() {
        if (this.e != null) {
            this.e.a(0);
        }
    }

    public void condictionStop(int i) {
        if (this.e != null) {
            this.e.a(i);
        }
    }

    public void setOnStageChangeListener(e eVar) {
        this.e = eVar;
    }

    public void setOnOptListener(d dVar) {
        this.f = dVar;
    }

    protected a a(int i, int i2) {
        return (a) this.a.inflate(i, null);
    }

    public boolean canGoBack() {
        return this.c > 1;
    }

    public boolean handleGoBack() {
        f fVar = null;
        if (this.c >= 1) {
            fVar = (f) this.b.get(this.c - 1);
        }
        if (fVar == null || !(fVar.c instanceof c)) {
            return false;
        }
        return ((c) fVar.c).a();
    }

    public boolean handleClose() {
        f fVar = null;
        if (this.c >= 1) {
            fVar = (f) this.b.get(this.c - 1);
        }
        if (fVar == null || !(fVar.c instanceof c)) {
            return false;
        }
        return ((c) fVar.c).b();
    }

    public boolean closeViews() {
        if (this.f != null) {
            return this.f.b();
        }
        dispatchOnStop(true);
        return true;
    }

    public int getCurrentStage() {
        return this.c;
    }

    public int getCurrentStageTitleResId() {
        if (this.c >= 1) {
            return ((f) this.b.get(this.c - 1)).b;
        }
        return 0;
    }

    public a getCurrentStageView() {
        if (this.c >= 1) {
            return ((f) this.b.get(this.c - 1)).c;
        }
        return null;
    }

    public boolean isEmpty() {
        return this.c == 0;
    }

    public void changeFirstStageView(int i, int i2, boolean z) {
        if (!this.b.isEmpty() && ((f) this.b.get(0)).a != i) {
            f fVar;
            for (int i3 = this.c - 1; i3 >= 0; i3--) {
                fVar = (f) this.b.get(i3);
                if (z) {
                    if (i3 == this.c - 1) {
                        fVar.c.dispatchOnPause();
                    }
                    fVar.c.dispatchOnStop();
                }
                removeView(fVar.c.getView());
            }
            this.b.clear();
            this.c = 0;
            fVar = new f();
            fVar.c = a(i, this.c + 1);
            fVar.a = i;
            fVar.b = i2;
            this.b.add(fVar);
            addView(fVar.c.getView(), this.c);
            setDisplayedChild(this.c);
            this.c++;
            if (z) {
                fVar.c.dispatchOnStart();
                fVar.c.dispatchOnResume();
                a(fVar.b);
            }
        }
    }

    public a createStageView(int i, int i2, boolean z) {
        f fVar;
        if (this.b.size() > this.c) {
            fVar = (f) this.b.get(this.c);
        } else {
            fVar = null;
        }
        if (this.c >= 1) {
            f fVar2 = (f) this.b.get(this.c - 1);
            if (fVar2.a == i && i2 == fVar2.b) {
                return fVar2.c;
            }
            fVar2.c.dispatchOnPause();
        }
        if (fVar == null) {
            fVar = new f();
            fVar.c = a(i, this.c + 1);
            fVar.a = i;
            fVar.b = i2;
            this.b.add(fVar);
            addView(fVar.c.getView(), this.c);
        } else if (fVar.a != i) {
            removeView(fVar.c.getView());
            fVar.c = a(i, this.c + 1);
            fVar.a = i;
            fVar.b = i2;
            addView(fVar.c.getView(), this.c);
        } else {
            fVar.b = i2;
        }
        a aVar = fVar.c;
        if (this.c >= 1) {
            aVar.dispatchOnStart();
            aVar.dispatchOnResume();
        }
        a(z, true);
        setDisplayedChild(this.c);
        this.c++;
        a(fVar.b);
        return aVar;
    }

    public a createStageViewWithAnim(int i, int i2, boolean z, int i3) {
        f fVar;
        if (this.b.size() > this.c) {
            fVar = (f) this.b.get(this.c);
        } else {
            fVar = null;
        }
        if (this.c >= 1) {
            f fVar2 = (f) this.b.get(this.c - 1);
            if (fVar2.a == i && i2 == fVar2.b) {
                return fVar2.c;
            }
            fVar2.c.dispatchOnPause();
        }
        if (fVar == null) {
            fVar = new f();
            fVar.c = a(i, this.c + 1);
            fVar.a = i;
            fVar.b = i2;
            this.b.add(fVar);
            addView(fVar.c.getView(), this.c);
        } else if (fVar.a != i) {
            removeView(fVar.c.getView());
            fVar.c = a(i, this.c + 1);
            fVar.a = i;
            fVar.b = i2;
            addView(fVar.c.getView(), this.c);
        } else {
            fVar.b = i2;
        }
        a aVar = fVar.c;
        if (this.c >= 1) {
            aVar.dispatchOnStart();
            aVar.dispatchOnResume();
        }
        if (i3 > 0) {
            a(z, true);
        } else {
            a(z, false);
        }
        setDisplayedChild(this.c);
        this.c++;
        a(fVar.b);
        return aVar;
    }

    public a destoryStageView(boolean z) {
        if (this.c > 1) {
            f fVar;
            if (this.b.size() > this.c - 1) {
                fVar = (f) this.b.get(this.c - 1);
            } else {
                fVar = null;
            }
            if (fVar != null) {
                a aVar = fVar.c;
                aVar.dispatchOnPause();
                aVar.dispatchOnStop();
                a(z, false);
                this.c--;
                setDisplayedChild(this.c - 1);
                fVar = (f) this.b.get(this.c - 1);
                fVar.c.dispatchOnResume();
                a(fVar.b);
                return aVar;
            }
        }
        return null;
    }

    public void dispatchOnStart(boolean z) {
        if (this.c >= 1) {
            f fVar = (f) this.b.get(this.c - 1);
            fVar.c.dispatchOnStart();
            fVar.c.dispatchOnResume();
        }
    }

    public void dispatchOnStop(boolean z) {
        if (this.c >= 1) {
            f fVar;
            for (int i = this.c - 1; i >= 1; i--) {
                fVar = (f) this.b.get(i);
                fVar.c.dispatchOnPause();
                fVar.c.dispatchOnStop();
            }
            fVar = (f) this.b.get(0);
            a(z, false);
            this.c = 1;
            setDisplayedChild(0);
            fVar.c.dispatchOnPause();
            fVar.c.dispatchOnStop();
            a(fVar.b);
        }
    }

    public void clearAllStage() {
        if (this.c >= 1) {
            a(false, false);
            this.c = 1;
            setDisplayedChild(0);
        }
    }

    public void stopAllStage() {
        if (this.c >= 1) {
            for (int i = this.c - 1; i >= 0; i--) {
                f fVar = (f) this.b.get(i);
                if (i == this.c - 1) {
                    fVar.c.dispatchOnPause();
                }
                fVar.c.dispatchOnStop();
            }
        }
    }

    public void startAllStage() {
        if (this.c >= 1) {
            for (int i = 0; i <= this.c - 1; i++) {
                f fVar = (f) this.b.get(i);
                fVar.c.dispatchOnStart();
                if (i == this.c - 1) {
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
            setInAnimation(this.i);
            setOutAnimation(this.h);
        } else {
            setInAnimation(this.g);
            setOutAnimation(this.j);
        }
    }

    private void a() {
        this.g = AnimationUtils.loadAnimation(this.d, R.anim.bi);
        this.h = AnimationUtils.loadAnimation(this.d, R.anim.bk);
        this.i = AnimationUtils.loadAnimation(this.d, R.anim.bp);
        this.j = AnimationUtils.loadAnimation(this.d, R.anim.bq);
    }

    private void a(int i) {
        if (this.e != null) {
            this.e.a(this.c, i, 0);
        }
    }
}
