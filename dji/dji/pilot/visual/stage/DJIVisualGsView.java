package dji.pilot.visual.stage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.g;
import dji.pilot.visual.a.g$e;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIVisualGsView extends DJILinearLayout implements a, g {
    private final c a = c.getInstance();
    private DJIVisualTrackOptView b = null;
    private DJIVisualPointOptView c = null;
    private DJIVisualSelfieView d = null;
    private OnClickListener e = null;

    public DJIVisualGsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onEventMainThread(g$e dji_pilot_visual_a_g_e) {
        if (dji_pilot_visual_a_g_e == g$e.POINT_MODE) {
            b();
        } else if (dji_pilot_visual_a_g_e == g$e.TRACK_MODE) {
            a();
        }
    }

    private void a() {
        if (dji.pilot.fpv.flightmode.c.getInstance().a() == c$b.TRACK_SELFIE) {
            this.b.go();
            this.c.go();
            this.d.show();
            return;
        }
        this.b.show();
        this.c.go();
        this.d.go();
    }

    private void b() {
        this.b.go();
        this.c.show();
        this.d.go();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            c();
            this.b = (DJIVisualTrackOptView) findViewById(R.id.d9i);
            this.c = (DJIVisualPointOptView) findViewById(R.id.d9j);
            this.d = (DJIVisualSelfieView) findViewById(R.id.d9k);
            this.b.setHideClickListener(this.e);
            this.c.setHideClickListener(this.e);
            this.d.setHideClickListener(this.e);
        }
    }

    private void c() {
        this.e = new OnClickListener(this) {
            final /* synthetic */ DJIVisualGsView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.d_i == id || R.id.d9t == id || R.id.d_p == id) {
                    ((DJIStageView) this.a.getParent()).stop();
                }
            }
        };
    }

    public void dispatchOnStart() {
        this.b.dispatchOnStart();
        this.c.dispatchOnStart();
    }

    public void dispatchOnStop() {
        this.b.dispatchOnStop();
        this.c.dispatchOnStop();
    }

    public void dispatchOnResume() {
        onEventMainThread(this.a.f());
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        this.b.dispatchOnResume();
        this.c.dispatchOnResume();
    }

    public void dispatchOnPause() {
        this.b.dispatchOnPause();
        this.c.dispatchOnPause();
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
    }

    public View getView() {
        return this;
    }
}
