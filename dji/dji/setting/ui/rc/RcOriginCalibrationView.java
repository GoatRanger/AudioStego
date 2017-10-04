package dji.setting.ui.rc;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import dji.apppublic.reflect.AppPublicReflect;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetCalibration;
import dji.midware.data.model.P3.DataRcSetCalibration.MODE;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DJICalProgressBar;
import dji.setting.ui.widget.DJIStickCircleView;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;

public class RcOriginCalibrationView extends DividerLinearLayout {
    private static final int c = 4096;
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 1024;
    private static final int g = 364;
    private static final int h = 1684;
    public DJIStickCircleView a;
    public DJIStickCircleView b;
    private DJICalProgressBar i = null;
    private TextView l = null;
    private TextView m = null;
    private b n = null;
    private a o = null;
    private OnClickListener p = null;

    private final class a {
        final /* synthetic */ RcOriginCalibrationView a;
        private DataRcSetCalibration b;
        private d c;
        private boolean d;
        private boolean e;
        private MODE f;

        private a(final RcOriginCalibrationView rcOriginCalibrationView) {
            this.a = rcOriginCalibrationView;
            this.b = null;
            this.c = null;
            this.d = false;
            this.e = false;
            this.f = MODE.f;
            this.b = DataRcSetCalibration.getInstance();
            this.c = new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    this.b.a.n.obtainMessage(4096, 0, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.a.n.obtainMessage(4096, 1, 0).sendToTarget();
                }
            };
        }

        private void a() {
            this.e = true;
            if (this.f == MODE.f) {
                this.b.a(MODE.a).start(this.c);
            }
        }

        private void b() {
            this.d = false;
            this.e = false;
            this.f = MODE.f;
        }

        private boolean c() {
            return this.d;
        }

        private MODE a(boolean z) {
            if (z && this.e) {
                MODE a = this.b.a();
                if (a == this.f || a == MODE.c) {
                    b(true);
                } else if (this.f == MODE.a && a == MODE.b) {
                    b(true);
                }
                this.f = a;
            }
            return this.f;
        }

        private void b(boolean z) {
            DJILogHelper.getInstance().LOGD("", "con[" + this.e + "]mode[" + this.f + dji.pilot.usercenter.protocol.d.H, false, true);
            if (this.e) {
                if (this.f == MODE.f) {
                    this.b.a(MODE.a).start(this.c);
                } else if (this.f == MODE.a) {
                    if (this.d) {
                        this.b.a(MODE.b).start(this.c);
                    } else {
                        this.d = true;
                    }
                } else if (this.f == MODE.b) {
                    this.d = false;
                    this.b.a(MODE.c).start(this.c);
                } else if (this.f == MODE.c) {
                    this.b.a(MODE.c).start(this.c);
                } else if (this.f == MODE.d) {
                    this.b.a(MODE.d).start(this.c);
                }
            } else if (!z) {
                Toast.makeText(this.a.getContext().getApplicationContext(), R.string.setting_ui_rc_tip_disconnect, 0).show();
            }
        }
    }

    private static final class b extends Handler {
        private final WeakReference<RcOriginCalibrationView> a;

        public b(RcOriginCalibrationView rcOriginCalibrationView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(rcOriginCalibrationView);
        }

        public void handleMessage(Message message) {
            RcOriginCalibrationView rcOriginCalibrationView = (RcOriginCalibrationView) this.a.get();
            if (rcOriginCalibrationView != null) {
                switch (message.what) {
                    case 4096:
                        if (message.arg1 == 0) {
                            rcOriginCalibrationView.o.a(true);
                            rcOriginCalibrationView.e();
                            return;
                        }
                        rcOriginCalibrationView.o.b(true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public RcOriginCalibrationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_rc_origin_calibration);
        if (!isInEditMode()) {
            b();
            this.a = (DJIStickCircleView) findViewById(R.id.fpv_rcsetting_cele_left_stick);
            this.b = (DJIStickCircleView) findViewById(R.id.fpv_rcsetting_cele_right_stick);
            this.i = (DJICalProgressBar) findViewById(R.id.fpv_rcsetting_cele_item_pgb);
            this.i.setValue(0, 0);
            this.l = (TextView) findViewById(R.id.fpv_rcsetting_cele_btn);
            this.l.setOnClickListener(this.p);
            this.m = (TextView) findViewById(R.id.fpv_rcsetting_cele_status_tv);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (ServiceManager.getInstance().isConnected()) {
                a(p.b, false);
            } else {
                a(p.a, false);
            }
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        this.o.b();
        d();
        c.a().d(this);
        super.onDetachedFromWindow();
        AppPublicReflect.DismissConnectWaning();
    }

    private void b() {
        this.n = new b(this);
        this.o = new a();
        this.p = new OnClickListener(this) {
            final /* synthetic */ RcOriginCalibrationView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (R.id.fpv_rcsetting_cele_btn == view.getId()) {
                    this.a.c();
                }
            }
        };
    }

    private void c() {
        e.a("FPV_RCSettings_MasterRCControlSettings_Button_RemoteControlCalibration");
        if (this.o.a(false) != MODE.a) {
            this.o.b(false);
        } else if (this.o.c()) {
            getContext();
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_rc_cele_tip, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ RcOriginCalibrationView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.o.b(false);
                    dialogInterface.dismiss();
                }
            });
        } else {
            this.m.setVisibility(0);
            this.l.setText(R.string.setting_ui_rc_start);
            this.m.setText(R.string.setting_ui_rc_middle_desc);
            this.o.b(false);
        }
    }

    private int a(int i) {
        int i2;
        if (i >= 1024) {
            i2 = ((i - 1024) * 100) / 660;
        } else {
            i2 = 0 - (((1024 - i) * 100) / 660);
        }
        if (i2 > 100) {
            return 100;
        }
        if (i2 < -100) {
            return -100;
        }
        return i2;
    }

    private void d() {
        a(0, 1024, 1024);
        a(1, 1024, 1024);
        c(1024);
    }

    private void a(int i, int i2, int i3) {
        DJIStickCircleView dJIStickCircleView;
        int i4;
        int i5 = 0;
        int a = a(i2);
        int a2 = a(i3);
        if (i == 0) {
            dJIStickCircleView = this.a;
        } else {
            dJIStickCircleView = this.b;
        }
        if (a2 < 0) {
            i4 = -a2;
        } else {
            i4 = 0;
        }
        int i6 = a > 0 ? a : 0;
        if (a2 <= 0) {
            a2 = 0;
        }
        if (a < 0) {
            i5 = -a;
        }
        dJIStickCircleView.setValue(i4, i6, a2, i5);
    }

    private void c(int i) {
        int i2;
        int a = a(i);
        DJICalProgressBar dJICalProgressBar = this.i;
        if (a < 0) {
            i2 = -a;
        } else {
            i2 = 0;
        }
        if (a <= 0) {
            a = 0;
        }
        dJICalProgressBar.setValue(i2, a);
    }

    private void e() {
        MODE a = this.o.a(false);
        if (a == MODE.a || a == MODE.f) {
            this.m.setVisibility(8);
            this.l.setEnabled(true);
            this.l.setText(R.string.setting_ui_rc_cele);
        } else if (a == MODE.b) {
            this.m.setVisibility(0);
            this.l.setEnabled(false);
            this.l.setText(R.string.setting_ui_rc_start);
            this.m.setText(R.string.setting_ui_rc_middle_desc);
        } else if (a == MODE.c) {
            this.m.setVisibility(0);
            this.l.setText(R.string.setting_ui_rc_finish);
            this.l.setEnabled(false);
            this.m.setText(R.string.setting_ui_rc_limit_tip);
        } else if (a == MODE.d) {
            this.m.setVisibility(0);
            this.l.setText(R.string.setting_ui_rc_finish);
            this.l.setEnabled(true);
            this.m.setText(R.string.setting_ui_rc_finish_tip);
        }
    }

    public void onEventBackgroundThread(p pVar) {
        a(pVar, true);
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            AppPublicReflect.showConnectWarning();
            c.a().e(dji.setting.ui.SettingUIRootView.a.BackBtnClick);
        } else if (oVar == o.a) {
            AppPublicReflect.DismissConnectWaning();
        }
    }

    public void onEventMainThread(DataRcGetPushParams dataRcGetPushParams) {
        a(0, dataRcGetPushParams.getThrottle(), dataRcGetPushParams.getRudder());
        a(1, dataRcGetPushParams.getElevator(), dataRcGetPushParams.getAileron());
        c(dataRcGetPushParams.getGyroValue());
    }

    private void a(p pVar, boolean z) {
        if (pVar == p.b) {
            this.o.a();
        } else if (pVar == p.a) {
            this.o.b();
            d();
            e();
        }
    }
}
