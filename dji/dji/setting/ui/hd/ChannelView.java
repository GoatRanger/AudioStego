package dji.setting.ui.hd;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataOsdGetPushConfig;
import dji.midware.data.model.P3.DataOsdGetSDRPushCustomCodeRate;
import dji.midware.data.model.P3.DataOsdGetSdrConfig;
import dji.midware.data.model.P3.DataOsdSetConfig;
import dji.midware.data.model.P3.DataOsdSetSweepFrequency;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.util.a;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.pilot.visual.a.d;
import dji.setting.ui.hd.sdr.SdrViewStatusBtn;
import dji.setting.ui.rc.FreqSnrSdrView;
import dji.setting.ui.rc.FreqSnrView;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;
import java.util.Locale;
import lecho.lib.hellocharts.model.l;

public class ChannelView extends DividerLinearLayout implements b {
    private static final int C = 12;
    private static final int M = 0;
    private static final int N = 1;
    private static final int O = 2;
    private static final int P = 3;
    private static final int Q = 4;
    private static final int R = 5;
    private static final int S = 6;
    private static final int T = 10001;
    private static final int U = 10003;
    private static String[] W = new String[]{"ALB", "AND", "AUT", "BLR", "BEL", "BIH", "BGR", "HRV", "CYP", "CZE", "DNK", "EST", "FRO", "FIN", "FRA", "DEU", "GIB", "GRC", "HUN", "ISL", "IRL", "ITA", "LVA", "LIE", "LTU", "LUX", "MKD", "MLT", "MDA", "MCO", "NLD", "NOR", "POL", "PRT", "ROU", "RUS", "SMR", "SRB", "SVK", "SVN", "ESP", "SWE", "CHE", "UKR", "GBR", "VAT", "RSB", "IMN", "XKX", "MNE"};
    private static final int w = 3;
    private final float[] A = new float[]{0.3f, l.n, 1.0f, 1.4f, 1.7f, 2.1f, 2.7f, 3.3f, 3.9f, 4.7f, 5.2f, 6.0f, 6.6f, 7.4f, 8.6f, 9.7f, 10.6f, 11.4f, 12.3f, 13.8f, 14.9f, dji.gs.e.b.a, 17.2f, 19.1f, 20.5f, 21.3f, 22.9f};
    private final float[] B = new float[]{0.2f, 0.3f, d.c, 0.7f, 0.8f, 1.1f, 1.4f, 1.7f, 2.0f, 2.3f, 2.6f, 3.0f, 3.3f, 3.7f, 4.3f, 4.8f, 5.4f, 5.8f, 6.0f, 6.9f, 7.4f, 8.0f, 8.6f, 9.4f, 10.2f, 10.6f, 11.4f};
    private int D = 1;
    private BandHdView E;
    private SdrBandWidthSpinner F;
    private SweepRangeView G;
    private ProgressBar H;
    private SdrViewStatusBtn I;
    private LinearLayout J;
    private View K;
    private View L;
    private Handler V = new Handler(new Callback(this) {
        final /* synthetic */ ChannelView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            boolean z = true;
            FreqSnrView j;
            int i;
            switch (message.what) {
                case 0:
                    this.a.f();
                    this.a.j();
                    j = this.a.e;
                    if (dji.pilot.c.d.c != 1) {
                        z = false;
                    }
                    j.setIsAuto(z);
                    break;
                case 1:
                    i = FreqSnrView.a ? this.a.a - FreqSnrView.b : this.a.a;
                    if (i < 0) {
                        i = 0;
                    }
                    if (FreqSnrView.a) {
                        if (i >= 8) {
                            i = 7;
                        }
                    } else if (i >= 32) {
                        i = 31;
                    }
                    this.a.f.setIndex(i);
                    break;
                case 2:
                    j = this.a.e;
                    if (dji.pilot.c.d.c != 1) {
                        z = false;
                    }
                    j.setIsAuto(z);
                    this.a.j();
                    this.a.k();
                    break;
                case 3:
                    this.a.f();
                    break;
                case 4:
                    Toast.makeText(this.a.getContext(), (String) message.obj, message.arg1).show();
                    break;
                case 5:
                    i = dji.pilot.c.d.d - 1;
                    if (i < 0) {
                        i = 0;
                    }
                    this.a.l.setProgress(i);
                    this.a.k();
                    break;
                case 6:
                    this.a.k();
                    break;
            }
            return false;
        }
    });
    protected int a;
    protected float b;
    private LinearLayout c;
    private RadioGroup d;
    private FreqSnrView e;
    private DJISpinnerButton f;
    private LinearLayout g;
    private RadioButton h;
    private RadioButton i;
    private SeekBar l;
    private LinearLayout m;
    private TextView n;
    private WorkingFreqView o;
    private int[] p = new int[]{2, 4, 6, 8, 10};
    private float[] q = new float[]{DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity, 3.0f, 2.0f, a.b.c, 0.7f};
    private String r = "%dMbps(%s)";
    private String s = "%.2f";
    private String[] t = null;
    private String[] u = null;
    private int v = 0;
    private boolean x = false;
    private String y = "%.2fMbps";
    private final float[] z = new float[]{l.n, 1.2f, 2.1f, 2.7f, 3.4f, 4.3f, 5.4f, 6.6f, 7.7f, 9.2f, 10.6f, 11.9f, 13.2f, 14.9f, 17.2f, 19.1f, 21.3f, 22.9f, 24.6f, 27.5f, 29.4f, 32.9f, 35.2f, 38.3f, 41.3f, 43.0f, 46.2f};

    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] a = new int[p.values().length];

        static {
            try {
                a[p.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[p.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public ChannelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_hd_channel);
        if (!isInEditMode()) {
            this.e = (FreqSnrView) findViewById(R.id.fpv_hd_channel_freq);
            this.f = (DJISpinnerButton) findViewById(R.id.fpv_hd_channel_spinner);
            this.g = (LinearLayout) findViewById(R.id.fpv_hd_channel_select_ly);
            this.m = (LinearLayout) findViewById(R.id.fpv_hd_quality_select_ly);
            this.l = (SeekBar) findViewById(R.id.fpv_hd_mcs_value_seekbar);
            this.n = (TextView) findViewById(R.id.fpv_hd_mcs_value);
            this.h = (RadioButton) findViewById(R.id.fpv_hd_channel_auto_radio);
            this.i = (RadioButton) findViewById(R.id.fpv_hd_channel_custom_radio);
            this.c = (LinearLayout) findViewById(R.id.fpv_hd_channel_ly);
            this.d = (RadioGroup) findViewById(R.id.fpv_hd_channel_rg);
            this.J = (LinearLayout) findViewById(R.id.fpv_hd_channel_freq_ly);
            this.d.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
                final /* synthetic */ ChannelView a;

                {
                    this.a = r1;
                }

                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    this.a.setChannelMode(i);
                }
            });
            c();
            this.l.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
                final /* synthetic */ ChannelView a;

                {
                    this.a = r1;
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    int progress = seekBar.getProgress() + 1;
                    this.a.setMcs(progress);
                    if (progress != dji.pilot.c.d.d) {
                        e.a("FPV_ImageTransmissionSettings_Slider_ImageTransmissionQuality");
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                }
            });
            this.o = (WorkingFreqView) findViewById(R.id.setting_hd_newlb_ofdm_workfreq);
            this.o.setOnOfdmWoringFreqListener(new WorkingFreqView.a(this) {
                final /* synthetic */ ChannelView a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    this.a.configOfdmMode(i);
                }
            });
            b();
        }
    }

    private void b() {
        this.E = (BandHdView) findViewById(R.id.setting_hd_band_type_select);
        this.F = (SdrBandWidthSpinner) findViewById(R.id.setting_hd_band_width_select);
        this.F.setOnBandChangedListener(new SdrBandWidthSpinner.a(this) {
            final /* synthetic */ ChannelView a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i == 0) {
                    this.a.l.setMax(this.a.z.length - 1);
                } else if (i == 1) {
                    this.a.l.setMax(this.a.A.length - 1);
                } else if (i == 2) {
                    this.a.l.setMax(this.a.B.length - 1);
                }
                this.a.i();
            }

            public void a() {
            }
        });
        this.H = (ProgressBar) findViewById(R.id.fpv_hd_mcs_value_progressbar_sdr);
        this.G = (SweepRangeView) findViewById(R.id.setting_ui_hd_sdr_sweep_check);
        this.G.setOnRangeChangedListener(new SweepRangeView.a(this) {
            final /* synthetic */ ChannelView a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.D = i;
                this.a.V.sendEmptyMessage(0);
            }
        });
        this.L = findViewById(R.id.setting_hd_sdr_channel_mode);
        this.K = findViewById(R.id.sdr_freq_view);
        this.I = (SdrViewStatusBtn) findViewById(R.id.sdr_view_status_btn);
        this.I.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ChannelView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.I.setVisibility(8);
                this.a.K.setVisibility(0);
            }
        });
        onEventMainThread(i.getInstance().c());
    }

    private void c() {
        this.n.setText(String.format("%dMbps", new Object[]{Integer.valueOf(0)}));
    }

    public void onEventMainThread(ProductType productType) {
        if (a.d()) {
            dji.pilot.c.d.i = 1;
            this.J.setVisibility(8);
            this.e.go();
            if (this.K.isShown()) {
                this.K.setVisibility(0);
            } else {
                this.I.setVisibility(0);
            }
            this.L.setVisibility(0);
            this.c.setVisibility(8);
            this.g.setVisibility(8);
            this.f.setEnabled(false);
            this.l.setVisibility(8);
            this.H.setVisibility(0);
            return;
        }
        dji.pilot.c.d.i = 0;
        this.J.setVisibility(0);
        this.e.show();
        this.I.setVisibility(8);
        this.K.setVisibility(8);
        this.L.setVisibility(8);
        this.c.setVisibility(0);
        CameraType cameraType = CameraType.OTHER;
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (productType == ProductType.N1 && r0 == CameraType.DJICameraTypeGD600) {
            this.l.setMax(1);
        } else {
            this.l.setMax(3);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (dji.pilot.c.d.i == 1) {
                h();
            } else {
                d();
            }
            c.a().a(this);
            if (dji.pilot.c.d.b == MODE.b) {
                DJILogHelper.getInstance().LOGD("", "slave", false, true);
                this.m.setVisibility(8);
                this.g.setVisibility(8);
            } else {
                DJILogHelper.getInstance().LOGD("", "master", false, true);
                this.m.setVisibility(0);
                if (!a.d()) {
                    this.g.setVisibility(0);
                }
            }
            onEventMainThread(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void d() {
        String[] strArr;
        int i;
        g();
        DataOsdGetPushConfig.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ ChannelView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.e();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                Log.d("", "DataOsdGetConfig=" + aVar);
            }
        });
        if (FreqSnrView.a) {
            strArr = new String[8];
            i = FreqSnrView.b;
        } else {
            strArr = new String[32];
            i = 0;
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            strArr[i2] = getContext().getString(R.string.setting_ui_hd_channel_select_name, new Object[]{Integer.valueOf((i2 + i) + 1)});
        }
        this.f.setData(strArr, 0, (b) this);
    }

    public void configOfdmMode(int i) {
        String[] strArr;
        int i2;
        this.e.configOfdmFreqMode(i);
        String[] strArr2 = new String[8];
        if (i == 0) {
            if (FreqSnrView.a) {
                strArr = new String[8];
                i2 = FreqSnrView.b;
            } else {
                strArr = new String[32];
                i2 = 0;
            }
        } else if (i == 2) {
            strArr = new String[29];
            i2 = 0;
        } else {
            strArr = strArr2;
            i2 = 0;
        }
        for (int i3 = 0; i3 < strArr.length; i3++) {
            strArr[i3] = getContext().getString(R.string.setting_ui_hd_channel_select_name, new Object[]{Integer.valueOf((i3 + i2) + 1)});
        }
        this.f.setData(strArr, 0, (b) this);
        this.V.sendEmptyMessage(1);
        this.e.postInvalidate();
    }

    public void onItemClick(int i) {
        if (FreqSnrView.a) {
            i += FreqSnrView.b;
        }
        if (this.a != i) {
            e.a("FPV_ImageTransmissionSettings_PullDownView_SelectChannel");
            Log.d("", "mChannelId=" + this.a);
            Log.d("", "position=" + i);
            setChannel(i);
        }
    }

    private void setChannel(final int i) {
        DataOsdSetConfig.getInstance().c(0).d(i).start(new dji.midware.e.d(this) {
            final /* synthetic */ ChannelView b;

            public void onSuccess(Object obj) {
                this.b.a = i;
                this.b.e.setWorkFreqIndex(this.b.a);
                this.b.e.postInvalidate();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.V.sendMessage(this.b.V.obtainMessage(4, 1000, 0, "Channel " + (i + 1) + " set fail"));
                this.b.V.sendEmptyMessage(1);
            }
        });
    }

    private void e() {
        int i = 1;
        this.a = DataOsdGetPushConfig.getInstance().getChannel();
        this.e.setWorkFreqIndex(this.a);
        Log.d("", "mChannelId=" + this.a);
        this.V.sendEmptyMessage(1);
        if (!DataOsdGetPushConfig.getInstance().getIsAuto()) {
            i = 0;
        }
        dji.pilot.c.d.c = i;
        Log.d("", "mIsAuto=" + dji.pilot.c.d.c);
        this.V.sendEmptyMessage(0);
        dji.pilot.c.d.d = DataOsdGetPushConfig.getInstance().getMcs();
        dji.pilot.c.d.d = dji.pilot.c.d.d > 4 ? 4 : dji.pilot.c.d.d;
        Log.d("", "mcs=" + dji.pilot.c.d.d);
        this.V.sendEmptyMessage(5);
    }

    private void f() {
        if (dji.pilot.c.d.c == 0) {
            this.i.setChecked(true);
            e.a("FPV_ImageTransmissionSettings_Channel_Button_Custom");
            return;
        }
        this.h.setChecked(true);
        e.a("FPV_ImageTransmissionSettings_Channel_Button_Auto");
    }

    private void g() {
        if (isEuropeCountry()) {
            this.c.setVisibility(8);
            this.m.setVisibility(8);
            this.g.setVisibility(8);
            setChannelMode(R.id.fpv_hd_channel_auto_radio);
            return;
        }
        this.c.setVisibility(0);
    }

    private void setChannelMode(int i) {
        boolean z;
        int i2 = 1;
        if (i == R.id.fpv_hd_channel_auto_radio) {
            z = true;
        } else {
            z = false;
        }
        int i3 = dji.pilot.c.d.c;
        if (!z) {
            i2 = 0;
        }
        if (i3 != i2) {
            if (z) {
                DataOsdSetConfig.getInstance().a(z).start(new dji.midware.e.d(this) {
                    final /* synthetic */ ChannelView b;

                    public void onSuccess(Object obj) {
                        dji.pilot.c.d.c = z ? 1 : 0;
                        this.b.V.sendEmptyMessage(2);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.b.V.sendEmptyMessage(3);
                    }
                });
            } else {
                a(z);
            }
        }
    }

    private void a(final boolean z) {
        DataOsdSetConfig.getInstance().a(z).start(new dji.midware.e.d(this) {
            final /* synthetic */ ChannelView b;

            public void onSuccess(Object obj) {
                dji.pilot.c.d.c = z ? 1 : 0;
                this.b.V.sendEmptyMessage(2);
                this.b.setMcs(2);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.V.sendEmptyMessage(3);
            }
        });
    }

    private void h() {
        int i;
        DataOsdGetSdrConfig.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ ChannelView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.v = 0;
                DJILogHelper.getInstance().LOGD("", "DataOsdGetPushSdrConfig success", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                Log.d("", "DataOsdGetPushSdrConfig=" + aVar);
                this.a.i();
                DJILogHelper.getInstance().LOGD("", "DataOsdGetPushSdrConfig=" + aVar, false, true);
            }
        });
        this.t = new String[32];
        this.u = new String[64];
        for (i = 0; i < this.t.length; i++) {
            this.t[i] = getContext().getString(R.string.setting_ui_hd_channel_select_name2) + ((i + 0) + 1);
        }
        for (i = 0; i < this.u.length; i++) {
            this.u[i] = getContext().getString(R.string.setting_ui_hd_channel_select_name2) + (((((float) i) / 2.0f) + ((float) null)) + 1.0f);
        }
        if (FreqSnrSdrView.a) {
            String[] strArr = new String[8];
            int i2 = FreqSnrSdrView.b;
            for (i = 0; i < strArr.length; i++) {
                strArr[i] = getContext().getString(R.string.setting_ui_hd_channel_select_name2) + ((i + i2) + 1);
            }
            this.f.setData(strArr, 0, (b) this);
            return;
        }
        this.f.setData(this.t, 0, (b) this);
    }

    private void i() {
        DataOsdGetSdrConfig.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ ChannelView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.v = 0;
                DJILogHelper.getInstance().LOGD("", "DataOsdGetPushSdrConfig success", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                Log.d("", "DataOsdGetPushSdrConfig=" + aVar);
                if (this.a.v < 3) {
                    this.a.i();
                    this.a.v = this.a.v + 1;
                }
                DJILogHelper.getInstance().LOGD("", "DataOsdGetPushSdrConfig=" + aVar, false, true);
            }
        });
    }

    private void setMcs(final int i) {
        if (i != dji.pilot.c.d.d) {
            DataOsdSetConfig.getInstance().c(0).e(i).start(new dji.midware.e.d(this) {
                final /* synthetic */ ChannelView b;

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD("", "mcs set ok");
                    dji.pilot.c.d.d = i;
                    this.b.V.sendEmptyMessage(5);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("", "mcs set failure");
                }
            });
        }
    }

    private void j() {
        if (dji.pilot.c.d.i == 1) {
            this.g.setVisibility(8);
            this.f.setEnabled(false);
            this.l.setVisibility(8);
            this.H.setVisibility(0);
        } else if (dji.pilot.c.d.c == 1) {
            this.m.setVisibility(8);
            this.g.setVisibility(8);
            DataOsdSetSweepFrequency.getInstance().b(false).start(null);
        } else {
            if (dji.pilot.c.d.b == MODE.b) {
                this.m.setVisibility(8);
                this.g.setVisibility(8);
            } else {
                this.m.setVisibility(0);
                this.g.setVisibility(0);
            }
            DataOsdSetSweepFrequency.getInstance().b(true).start(null);
        }
    }

    private void k() {
        if (dji.pilot.c.d.i != 1) {
            this.n.setText(String.format(this.r, new Object[]{Integer.valueOf(this.p[dji.pilot.c.d.d]), a(dji.pilot.c.d.d)}));
        } else if (dji.pilot.c.d.c == 1) {
            r0 = DataOsdGetSDRPushCustomCodeRate.getInstance().getCodeRate();
            this.n.setText(String.format(this.y, new Object[]{Float.valueOf(r0)}));
        } else {
            r0 = DataOsdGetSDRPushCustomCodeRate.getInstance().getCodeRate();
            this.n.setText(String.format(this.y, new Object[]{Float.valueOf(r0)}));
        }
    }

    public void onEventMainThread(DataOsdGetSDRPushCustomCodeRate dataOsdGetSDRPushCustomCodeRate) {
        float codeRate = dataOsdGetSDRPushCustomCodeRate.getCodeRate();
        this.n.setText(String.format(this.y, new Object[]{Float.valueOf(codeRate)}));
        this.H.setProgress((int) ((codeRate / 12.0f) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
    }

    private String a(int i) {
        float f = this.q[i];
        String str = "km";
        if (!DJIGenSettingDataManager.getInstance().z()) {
            str = "mi";
            f /= a.b.d;
        }
        return subZeroAndDot(String.format(this.s, new Object[]{Float.valueOf(f)})) + str;
    }

    public void onEventMainThread(p pVar) {
        switch (AnonymousClass7.a[pVar.ordinal()]) {
            case 2:
                if (dji.pilot.c.d.b == MODE.b) {
                    DJILogHelper.getInstance().LOGD("", "slave fuck", false, true);
                    this.m.setVisibility(8);
                    this.g.setVisibility(8);
                    return;
                }
                DJILogHelper.getInstance().LOGD("", "master fuck", false, true);
                this.m.setVisibility(0);
                if (dji.pilot.c.d.c != 1) {
                    this.g.setVisibility(0);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushConfig dataOsdGetPushConfig) {
        this.a = DataOsdGetPushConfig.getInstance().getChannel();
        this.e.setWorkFreqIndex(this.a);
        this.V.sendEmptyMessage(1);
    }

    public static String subZeroAndDot(String str) {
        if (str.indexOf(".") > 0) {
            return str.replaceAll("0+?$", "").replaceAll("[.]$", "");
        }
        return str;
    }

    public static boolean isEuropeCountry() {
        int i = 0;
        while (i < W.length) {
            try {
                if (W[i].equals(Locale.getDefault().getISO3Country())) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
