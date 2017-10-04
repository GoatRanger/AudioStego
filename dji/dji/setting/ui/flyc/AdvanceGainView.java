package dji.setting.ui.flyc;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.media.TransportMediator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetParamInfoByHash;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.a.b;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.lang.reflect.Array;

public class AdvanceGainView extends DividerLinearLayout implements TextWatcher, OnFocusChangeListener, OnEditorActionListener {
    private static final int[] a = new int[]{70, TransportMediator.KEYCODE_MEDIA_RECORD};
    private static final int[] b = new int[]{80, 120};
    private static final int[] c = new int[]{50, 150};
    private static final int[] d = new int[]{70, FTPCodes.RESTART_MARKER};
    private boolean A = false;
    private EditText e;
    private EditText f;
    private EditText g;
    private EditText h;
    private EditText i;
    private EditText l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private LinearLayout s;
    private ProductType t = ProductType.OTHER;
    private DroneType u = DroneType.None;
    private String[] v;
    private EditText[] w = null;
    private TextView[] x = null;
    private int[][] y = ((int[][]) null);
    private Handler z = new Handler(new Callback(this) {
        final /* synthetic */ AdvanceGainView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (!this.a.A) {
                        this.a.s.setVisibility(8);
                        break;
                    }
                    this.a.s.setVisibility(0);
                    break;
            }
            return false;
        }
    });

    public AdvanceGainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_flyc_adv_gain);
        if (!isInEditMode()) {
            this.e = (EditText) findViewById(R.id.fpv_flyc_gain_pitch);
            this.f = (EditText) findViewById(R.id.fpv_flyc_gain_roll);
            this.g = (EditText) findViewById(R.id.fpv_flyc_gain_yaw);
            this.h = (EditText) findViewById(R.id.fpv_flyc_atti_pitch);
            this.i = (EditText) findViewById(R.id.fpv_flyc_atti_roll);
            this.l = (EditText) findViewById(R.id.fpv_flyc_atti_yaw);
            this.s = (LinearLayout) findViewById(R.id.fpv_flyc_attily);
            this.w = new EditText[]{this.e, this.f, this.g, this.l, this.h, this.i};
            if (this.v == null) {
                this.v = new String[]{"g_config.control.basic_pitch_0", "g_config.control.basic_roll_0", "g_config.control.basic_tail_0", "g_config.control.atti_vertical_0", "g_config.control.tilt_atti_gain_0", "g_config.control.tilt_gyro_gain_0"};
                this.y = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.v.length, 2});
            }
            this.m = (TextView) findViewById(R.id.fpv_flyc_gain_pitch_range);
            this.n = (TextView) findViewById(R.id.fpv_flyc_gain_roll_range);
            this.o = (TextView) findViewById(R.id.fpv_flyc_gain_yaw_range);
            this.p = (TextView) findViewById(R.id.fpv_flyc_atti_pitch_range);
            this.q = (TextView) findViewById(R.id.fpv_flyc_atti_roll_range);
            this.r = (TextView) findViewById(R.id.fpv_flyc_atti_yaw_range);
            this.x = new TextView[]{this.m, this.n, this.o, this.r, this.p, this.q};
            setListener(this.e);
            setListener(this.f);
            setListener(this.g);
            setListener(this.h);
            setListener(this.i);
            setListener(this.l);
        }
    }

    private void b() {
        int i = 0;
        ProductType c = i.getInstance().c();
        DroneType droneType = DataOsdGetPushCommon.getInstance().getDroneType();
        if (!(c == this.t && this.u == droneType)) {
            Object obj;
            this.t = c;
            this.u = droneType;
            CharSequence format;
            if (c == ProductType.A3 || c == ProductType.N3) {
                format = String.format("(%d%%~%d%%)", new Object[]{Integer.valueOf(c[0]), Integer.valueOf(c[1])});
                for (TextView text : this.x) {
                    text.setText(format);
                }
                obj = c;
            } else if (dji.pilot.publics.e.a.d(c)) {
                format = String.format("(%d%%~%d%%)", new Object[]{Integer.valueOf(d[0]), Integer.valueOf(d[1])});
                for (TextView text2 : this.x) {
                    text2.setText(format);
                }
                obj = d;
            } else if (dji.pilot.publics.e.a.g(this.t)) {
                format = String.format("(%d%%~%d%%)", new Object[]{Integer.valueOf(a[0]), Integer.valueOf(a[1])});
                for (TextView text22 : this.x) {
                    text22.setText(format);
                }
                obj = a;
            } else {
                format = String.format("(%d%%~%d%%)", new Object[]{Integer.valueOf(b[0]), Integer.valueOf(b[1])});
                for (TextView text222 : this.x) {
                    text222.setText(format);
                }
                obj = b;
            }
            for (Object arraycopy : this.y) {
                System.arraycopy(obj, 0, arraycopy, 0, 2);
            }
        }
        if (!d()) {
            while (i != this.v.length) {
                final DataFlycGetParamInfoByHash dataFlycGetParamInfoByHash = new DataFlycGetParamInfoByHash();
                dataFlycGetParamInfoByHash.setIndex(this.v[i]).start(new d(this) {
                    final /* synthetic */ AdvanceGainView c;

                    public void onSuccess(Object obj) {
                        this.c.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                ParamInfo paramInfo = dataFlycGetParamInfoByHash.getParamInfo();
                                CharSequence format = String.format("(%d%%~%d%%)", new Object[]{Integer.valueOf(paramInfo.range.a.intValue()), Integer.valueOf(paramInfo.range.b.intValue())});
                                this.a.c.y[i][0] = paramInfo.range.a.intValue();
                                this.a.c.y[i][1] = paramInfo.range.b.intValue();
                                this.a.c.x[i].setText(format);
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
                i++;
            }
        }
    }

    private void c() {
        DataFlycGetParams.getInstance().setInfos(this.v).start(new d(this) {
            final /* synthetic */ AdvanceGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.z.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        for (int i = 0; i < this.a.a.v.length; i++) {
                            ParamInfo read = dji.midware.data.manager.P3.d.read(this.a.a.v[i]);
                            DJILogHelper.getInstance().LOGD("pm820", "max: " + read.range.b + " min:　" + read.range.a);
                            int intValue = read.value.intValue();
                            this.a.a.w[i].setText("" + intValue);
                            this.a.a.w[i].setTag("" + intValue);
                            b.a(this.a.a.getContext(), this.a.a.w[i], true);
                        }
                        this.a.a.b();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.z.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        for (int i = 0; i < this.a.a.v.length; i++) {
                            b.a(this.a.a.getContext(), this.a.a.w[i], true);
                            this.a.a.b();
                        }
                    }
                });
            }
        });
    }

    private void setListener(EditText editText) {
        editText.addTextChangedListener(this);
        editText.setOnEditorActionListener(this);
        editText.setOnFocusChangeListener(this);
    }

    private boolean d() {
        return (dji.pilot.publics.e.a.g() || dji.pilot.publics.e.a.i()) ? false : true;
    }

    private boolean a(int i, int i2) {
        ParamInfo read;
        for (int i3 = 0; i3 < this.w.length; i3++) {
            if (this.w[i3].getId() == i) {
                read = dji.midware.data.manager.P3.d.read(this.v[i3]);
                break;
            }
        }
        read = null;
        if (read == null) {
            return true;
        }
        DJILogHelper.getInstance().LOGD("Gain", "Interval[" + read.range.a + "-" + read.range.b + dji.pilot.usercenter.protocol.d.H, false, true);
        return read.isCorrect(Integer.valueOf(i2));
    }

    private boolean b(int i, int i2) {
        if (this.t == ProductType.A3 || this.t == ProductType.N3) {
            if (c[0] > i2 || i2 > c[1]) {
                return false;
            }
            return true;
        } else if (dji.pilot.publics.e.a.d(this.t)) {
            if (d[0] > i2 || i2 > d[1]) {
                return false;
            }
            return true;
        } else if (dji.pilot.publics.e.a.g(this.t)) {
            if (a[0] > i2 || i2 > a[1]) {
                return false;
            }
            return true;
        } else if (b[0] > i2 || i2 > b[1]) {
            return false;
        } else {
            return true;
        }
    }

    private boolean c(int i, int i2) {
        int i3 = 0;
        while (i3 < this.w.length) {
            if (this.w[i3].getId() == i) {
                break;
            }
            i3++;
        }
        i3 = Integer.MIN_VALUE;
        if (i3 < 0 || i3 >= this.w.length) {
            return true;
        }
        if (this.y[i3][0] > i2 || i2 > this.y[i3][1]) {
            return false;
        }
        return true;
    }

    private boolean d(int i, int i2) {
        return c(i, i2);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c();
            this.z.sendEmptyMessage(1);
            if (DataOsdGetPushCommon.getInstance().isGetted()) {
                onEventBackgroundThread(DataOsdGetPushCommon.getInstance());
            }
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        String charSequence = textView.getText().toString();
        if (charSequence.equals("") || charSequence.equals(null)) {
            textView.setText(textView.getTag().toString());
        } else {
            int id = textView.getId();
            final int a = a.a(charSequence, Integer.MIN_VALUE);
            if (id == R.id.fpv_flyc_gain_pitch) {
                e.a("FPV_MCSettings_AdvancedSettings_GainSettings_BasicGain_TextField_Pitch");
            } else if (id == R.id.fpv_flyc_gain_roll) {
                e.a("FPV_MCSettings_AdvancedSettings_GainSettings_BasicGain_TextField_Roll");
            } else if (id == R.id.fpv_flyc_gain_yaw) {
                e.a("FPV_MCSettings_AdvancedSettings_GainSettings_BasicGain_TextField_Yaw");
            } else if (id == R.id.fpv_flyc_atti_pitch) {
                e.a("FPV_MCSettings_AdvancedSettings_GainSettings_AdvancedGain_TextField_AttiGain");
            } else if (id == R.id.fpv_flyc_atti_roll) {
                e.a("FPV_MCSettings_AdvancedSettings_GainSettings_AdvancedGain_TextField_GyroGain");
            } else if (id == R.id.fpv_flyc_atti_yaw) {
                e.a("FPV_MCSettings_AdvancedSettings_GainSettings_AdvancedGain_TextField_Vertical");
            }
            int i2 = 0;
            while (i2 < this.w.length) {
                final EditText editText = this.w[i2];
                if (editText.getId() == id) {
                    final ParamInfo read = dji.midware.data.manager.P3.d.read(this.v[i2]);
                    if (d(id, a)) {
                        id = ((Integer) read.value).intValue();
                        new DataFlycSetParams().a(this.v[i2], Integer.valueOf(a)).start(new d(this) {
                            final /* synthetic */ AdvanceGainView e;

                            public void onSuccess(Object obj) {
                                this.e.z.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass3 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        editText.setText("" + a);
                                        editText.setTag("" + a);
                                    }
                                });
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                this.e.z.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass3 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        editText.setText("" + id);
                                        Log.d("", "paramInfo.value=" + read.value);
                                    }
                                });
                            }
                        });
                    }
                } else {
                    i2++;
                }
            }
        }
        return false;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        String obj = editable.toString();
        EditText editText = (EditText) findFocus();
        if (editText != null && !obj.equals("") && !obj.equals(null)) {
            b.a(getContext(), editText, d(editText.getId(), a.a(obj, Integer.MIN_VALUE)));
        }
    }

    public void onFocusChange(View view, boolean z) {
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        boolean z = dataOsdGetPushCommon.getFlycVersion() < 5;
        if (z != this.A) {
            this.A = z;
            this.z.sendEmptyMessage(1);
        }
    }

    public void onEventMainThread(ProductType productType) {
        b();
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        b();
    }
}
