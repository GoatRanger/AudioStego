package dji.setting.ui.flyc;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.alipay.sdk.j.i;
import dji.common.error.DJIError;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetControlMode.ChannelCustomModel;
import dji.midware.data.model.P3.DataRcSetControlMode.ChannelType;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.e.a.a$a;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.setting.ui.widget.DJITouchCurveView;
import dji.setting.ui.widget.DJITouchCurveView.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;
import java.util.ArrayList;

public class ExpView extends DividerLinearLayout {
    private static final int f = 1;
    private static final int g = 2;
    private static final int v = 300;
    private static final int w = 0;
    private static final int x = 1;
    private static final int y = 2;
    private ArrayList<String> A;
    private int B = Integer.MIN_VALUE;
    private ParamInfo C;
    private ParamInfo D;
    private ParamInfo E;
    private ParamInfo F = null;
    private ParamInfo G = null;
    private ParamInfo H = null;
    private ParamInfo I = null;
    private ParamInfo J = null;
    private ParamInfo K = null;
    private OnEditorActionListener L = new OnEditorActionListener(this) {
        final /* synthetic */ ExpView a;

        {
            this.a = r1;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (textView == this.a.l) {
                this.a.a(false, true);
            } else if (textView == this.a.n) {
                this.a.b(false, true);
            } else if (textView == this.a.p) {
                this.a.c(false, true);
            }
            return false;
        }
    };
    private a M = new a(this) {
        final /* synthetic */ ExpView a;

        {
            this.a = r1;
        }

        public void a(View view, float f) {
            if (view == this.a.i) {
                this.a.a = f;
                this.a.P.sendEmptyMessage(0);
            } else if (view == this.a.m) {
                this.a.b = f;
                this.a.P.sendEmptyMessage(1);
            } else {
                this.a.c = f;
                this.a.P.sendEmptyMessage(2);
            }
        }

        public void a(View view) {
            view.setTag(Boolean.valueOf(true));
        }

        public void b(View view) {
            view.setTag(Boolean.valueOf(false));
            if (view == this.a.i) {
                this.a.a(false, true);
            } else if (view == this.a.m) {
                this.a.b(false, true);
            } else {
                this.a.c(false, true);
            }
        }
    };
    private TextWatcher N = new TextWatcher(this) {
        final /* synthetic */ ExpView a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (this.a.l.isFocused()) {
                this.a.a(true, false);
            } else if (this.a.n.isFocused()) {
                this.a.b(true, false);
            } else if (this.a.p.isFocused()) {
                this.a.c(true, false);
            }
        }
    };
    private String O = "%.2f";
    private Handler P = new Handler(this) {
        final /* synthetic */ ExpView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int selectionStart;
            String format;
            EditText e;
            switch (message.what) {
                case 0:
                    selectionStart = this.a.l.getSelectionStart();
                    format = String.format(this.a.O, new Object[]{Float.valueOf(this.a.a)});
                    this.a.l.setText(format);
                    e = this.a.l;
                    if (selectionStart > format.length()) {
                        selectionStart = format.length();
                    }
                    e.setSelection(selectionStart);
                    this.a.i.setOrder(this.a.a);
                    return;
                case 1:
                    selectionStart = this.a.n.getSelectionStart();
                    format = String.format(this.a.O, new Object[]{Float.valueOf(this.a.b)});
                    this.a.n.setText(format);
                    e = this.a.n;
                    if (selectionStart > format.length()) {
                        selectionStart = format.length();
                    }
                    e.setSelection(selectionStart);
                    this.a.m.setOrder(this.a.b);
                    return;
                case 2:
                    selectionStart = this.a.p.getSelectionStart();
                    format = String.format(this.a.O, new Object[]{Float.valueOf(this.a.c)});
                    this.a.p.setText(format);
                    e = this.a.p;
                    if (selectionStart > format.length()) {
                        selectionStart = format.length();
                    }
                    e.setSelection(selectionStart);
                    this.a.o.setOrder(this.a.c);
                    return;
                case 200:
                    if (this.a.Q) {
                        this.a.h.setVisibility(8);
                        return;
                    } else {
                        this.a.h.setVisibility(0);
                        return;
                    }
                case 300:
                    if (dji.pilot.publics.e.a.e() && dji.pilot.publics.e.a.w(null)) {
                        this.a.q.setVisibility(0);
                        this.a.getSportData();
                        if (dji.sdksharedlib.e.a.a.getInstance().b(e.cw)) {
                            this.a.getGentleData();
                            return;
                        }
                        return;
                    }
                    this.a.q.setVisibility(8);
                    this.a.a(0, false);
                    return;
                default:
                    return;
            }
        }
    };
    private boolean Q = false;
    private volatile int R = 0;
    private int S = 364;
    private int T = 1024;
    private int U = 1684;
    private int V = 1320;
    protected float a;
    protected float b;
    protected float c;
    protected ControlMode d;
    protected ArrayList<ChannelCustomModel> e;
    private LinearLayout h;
    private DJITouchCurveView i;
    private EditText l;
    private DJITouchCurveView m;
    private EditText n;
    private DJITouchCurveView o;
    private EditText p;
    private View q;
    private RadioGroup r;
    private TextView s = null;
    private ArrayList<String> t;
    private DataRcGetPushParams u = DataRcGetPushParams.getInstance();
    private ArrayList<String> z;

    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] a = new int[ChannelType.values().length];
        static final /* synthetic */ int[] b = new int[ControlMode.values().length];

        static {
            try {
                b[ControlMode.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[ControlMode.a.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[ControlMode.c.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[ControlMode.d.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ChannelType.d.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[ChannelType.e.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[ChannelType.c.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[ChannelType.b.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public ExpView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_flyc_exp);
        if (!isInEditMode()) {
            this.i = (DJITouchCurveView) findViewById(R.id.fpv_rc_gain_thr_curve);
            this.l = (EditText) findViewById(R.id.fpv_rc_gain_thr_edit);
            this.m = (DJITouchCurveView) findViewById(R.id.fpv_rc_gain_yaw_curve);
            this.n = (EditText) findViewById(R.id.fpv_rc_gain_yaw_edit);
            this.o = (DJITouchCurveView) findViewById(R.id.fpv_rc_gain_tilt_curve);
            this.p = (EditText) findViewById(R.id.fpv_rc_gain_tilt_edit);
            this.h = (LinearLayout) findViewById(R.id.fpv_rc_gain_tiltly);
            this.s = (TextView) findViewById(R.id.setting_ui_exp_desc_tv);
            this.o.setDoubleVertical(true);
            this.i.setOnDJICurveTouchListener(this.M);
            this.m.setOnDJICurveTouchListener(this.M);
            this.o.setOnDJICurveTouchListener(this.M);
            this.l.addTextChangedListener(this.N);
            this.n.addTextChangedListener(this.N);
            this.p.addTextChangedListener(this.N);
            this.l.setOnEditorActionListener(this.L);
            this.n.setOnEditorActionListener(this.L);
            this.p.setOnEditorActionListener(this.L);
            this.t = new ArrayList();
            this.t.add(e.cn);
            this.t.add(e.cp);
            this.t.add(e.co);
            this.z = new ArrayList();
            this.z.add(e.cq);
            this.z.add(e.cs);
            this.z.add(e.cr);
            this.A = new ArrayList();
            this.A.add(e.cw);
            this.A.add(e.cv);
            this.A.add(e.cu);
            this.i.setOrder(DJITouchCurveView.b);
            this.m.setOrder(DJITouchCurveView.b);
            this.o.setOrder(DJITouchCurveView.b);
            this.l.setText(String.format(this.O, new Object[]{Float.valueOf(DJITouchCurveView.b)}));
            this.n.setText(String.format(this.O, new Object[]{Float.valueOf(DJITouchCurveView.b)}));
            this.p.setText(String.format(this.O, new Object[]{Float.valueOf(DJITouchCurveView.b)}));
            d();
        }
    }

    private void a() {
        onEventBackgroundThread(ServiceManager.getInstance().isRemoteOK() ? o.b : o.a);
        this.P.sendEmptyMessage(200);
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventBackgroundThread(DataOsdGetPushCommon.getInstance());
        }
        this.i.setTag(Boolean.valueOf(false));
        this.m.setTag(Boolean.valueOf(false));
        this.o.setTag(Boolean.valueOf(false));
        dji.sdksharedlib.e.a.a.getInstance().a(this.t, new b.e(this) {
            final /* synthetic */ ExpView a;

            {
                this.a = r1;
            }

            public void a(Object obj) {
                this.a.a = this.a.a(this.a.C.value);
                this.a.b = this.a.a(this.a.D.value);
                this.a.c = this.a.a(this.a.E.value);
                DJILogHelper.getInstance().LOGD("View", "get thr=" + this.a.a + " yaw=" + this.a.b + " tilt=" + this.a.c, false, true);
                this.a.P.sendEmptyMessage(0);
                this.a.P.sendEmptyMessage(1);
                this.a.P.sendEmptyMessage(2);
            }

            public void a(DJIError dJIError) {
                DJILogHelper.getInstance().LOGD("View", "get thr yaw tilt fail " + dJIError.getDescription(), false, true);
                this.a.P.sendEmptyMessage(3);
            }
        });
        DataRcGetControlMode.getInstance().start(new d(this) {
            final /* synthetic */ ExpView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.d = DataRcGetControlMode.getInstance().getControlType();
                if (this.a.d == ControlMode.d) {
                    this.a.e = DataRcGetControlMode.getInstance().getChannels();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
        getSportData();
        getGentleData();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private void a(boolean z, boolean z2) {
        final float a = a(this.l);
        boolean a2 = a(a);
        dji.setting.a.b.a(getContext(), this.l, a2);
        if (!z) {
            if (a != this.a || z2) {
                if (!z2) {
                    dji.pilot.fpv.d.e.a("FPV_RCSettings_MasterRCControlSettings_StickEXPCurve_TextField_ThrottleExpCurve");
                }
                boolean booleanValue = ((Boolean) this.i.getTag()).booleanValue();
                if (a2 && !booleanValue) {
                    float f;
                    if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 16) {
                        f = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity * a;
                    } else {
                        f = a;
                    }
                    dji.sdksharedlib.e.a.a.getInstance().a(c(0), Float.valueOf(f), new b.e(this) {
                        final /* synthetic */ ExpView b;

                        public void a(Object obj) {
                            this.b.a = a;
                            this.b.P.sendEmptyMessage(0);
                        }

                        public void a(DJIError dJIError) {
                        }
                    });
                }
            }
        }
    }

    private void b(boolean z, boolean z2) {
        final float a = a(this.n);
        boolean a2 = a(a);
        dji.setting.a.b.a(getContext(), this.n, a2);
        if (!z) {
            if (a != this.b || z2) {
                if (!z2) {
                    dji.pilot.fpv.d.e.a("FPV_RCSettings_MasterRCControlSettings_StickEXPCurve_TextField_YawEXPCurve");
                }
                boolean booleanValue = ((Boolean) this.m.getTag()).booleanValue();
                if (a2 && !booleanValue) {
                    float f;
                    if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 16) {
                        f = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity * a;
                    } else {
                        f = a;
                    }
                    dji.sdksharedlib.e.a.a.getInstance().a(c(1), Float.valueOf(f), new b.e(this) {
                        final /* synthetic */ ExpView b;

                        public void a(Object obj) {
                            this.b.b = a;
                            this.b.P.sendEmptyMessage(1);
                        }

                        public void a(DJIError dJIError) {
                        }
                    });
                }
            }
        }
    }

    private void c(boolean z, boolean z2) {
        final float a = a(this.p);
        boolean a2 = a(a);
        dji.setting.a.b.a(getContext(), this.p, a2);
        if (!z) {
            if (a != this.c || z2) {
                if (!z2) {
                    dji.pilot.fpv.d.e.a("FPV_RCSettings_MasterRCControlSettings_StickEXPCurve_TextField_TiltEXPCurve");
                }
                boolean booleanValue = ((Boolean) this.o.getTag()).booleanValue();
                if (a2 && !booleanValue) {
                    float f;
                    if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 16) {
                        f = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity * a;
                    } else {
                        f = a;
                    }
                    dji.sdksharedlib.e.a.a.getInstance().a(c(2), Float.valueOf(f), new b.e(this) {
                        final /* synthetic */ ExpView b;

                        public void a(Object obj) {
                            this.b.c = a;
                            this.b.P.sendEmptyMessage(2);
                        }

                        public void a(DJIError dJIError) {
                        }
                    });
                }
            }
        }
    }

    private float a(EditText editText) {
        String obj = editText.getText().toString();
        float f = -1.0f;
        if (!(obj == null || obj.isEmpty() || obj == "")) {
            try {
                f = Float.parseFloat(obj);
            } catch (Exception e) {
            }
        }
        return f;
    }

    private boolean a(float f) {
        return f <= DJITouchCurveView.a && f >= DJITouchCurveView.b;
    }

    private void a(float f, float f2) {
        DJITouchCurveView.setMaxMin(f2, f);
        this.s.setText(getResources().getString(R.string.setting_ui_flyc_exp_description, new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
    }

    public void onEventBackgroundThread(o oVar) {
        if (o.b == oVar) {
            dji.sdksharedlib.e.a.a.getInstance().b((String) this.t.get(0), new b.e(this) {
                final /* synthetic */ ExpView a;

                {
                    this.a = r1;
                }

                public void a(final Object obj) {
                    this.a.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void run() {
                            a$a dji_sdksharedlib_e_a_a_a = (a$a) obj;
                            float a = this.b.a.a(dji_sdksharedlib_e_a_a_a.a);
                            float a2 = this.b.a.a(dji_sdksharedlib_e_a_a_a.b);
                            DJILogHelper.getInstance().LOGD("", "expview567-" + a + i.b + a2 + i.b, false, true);
                            this.b.a.a(a, a2);
                        }
                    });
                }

                public void a(DJIError dJIError) {
                    DJILogHelper.getInstance().LOGD("", "expview567-" + dJIError, false, true);
                }
            });
        }
    }

    public void onEventMainThread(ProductType productType) {
        if (dji.pilot.publics.e.a.e() && dji.pilot.publics.e.a.w(productType)) {
            this.q.setVisibility(0);
            getSportData();
            if (dji.sdksharedlib.e.a.a.getInstance().b(e.cw)) {
                getGentleData();
                return;
            }
            return;
        }
        this.q.setVisibility(8);
        a(0, false);
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        boolean z = dataOsdGetPushCommon.getFlycVersion() < 5;
        if (z != this.Q) {
            this.Q = z;
            this.P.sendEmptyMessage(200);
        }
        int flycVersion = dataOsdGetPushCommon.getFlycVersion();
        if (flycVersion != this.R) {
            this.R = flycVersion;
            this.P.sendEmptyMessage(300);
        }
    }

    public void onEventBackgroundThread(DataRcGetPushParams dataRcGetPushParams) {
        DJILogHelper.getInstance().LOGD("View", "A=" + dataRcGetPushParams.getAileron() + " E=" + dataRcGetPushParams.getElevator() + " T=" + dataRcGetPushParams.getThrottle() + " R=" + dataRcGetPushParams.getRudder(), false, true);
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ ExpView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b();
            }
        });
    }

    private void b() {
        if (this.d != null) {
            switch (AnonymousClass7.b[this.d.ordinal()]) {
                case 1:
                    this.i.setVerticalX(a(this.u.getThrottle()));
                    this.m.setVerticalX(a(this.u.getRudder()));
                    this.o.setVerticalX(a(this.u.getAileron()));
                    this.o.setVerticalX2(a(this.u.getElevator()));
                    return;
                case 2:
                    this.i.setVerticalX(a(this.u.getElevator()));
                    this.m.setVerticalX(a(this.u.getRudder()));
                    this.o.setVerticalX(a(this.u.getThrottle()));
                    this.o.setVerticalX2(a(this.u.getAileron()));
                    return;
                case 3:
                    this.i.setVerticalX(a(this.u.getElevator()));
                    this.m.setVerticalX(a(this.u.getAileron()));
                    this.o.setVerticalX(a(this.u.getThrottle()));
                    this.o.setVerticalX2(a(this.u.getRudder()));
                    return;
                case 4:
                    DJILogHelper.getInstance().LOGD("View", "rcChannels.size()=" + this.e.size(), false, true);
                    for (int i = 0; i < this.e.size(); i++) {
                        ChannelCustomModel channelCustomModel = (ChannelCustomModel) this.e.get(i);
                        int i2 = channelCustomModel.a;
                        ChannelType find = ChannelType.find(channelCustomModel.b);
                        DJILogHelper.getInstance().LOGD("View", "T=" + find + " position=" + i, false, true);
                        switch (AnonymousClass7.a[find.ordinal()]) {
                            case 1:
                                this.i.setVerticalX(a(a(i, i2)));
                                break;
                            case 2:
                                this.m.setVerticalX(a(a(i, i2)));
                                break;
                            case 3:
                                this.o.setVerticalX2(a(a(i, i2)));
                                break;
                            case 4:
                                this.o.setVerticalX(a(a(i, i2)));
                                break;
                            default:
                                break;
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private int a(int i, int i2) {
        int i3 = 0;
        switch (i) {
            case 0:
                i3 = this.u.getAileron();
                break;
            case 1:
                i3 = this.u.getElevator();
                break;
            case 2:
                i3 = this.u.getThrottle();
                break;
            case 3:
                i3 = this.u.getRudder();
                break;
        }
        return i2 == 1 ? (this.T * 2) - i3 : i3;
    }

    private float a(int i) {
        return (((((float) (i - this.S)) * 1.0f) / 1320.0f) - dji.pilot.visual.a.d.c) * 2.0f;
    }

    private String c(int i) {
        String str = "";
        switch (this.B) {
            case 0:
                return (String) this.t.get(i);
            case 1:
                return (String) this.z.get(i);
            case 2:
                return (String) this.A.get(i);
            default:
                return (String) this.t.get(i);
        }
    }

    private void getSportData() {
        if (dji.pilot.publics.e.a.w(dji.midware.data.manager.P3.i.getInstance().c())) {
            this.r.setVisibility(0);
            dji.sdksharedlib.e.a.a.getInstance().a(this.z, new b.e(this) {
                final /* synthetic */ ExpView a;

                {
                    this.a = r1;
                }

                public void a(Object obj) {
                    ArrayList arrayList = (ArrayList) obj;
                    if (arrayList != null && arrayList.size() >= 3) {
                        this.a.F = (ParamInfo) arrayList.get(0);
                        this.a.G = (ParamInfo) arrayList.get(1);
                        this.a.H = (ParamInfo) arrayList.get(2);
                        this.a.c();
                    }
                }

                public void a(DJIError dJIError) {
                    DJILogHelper.getInstance().LOGD("View", "get thr yaw tilt fail " + dJIError.getDescription(), false, true);
                }
            });
            return;
        }
        this.r.setVisibility(8);
    }

    private void getGentleData() {
        if (dji.sdksharedlib.e.a.a.getInstance().b(e.cw)) {
            this.r.setVisibility(0);
            dji.sdksharedlib.e.a.a.getInstance().a(this.A, new b.e(this) {
                final /* synthetic */ ExpView a;

                {
                    this.a = r1;
                }

                public void a(Object obj) {
                    ArrayList arrayList = (ArrayList) obj;
                    try {
                        this.a.I = (ParamInfo) arrayList.get(0);
                        this.a.J = (ParamInfo) arrayList.get(1);
                        this.a.K = (ParamInfo) arrayList.get(2);
                        this.a.c();
                    } catch (Exception e) {
                    }
                }

                public void a(DJIError dJIError) {
                    DJILogHelper.getInstance().LOGD("View", "get thr yaw tilt fail " + dJIError.getDescription(), false, true);
                }
            });
            return;
        }
        this.r.getChildAt(2).setVisibility(8);
    }

    private void c() {
        if (this.B == 0) {
            this.a = a(this.C.value);
            this.b = a(this.D.value);
            this.c = a(this.E.value);
            DJILogHelper.getInstance().LOGD("View", "get thr=" + this.a + " yaw=" + this.b + " tilt=" + this.c, false, true);
        } else if (1 == this.B) {
            this.a = a(this.F.value);
            this.b = a(this.G.value);
            this.c = a(this.H.value);
        } else if (2 == this.B) {
            this.a = a(this.I.value);
            this.b = a(this.J.value);
            this.c = a(this.K.value);
        }
        if (!this.P.hasMessages(0)) {
            this.P.sendEmptyMessage(0);
        }
        if (!this.P.hasMessages(1)) {
            this.P.sendEmptyMessage(1);
        }
        if (!this.P.hasMessages(2)) {
            this.P.sendEmptyMessage(2);
        }
    }

    private void d() {
        this.q = findViewById(R.id.setting_ui_flyc_mode_ly);
        this.r = (RadioGroup) findViewById(R.id.setting_ui_item_radiogroup);
        this.C = dji.sdksharedlib.e.a.a.getInstance().a(e.cn);
        this.D = dji.sdksharedlib.e.a.a.getInstance().a(e.cp);
        this.E = dji.sdksharedlib.e.a.a.getInstance().a(e.co);
        this.F = dji.sdksharedlib.e.a.a.getInstance().a(e.cq);
        this.G = dji.sdksharedlib.e.a.a.getInstance().a(e.cs);
        this.H = dji.sdksharedlib.e.a.a.getInstance().a(e.cr);
        this.I = dji.sdksharedlib.e.a.a.getInstance().a(e.cw);
        this.J = dji.sdksharedlib.e.a.a.getInstance().a(e.cv);
        this.K = dji.sdksharedlib.e.a.a.getInstance().a(e.cu);
        float a = a(this.C.range.a);
        float a2 = a(this.C.range.b);
        DJILogHelper.getInstance().LOGD("", "expview882-" + a + i.b + a2 + i.b, false, true);
        a(a, a2);
        this.r.check(R.id.setting_ui_group_unit_imperial);
        this.r.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ExpView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.setting_ui_group_unit_imperial) {
                    this.a.a(0, false);
                } else if (i == R.id.setting_ui_group_unit_metric) {
                    this.a.a(1, false);
                } else if (i == R.id.setting_ui_item_radio_gentle) {
                    this.a.a(2, false);
                }
            }
        });
        a(0, true);
    }

    private void a(int i, boolean z) {
        if (this.B != i) {
            this.B = i;
            if (this.B == 0) {
                if (this.r.getCheckedRadioButtonId() != R.id.setting_ui_group_unit_imperial) {
                    this.r.check(R.id.setting_ui_group_unit_imperial);
                }
            } else if (this.B == 1) {
                if (this.r.getCheckedRadioButtonId() != R.id.setting_ui_group_unit_metric) {
                    this.r.check(R.id.setting_ui_group_unit_metric);
                }
            } else if (this.B == 2 && this.r.getCheckedRadioButtonId() != R.id.setting_ui_item_radio_gentle) {
                this.r.check(R.id.setting_ui_item_radio_gentle);
            }
            if (!z) {
                c();
            }
        }
    }

    private float a(Number number) {
        if (!(number instanceof Float)) {
            return (((float) number.intValue()) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
        }
        float floatValue = number.floatValue();
        if (floatValue > 1.0f) {
            return floatValue / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
        }
        return floatValue;
    }
}
