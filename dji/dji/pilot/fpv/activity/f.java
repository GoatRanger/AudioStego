package dji.pilot.fpv.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataFlycFaultInject;
import dji.midware.data.model.P3.DataFlycFaultInject.INJECT_CMD;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushFaultInject;
import dji.midware.data.model.P3.DataFlycGetPushFaultInject.INJECT_STATUS;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus.LED_COLOR;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus.LED_REASON;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus.LedStatus;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.CustomerSpinner;
import dji.pilot.publics.widget.DJIEditText;
import dji.pilot.publics.widget.DJISwitch;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;
import java.util.List;

public class f extends c {
    private static final int b = 4096;
    private LED_REASON a = null;
    private DJIImageView c = null;
    private List<LedStatus> d = null;
    private int e = 0;
    private a f = null;
    private DJISwitch g = null;
    private CustomerSpinner h = null;
    private DJIEditText i = null;
    private DJIEditText j = null;
    private CustomerSpinner k = null;
    private DJIEditText l = null;
    private DJIEditText m = null;
    private DJIEditText n = null;
    private DJIEditText o = null;
    private DJIEditText p = null;
    private DJIEditText q = null;
    private DJITextView r = null;
    private DJITextView s = null;
    private final Handler t = new Handler();
    private boolean u = false;
    private final Runnable v = new Runnable(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.u) {
                new DataFlycGetParams().setInfos(new String[]{"g_config.fdi_open.fit_open_0"}).start(new d(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a.g.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (dji.midware.data.manager.P3.d.read("g_config.fdi_open.fit_open_0").value.intValue() == 1) {
                                    this.a.a.a.g.setChecked(true);
                                    this.a.a.a.a(true);
                                    return;
                                }
                                this.a.a.a.g.setChecked(false);
                                this.a.a.a.a(false);
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.a.a.t.postDelayed(this.a.a.v, 2000);
                    }
                });
            }
        }
    };
    private OnClickListener w = new OnClickListener(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            int i = 0;
            float f = 0.0f;
            switch (view.getId()) {
                case R.id.a09:
                    this.a.dismiss();
                    return;
                case R.id.a0l:
                    int parseInt;
                    int parseInt2;
                    int parseInt3;
                    float parseFloat;
                    float parseFloat2;
                    float parseFloat3;
                    long j = 0;
                    int selectedItemPosition = this.a.h.getSelectedItemPosition() + 1;
                    int selectedItemPosition2 = this.a.k.getSelectedItemPosition() + 1;
                    try {
                        parseInt = Integer.parseInt(this.a.i.getText().toString());
                    } catch (Exception e) {
                        parseInt = i;
                    }
                    try {
                        parseInt2 = Integer.parseInt(this.a.j.getText().toString());
                    } catch (Exception e2) {
                        parseInt2 = i;
                    }
                    try {
                        parseInt3 = Integer.parseInt(this.a.p.getText().toString());
                    } catch (Exception e3) {
                        parseInt3 = i;
                    }
                    try {
                        parseFloat = Float.parseFloat(this.a.q.getText().toString());
                    } catch (Exception e4) {
                        parseFloat = f;
                    }
                    try {
                        parseFloat2 = Float.parseFloat(this.a.l.getText().toString());
                    } catch (Exception e5) {
                        parseFloat2 = f;
                    }
                    try {
                        parseFloat3 = Float.parseFloat(this.a.m.getText().toString());
                    } catch (Exception e6) {
                        parseFloat3 = f;
                    }
                    try {
                        f = Float.parseFloat(this.a.n.getText().toString());
                    } catch (Exception e7) {
                    }
                    try {
                        j = Long.parseLong(this.a.o.getText().toString());
                    } catch (Exception e8) {
                    }
                    DataFlycFaultInject.getInstance().a(INJECT_CMD.c).a(parseInt).b(selectedItemPosition).c(parseInt2).d(selectedItemPosition2).e(parseInt3).a(parseFloat).b(parseFloat2).c(parseFloat3).d(f).a(j).start(null);
                    return;
                default:
                    return;
            }
        }
    };

    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] a = new int[INJECT_STATUS.values().length];

        static {
            try {
                a[INJECT_STATUS.FIT_AUTO_STOP_FOR_SAFE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[INJECT_STATUS.FIT_CLOSE_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[INJECT_STATUS.FIT_DENY_FOR_DISCONNECT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[INJECT_STATUS.FIT_DENY_FOR_FAULT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[INJECT_STATUS.FIT_DENY_FOR_FUNC_CLOSED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[INJECT_STATUS.FIT_DENY_FOR_UNOPEN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[INJECT_STATUS.FIT_DENY_FOR_UNSAFE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[INJECT_STATUS.FIT_FDI_DETECT_FAILED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[INJECT_STATUS.FIT_FDI_DETECT_SUCCESS.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[INJECT_STATUS.FIT_INJECT_FAILED.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[INJECT_STATUS.FIT_INJECT_SUCCESS.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[INJECT_STATUS.FIT_INVALID_SYSTEM_ID.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[INJECT_STATUS.FIT_MODULE_CANNOT_FOUND.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[INJECT_STATUS.FIT_MSG_LEN_ERR.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[INJECT_STATUS.FIT_OPEN_FAILED.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[INJECT_STATUS.FIT_OPEN_SUCCESS.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[INJECT_STATUS.FIT_ROUTE_FAILED.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[INJECT_STATUS.FIT_TIME_PARA_INVALID.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[INJECT_STATUS.FIT_UNKNOWN_CMD_ID.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[INJECT_STATUS.FIT_UNKNOWN_FAULT_TYPE.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                a[INJECT_STATUS.FIT_UNKNOWN_MODULE_TYPE.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                a[INJECT_STATUS.FIT_UNSUPPORT_NOW.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                a[INJECT_STATUS.FIT_VERSION_UNMATCH.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
        }
    }

    private static final class a extends Handler {
        private final WeakReference<f> a;

        private a(f fVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(fVar);
        }

        public void handleMessage(Message message) {
            f fVar = (f) this.a.get();
            if (fVar != null) {
                switch (message.what) {
                    case 4096:
                        fVar.c();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public f(Context context) {
        super(context);
        setContentView(R.layout.fpv_fault_inject_view);
        this.c = (DJIImageView) findViewById(R.id.a08);
        findViewById(R.id.a09).setOnClickListener(this.w);
        findViewById(R.id.a0l).setOnClickListener(this.w);
        this.h = (CustomerSpinner) findViewById(R.id.a0a);
        this.h.setShowType(0);
        this.h.setData(getContext().getResources().getStringArray(R.array.ca));
        this.i = (DJIEditText) findViewById(R.id.a0b);
        this.j = (DJIEditText) findViewById(R.id.a0c);
        this.k = (CustomerSpinner) findViewById(R.id.a0d);
        this.k.setShowType(0);
        this.k.setData(getContext().getResources().getStringArray(R.array.cb));
        this.l = (DJIEditText) findViewById(R.id.a0e);
        this.m = (DJIEditText) findViewById(R.id.a0f);
        this.n = (DJIEditText) findViewById(R.id.a0g);
        this.o = (DJIEditText) findViewById(R.id.a0h);
        this.p = (DJIEditText) findViewById(R.id.a0i);
        this.q = (DJIEditText) findViewById(R.id.a0j);
        this.r = (DJITextView) findViewById(R.id.a0k);
        this.s = (DJITextView) findViewById(R.id.a0l);
        this.g = (DJISwitch) findViewById(R.id.a0_);
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                final boolean isChecked = this.a.g.isChecked();
                this.a.a(isChecked);
                INJECT_CMD inject_cmd = isChecked ? INJECT_CMD.b : INJECT_CMD.a;
                this.a.g.setEnabled(false);
                DataFlycFaultInject.getInstance().a(inject_cmd).start(new d(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void onSuccess(Object obj) {
                        if (DataFlycFaultInject.getInstance().a() != 1) {
                            boolean z;
                            f fVar = this.b.a;
                            if (isChecked) {
                                z = false;
                            } else {
                                z = true;
                            }
                            fVar.a(false, z, String.format("%d", new Object[]{Integer.valueOf(r3)}));
                            return;
                        }
                        this.b.a.a(true, true, "");
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        boolean z;
                        f fVar = this.b.a;
                        if (isChecked) {
                            z = false;
                        } else {
                            z = true;
                        }
                        fVar.a(false, z, aVar.toString());
                    }
                });
            }
        });
        this.g.setChecked(false);
        a(false);
        this.f = new a();
        this.u = false;
        this.t.post(this.v);
        setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                this.a.u = true;
            }
        });
    }

    private void a(final boolean z, final boolean z2, final String str) {
        this.g.post(new Runnable(this) {
            final /* synthetic */ f d;

            public void run() {
                this.d.g.setEnabled(true);
                this.d.a(z2);
                if (!z) {
                    this.d.g.setChecked(z2);
                    this.d.r.setText(str);
                }
            }
        });
    }

    private void a(boolean z) {
        this.h.setEnabled(z);
        this.i.setEnabled(z);
        this.j.setEnabled(z);
        this.k.setEnabled(z);
        this.l.setEnabled(z);
        this.m.setEnabled(z);
        this.n.setEnabled(z);
        this.o.setEnabled(z);
        this.p.setEnabled(z);
        this.q.setEnabled(z);
        this.s.setEnabled(z);
        c();
    }

    public void onEventMainThread(DataFlycGetPushFaultInject dataFlycGetPushFaultInject) {
        switch (AnonymousClass6.a[dataFlycGetPushFaultInject.getStatus().ordinal()]) {
            case 1:
                this.r.setText("FIT_AUTO_STOP_FOR_SAFE");
                return;
            case 2:
                if (this.g.isChecked()) {
                    this.g.setChecked(false);
                }
                this.r.setText("FIT_CLOSE_SUCCESS");
                return;
            case 3:
                this.r.setText("FIT_DENY_FOR_DISCONNECT");
                return;
            case 4:
                this.r.setText("FIT_DENY_FOR_FAULT");
                return;
            case 5:
                if (this.g.isChecked()) {
                    this.g.setChecked(false);
                }
                this.r.setText("FIT_DENY_FOR_FUNC_CLOSED");
                return;
            case 6:
                if (this.g.isChecked()) {
                    this.g.setChecked(false);
                }
                this.r.setText("FIT_DENY_FOR_UNOPEN");
                return;
            case 7:
                this.r.setText("FIT_DENY_FOR_UNSAFE");
                return;
            case 8:
                this.r.setText("FIT_FDI_DETECT_FAILED");
                return;
            case 9:
                this.r.setText("FIT_FDI_DETECT_SUCCESS");
                return;
            case 10:
                this.r.setText("FIT_INJECT_FAILED");
                return;
            case 11:
                this.r.setText("FIT_INJECT_SUCCESS");
                return;
            case 12:
                this.r.setText("FIT_INVALID_SYSTEM_ID");
                return;
            case 13:
                this.r.setText("FIT_MODULE_CANNOT_FOUND");
                return;
            case 14:
                this.r.setText("FIT_MSG_LEN_ERR");
                return;
            case 15:
                if (this.g.isChecked()) {
                    this.g.setChecked(false);
                }
                this.r.setText("FIT_OPEN_FAILED");
                return;
            case 16:
                if (!this.g.isChecked()) {
                    this.g.setChecked(true);
                }
                this.r.setText("FIT_OPEN_SUCCESS");
                return;
            case 17:
                this.r.setText("FIT_ROUTE_FAILED");
                return;
            case 18:
                this.r.setText("FIT_TIME_PARA_INVALID");
                return;
            case 19:
                this.r.setText("FIT_UNKNOWN_CMD_ID");
                return;
            case 20:
                this.r.setText("FIT_UNKNOWN_FAULT_TYPE");
                return;
            case 21:
                this.r.setText("FIT_UNKNOWN_MODULE_TYPE");
                return;
            case 22:
                this.r.setText("FIT_UNSUPPORT_NOW");
                return;
            case 23:
                this.r.setText("FIT_VERSION_UNMATCH");
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        a(b.a(this.N, R.dimen.l8), b.a(this.N, R.dimen.l7), 0, 17, true, false);
    }

    protected void onStart() {
        dji.thirdparty.a.c.a().a(this);
        onEventMainThread(DataFlycGetPushLedStatus.getInstance());
        super.onStart();
    }

    protected void onStop() {
        dji.thirdparty.a.c.a().d(this);
        super.onStop();
    }

    protected void b() {
        dismiss();
    }

    private int a(LED_COLOR led_color) {
        int i;
        if (LED_COLOR.RED == led_color) {
            i = R.drawable.light_red;
        } else if (LED_COLOR.GREEN == led_color) {
            i = R.drawable.light_green;
        } else if (LED_COLOR.BLUE == led_color) {
            i = R.drawable.light_blue;
        } else if (LED_COLOR.YELLOW == led_color) {
            i = R.drawable.light_yellow;
        } else if (LED_COLOR.DEEP_RED == led_color) {
            i = R.drawable.light_deep_red;
        } else if (LED_COLOR.CYAN == led_color) {
            i = R.drawable.light_cyan;
        } else if (LED_COLOR.PURPLE == led_color || LED_COLOR.PURPLE1 == led_color || LED_COLOR.PURPLE2 == led_color || LED_COLOR.PURPLE3 == led_color) {
            i = R.drawable.light_purple;
        } else {
            i = LED_COLOR.WHITE == led_color ? R.drawable.light_white : R.drawable.light_normal;
        }
        if (this.g.isChecked()) {
            return i;
        }
        return R.drawable.light_normal;
    }

    public void c() {
        if (this.d == null || this.d.isEmpty()) {
            this.c.setBackgroundResource(a(LED_COLOR.OFF));
        } else if (this.d.size() == 1) {
            this.c.setBackgroundResource(a(((LedStatus) this.d.get(0)).mColor));
        } else {
            LedStatus ledStatus = (LedStatus) this.d.get(this.e);
            this.c.setBackgroundResource(a(ledStatus.mColor));
            this.f.sendEmptyMessageDelayed(4096, (long) ledStatus.mInterval);
            int i = this.e + 1;
            this.e = i;
            this.e = i % this.d.size();
        }
    }

    public void onEventMainThread(DataFlycGetPushLedStatus dataFlycGetPushLedStatus) {
        if (dji.pilot.fpv.d.b.c()) {
            c();
            return;
        }
        LED_REASON ledReason = dataFlycGetPushLedStatus.getLedReason();
        List ledStatus = dataFlycGetPushLedStatus.getLedStatus();
        if (ledReason != this.a || !a(ledStatus, this.d)) {
            this.f.removeMessages(4096);
            this.a = ledReason;
            this.d = dataFlycGetPushLedStatus.getLedStatus();
            this.e = 0;
            c();
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            d();
            c();
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.a) {
            d();
            c();
        }
    }

    public void onEventMainThread(dji.setting.ui.flyc.SdModeView.a aVar) {
        if (aVar == dji.setting.ui.flyc.SdModeView.a.SUCCESS) {
            d();
            c();
        }
    }

    private void d() {
        this.f.removeMessages(4096);
        this.a = null;
        this.d = null;
    }

    private boolean a(List<LedStatus> list, List<LedStatus> list2) {
        if (list != list2) {
            if (list == null || list2 == null) {
                return false;
            }
            int size = list.size();
            if (size != list2.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                LedStatus ledStatus = (LedStatus) list.get(i);
                LedStatus ledStatus2 = (LedStatus) list2.get(i);
                if (ledStatus.mColor != ledStatus2.mColor || ledStatus.mInterval != ledStatus2.mInterval) {
                    return false;
                }
            }
        }
        return true;
    }
}
