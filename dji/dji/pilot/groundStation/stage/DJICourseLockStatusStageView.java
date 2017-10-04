package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.view.DJIGSCourseLockView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJICourseLockStatusStageView extends DJIRelativeLayout implements a {
    private DJIGSCourseLockView a = null;
    private OnClickListener b = new OnClickListener(this) {
        final /* synthetic */ DJICourseLockStatusStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.aiw:
                    this.a.d = true;
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_exit_current_mession_view, 26, false);
                    return;
                case R.id.aix:
                    dji.pilot.groundStation.a.a.getInstance(null).a();
                    ((DJIStageView) this.a.getParent()).stop();
                    this.a.d = true;
                    return;
                case R.id.aj0:
                    DataFlycFunctionControl instance = DataFlycFunctionControl.getInstance();
                    instance.setCommand(FLYC_COMMAND.HOMEPOINT_LOC);
                    instance.start(new d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            b bVar = new b();
                            bVar.a = DJIErrorPopView.d.a;
                            bVar.f = c.a;
                            bVar.b = R.string.gs_course_lock_reset_success;
                            dji.thirdparty.a.c.a().e(bVar);
                            dji.pilot.groundStation.a.a.getInstance(null).a(((float) DataOsdGetPushCommon.getInstance().getYaw()) / 10.0f);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            b bVar = new b();
                            bVar.a = DJIErrorPopView.d.b;
                            bVar.f = c.a;
                            bVar.b = R.string.gs_course_lock_reset_failed;
                            dji.thirdparty.a.c.a().e(bVar);
                        }
                    });
                    return;
                default:
                    return;
            }
        }
    };
    private final Handler c = new Handler();
    private boolean d = false;
    private final Runnable e = new Runnable(this) {
        final /* synthetic */ DJICourseLockStatusStageView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.d) {
                DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
                this.a.a.updateRotate((((float) instance.getYaw()) / 10.0f) - this.a.a.getPhoneAzimuth());
                if (i.getInstance().c() != ProductType.A2) {
                    this.a.a.lock(true, (float) (((double) ((((float) DataOsdGetPushHome.getInstance().getCourseLockAngle()) * 180.0f) / 1000.0f)) / 3.141592653589793d));
                } else {
                    this.a.a.lock(true, dji.pilot.groundStation.a.a.getInstance(null).C());
                }
                this.a.c.postDelayed(this.a.e, 200);
            }
        }
    };

    public DJICourseLockStatusStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.d = true;
    }

    public void dispatchOnResume() {
        this.d = false;
        this.a.lock(true, dji.pilot.groundStation.a.a.getInstance(null).C());
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
            findViewById(R.id.aix).setOnClickListener(this.b);
            findViewById(R.id.aiw).setOnClickListener(this.b);
            findViewById(R.id.aj0).setOnClickListener(this.b);
            this.a = (DJIGSCourseLockView) findViewById(R.id.aj1);
        }
    }
}
