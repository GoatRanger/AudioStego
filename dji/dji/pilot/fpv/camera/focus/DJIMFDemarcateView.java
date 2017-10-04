package dji.pilot.fpv.camera.focus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraSetFocusWindow;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.publics.widget.DJIRoundLinearLayout;
import dji.pilot.usercenter.protocol.d;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJIMFDemarcateView extends DJIRoundLinearLayout {
    private DJITextView a = null;
    private DJITextView b = null;
    private DJITextView c = null;
    private DJITextView d = null;
    private OnClickListener e = null;
    private FuselageFocusMode f = FuselageFocusMode.OTHER;
    private int g = Integer.MAX_VALUE;
    private int h = 0;

    public enum a {
        NEEDSHOW,
        SHOW,
        HIDE
    }

    public DJIMFDemarcateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void showView() {
        if (getVisibility() != 0) {
            setVisibility(0);
            dji.pilot.fpv.camera.more.a.getInstance().a();
            c.a().e(a.SHOW);
            onEventMainThread(DataCameraGetPushShotInfo.getInstance());
            c.a().a(this);
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            setVisibility(8);
            c.a().e(a.HIDE);
            c.a().d(this);
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
        if (fuselageFocusMode != this.f) {
            this.f = fuselageFocusMode;
        }
        dji.pilot.fpv.camera.more.a.a("****cur[" + dataCameraGetPushShotInfo.getShotFocusCurStroke() + "," + dataCameraGetPushShotInfo.getShotFocusMaxStroke() + d.H);
        if (fuselageFocusMode == FuselageFocusMode.OneAuto || FuselageFocusMode.ContinuousAuto == fuselageFocusMode) {
            int focusStatus = dataCameraGetPushShotInfo.getFocusStatus();
            if (focusStatus != this.g) {
                if (this.g == 1 && focusStatus == 2 && this.h != 0) {
                    if (((float) dataCameraGetPushShotInfo.getShotFocusCurStroke()) > ((float) dataCameraGetPushShotInfo.getShotFocusMaxStroke()) * lecho.lib.hellocharts.d.c.a) {
                        this.h = 1;
                        this.c.setEnabled(false);
                        b bVar = new b();
                        bVar.b = R.string.fpv_demarcate_bad_tip;
                        bVar.a = DJIErrorPopView.d.b;
                        bVar.g = f.a;
                        c.a().e(bVar);
                    } else {
                        this.h = 2;
                        this.c.setEnabled(true);
                    }
                }
                this.g = focusStatus;
            }
        }
    }

    private void a() {
        if (this.h == 0) {
            this.b.setText(R.string.camera_mf_demarcate_tip);
            this.c.setText(R.string.camera_demarcate_start);
            this.d.setText(R.string.camera_demarcate_later);
            this.c.setEnabled(true);
        } else if (1 == this.h) {
            this.b.setText(R.string.camera_mf_demarcate_step2_tip);
            this.c.setText(R.string.app_enter);
            this.d.setText(R.string.app_cancel);
            this.c.setEnabled(false);
        } else if (2 == this.h) {
            this.c.setEnabled(true);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.e = new OnClickListener(this) {
                final /* synthetic */ DJIMFDemarcateView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (view == this.a.c) {
                        if (this.a.h == 0) {
                            if (this.a.f == FuselageFocusMode.Manual || this.a.f == FuselageFocusMode.ManualFine) {
                                new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.OneAuto.value()).start(null);
                            }
                            DataCameraSetFocusWindow.getInstance().a(2).b(2).c(1).start(null);
                            this.a.h = 1;
                            this.a.a();
                        } else if (2 == this.a.h) {
                            this.a.hideView();
                            dji.pilot.fpv.camera.more.a.getInstance().d();
                            this.a.h = 0;
                            this.a.a();
                            this.a.g = Integer.MAX_VALUE;
                        }
                    } else if (view == this.a.d) {
                        dji.pilot.fpv.camera.more.a.getInstance().c();
                        this.a.hideView();
                        this.a.h = 0;
                        this.a.a();
                        this.a.g = Integer.MAX_VALUE;
                    }
                }
            };
            this.a = (DJITextView) findViewById(R.id.jk);
            this.b = (DJITextView) findViewById(R.id.jl);
            this.c = (DJITextView) findViewById(R.id.jm);
            this.d = (DJITextView) findViewById(R.id.jn);
            this.c.setOnClickListener(this.e);
            this.d.setOnClickListener(this.e);
        }
    }
}
