package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.gs.e.b;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.forbid.FlyForbidProtocol;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.view.DJIGSFollowMeView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIFollowMeStatusStageView extends DJIRelativeLayout implements a {
    private DJITextView a = null;
    private DJITextView b = null;
    private DJIGSFollowMeView c = null;
    private DJITextView d = null;
    private OnClickListener e = new OnClickListener(this) {
        final /* synthetic */ DJIFollowMeStatusStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.aji:
                    this.a.g = true;
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_exit_current_mession_view, 26, false);
                    return;
                case R.id.ajj:
                    this.a.g = true;
                    dji.pilot.groundStation.a.a.getInstance(null).a();
                    ((DJIStageView) this.a.getParent()).stop();
                    return;
                default:
                    return;
            }
        }
    };
    private final Handler f = new Handler();
    private boolean g = false;
    private final Runnable h = new Runnable(this) {
        final /* synthetic */ DJIFollowMeStatusStageView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.g) {
                float f;
                DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
                b k = k.k();
                if (k != null) {
                    double latitude = instance.getLatitude();
                    double longitude = instance.getLongitude();
                    if (latitude == 0.0d && longitude == 0.0d) {
                        f = 0.0f;
                    } else {
                        b bVar = new b(latitude, longitude);
                        f = (float) ((Math.atan2((bVar.c - k.c) * 1000.0d, (-(bVar.b - k.b)) * FlyForbidProtocol.UNLOCK_RADIUS) * 180.0d) / 3.141592653589793d);
                        if (f < 0.0f) {
                            f += 360.0f;
                        }
                        DJILogHelper.getInstance().LOGE("gs", String.format("angle=%.1f", new Object[]{Float.valueOf(f)}), false, true);
                    }
                    this.a.b.setText(this.a.getContext().getString(R.string.gs_follow_me_phone_gps_strong));
                } else {
                    this.a.b.setText(this.a.getContext().getString(R.string.gs_follow_me_phone_gps_weak));
                    f = 0.0f;
                }
                float yaw = ((float) instance.getYaw()) / 10.0f;
                float w = (float) dji.pilot.groundStation.a.a.getInstance(null).w();
                this.a.c.updateRotate(f + w, yaw - w, (yaw + (((float) DataGimbalGetPushParams.getInstance().getYawAngle()) / 10.0f)) - w);
                f = ((float) DataOsdGetPushCommon.getInstance().getHeight()) / 10.0f;
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    this.a.a.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a(f))}));
                } else {
                    this.a.a.setText(String.format("%.1fM", new Object[]{Float.valueOf(f)}));
                }
                this.a.f.postDelayed(this.a.h, 500);
            }
        }
    };

    public DJIFollowMeStatusStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.g = true;
    }

    public void dispatchOnResume() {
        this.g = false;
        this.f.post(this.h);
    }

    public void dispatchOnPause() {
        this.g = true;
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            findViewById(R.id.ajj).setOnClickListener(this.e);
            findViewById(R.id.aji).setOnClickListener(this.e);
            this.c = (DJIGSFollowMeView) findViewById(R.id.ajl);
            this.a = (DJITextView) findViewById(R.id.ajn);
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.a.setText("0FT");
            } else {
                this.a.setText("0M");
            }
            this.b = (DJITextView) findViewById(R.id.ajm);
            this.b.setText(getContext().getString(R.string.gs_follow_me_phone_gps_strong));
            this.d = (DJITextView) findViewById(R.id.ajo);
            ProductType c = i.getInstance().c();
            if (c == ProductType.litchiS || c == ProductType.litchiX) {
                this.d.setText(String.format(getContext().getString(R.string.gs_follow_me_status_limits_desc), new Object[]{Integer.valueOf(0), Integer.valueOf(200), Integer.valueOf(3), Integer.valueOf(120)}));
                return;
            }
            this.d.setText(String.format(getContext().getString(R.string.gs_follow_me_status_limits_desc), new Object[]{Integer.valueOf(0), Integer.valueOf(200), Integer.valueOf(5), Integer.valueOf(120)}));
        }
    }
}
