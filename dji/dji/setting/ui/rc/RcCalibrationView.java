package dji.setting.ui.rc;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
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
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.i;
import dji.sdksharedlib.c.d;
import dji.setting.ui.widget.DJICalProgressBar;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.setting.ui.widget.JoyStickDashedSquare;
import java.lang.ref.WeakReference;

public class RcCalibrationView extends DividerLinearLayout implements d {
    private static final String c = "RcCalibrationView";
    private static final int d = 4096;
    private static final int e = 0;
    private static final int f = 1;
    private static final int g = 1024;
    private static final int h = 364;
    private static final int i = 1684;
    public JoyStickDashedSquare a;
    public JoyStickDashedSquare b;
    private DJICalProgressBar l = null;
    private TextView m = null;
    private TextView n = null;
    private b o = null;
    private a p = null;
    private OnClickListener q = null;
    private c r;
    private c s;
    private c t;
    private c u;
    private c v;
    private c w;
    private c x;
    private c y;
    private c z;

    private final class a {
        final /* synthetic */ RcCalibrationView a;
        private DataRcSetCalibration b;
        private dji.midware.e.d c;
        private boolean d;
        private boolean e;
        private boolean f;
        private MODE g;

        private a(final RcCalibrationView rcCalibrationView) {
            this.a = rcCalibrationView;
            this.b = null;
            this.c = null;
            this.d = false;
            this.e = false;
            this.f = false;
            this.g = MODE.f;
            this.b = DataRcSetCalibration.getInstance();
            this.c = new dji.midware.e.d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    this.b.a.o.obtainMessage(4096, 0, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.a.o.obtainMessage(4096, 1, 0).sendToTarget();
                }
            };
        }

        private void a() {
            this.e = true;
            this.f = false;
            if (this.g == MODE.f) {
                this.b.a(MODE.a).start(this.c);
            }
        }

        private void b() {
            this.d = false;
            this.e = false;
            this.f = false;
            this.g = MODE.f;
        }

        private boolean c() {
            return this.d;
        }

        private MODE a(boolean z) {
            if (z && this.e) {
                MODE a = this.b.a();
                if (a == this.g || a == MODE.c || a == MODE.e) {
                    b(true);
                } else if (this.g == MODE.a && a == MODE.b) {
                    b(true);
                }
                this.g = a;
            }
            return this.g;
        }

        private void b(boolean z) {
            DJILogHelper.getInstance().LOGD("CJTesting2", "con[" + this.e + "]mode[" + this.g + dji.pilot.usercenter.protocol.d.H, false, true);
            if (this.e) {
                if (this.g == MODE.f) {
                    this.d = false;
                    this.b.a(MODE.a).start(this.c);
                } else if (this.g == MODE.a) {
                    if (this.d) {
                        this.b.a(MODE.b).start(this.c);
                    } else {
                        this.d = true;
                    }
                } else if (this.g == MODE.b) {
                    this.d = false;
                    this.b.a(MODE.c).start(this.c);
                } else if (this.g == MODE.c) {
                    this.b.a(MODE.c).start(this.c);
                } else if (this.g == MODE.d) {
                    this.b.a(MODE.d).start(this.c);
                    this.a.a.reset();
                    this.a.b.reset();
                } else if (this.g == MODE.e) {
                    if (!this.f) {
                        this.f = true;
                        dji.setting.ui.widget.a.a(this.a.getContext(), R.string.setting_ui_rc_calibration_time_out, new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ a a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                this.a.a.a.reset();
                                this.a.a.b.reset();
                            }
                        });
                    }
                    this.b.a(MODE.e).start(this.c);
                }
                if (this.g != MODE.e) {
                    this.f = false;
                }
            } else if (!z) {
                Toast.makeText(this.a.getContext().getApplicationContext(), R.string.setting_ui_rc_tip_disconnect, 0).show();
            }
        }
    }

    private static final class b extends Handler {
        private final WeakReference<RcCalibrationView> a;

        public b(RcCalibrationView rcCalibrationView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(rcCalibrationView);
        }

        public void handleMessage(Message message) {
            RcCalibrationView rcCalibrationView = (RcCalibrationView) this.a.get();
            if (rcCalibrationView != null) {
                switch (message.what) {
                    case 4096:
                        if (message.arg1 == 0) {
                            rcCalibrationView.p.a(true);
                            rcCalibrationView.e();
                            return;
                        }
                        rcCalibrationView.p.b(true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void setOnCreateContextMenuListener(OnCreateContextMenuListener onCreateContextMenuListener) {
        super.setOnCreateContextMenuListener(onCreateContextMenuListener);
    }

    public RcCalibrationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_rc_calibration);
        if (!isInEditMode()) {
            b();
            this.a = (JoyStickDashedSquare) findViewById(R.id.fpv_rcsetting_cele_left_stick);
            this.b = (JoyStickDashedSquare) findViewById(R.id.fpv_rcsetting_cele_right_stick);
            this.l = (DJICalProgressBar) findViewById(R.id.fpv_rcsetting_cele_item_pgb);
            this.l.setValue(0, 0);
            this.m = (TextView) findViewById(R.id.fpv_rcsetting_cele_btn);
            this.m.setOnClickListener(this.q);
            this.n = (TextView) findViewById(R.id.fpv_rcsetting_cele_status_tv);
            this.r = dji.sdksharedlib.a.b.k(i.aa);
            this.s = dji.sdksharedlib.a.b.k(i.ab);
            this.t = dji.sdksharedlib.a.b.k(i.ac);
            this.u = dji.sdksharedlib.a.b.k(i.ad);
            this.v = dji.sdksharedlib.a.b.k(i.ae);
            this.w = dji.sdksharedlib.a.b.k(i.af);
            this.x = dji.sdksharedlib.a.b.k(i.ag);
            this.y = dji.sdksharedlib.a.b.k(i.ah);
            this.z = dji.sdksharedlib.a.b.k(i.ai);
            dji.sdksharedlib.a.a.a(this, true, new c[]{this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z});
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
            dji.thirdparty.a.c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        this.p.b();
        d();
        dji.thirdparty.a.c.a().d(this);
        DJISDKCache.getInstance().stopListening(this);
        super.onDetachedFromWindow();
        AppPublicReflect.DismissConnectWaning();
    }

    private void b() {
        this.o = new b(this);
        this.p = new a();
        this.q = new OnClickListener(this) {
            final /* synthetic */ RcCalibrationView a;

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
        if (this.p.a(false) != MODE.a) {
            this.p.b(false);
        } else if (this.p.c()) {
            getContext();
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_rc_cele_tip, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ RcCalibrationView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.p.b(false);
                    dialogInterface.dismiss();
                }
            });
        } else {
            this.n.setVisibility(0);
            this.m.setText(R.string.setting_ui_rc_start);
            this.n.setText(R.string.setting_ui_rc_middle_desc);
            this.p.b(false);
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
        JoyStickDashedSquare joyStickDashedSquare;
        int i4;
        int i5 = 0;
        int a = a(i2);
        int a2 = a(i3);
        if (i == 0) {
            joyStickDashedSquare = this.a;
        } else {
            joyStickDashedSquare = this.b;
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
        joyStickDashedSquare.setCircleCenter(i4, i6, a2, i5);
    }

    private void c(int i) {
        int i2;
        int a = a(i);
        DJICalProgressBar dJICalProgressBar = this.l;
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
        MODE a = this.p.a(false);
        if (a == MODE.a || a == MODE.f) {
            this.n.setVisibility(8);
            this.m.setEnabled(true);
            this.m.setText(R.string.setting_ui_rc_cele);
        } else if (a == MODE.b) {
            this.n.setVisibility(0);
            this.m.setEnabled(false);
            this.m.setText(R.string.setting_ui_rc_start);
            this.n.setText(R.string.setting_ui_rc_middle_desc);
        } else if (a == MODE.c) {
            this.n.setVisibility(0);
            this.m.setText(R.string.setting_ui_rc_finish);
            this.m.setEnabled(false);
            this.n.setText(R.string.setting_ui_rc_limit_tip);
        } else if (a == MODE.d) {
            this.n.setVisibility(0);
            this.m.setText(R.string.setting_ui_rc_finish);
            this.m.setEnabled(true);
            this.n.setText(R.string.setting_ui_rc_finish_tip);
        }
    }

    public void onEventBackgroundThread(p pVar) {
        a(pVar, true);
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            AppPublicReflect.showConnectWarning();
            dji.thirdparty.a.c.a().e(dji.setting.ui.SettingUIRootView.a.BackBtnClick);
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
            this.p.a();
        } else if (pVar == p.a) {
            this.p.b();
            d();
            e();
        }
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar != null && aVar2 != null) {
            if (aVar2.e() == null) {
                this.a.reset();
                this.b.reset();
            } else if (DataRcSetCalibration.getInstance().a().equals(MODE.e)) {
                this.b.reset();
                this.a.reset();
            } else {
                DJILogHelper.getInstance().LOGD("TESTING", "ValueChanged", true, true);
                int intValue = ((Integer) aVar2.e()).intValue();
                if (cVar.equals(this.r)) {
                    this.a.setSegmentNum(intValue);
                    this.b.setSegmentNum(intValue);
                }
                Log.d("CJTesting1", intValue + ", " + cVar.f());
                if (!this.a.hasSegNumSet() || !this.b.hasSegNumSet()) {
                    return;
                }
                if (cVar.equals(this.s)) {
                    this.b.drawCalibration(intValue, 0);
                } else if (cVar.equals(this.t)) {
                    this.b.drawCalibration(intValue, 2);
                } else if (cVar.equals(this.u)) {
                    this.b.drawCalibration(intValue, 1);
                } else if (cVar.equals(this.v)) {
                    this.b.drawCalibration(intValue, 3);
                } else if (cVar.equals(this.w)) {
                    this.a.drawCalibration(intValue, 0);
                } else if (cVar.equals(this.x)) {
                    this.a.drawCalibration(intValue, 2);
                } else if (cVar.equals(this.y)) {
                    this.a.drawCalibration(intValue, 1);
                } else if (cVar.equals(this.z)) {
                    this.a.drawCalibration(intValue, 3);
                }
            }
        }
    }
}
