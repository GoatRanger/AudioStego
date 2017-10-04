package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.gs.e.b;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIHomeLockStatusStageView extends DJIRelativeLayout implements a {
    private DJITextView a = null;
    private OnClickListener b = new OnClickListener(this) {
        final /* synthetic */ DJIHomeLockStatusStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ajx:
                    this.a.d = true;
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_exit_current_mession_view, 26, false);
                    return;
                case R.id.ajy:
                    this.a.d = true;
                    dji.pilot.groundStation.a.a.getInstance(null).a();
                    ((DJIStageView) this.a.getParent()).stop();
                    return;
                default:
                    return;
            }
        }
    };
    private final Handler c = new Handler();
    private boolean d = false;
    private final Runnable e = new Runnable(this) {
        final /* synthetic */ DJIHomeLockStatusStageView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.d) {
                DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
                double latitude = instance.getLatitude();
                double longitude = instance.getLongitude();
                if (!(latitude == 0.0d && longitude == 0.0d)) {
                    b a = dji.gs.utils.a.a(new b(latitude, longitude));
                    b f = dji.pilot.groundStation.a.a.getInstance(null).i().l().f();
                    if (f != null) {
                        float atan2 = (float) ((Math.atan2((a.c - f.c) * 10000.0d, (-(a.b - f.b)) * 20000.0d) * 180.0d) / 3.141592653589793d);
                        if (atan2 < 0.0f) {
                            atan2 = (float) (((double) atan2) + 6.283185307179586d);
                        }
                        longitude = dji.gs.utils.a.a(a.b, a.c, f.b, f.c);
                        if (DJIGenSettingDataManager.getInstance().v() == 0) {
                            this.a.a.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a((float) longitude))}));
                        } else {
                            this.a.a.setText(String.format("%.1fM", new Object[]{Double.valueOf(longitude)}));
                        }
                    } else {
                        this.a.a.setText("N/A");
                    }
                }
                this.a.c.postDelayed(this.a.e, 500);
            }
        }
    };

    public DJIHomeLockStatusStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.d = true;
    }

    public void dispatchOnResume() {
        this.d = false;
        this.c.post(this.e);
    }

    public void dispatchOnPause() {
        this.d = true;
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            findViewById(R.id.ajy).setOnClickListener(this.b);
            findViewById(R.id.ajx).setOnClickListener(this.b);
            this.a = (DJITextView) findViewById(R.id.ak1);
        }
    }
}
