package dji.device.camera.settings;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.AbstractWheelView;
import antistatic.spinnerwheel.d;
import com.tencent.android.tpush.common.MessageKey;
import dji.device.camera.a.a;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIRoundLinearLayoutCompat;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.device.widget.b;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataCameraGetIso.TYPE;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetIso;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class LonganCameraSettingsView extends DJIRoundLinearLayoutCompat implements OnClickListener {
    private static final int R = 4096;
    private static final int S = 4097;
    private static final int T = 4098;
    private static final int U = 4099;
    private static final int V = 4100;
    private static final int W = 16384;
    private static final long ar = 200;
    b<String> A;
    b<String> B;
    protected String[] C = null;
    protected String[] D = null;
    protected String[] E = null;
    protected String[] F = null;
    int[] G;
    int H;
    protected int I = Integer.MAX_VALUE;
    protected int J = Integer.MIN_VALUE;
    protected int K = Integer.MIN_VALUE;
    protected int L = Integer.MAX_VALUE;
    protected CameraType M = CameraType.OTHER;
    protected ProductType N = ProductType.OTHER;
    boolean O = false;
    float P = 0.7f;
    private final String Q = "LonganCameraSettingsView";
    int a = 50;
    private antistatic.spinnerwheel.b aA = new antistatic.spinnerwheel.b(this) {
        final /* synthetic */ LonganCameraSettingsView a;

        {
            this.a = r1;
        }

        public void a(AbstractWheel abstractWheel, int i, int i2) {
            if (this.a.ap) {
                this.a.c(false, i2);
                this.a.z.i(i2);
            }
        }
    };
    private String[] aa;
    private int[] ab;
    private int[] ac;
    private int[] ad = new int[]{1, 2, 4};
    private int ae = -1;
    private int af = 0;
    private int ag = 0;
    private int ah = -1;
    private int ai = 0;
    private int aj = 0;
    private int ak = -1;
    private int al = Integer.MAX_VALUE;
    private int am = Integer.MAX_VALUE;
    private String an = null;
    private int ao = Integer.MAX_VALUE;
    private boolean ap = false;
    private DJICameraDataManagerCompat aq = DJICameraDataManagerCompat.getInstance();
    private Handler as = new Handler(new Callback(this) {
        final /* synthetic */ LonganCameraSettingsView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 4096:
                    a.getInstance().a(message.arg1);
                    break;
                case 4097:
                    boolean z;
                    int[] iArr = (int[]) message.obj;
                    if (iArr[0] == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    a.getInstance().a(z, iArr[1], iArr[2]);
                    break;
                case 4098:
                    a.getInstance().c(message.arg1);
                    break;
                case 4099:
                    a.getInstance().e(message.arg1);
                    break;
                case 4100:
                    a.getInstance().d(message.arg1);
                    break;
                case 16384:
                    this.a.startAnimation(this.a.w);
                    break;
            }
            return false;
        }
    });
    private d at = new d(this) {
        final /* synthetic */ LonganCameraSettingsView a;

        {
            this.a = r1;
        }

        public void a(AbstractWheel abstractWheel) {
            this.a.ap = true;
        }

        public void b(AbstractWheel abstractWheel) {
            this.a.ap = false;
            this.a.a(true, abstractWheel.getCurrentItem());
            this.a.x.i(abstractWheel.getCurrentItem());
            this.a.k.setCurrentItem(abstractWheel.getCurrentItem());
        }
    };
    private antistatic.spinnerwheel.b au = new antistatic.spinnerwheel.b(this) {
        final /* synthetic */ LonganCameraSettingsView a;

        {
            this.a = r1;
        }

        public void a(AbstractWheel abstractWheel, int i, int i2) {
            if (this.a.ap) {
                this.a.a(false, i2);
                this.a.x.i(i2);
            }
        }
    };
    private d av = new d(this) {
        final /* synthetic */ LonganCameraSettingsView a;

        {
            this.a = r1;
        }

        public void a(AbstractWheel abstractWheel) {
            this.a.ap = true;
        }

        public void b(AbstractWheel abstractWheel) {
            this.a.ap = false;
            this.a.b(true, abstractWheel.getCurrentItem());
            this.a.y.i(abstractWheel.getCurrentItem());
            this.a.m.setCurrentItem(abstractWheel.getCurrentItem());
        }
    };
    private antistatic.spinnerwheel.b aw = new antistatic.spinnerwheel.b(this) {
        final /* synthetic */ LonganCameraSettingsView a;

        {
            this.a = r1;
        }

        public void a(AbstractWheel abstractWheel, int i, int i2) {
            if (this.a.ap) {
                this.a.b(false, i2);
                this.a.y.i(i2);
            }
        }
    };
    private d ax = new d(this) {
        final /* synthetic */ LonganCameraSettingsView a;

        {
            this.a = r1;
        }

        public void a(AbstractWheel abstractWheel) {
            this.a.ap = true;
        }

        public void b(AbstractWheel abstractWheel) {
            this.a.ap = false;
            this.a.d(true, abstractWheel.getCurrentItem());
            this.a.A.i(abstractWheel.getCurrentItem());
            this.a.l.setCurrentItem(abstractWheel.getCurrentItem());
        }
    };
    private antistatic.spinnerwheel.b ay = new antistatic.spinnerwheel.b(this) {
        final /* synthetic */ LonganCameraSettingsView a;

        {
            this.a = r1;
        }

        public void a(AbstractWheel abstractWheel, int i, int i2) {
            if (this.a.ap) {
                this.a.d(false, i2);
                this.a.A.i(i2);
            }
        }
    };
    private d az = new d(this) {
        final /* synthetic */ LonganCameraSettingsView a;

        {
            this.a = r1;
        }

        public void a(AbstractWheel abstractWheel) {
            this.a.ap = true;
        }

        public void b(AbstractWheel abstractWheel) {
            this.a.ap = false;
            this.a.c(true, abstractWheel.getCurrentItem());
            this.a.z.i(abstractWheel.getCurrentItem());
            this.a.n.setCurrentItem(abstractWheel.getCurrentItem());
        }
    };
    final int b = 5;
    final int c = 6;
    final int d = 4;
    final int e = 8;
    TextView f;
    TextView g;
    TextView h;
    TextView i;
    DJITextView j;
    AbstractWheelView k;
    AbstractWheelView l;
    AbstractWheelView m;
    AbstractWheelView n;
    AbstractWheelView o;
    RelativeLayout p;
    RelativeLayout q;
    ImageView r;
    ImageView s;
    ImageView t;
    ImageView u;
    DJIStateImageViewCompat v;
    Animation w = AnimationUtils.loadAnimation(getContext(), R.anim.longan_menu_fade_in);
    b<String> x;
    b<String> y;
    b<String> z;

    public LonganCameraSettingsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.w.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ LonganCameraSettingsView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.setVisibility(0);
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.p = (RelativeLayout) findViewById(R.id.longan_camera_settings_land_ly);
        this.q = (RelativeLayout) findViewById(R.id.longan_camera_settings_port_ly);
        this.D = getResources().getStringArray(R.array.longan_camera_shutter_array);
        this.aa = getResources().getStringArray(R.array.longan_camera_shutter_value_array);
        this.C = getResources().getStringArray(R.array.longan_camera_iso_array);
        this.E = getResources().getStringArray(R.array.camera_ev_array);
        this.ab = getResources().getIntArray(R.array.camera_ev_value_array);
        this.G = getResources().getIntArray(R.array.fpv_camera_video_fps_array);
        this.F = getResources().getStringArray(R.array.longan_camera_aperture_array);
        this.ac = getResources().getIntArray(R.array.longan_camera_aperture_value_array);
        this.y = new b(getContext(), this.D);
        this.y.c(R.layout.longan_wheel_item_camera_set_port);
        this.y.d(R.id.longan_camera_settings_wheel_text);
        this.x = new b(getContext(), this.C);
        this.x.c(R.layout.longan_wheel_item_camera_set_port);
        this.x.d(R.id.longan_camera_settings_wheel_text);
        this.z = new b(getContext(), this.E);
        this.z.c(R.layout.longan_wheel_item_camera_set_port);
        this.z.d(R.id.longan_camera_settings_wheel_text);
        this.A = new b(getContext(), this.F);
        this.A.c(R.layout.longan_wheel_item_camera_set_port);
        this.A.d(R.id.longan_camera_settings_wheel_text);
        f();
        g();
        d();
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        onEventMainThread(a.getInstance());
        onEventMainThread(DataCameraGetPushShotInfo.getInstance());
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        c(this.ae);
        a(ExposureMode.find(this.ae));
        e();
        c.a().a(this);
        this.O = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
        this.O = false;
    }

    public void setVisibility(int i) {
        if (this.O) {
            onEventMainThread(DataCameraGetPushShotParams.getInstance());
            e();
        }
        super.setVisibility(i);
    }

    private void d() {
        this.n.setViewAdapter(this.z);
        this.n.addChangingListener(this.aA);
        this.n.addScrollingListener(this.az);
        this.k.setViewAdapter(this.x);
        this.k.addChangingListener(this.au);
        this.k.addScrollingListener(this.at);
        this.v.setOnClickListener(this);
        this.m.setViewAdapter(this.y);
        this.m.addChangingListener(this.aw);
        this.m.addScrollingListener(this.av);
        this.l.setViewAdapter(this.A);
        this.l.addChangingListener(this.ay);
        this.l.addScrollingListener(this.ax);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
    }

    private void e() {
        if (!this.ap) {
            if (!(this.k == null || this.k.getCurrentItem() == this.ag || this.ap) || this.ag == 0) {
                this.k.setCurrentItem(this.ag);
                this.x.i(this.ag);
            }
            if (!(this.m == null || this.m.getCurrentItem() == this.af || this.ap) || this.af == 0) {
                this.m.setCurrentItem(this.af);
                this.y.i(this.af);
            }
            if (!(this.n == null || this.n.getCurrentItem() == this.ai || this.ap) || this.ai == 0) {
                this.n.setCurrentItem(this.ai);
                this.z.i(this.ai);
            }
            if (!(this.l == null || this.l.getCurrentItem() == this.aj || this.ap) || this.aj == 0) {
                this.l.setCurrentItem(this.aj);
                this.A.i(this.aj);
            }
            a(ExposureMode.find(this.ak), this.ah);
        }
    }

    private void f() {
        if (getResources().getConfiguration().orientation == 2) {
            a(this.p);
        } else {
            a(this.q);
        }
    }

    private void a(View view) {
        this.f = (TextView) view.findViewById(R.id.longan_camera_mode_p);
        this.g = (TextView) view.findViewById(R.id.longan_camera_mode_s);
        this.h = (TextView) view.findViewById(R.id.longan_camera_mode_m);
        this.i = (TextView) view.findViewById(R.id.longan_camera_mode_a);
        this.j = (DJITextView) view.findViewById(R.id.longan_camera_setting_iso_tv);
        this.j.setAlpha(this.P);
        this.k = (AbstractWheelView) view.findViewById(R.id.longan_camera_settings_iso_wheel);
        this.v = (DJIStateImageViewCompat) view.findViewById(R.id.longan_camera_setting_iso_auto_img);
        this.m = (AbstractWheelView) view.findViewById(R.id.longan_camera_settings_shutter_wheel);
        this.n = (AbstractWheelView) view.findViewById(R.id.longan_camera_settings_ev_wheel);
        this.l = (AbstractWheelView) view.findViewById(R.id.longan_camera_settings_aperture_wheel);
        this.s = (ImageView) view.findViewById(R.id.longan_shutter_wheel_position_iv);
        this.r = (ImageView) view.findViewById(R.id.longan_iso_wheel_position_iv);
        this.t = (ImageView) view.findViewById(R.id.longan_ev_wheel_position_iv);
        this.u = (ImageView) view.findViewById(R.id.longan_aperture_wheel_position_iv);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        f();
        g();
        d();
        e();
        c(this.ae);
        a(ExposureMode.find(this.ak));
    }

    private void g() {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (getResources().getConfiguration().orientation == 2) {
            this.p.setVisibility(0);
            this.q.setVisibility(8);
            layoutParams.addRule(2, 0);
            layoutParams.addRule(14, 0);
            layoutParams.addRule(1, R.id.longan_preview_cameracontrol);
            layoutParams.addRule(15);
            return;
        }
        this.p.setVisibility(8);
        this.q.setVisibility(0);
        layoutParams.addRule(1, 0);
        layoutParams.addRule(15, 0);
        layoutParams.addRule(2, R.id.longan_preview_cameracontrol);
        layoutParams.addRule(14);
    }

    public void onClick(View view) {
        int i = 0;
        int id = view.getId();
        if (id == R.id.longan_camera_mode_p) {
            i = ExposureMode.b.a();
        } else if (id == R.id.longan_camera_mode_s) {
            i = ExposureMode.c.a();
        } else if (id == R.id.longan_camera_mode_m) {
            i = ExposureMode.e.a();
        } else if (id == R.id.longan_camera_mode_a) {
            i = ExposureMode.d.a();
        } else if (id == R.id.longan_camera_setting_iso_auto_img) {
            h();
        }
        if (i != 0) {
            a.getInstance().e(i);
            c(i);
        }
    }

    private void h() {
        if (this.v.isSelected()) {
            new DataCameraSetIso().a(true).a(getIsoType()).start(new dji.midware.e.d(this) {
                final /* synthetic */ LonganCameraSettingsView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD("LonganCameraSettingsView", "set auto iso success", false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("LonganCameraSettingsView", "set auto iso failed" + aVar, false, true);
                }
            });
        } else {
            new DataCameraSetIso().a(true).a(TYPE.AUTO).start(new dji.midware.e.d(this) {
                final /* synthetic */ LonganCameraSettingsView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD("LonganCameraSettingsView", "set auto iso success", false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("LonganCameraSettingsView", "set auto iso failed" + aVar, false, true);
                }
            });
        }
    }

    protected TYPE getIsoType() {
        Object obj = null;
        int relISO = DataCameraGetPushShotParams.getInstance().getRelISO() / 100;
        int i = 0;
        while (relISO > 0) {
            int i2 = i + 1;
            int i3 = relISO >>> 1;
            if (i3 > 0) {
                obj = (relISO & 1) != 0 ? 1 : null;
                relISO = i3;
                i = i2;
            } else {
                i = i2;
                relISO = i3;
            }
        }
        i += 2;
        if (obj != null) {
            i++;
        }
        if (i < TYPE.ISO100.value()) {
            i = TYPE.ISO100.value();
        } else if (i > TYPE.ISO25600.value()) {
            i = TYPE.ISO25600.value();
        }
        return TYPE.find(i);
    }

    private void c(int i) {
        if (this.ak == 0 || i == this.ak || !(dji.device.camera.a.d.getInstance().c() == dji.device.camera.a.d.a.RECORDING || dji.device.camera.a.b.getInstance().d() == dji.device.camera.a.b.c.TIMING)) {
            if (i == ExposureMode.e.a()) {
                this.v.setEnabled(false);
                this.v.setSelected(false);
            } else {
                this.v.setEnabled(true);
            }
            if (i == ExposureMode.b.a() || i == ExposureMode.a.a()) {
                this.ak = ExposureMode.b.a();
                if (!this.f.isSelected()) {
                    this.f.setSelected(true);
                    this.g.setSelected(false);
                    this.h.setSelected(false);
                    this.m.setEnabled(false);
                    this.m.setAlpha(this.P);
                    this.k.setEnabled(false);
                    this.k.setAlpha(this.P);
                    this.k.setVisibleItems(1);
                    this.r.setVisibility(4);
                    this.n.setEnabled(true);
                    this.n.setAlpha(1.0f);
                    this.s.setVisibility(4);
                    this.t.setVisibility(0);
                    this.m.setVisibleItems(1);
                    this.n.setVisibleItems(3);
                    this.i.setSelected(false);
                    this.l.setEnabled(false);
                    this.l.setAlpha(this.P);
                    this.l.setVisibleItems(1);
                    this.u.setVisibility(4);
                    invalidate();
                    return;
                }
                return;
            } else if (i == ExposureMode.c.a()) {
                this.ak = ExposureMode.c.a();
                if (!this.g.isSelected()) {
                    this.f.setSelected(false);
                    this.g.setSelected(true);
                    this.h.setSelected(false);
                    this.i.setSelected(false);
                    return;
                }
                return;
            } else if (i == ExposureMode.e.a()) {
                this.ak = ExposureMode.e.a();
                if (!this.h.isSelected()) {
                    this.f.setSelected(false);
                    this.g.setSelected(false);
                    this.h.setSelected(true);
                    this.i.setSelected(false);
                    return;
                }
                return;
            } else if (i == ExposureMode.d.a()) {
                this.ak = ExposureMode.d.a();
                if (!this.i.isSelected()) {
                    this.f.setSelected(false);
                    this.g.setSelected(false);
                    this.h.setSelected(false);
                    this.i.setSelected(true);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        Toast.makeText(getContext(), getResources().getString(R.string.fpv_changemode_tip), 0).show();
    }

    private void a(ExposureMode exposureMode) {
        if (exposureMode == ExposureMode.e) {
            this.v.setEnabled(false);
            this.v.setSelected(false);
        } else {
            this.v.setEnabled(true);
        }
        if (exposureMode == ExposureMode.b || exposureMode == ExposureMode.a) {
            this.ak = ExposureMode.b.a();
            this.m.setEnabled(false);
            this.m.setAlpha(this.P);
            if (dji.logic.f.b.h(this.M)) {
                this.k.setEnabled(true);
                this.k.setAlpha(1.0f);
                this.k.setVisibleItems(3);
                this.r.setVisibility(0);
            } else {
                this.k.setEnabled(false);
                this.k.setAlpha(this.P);
                this.k.setVisibleItems(1);
            }
            this.n.setEnabled(true);
            this.n.setAlpha(1.0f);
            this.s.setVisibility(4);
            this.t.setVisibility(0);
            this.m.setVisibleItems(1);
            this.n.setVisibleItems(3);
            this.l.setEnabled(false);
            this.l.setAlpha(this.P);
            this.l.setVisibleItems(1);
            this.u.setVisibility(4);
        } else if (exposureMode == ExposureMode.c) {
            this.ak = ExposureMode.c.a();
            this.m.setEnabled(true);
            this.m.setAlpha(1.0f);
            if (dji.logic.f.b.h(this.M)) {
                this.k.setEnabled(true);
                this.k.setAlpha(1.0f);
                this.k.setVisibleItems(3);
                this.r.setVisibility(0);
            } else {
                this.k.setEnabled(false);
                this.k.setAlpha(this.P);
                this.k.setVisibleItems(1);
            }
            this.n.setEnabled(true);
            this.n.setAlpha(1.0f);
            this.s.setVisibility(0);
            this.t.setVisibility(0);
            this.m.setVisibleItems(3);
            this.n.setVisibleItems(3);
            this.l.setEnabled(false);
            this.l.setAlpha(this.P);
            this.l.setVisibleItems(1);
            this.u.setVisibility(4);
        } else if (exposureMode == ExposureMode.e) {
            this.ak = ExposureMode.e.a();
            this.m.setEnabled(true);
            this.m.setAlpha(1.0f);
            if (dji.logic.f.b.h(this.M)) {
                this.k.setEnabled(true);
                this.k.setAlpha(1.0f);
                this.k.setVisibleItems(3);
                this.r.setVisibility(0);
            } else {
                this.k.setEnabled(true);
                this.k.setAlpha(1.0f);
                this.k.setVisibleItems(3);
            }
            this.n.setEnabled(false);
            this.n.setAlpha(this.P);
            this.s.setVisibility(0);
            this.t.setVisibility(4);
            this.m.setVisibleItems(3);
            this.n.setVisibleItems(1);
            this.l.setEnabled(true);
            this.l.setAlpha(1.0f);
            this.l.setVisibleItems(3);
            this.u.setVisibility(0);
        } else if (exposureMode == ExposureMode.d) {
            this.ak = ExposureMode.d.a();
            this.m.setEnabled(false);
            this.m.setAlpha(this.P);
            if (dji.logic.f.b.h(this.M)) {
                this.k.setEnabled(true);
                this.k.setAlpha(1.0f);
                this.k.setVisibleItems(3);
                this.r.setVisibility(0);
            } else {
                this.k.setEnabled(false);
                this.k.setAlpha(this.P);
                this.k.setVisibleItems(1);
            }
            this.n.setEnabled(true);
            this.n.setAlpha(1.0f);
            this.s.setVisibility(4);
            this.t.setVisibility(4);
            this.m.setVisibleItems(1);
            this.n.setVisibleItems(1);
            this.l.setEnabled(true);
            this.l.setAlpha(1.0f);
            this.l.setVisibleItems(3);
            this.u.setVisibility(0);
        }
    }

    private void a(boolean z, int i) {
        this.ag = i;
        int iSOCmdValue = this.aq.getISOCmdValue(i);
        if (this.ah != iSOCmdValue) {
            this.al = iSOCmdValue;
            this.as.removeMessages(4096);
            if (z) {
                a.getInstance().a(iSOCmdValue);
                return;
            }
            if (isShown()) {
                this.aq.playSimpleSound(getContext());
            }
            this.as.sendMessageDelayed(this.as.obtainMessage(4096, iSOCmdValue, 0), 200);
        }
    }

    private void b(boolean z, int i) {
        this.af = i;
        String shutterValue = this.aq.getShutterValue(i);
        if (!shutterValue.equalsIgnoreCase(this.an)) {
            int parseInt;
            int i2;
            String[] split = shutterValue.split(dji.pilot.usercenter.protocol.d.t);
            boolean z2 = split.length > 1;
            split = split[split.length - 1].split("\\.");
            int parseInt2 = Integer.parseInt(split[0]);
            if (split.length > 1) {
                parseInt = Integer.parseInt(split[1]);
            } else {
                parseInt = 0;
            }
            this.as.removeMessages(4097);
            Object obj = new int[3];
            if (z2) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            obj[0] = i2;
            obj[1] = parseInt2;
            obj[2] = parseInt;
            if (z) {
                a.getInstance().a(z2, parseInt2, parseInt);
            } else {
                if (isShown()) {
                    this.aq.playSimpleSound(getContext());
                }
                this.as.sendMessageDelayed(this.as.obtainMessage(4097, 0, 0, obj), 200);
            }
            this.an = shutterValue;
        }
    }

    private void c(boolean z, int i) {
        this.ai = i;
        int evCmdValue = this.aq.getEvCmdValue(i);
        if (this.am != evCmdValue) {
            this.as.removeMessages(4098);
            if (z) {
                a.getInstance().c(evCmdValue);
            } else {
                if (isShown()) {
                    this.aq.playSimpleSound(getContext());
                }
                this.as.sendMessageDelayed(this.as.obtainMessage(4098, evCmdValue, 0), 200);
            }
            this.am = evCmdValue;
        }
    }

    private void d(boolean z, int i) {
        this.aj = i;
        int i2 = this.ac[i];
        if (this.ao != i2) {
            this.as.removeMessages(4100);
            if (z) {
                a.getInstance().d(i2);
            } else {
                if (isShown()) {
                    this.aq.playSimpleSound(getContext());
                }
                this.as.sendMessageDelayed(this.as.obtainMessage(4100, i2, 0), 200);
            }
            this.ao = i2;
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int i = 0;
        ExposureMode exposureMode = dataCameraGetPushShotParams.getExposureMode();
        int videoFps = dataCameraGetPushShotParams.getVideoFps();
        if (exposureMode == ExposureMode.b || exposureMode == ExposureMode.a) {
            i = 1;
        } else if (exposureMode == ExposureMode.c) {
            i = 2;
        } else if (exposureMode == ExposureMode.e) {
            i = 4;
        } else if (exposureMode == ExposureMode.d) {
            i = 3;
        }
        if (!(i == this.ae && this.H == videoFps)) {
            this.ae = i;
            a(exposureMode);
            b(exposureMode);
        }
        a(exposureMode, dataCameraGetPushShotParams.getRelISO());
        if (a(exposureMode, DataCameraGetPushStateInfo.getInstance().getMode())) {
            b();
        } else {
            this.y.j();
        }
        i = getIndex(this.aa, dataCameraGetPushShotParams.getRelShutterString());
        if (i != this.af) {
            this.af = i;
        }
        if (exposureMode == ExposureMode.e) {
            i = dataCameraGetPushShotParams.getRelExposureCompensation();
        } else {
            i = dataCameraGetPushShotParams.getExposureCompensation();
        }
        i = a(i, this.ab);
        if (i != this.ai) {
            this.ai = i;
        }
        i = dataCameraGetPushShotParams.getRealApertureSize();
        dataCameraGetPushShotParams.getRealApertureSize();
        i = a(i, this.ac);
        if (this.aj != i) {
            this.aj = i;
        }
        e();
    }

    protected void a(ExposureMode exposureMode, int i) {
        int iso;
        if (dji.logic.f.b.h(this.M)) {
            iso = DataCameraGetPushShotParams.getInstance().getISO();
            if (exposureMode == ExposureMode.e || !(iso == 0 || iso == 1)) {
                this.v.setImageResource(R.drawable.camera_isoauto_normal);
                this.v.setSelected(false);
                this.r.setVisibility(0);
                this.j.setVisibility(4);
                this.k.setVisibility(0);
                iso = getIndex(this.C, i + "");
                if (iso != this.ag) {
                    this.ag = iso;
                    return;
                }
                return;
            }
            this.v.setImageResource(R.drawable.camera_isoauto_highlight);
            this.v.setSelected(true);
            this.r.setVisibility(4);
            this.j.setVisibility(0);
            this.k.setVisibility(4);
            this.j.setText(i + "");
            this.ah = i;
            return;
        }
        if (i != this.ah) {
            this.ah = i;
        }
        if (exposureMode.a() == 2) {
            this.r.setVisibility(4);
            this.j.setVisibility(0);
            this.k.setVisibility(4);
            this.j.setText(i + "");
            return;
        }
        this.r.setVisibility(0);
        this.j.setVisibility(4);
        this.k.setVisibility(0);
        iso = getIndex(this.C, i + "");
        if (iso != this.ag) {
            this.ag = iso;
        }
    }

    protected boolean a() {
        if (dji.logic.f.b.h(this.M)) {
            int iso = DataCameraGetPushShotParams.getInstance().getISO();
            if (iso == 0 || iso == 1) {
                return true;
            }
            return false;
        } else if (this.ak == ExposureMode.c.a() || this.ak == ExposureMode.b.a()) {
            return true;
        } else {
            return false;
        }
    }

    protected int a(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (a()) {
            return dataCameraGetPushShotParams.getRelISO();
        }
        return dataCameraGetPushShotParams.getISO();
    }

    protected boolean a(ExposureMode exposureMode, MODE mode) {
        if (dji.logic.f.b.d(this.M)) {
            if (exposureMode == ExposureMode.c) {
                return true;
            }
            return false;
        } else if (mode == MODE.TAKEPHOTO && exposureMode == ExposureMode.c) {
            return true;
        } else {
            return false;
        }
    }

    protected void b() {
        if (dji.logic.f.b.h(this.M)) {
            int exposureStatus = DataCameraGetPushShotParams.getInstance().getExposureStatus();
            if (exposureStatus == 0) {
                this.y.j();
                return;
            } else if (exposureStatus == 1) {
                exposureStatus = this.aq.getShutterVauePos(b(DataCameraGetPushShotParams.getInstance()));
                if (exposureStatus == -1) {
                    this.y.j();
                    return;
                } else {
                    this.y.a(exposureStatus, exposureStatus);
                    return;
                }
            } else if (exposureStatus == 2) {
                exposureStatus = this.aq.getShutterVauePos(b(DataCameraGetPushShotParams.getInstance()));
                if (exposureStatus < 0) {
                    this.y.j();
                    return;
                } else {
                    this.y.a(exposureStatus, exposureStatus);
                    return;
                }
            } else {
                return;
            }
        }
        String capMinShutterStr = DataCameraGetPushShotParams.getInstance().getCapMinShutterStr();
        String capMaxShutterStr = DataCameraGetPushShotParams.getInstance().getCapMaxShutterStr();
        this.y.a(this.aq.getShutterVauePos(capMinShutterStr), this.aq.getShutterVauePos(capMaxShutterStr));
    }

    protected boolean c() {
        if (dji.logic.f.b.h(null)) {
            if (this.ak == ExposureMode.b.a() || this.ak == ExposureMode.d.a()) {
                return true;
            }
            return false;
        } else if (this.ak == ExposureMode.b.a()) {
            return true;
        } else {
            return false;
        }
    }

    protected String b(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (c()) {
            return dataCameraGetPushShotParams.getRelShutterString();
        }
        return dataCameraGetPushShotParams.getShutterString();
    }

    public void onEventMainThread(ProductType productType) {
        if (this.N != productType) {
            this.N = productType;
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        int minAperture = dataCameraGetPushShotInfo.getMinAperture();
        int maxAperture = dataCameraGetPushShotInfo.getMaxAperture();
        DJILogHelper.getInstance().LOGD("", "max:" + maxAperture + MessageKey.MSG_ACCEPT_TIME_MIN + minAperture, false, true);
        if (this.I != minAperture || this.J != maxAperture) {
            int length;
            this.I = minAperture;
            this.J = maxAperture;
            String[] apertureAr = this.aq.getApertureAr();
            minAperture = this.aq.getApertureValuePos(minAperture);
            if (minAperture == -1) {
                minAperture = 0;
            }
            maxAperture = this.aq.getApertureValuePos(maxAperture);
            if (maxAperture == -1 || maxAperture == 0) {
                maxAperture = apertureAr.length - 1;
            }
            if (maxAperture <= minAperture) {
                maxAperture = 0;
                length = apertureAr.length - 1;
            } else {
                length = maxAperture;
                maxAperture = minAperture;
            }
            this.F = (String[]) dji.thirdparty.afinal.c.c.a(apertureAr, maxAperture, length + 1);
            this.ac = dji.thirdparty.afinal.c.c.a(this.aq.getApertureValueAr(), maxAperture, length + 1);
            this.A.a(this.F);
            e();
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (cameraType != this.M) {
            this.M = cameraType;
            if (dji.logic.f.b.h(cameraType)) {
                a(0);
            } else {
                a(8);
            }
            if (dji.logic.f.b.h(this.M)) {
                b(0);
            } else {
                b(4);
            }
        }
    }

    protected void a(int i) {
        this.p.findViewById(R.id.longan_camera_mode_a).setVisibility(i);
        this.p.findViewById(R.id.longan_camera_settings_aperture_ly).setVisibility(i);
        this.q.findViewById(R.id.longan_camera_mode_a).setVisibility(i);
        this.q.findViewById(R.id.longan_camera_settings_aperture_ly).setVisibility(i);
    }

    protected void b(int i) {
        this.p.findViewById(R.id.longan_camera_setting_iso_auto_img).setVisibility(i);
        this.q.findViewById(R.id.longan_camera_setting_iso_auto_img).setVisibility(i);
    }

    public void onEventMainThread(a aVar) {
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        DJILogHelper.getInstance().LOGD("LonganCameraSettingsView", "on mode event", false, true);
        if (aVar.d() == a.a.TAKEPHOTO) {
            if (dji.logic.f.b.h(null)) {
                this.x.j(9);
                DJILogHelper.getInstance().LOGD("LonganCameraSettingsView", "on mode event set max:9", false, true);
                if (this.k.getCurrentItem() > 8) {
                    a(true, 8);
                    this.k.setCurrentItem(9);
                    this.x.i(9);
                    this.y.j(-1);
                }
            } else {
                this.x.j(5);
                if (this.k.getCurrentItem() > 4) {
                    a(true, 4);
                    this.k.setCurrentItem(5);
                    this.x.i(5);
                    this.y.j(-1);
                }
            }
            b(DataCameraGetPushShotParams.getInstance().getExposureMode());
            ExposureMode exposureMode = DataCameraGetPushShotParams.getInstance().getExposureMode();
            if (!dji.logic.f.b.n(this.M)) {
                if (exposureMode == ExposureMode.c) {
                    b();
                    return;
                } else {
                    this.y.j();
                    return;
                }
            }
            return;
        }
        if (dji.logic.f.b.h(cameraType)) {
            this.x.j(7);
            if (this.k.getCurrentItem() > 6) {
                a(true, 6);
                this.k.setCurrentItem(7);
                this.x.i(7);
                this.y.j(-1);
            }
        } else {
            this.x.j(6);
            if (this.k.getCurrentItem() > 5) {
                a(true, 5);
                this.k.setCurrentItem(6);
                this.x.i(6);
                this.y.j(-1);
            }
        }
        b(DataCameraGetPushShotParams.getInstance().getExposureMode());
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.SHOW_PASM) {
            setVisibility(0);
        } else if (mVar == m.HIDE_PASM) {
            setVisibility(4);
        }
    }

    private void b(ExposureMode exposureMode) {
        if (a.getInstance().d() != a.a.TAKEPHOTO) {
            this.y.j(a(d(this.G[DataCameraGetPushShotParams.getInstance().getVideoFps()]), this.aa) + 1);
        } else if (exposureMode == ExposureMode.c) {
            if (this.m.getCurrentItem() > this.a + 1) {
                b(false, this.a);
                this.y.i(this.a + 1);
                this.m.setCurrentItem(this.a + 1);
            }
            this.y.j(this.a + 1);
        } else if (exposureMode == ExposureMode.e) {
            this.y.j(-1);
        } else {
            this.y.j(-1);
        }
    }

    public int getIndex(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return 0;
    }

    private int a(int i, int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return 0;
    }

    private int a(String str, String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return 0;
    }

    private String d(int i) {
        String str = "30";
        if (i == 24) {
            return "1/25";
        }
        if (i == 48) {
            return "1/50";
        }
        if (i == 480) {
            return "1/500";
        }
        return "1/" + i;
    }
}
