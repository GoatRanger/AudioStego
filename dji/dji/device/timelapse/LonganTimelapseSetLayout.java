package dji.device.timelapse;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.WheelHorizontalView;
import antistatic.spinnerwheel.WheelVerticalView;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.DJIUIEventManagerLongan.k;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.widget.b;
import dji.logic.f.d;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.pilot.fpv.R;
import dji.pilot.set.e;
import dji.thirdparty.a.c;

public class LonganTimelapseSetLayout extends FrameLayout implements OnClickListener {
    private static final String K = "LonganTimelapseSetLayout";
    final int[] A = getResources().getIntArray(R.array.longan_timelapse_interval_value_array);
    final String[] B = getResources().getStringArray(R.array.longan_timelapse_duration_name_array);
    final int[] C = getResources().getIntArray(R.array.longan_timelapse_duration_value_array);
    int D = 0;
    int E = 0;
    int F = 5;
    boolean G = false;
    boolean H;
    b I = b.getInstance();
    int J;
    private DJICameraDataManagerCompat L = DJICameraDataManagerCompat.getInstance();
    private Runnable M = new Runnable(this) {
        final /* synthetic */ LonganTimelapseSetLayout a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.H = false;
            this.a.syncDatas(DataCameraGetPushShotParams.getInstance());
        }
    };
    LinearLayout a;
    LinearLayout b;
    WheelHorizontalView c;
    WheelVerticalView d;
    WheelVerticalView e;
    WheelHorizontalView f;
    TextView g;
    TextView h;
    RelativeLayout i;
    RelativeLayout j;
    RelativeLayout k;
    TextView l;
    TextView m;
    TextView n;
    TextView o;
    Button p;
    Button q;
    Button r;
    Button s;
    TextView t;
    TextView u;
    LayoutParams v;
    LayoutParams w;
    protected b<String> x = null;
    protected b<String> y = null;
    final String[] z = getResources().getStringArray(R.array.longan_timelapse_interval_name_array);

    public LonganTimelapseSetLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    private void a() {
        if (this.G) {
            if (getResources().getConfiguration().orientation == 2) {
                this.a.setVisibility(0);
                this.b.setVisibility(8);
            } else {
                this.a.setVisibility(8);
                this.b.setVisibility(0);
            }
            d();
        }
    }

    private void b() {
        c();
        show();
        if (d.g(null)) {
            this.g.setVisibility(0);
            this.h.setVisibility(0);
            this.i.setVisibility(0);
            this.j.setVisibility(0);
            return;
        }
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        this.i.setVisibility(8);
        this.j.setVisibility(8);
    }

    private void c() {
        this.a = (LinearLayout) findViewById(R.id.longan_timelapse_mode_set_land_ly);
        this.b = (LinearLayout) findViewById(R.id.longan_timelapse_mode_set_port_ly);
        this.g = (TextView) findViewById(R.id.longan_new_timelapse_land_mode_title_tv);
        this.h = (TextView) findViewById(R.id.longan_new_timelapse_port_mode_title_tv);
        this.i = (RelativeLayout) findViewById(R.id.longan_new_timelapse_mode_ly);
        this.j = (RelativeLayout) findViewById(R.id.longan_new_timelapse_port_mode_ly);
        this.l = (TextView) this.i.findViewById(R.id.longan_timelapse_mode_stationary_tv);
        this.m = (TextView) this.i.findViewById(R.id.longan_timelapse_mode_motion_tv);
        this.n = (TextView) this.j.findViewById(R.id.longan_timelapse_port_mode_stationary_tv);
        this.o = (TextView) this.j.findViewById(R.id.longan_timelapse_port_mode_motion_tv);
        this.t = (TextView) findViewById(R.id.longan_new_timelapse_land_gnr_tv);
        this.u = (TextView) findViewById(R.id.longan_new_timelapse_port_gnr_tv);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.l.setSelected(true);
        this.n.setSelected(true);
        this.c = (WheelHorizontalView) findViewById(R.id.longan_timelapse_interval_wheel);
        this.d = (WheelVerticalView) findViewById(R.id.longan_timelapse_port_interval_wheel);
        this.f = (WheelHorizontalView) findViewById(R.id.longan_timelapse_duration_wheel);
        this.e = (WheelVerticalView) findViewById(R.id.longan_timelapse_port_duration_wheel);
        this.y = new b(getContext(), this.B);
        this.y.c(R.layout.longan_timelapse_wheel_item);
        this.y.d(R.id.longan_timelapse_wheel_text);
        this.x = new b(getContext(), this.z);
        this.x.c(R.layout.longan_timelapse_wheel_item);
        this.x.d(R.id.longan_timelapse_wheel_text);
        this.F = this.I.a;
        this.E = this.I.b;
        a(this.F, this.E);
        int wheelPos = getWheelPos(this.I.a, this.A);
        int wheelPos2 = getWheelPos(this.I.b, this.C);
        this.c.setViewAdapter(this.x);
        this.c.setCurrentItem(wheelPos);
        this.d.setViewAdapter(this.x);
        this.d.setCurrentItem(wheelPos);
        this.x.i(wheelPos);
        this.f.setViewAdapter(this.y);
        this.f.setCurrentItem(wheelPos2);
        this.e.setViewAdapter(this.y);
        this.e.setCurrentItem(wheelPos2);
        this.y.i(wheelPos2);
        e();
        this.p = (Button) findViewById(R.id.longan_timelapse_set_land_next);
        this.p.setOnClickListener(this);
        this.q = (Button) findViewById(R.id.longan_timelapse_set_port_next);
        this.q.setOnClickListener(this);
    }

    private void d() {
        int wheelPos = getWheelPos(this.F, this.A);
        int wheelPos2 = getWheelPos(this.E, this.C);
        this.c.setCurrentItem(wheelPos);
        this.d.setCurrentItem(wheelPos);
        this.x.i(wheelPos);
        this.f.setCurrentItem(wheelPos2);
        this.e.setCurrentItem(wheelPos2);
        this.y.i(wheelPos2);
    }

    private void e() {
        antistatic.spinnerwheel.b anonymousClass1 = new antistatic.spinnerwheel.b(this) {
            final /* synthetic */ LonganTimelapseSetLayout a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel, int i, int i2) {
                this.a.x.i(abstractWheel.getCurrentItem());
                this.a.F = this.a.A[abstractWheel.getCurrentItem()];
                this.a.a(this.a.F, this.a.E);
                if (LonganNewTimelapseMainLayout.e) {
                    this.a.L.playSimpleSound(this.a.getContext());
                }
            }
        };
        this.c.addChangingListener(anonymousClass1);
        this.d.addChangingListener(anonymousClass1);
        antistatic.spinnerwheel.d anonymousClass2 = new antistatic.spinnerwheel.d(this) {
            final /* synthetic */ LonganTimelapseSetLayout a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel) {
            }

            public void b(AbstractWheel abstractWheel) {
                this.a.F = this.a.A[abstractWheel.getCurrentItem()];
                this.a.I.a(this.a.F);
                this.a.I.a(1, this.a.F, this.a.E, true, 0, 0);
                this.a.H = true;
                this.a.g();
                this.a.p.setTextColor(this.a.getResources().getColor(R.color.longan_blue_light));
                this.a.q.setTextColor(this.a.getResources().getColor(R.color.longan_blue_light));
                this.a.p.setEnabled(true);
                this.a.q.setEnabled(true);
                if (DataCameraGetPushShotParams.getInstance().getTimelapseSaveType() != 0 && (this.a.F / 10 <= 1 || (this.a.I.c() == b.b.MOTION && this.a.f.getCurrentItem() == 0))) {
                    e.c(this.a.getContext(), R.string.longan_timelapse_tip_format);
                    this.a.p.setTextColor(this.a.getResources().getColor(R.color.grid_line));
                    this.a.q.setTextColor(this.a.getResources().getColor(R.color.grid_line));
                    this.a.p.setEnabled(false);
                    this.a.q.setEnabled(false);
                }
                if (!DataCameraGetPushShotParams.getInstance().isReciprocal() && this.a.F / 10 <= DataCameraGetPushShotParams.getInstance().getShutter()) {
                    e.c(this.a.getContext(), R.string.longan_timelapse_tip_shutter);
                }
            }
        };
        this.c.addScrollingListener(anonymousClass2);
        this.d.addScrollingListener(anonymousClass2);
        anonymousClass1 = new antistatic.spinnerwheel.b(this) {
            final /* synthetic */ LonganTimelapseSetLayout a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel, int i, int i2) {
                this.a.y.i(abstractWheel.getCurrentItem());
                this.a.E = this.a.C[abstractWheel.getCurrentItem()];
                this.a.a(this.a.F, this.a.E);
                if (LonganNewTimelapseMainLayout.e) {
                    this.a.L.playSimpleSound(this.a.getContext());
                }
            }
        };
        this.f.addChangingListener(anonymousClass1);
        this.e.addChangingListener(anonymousClass1);
        anonymousClass2 = new antistatic.spinnerwheel.d(this) {
            final /* synthetic */ LonganTimelapseSetLayout a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel) {
            }

            public void b(AbstractWheel abstractWheel) {
                this.a.E = this.a.C[abstractWheel.getCurrentItem()];
                this.a.I.b(this.a.E);
                if (this.a.I.c() == b.b.STATIONARY) {
                    this.a.I.a(1, this.a.F, this.a.E, true, 0, 0);
                    this.a.H = true;
                    this.a.g();
                } else if (abstractWheel.getCurrentItem() == 0 || (DataCameraGetPushShotParams.getInstance().getTimelapseSaveType() != 0 && this.a.F / 10 <= 1)) {
                    this.a.p.setTextColor(this.a.getResources().getColor(R.color.grid_line));
                    this.a.q.setTextColor(this.a.getResources().getColor(R.color.grid_line));
                    this.a.p.setEnabled(false);
                    this.a.q.setEnabled(false);
                } else {
                    this.a.p.setTextColor(this.a.getResources().getColor(R.color.longan_blue_light));
                    this.a.q.setTextColor(this.a.getResources().getColor(R.color.longan_blue_light));
                    this.a.p.setEnabled(true);
                    this.a.q.setEnabled(true);
                }
            }
        };
        this.f.addScrollingListener(anonymousClass2);
        this.e.addScrollingListener(anonymousClass2);
    }

    public int getPixel(int i) {
        return getResources().getDimensionPixelOffset(i);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_timelapse_mode_stationary_tv || id == R.id.longan_timelapse_port_mode_stationary_tv) {
            if (this.I.c() != b.b.STATIONARY) {
                this.I.a(b.b.STATIONARY);
                this.p.setText(getResources().getString(R.string.longan_timelapse_start));
                this.q.setText(getResources().getString(R.string.longan_timelapse_start));
                this.l.setSelected(true);
                this.m.setSelected(false);
                this.n.setSelected(true);
                this.o.setSelected(false);
                a(this.F, this.E);
                this.y.a(0, this.y.h() - 1);
            }
        } else if (id == R.id.longan_timelapse_mode_motion_tv || id == R.id.longan_timelapse_port_mode_motion_tv) {
            if (this.I.c() != b.b.MOTION) {
                this.I.a(b.b.MOTION);
                this.p.setText(getResources().getString(R.string.longan_timelapse_next));
                this.q.setText(getResources().getString(R.string.longan_timelapse_next));
                this.l.setSelected(false);
                this.m.setSelected(true);
                this.n.setSelected(false);
                this.o.setSelected(true);
                a(this.F, this.E);
                this.y.a(1, this.y.h() - 1);
            }
        } else if (id != R.id.longan_timelapse_set_land_next && id != R.id.longan_timelapse_set_port_next) {
        } else {
            if (this.I.c() == b.b.STATIONARY) {
                c.a().e(k.START);
            } else if ((DataCameraGetPushShotParams.getInstance().getTimelapseSaveType() == 0 || this.F / 10 > 1) && this.f.getCurrentItem() != 0) {
                c.a().e(m.SHOW_MOTION_LY);
            }
        }
    }

    private void f() {
        CharSequence charSequence = "--:--:--";
        this.t.setText(charSequence);
        this.u.setText(charSequence);
    }

    public static int getWheelPos(int i, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return 0;
    }

    public static int getMinInterval(int i, int[] iArr) {
        for (int length = iArr.length - 1; length >= 0; length--) {
            if (iArr[length] < i) {
                return length;
            }
        }
        return 0;
    }

    public void hide() {
        this.a.setVisibility(8);
        this.b.setVisibility(8);
        this.G = false;
    }

    public void show() {
        if (getResources().getConfiguration().orientation == 2) {
            this.a.setVisibility(0);
            this.b.setVisibility(8);
        } else {
            this.a.setVisibility(8);
            this.b.setVisibility(0);
        }
        this.G = true;
    }

    private void a(int i, int i2) {
        CharSequence generateTimeConvert = generateTimeConvert(i, i2);
        b bVar = this.I;
        b.h = generateTimeConvert;
        this.t.setText(generateTimeConvert);
        this.u.setText(generateTimeConvert);
    }

    public static String generateTimeConvert(int i, int i2) {
        if (i2 == 0) {
            return "-- : -- : --";
        }
        return convertSecondToTime((long) ((int) ((((float) i2) / (((float) i) / 10.0f)) / 30.0f)));
    }

    public static String convertSecondToTime(long j) {
        String str;
        String str2;
        int i = ((int) j) / 3600;
        int i2 = (((int) j) % 3600) / 60;
        int i3 = ((int) j) % 60;
        if (i3 == 0) {
            i3 = 1;
        }
        String str3 = "";
        str3 = "";
        str3 = "";
        if (i <= 9) {
            str3 = "0" + i;
        } else {
            str3 = "" + i;
        }
        if (i2 <= 9) {
            str = "0" + i2;
        } else {
            str = "" + i2;
        }
        if (i3 <= 9) {
            str2 = "0" + i3;
        } else {
            str2 = "" + i3;
        }
        return "" + str3 + ":" + str + ":" + str2;
    }

    private void g() {
        if (this.H) {
            removeCallbacks(this.M);
            postDelayed(this.M, 2000);
        }
    }

    public void syncDatas(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int shutter;
        int i;
        ExposureMode exposureMode = dataCameraGetPushShotParams.getExposureMode();
        if (exposureMode == ExposureMode.b) {
            this.x.j();
        } else if (dataCameraGetPushShotParams.isReciprocal()) {
            this.x.j();
        } else {
            shutter = dataCameraGetPushShotParams.getShutter();
            this.J = getMinInterval(shutter * 10, this.A);
            if (shutter == 1) {
                this.x.a(1, Integer.MAX_VALUE);
            } else {
                this.x.a(this.J + 2, Integer.MAX_VALUE);
            }
        }
        if (dataCameraGetPushShotParams.getTimelapseSaveType() != 0) {
            if (exposureMode == ExposureMode.b) {
                i = 1;
            } else if (dataCameraGetPushShotParams.isReciprocal()) {
                i = 1;
            } else if (dataCameraGetPushShotParams.getShutter() < 2) {
                i = 1;
            } else {
                boolean z = false;
            }
            if (i != 0) {
                this.x.a(1, Integer.MAX_VALUE);
            }
        }
        i = dataCameraGetPushShotParams.getVideoRecordIntervalTime();
        shutter = dataCameraGetPushShotParams.getTimelapseDuration();
        Log.d(K, "dur" + shutter + "int" + i + "frame:" + dataCameraGetPushShotParams.getTimelapseRecordedFrame());
        if (!(i == this.F && shutter == this.E)) {
            this.F = i;
            this.E = shutter;
            d();
        }
        if ((dataCameraGetPushShotParams.isReciprocal() || this.F / 10 > dataCameraGetPushShotParams.getShutter()) && (DataCameraGetPushShotParams.getInstance().getTimelapseSaveType() == 0 || this.F / 10 > 1)) {
            this.p.setTextColor(getResources().getColor(R.color.longan_blue_light));
            this.q.setTextColor(getResources().getColor(R.color.longan_blue_light));
            this.p.setEnabled(true);
            this.q.setEnabled(true);
            return;
        }
        this.p.setTextColor(getResources().getColor(R.color.grid_line));
        this.q.setTextColor(getResources().getColor(R.color.grid_line));
        this.p.setEnabled(false);
        this.q.setEnabled(false);
    }
}
