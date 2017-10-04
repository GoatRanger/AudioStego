package dji.pilot.fpv.leftmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import dji.gs.views.EventView.a;
import dji.pilot.R;
import dji.pilot.fpv.control.q;
import dji.publics.DJIUI.DJILinearLayout;
import dji.thirdparty.a.c;

public class DJILeftBar extends DJILinearLayout {
    private DJILeftMenu a = null;
    private DJILeftOptView b = null;
    private Animation c = null;
    private Animation d = null;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b = new int[a.values().length];

        static {
            try {
                b[a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            a = new int[q.a.values().length];
            try {
                a[q.a.SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[q.a.LARGE.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[q.a.SWITCH.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public DJILeftBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void hideMenu(boolean z) {
        if (getVisibility() == 8) {
            return;
        }
        if (z) {
            setVisibility(8);
            startAnimation(this.d);
            return;
        }
        this.a.hideMenu(z);
    }

    public void showMenu() {
        if (getVisibility() != 0) {
            setVisibility(0);
            startAnimation(this.c);
        }
    }

    public boolean isShowingMenu() {
        return this.a.isShowing();
    }

    public void dispatchOnCreate() {
        this.a.dispatchOnCreate();
    }

    public void dispatchOnDestroy() {
        this.a.dispatchOnDestroy();
    }

    public void switchGimbalMode() {
        this.a.switchGimbalMode();
    }

    public void setMutexView(ViewGroup viewGroup) {
        this.a.setMutexView(viewGroup);
    }

    public void onEventMainThread(q.a aVar) {
        switch (aVar) {
            case SMALL:
                showMenu();
                return;
            case LARGE:
                if (!isShown()) {
                    showMenu();
                    return;
                }
                return;
            case SWITCH:
                hideMenu(true);
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(a aVar) {
        switch (AnonymousClass1.b[aVar.ordinal()]) {
            case 1:
                hideMenu(false);
                return;
            default:
                return;
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJILeftMenu) findViewById(R.id.a3z);
            this.b = (DJILeftOptView) findViewById(R.id.a40);
            this.c = AnimationUtils.loadAnimation(getContext(), R.anim.bi);
            this.d = AnimationUtils.loadAnimation(getContext(), R.anim.bk);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    public void handleFMClicked() {
        this.a.handleFMClicked();
    }
}
