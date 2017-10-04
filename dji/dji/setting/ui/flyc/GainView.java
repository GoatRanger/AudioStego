package dji.setting.ui.flyc;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import dji.common.error.DJIError;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.e.a.a$a;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GainView extends DividerLinearLayout implements TextWatcher, OnFocusChangeListener, OnEditorActionListener {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int r = 0;
    private static final int s = 1;
    private static final int t = 2;
    private ParamInfo d;
    private ParamInfo e;
    private ParamInfo f;
    private ArrayList<String> g;
    private TextView[] h = null;
    private TextView i;
    private EditText l;
    private EditText m;
    private TextView n;
    private EditText o;
    private TextView p;
    private View q;
    private Number[][] u = ((Number[][]) null);
    private boolean v = false;

    public GainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_flyc_gain);
        if (!isInEditMode()) {
            this.l = (EditText) findViewById(R.id.fpv_flyc_gain_brake);
            this.i = (TextView) findViewById(R.id.fpv_flyc_gain_brake_range);
            this.o = (EditText) findViewById(R.id.fpv_flyc_yaw_endpoint);
            this.p = (TextView) findViewById(R.id.fpv_flyc_yaw_endpoint_range);
            this.m = (EditText) findViewById(R.id.fpv_rc_gain_tilt);
            this.n = (TextView) findViewById(R.id.fpv_rc_gain_tilt_range);
            this.q = findViewById(R.id.fpv_flyc_yaw_endpoint_layout);
            this.d = dji.sdksharedlib.e.a.a.getInstance().a(e.ce);
            this.e = dji.sdksharedlib.e.a.a.getInstance().a(e.cg);
            this.f = dji.sdksharedlib.e.a.a.getInstance().a(e.cd);
            this.g = new ArrayList();
            this.g.add(e.ce);
            this.g.add(e.cg);
            this.g.add(e.cd);
            this.h = new TextView[]{this.n, this.i, this.p};
            this.u = (Number[][]) Array.newInstance(Number.class, new int[]{this.g.size(), 2});
            setListener(this.l);
            setListener(this.o);
            setListener(this.m);
        }
    }

    private void b() {
        int i = 0;
        c();
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventBackgroundThread(DataOsdGetPushCommon.getInstance());
        }
        for (int i2 = 0; i2 != this.g.size(); i2++) {
            dji.sdksharedlib.e.a.a.getInstance().a((String) this.g.get(i2), new b.e(this) {
                final /* synthetic */ GainView b;

                public void a(Object obj) {
                    this.b.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.a(i2);
                        }
                    });
                }

                public void a(DJIError dJIError) {
                    DJILogHelper.getInstance().LOGD("View", "get thr yaw tilt fail " + dJIError.getDescription(), false, true);
                    this.b.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.a(i2);
                        }
                    });
                }
            });
        }
        this.i.setText(String.format("(%d%%~%d%%)", new Object[]{this.e.range.a, this.e.range.b}));
        this.n.setText(String.format("(%d%%~%d%%)", new Object[]{this.d.range.a, this.d.range.b}));
        this.p.setText(String.format("(%d%%~%d%%)", new Object[]{Integer.valueOf(this.f.range.a.intValue()), Integer.valueOf(this.f.range.b.intValue())}));
        this.u[0][0] = Integer.valueOf(this.d.range.a.intValue());
        this.u[0][1] = Integer.valueOf(this.d.range.b.intValue());
        this.u[1][0] = Integer.valueOf(this.e.range.a.intValue());
        this.u[1][1] = Integer.valueOf(this.e.range.b.intValue());
        this.u[2][0] = Integer.valueOf(this.f.range.a.intValue());
        this.u[2][1] = Integer.valueOf(this.f.range.b.intValue());
        while (i != this.g.size()) {
            dji.sdksharedlib.e.a.a.getInstance().b((String) this.g.get(i), new b.e(this) {
                final /* synthetic */ GainView b;

                public void a(final Object obj) {
                    this.b.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void run() {
                            a$a dji_sdksharedlib_e_a_a_a = (a$a) obj;
                            this.b.b.h[i].setText(String.format("(%d%%~%d%%)", new Object[]{Integer.valueOf(dji_sdksharedlib_e_a_a_a.a.intValue()), Integer.valueOf(dji_sdksharedlib_e_a_a_a.b.intValue())}));
                            this.b.b.u[i][0] = Integer.valueOf(dji_sdksharedlib_e_a_a_a.a.intValue());
                            this.b.b.u[i][1] = Integer.valueOf(dji_sdksharedlib_e_a_a_a.b.intValue());
                        }
                    });
                }

                public void a(DJIError dJIError) {
                }
            });
            i++;
        }
    }

    private void a(int i) {
        if (i.getInstance().c() == ProductType.N3 || i.getInstance().c() == ProductType.A3) {
            this.q.setVisibility(8);
        } else {
            this.q.setVisibility(0);
        }
        Integer valueOf;
        switch (i) {
            case 0:
                valueOf = Integer.valueOf(this.d.value.intValue());
                this.m.setText("" + valueOf.toString());
                this.m.setTag("" + valueOf.toString());
                dji.setting.a.b.a(getContext(), this.m, true);
                return;
            case 1:
                valueOf = Integer.valueOf(this.e.value.intValue());
                this.l.setText("" + valueOf);
                this.l.setTag("" + valueOf);
                dji.setting.a.b.a(getContext(), this.l, true);
                return;
            case 2:
                valueOf = Integer.valueOf(this.f.value.intValue());
                this.o.setText(String.format("%d", new Object[]{valueOf}));
                this.o.setTag(String.format("%d", new Object[]{valueOf}));
                dji.setting.a.b.a(getContext(), this.o, true);
                return;
            default:
                return;
        }
    }

    private void setListener(EditText editText) {
        editText.addTextChangedListener(this);
        editText.setOnEditorActionListener(this);
        editText.setOnFocusChangeListener(this);
    }

    public void onFocusChange(View view, boolean z) {
    }

    private boolean a(int i, int i2) {
        if (i < 0 || i >= this.g.size()) {
            return true;
        }
        if (this.u[i][0].doubleValue() > ((double) i2) || ((double) i2) > this.u[i][1].doubleValue()) {
            return false;
        }
        return true;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        EditText editText = null;
        String charSequence = textView.getText().toString();
        if (charSequence.equals("") || charSequence.equals(null)) {
            textView.setText(textView.getTag().toString());
        } else {
            ParamInfo paramInfo;
            int id = textView.getId();
            final int b = b(charSequence);
            int i2;
            if (id == R.id.fpv_flyc_gain_brake) {
                paramInfo = this.e;
                editText = this.l;
                i2 = 1;
                dji.pilot.fpv.d.e.a("FPV_MCSettings_AdvancedSettings_EXPSettings_Sensitivity_TextField_Brake");
            } else if (id == R.id.fpv_flyc_yaw_endpoint) {
                paramInfo = this.f;
                editText = this.o;
                i2 = 2;
                dji.pilot.fpv.d.e.a("FPV_MCSettings_AdvancedSettings_EXPSettings_Sensitivity_TextField_YawEndPoint");
            } else if (id == R.id.fpv_rc_gain_tilt) {
                paramInfo = this.d;
                editText = this.m;
                dji.pilot.fpv.d.e.a("FPV_MCSettings_AdvancedSettings_EXPSettings_Sensitivity_TextField_Tilt");
                i2 = 0;
            } else {
                i2 = 0;
                paramInfo = null;
            }
            if (paramInfo != null && a(r0, b)) {
                final Number number = paramInfo.value;
                new DataFlycSetParams().a(paramInfo.name, Integer.valueOf(b)).start(new d(this) {
                    final /* synthetic */ GainView e;

                    public void onSuccess(Object obj) {
                        this.e.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                editText.setText("" + b);
                                editText.setTag("" + b);
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.e.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                editText.setText("" + number.toString());
                                Log.d("", "paramInfo.value=" + paramInfo.value);
                            }
                        });
                    }
                });
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
        if (editText != null) {
            int i = Integer.MIN_VALUE;
            if (editText == this.l) {
                i = 1;
            } else if (editText == this.o) {
                i = 2;
            } else if (editText == this.m) {
                i = 0;
            }
            if (!obj.equals("") && !obj.equals(null)) {
                dji.setting.a.b.a(getContext(), editText, a(i, b(obj)));
            }
        }
    }

    private int b(String str) {
        int i = Integer.MAX_VALUE;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
        }
        return i;
    }

    private boolean b(int i, int i2) {
        ParamInfo paramInfo = null;
        if (i == R.id.fpv_flyc_gain_brake) {
            paramInfo = this.e;
        } else if (i == R.id.fpv_rc_gain_tilt) {
            paramInfo = this.d;
        } else if (i == R.id.fpv_flyc_yaw_endpoint) {
            paramInfo = this.f;
        }
        if (paramInfo != null) {
            return paramInfo.isCorrect(Integer.valueOf(i2));
        }
        return true;
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        boolean z = dataOsdGetPushCommon.getFlycVersion() < 5;
        if (z != this.v) {
            this.v = z;
            c();
        }
    }

    private void c() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ GainView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.v) {
                    this.a.setVisibility(8);
                } else {
                    this.a.setVisibility(0);
                }
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            b();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private int[] getYawRange() {
        int[] iArr = new int[]{this.f.range.a.intValue(), this.f.range.b.intValue()};
        return null;
    }

    private int getYawValue() {
        return 0;
    }
}
