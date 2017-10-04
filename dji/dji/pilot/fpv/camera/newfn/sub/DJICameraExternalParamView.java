package dji.pilot.fpv.camera.newfn.sub;

import android.content.Context;
import android.support.v4.widget.AutoScrollHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.alipay.sdk.j.i;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraTauExterParamType;
import dji.midware.data.model.P3.DataCameraTauExterParamType.ExterParamType;
import dji.midware.data.model.P3.DataCameraTauExterParams;
import dji.midware.data.model.P3.DataCameraTauExterParams.ExterParamId;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.model.p;
import dji.pilot.publics.widget.DJIEditText;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.Locale;

public class DJICameraExternalParamView extends ScrollView implements dji.pilot.fpv.view.DJIStageView.a {
    private static final ExterParamId[] a = new ExterParamId[]{ExterParamId.b, ExterParamId.c, ExterParamId.d, ExterParamId.e, ExterParamId.f, ExterParamId.g, ExterParamId.h, ExterParamId.i};
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;
    private static final int i = 5;
    private static final int j = 6;
    private static final int k = 7;
    private static final int l = 8;
    private DJITextView m = null;
    private DJITextView n = null;
    private DJITextView o = null;
    private DJITextView p = null;
    private final b[] q = new b[8];
    private OnClickListener r = null;
    private OnEditorActionListener s = null;
    private Context t = null;
    private DJITextView u = null;
    private volatile ExterParamType v = ExterParamType.d;
    private int w = Integer.MIN_VALUE;

    private final class a implements TextWatcher {
        final /* synthetic */ DJICameraExternalParamView a;
        private int b;

        private a(DJICameraExternalParamView dJICameraExternalParamView, int i) {
            this.a = dJICameraExternalParamView;
            this.b = 8;
            this.b = i;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            this.a.a(this.a.q[this.b].d, editable, this.b);
        }
    }

    private static final class b {
        private DJILinearLayout a;
        private DJITextView b;
        private DJITextView c;
        private DJIEditText d;
        private TextWatcher e;
        private c f;
        private int g;
        private volatile int h;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = Integer.MAX_VALUE;
            this.h = Integer.MIN_VALUE;
        }
    }

    private static final class c {
        private int a;
        private final float[] b;
        private final float[] c;

        private c() {
            this.a = 0;
            this.b = new float[2];
            this.c = new float[2];
        }
    }

    public DJICameraExternalParamView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = context;
    }

    public void onEventMainThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        a(dataCameraGetPushTauParam, false);
    }

    private void a(DataCameraGetPushTauParam dataCameraGetPushTauParam, boolean z) {
        boolean z2;
        boolean z3 = false;
        ExterParamType exterParamType = dataCameraGetPushTauParam.getExterParamType();
        if (exterParamType != this.v) {
            this.v = exterParamType;
            DJITextView dJITextView = this.u;
            if (ExterParamType.a == exterParamType) {
                this.u = this.m;
            } else if (ExterParamType.b == exterParamType) {
                this.u = this.n;
            } else if (ExterParamType.c == exterParamType) {
                this.u = this.o;
            }
            this.u.setSelected(true);
            if (dJITextView != null) {
                dJITextView.setSelected(false);
            }
        }
        int targetEmissivity = dataCameraGetPushTauParam.getTargetEmissivity();
        if (z || (this.q[0].g != targetEmissivity && (this.q[0].h == Integer.MIN_VALUE || this.q[0].h == targetEmissivity))) {
            this.q[0].g = targetEmissivity;
            this.q[0].h = Integer.MIN_VALUE;
            this.q[0].d.setText(a(0, (float) targetEmissivity));
        }
        targetEmissivity = dataCameraGetPushTauParam.getBackgroundTemperature();
        if (z || (this.q[1].g != targetEmissivity && (this.q[1].h == Integer.MIN_VALUE || this.q[1].h == targetEmissivity))) {
            this.q[1].g = targetEmissivity;
            this.q[1].h = Integer.MIN_VALUE;
            this.q[1].d.setText(a(1, (float) targetEmissivity));
        }
        targetEmissivity = dataCameraGetPushTauParam.getAtmosphereTransmission();
        if (z || (this.q[2].g != targetEmissivity && (this.q[2].h == Integer.MIN_VALUE || this.q[2].h == targetEmissivity))) {
            this.q[2].g = targetEmissivity;
            this.q[2].h = Integer.MIN_VALUE;
            this.q[2].d.setText(a(2, (float) targetEmissivity));
        }
        targetEmissivity = dataCameraGetPushTauParam.getAtmosphereTemperature();
        if (z || (this.q[3].g != targetEmissivity && (this.q[3].h == Integer.MIN_VALUE || this.q[3].h == targetEmissivity))) {
            this.q[3].g = targetEmissivity;
            this.q[3].h = Integer.MIN_VALUE;
            this.q[3].d.setText(a(3, (float) targetEmissivity));
        }
        int windowTransmission = dataCameraGetPushTauParam.getWindowTransmission();
        if (z || (this.q[4].g != windowTransmission && (this.q[4].h == Integer.MIN_VALUE || this.q[4].h == windowTransmission))) {
            this.q[4].g = windowTransmission;
            this.q[4].h = Integer.MIN_VALUE;
            z2 = true;
        } else {
            z2 = false;
        }
        int windowReflection = dataCameraGetPushTauParam.getWindowReflection();
        if (z || (this.q[5].g != windowReflection && (this.q[5].h == Integer.MIN_VALUE || this.q[5].h == windowReflection))) {
            this.q[5].g = windowReflection;
            this.q[5].h = Integer.MIN_VALUE;
            z3 = true;
        }
        if (z2) {
            this.q[4].d.setText(a(4, (float) windowTransmission));
        }
        if (z3) {
            this.q[5].d.setText(a(5, (float) windowReflection));
        }
        targetEmissivity = dataCameraGetPushTauParam.getWindowTemperature();
        if (z || (this.q[6].g != targetEmissivity && (this.q[6].h == Integer.MIN_VALUE || this.q[6].h == targetEmissivity))) {
            this.q[6].g = targetEmissivity;
            this.q[6].h = Integer.MIN_VALUE;
            this.q[6].d.setText(a(6, (float) targetEmissivity));
        }
        targetEmissivity = dataCameraGetPushTauParam.getWindowReflectedTemperature();
        if (!z) {
            if (this.q[7].g == targetEmissivity) {
                return;
            }
            if (!(this.q[7].h == Integer.MIN_VALUE || this.q[7].h == targetEmissivity)) {
                return;
            }
        }
        this.q[7].g = targetEmissivity;
        this.q[7].h = Integer.MIN_VALUE;
        this.q[7].d.setText(a(7, (float) targetEmissivity));
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
        b();
    }

    private void a() {
        this.r = new OnClickListener(this) {
            final /* synthetic */ DJICameraExternalParamView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.l3 == id || R.id.l4 == id || R.id.l5 == id) {
                    this.a.a(id);
                } else if (R.id.ld == id) {
                    new DataCameraTauExterParams().a(ExterParamId.a, (short) 0).start(new d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            dji.pilot.fpv.camera.more.a.a("Exter Default success");
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            dji.pilot.fpv.camera.more.a.a("Exter Default fail");
                        }
                    });
                }
            }
        };
        this.s = new OnEditorActionListener(this) {
            final /* synthetic */ DJICameraExternalParamView a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                this.a.a(textView, i, keyEvent);
                return true;
            }
        };
    }

    private void a(int i) {
        DataCameraTauExterParamType dataCameraTauExterParamType = new DataCameraTauExterParamType();
        ExterParamType exterParamType = ExterParamType.d;
        if (R.id.l3 == i) {
            exterParamType = ExterParamType.a;
        } else if (R.id.l4 == i) {
            exterParamType = ExterParamType.b;
        } else if (R.id.l5 == i) {
            exterParamType = ExterParamType.c;
        }
        if (exterParamType != this.v && exterParamType != ExterParamType.d) {
            dataCameraTauExterParamType.a(exterParamType).start(new d(this) {
                final /* synthetic */ DJICameraExternalParamView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    private void a(TextView textView, int i, KeyEvent keyEvent) {
        final int a = a((View) textView);
        if (a != 8) {
            String charSequence = textView.getText().toString();
            float a2 = a(charSequence);
            dji.pilot.fpv.camera.more.a.a("Edit Action On Index[" + a + "]actionId[" + i + dji.pilot.usercenter.protocol.d.H + charSequence + i.b + a2 + i.b);
            if (dji.pilot.publics.e.d.a(charSequence) || !b(a, a2)) {
                a(this.q[a].d, true, a(a, (float) this.q[a].g));
                Toast.makeText(this.t, R.string.tau_isotherm_value_incorrect, 1).show();
                return;
            }
            int i2;
            if (this.q[a].f.a == 1) {
                float b = dji.pilot.fpv.d.b.b(a2);
                if (this.q[a].f.b[0] > b) {
                    b = this.q[a].f.b[0];
                } else if (this.q[a].f.b[1] < b) {
                    b = this.q[a].f.b[1];
                }
                i2 = (int) (b * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            } else {
                i2 = (int) (a2 * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            }
            b(a);
            if (i2 != this.q[a].g) {
                this.q[a].h = i2;
                new DataCameraTauExterParams().a(a[a], (short) i2).start(new d(this) {
                    final /* synthetic */ DJICameraExternalParamView b;

                    public void onSuccess(Object obj) {
                        this.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                dji.pilot.fpv.camera.more.a.a("Success On Index[" + a + dji.pilot.usercenter.protocol.d.H);
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                dji.pilot.fpv.camera.more.a.a("Fail On Index[" + a + dji.pilot.usercenter.protocol.d.H);
                                this.a.b.q[a].h = Integer.MIN_VALUE;
                                this.a.b.a(this.a.b.q[a].d, true, this.a.b.a(a, (float) this.a.b.q[a].g));
                            }
                        });
                    }
                });
            }
        }
    }

    private void b(int i) {
        try {
            ((InputMethodManager) this.t.getSystemService("input_method")).hideSoftInputFromWindow(this.q[i].d.getWindowToken(), 2);
        } catch (Throwable th) {
        }
    }

    private void a(EditText editText, boolean z, String str) {
        p.a(this.t, editText, true);
        editText.setText(str);
        if (z) {
            editText.setSelection(editText.getText().length());
        }
    }

    private void b() {
        this.m = (DJITextView) findViewById(R.id.l3);
        this.n = (DJITextView) findViewById(R.id.l4);
        this.o = (DJITextView) findViewById(R.id.l5);
        this.p = (DJITextView) findViewById(R.id.ld);
        this.m.setOnClickListener(this.r);
        this.n.setOnClickListener(this.r);
        this.o.setOnClickListener(this.r);
        this.p.setOnClickListener(this.r);
        int[] iArr = new int[]{R.string.tau_target_emissivity, R.string.tau_bg_temperature, R.string.tau_atmosphere_transmission, R.string.tau_atmosphere_temperature, R.string.tau_window_transmission, R.string.tau_window_reflection, R.string.tau_window_temperature, R.string.tau_window_reflected_temperature};
        int[] iArr2 = new int[]{R.id.l6, R.id.l7, R.id.l8, R.id.l9, R.id.l_, R.id.la, R.id.lb, R.id.lc};
        int[] iArr3 = new int[]{0, 1, 1, 1, 0, 0, 1, 1};
        float[][] fArr = new float[][]{new float[]{50.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity}, new float[]{-50.0f, 327.67f}, new float[]{50.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity}, new float[]{-50.0f, 327.67f}, new float[]{50.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity}, new float[]{0.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity}, new float[]{-50.0f, 327.67f}, new float[]{-50.0f, 327.67f}};
        for (int i = 0; i < 8; i++) {
            b bVar = new b();
            DJILinearLayout dJILinearLayout = (DJILinearLayout) findViewById(iArr2[i]);
            bVar.a = dJILinearLayout;
            bVar.b = (DJITextView) dJILinearLayout.findViewById(R.id.le);
            bVar.c = (DJITextView) dJILinearLayout.findViewById(R.id.lf);
            bVar.d = (DJIEditText) dJILinearLayout.findViewById(R.id.lg);
            bVar.e = new a(i);
            bVar.d.setOnEditorActionListener(this.s);
            bVar.d.addTextChangedListener(bVar.e);
            bVar.b.setText(iArr[i]);
            bVar.c.setText(a(i, fArr[i], iArr3[i]));
            c cVar = new c();
            cVar.a = iArr3[i];
            System.arraycopy(fArr[i], 0, cVar.b, 0, 2);
            System.arraycopy(fArr[i], 0, cVar.c, 0, 2);
            bVar.f = cVar;
            this.q[i] = bVar;
        }
    }

    private String a(int i, float f) {
        if (this.q[i].f.a == 1) {
            float a = dji.pilot.fpv.d.b.a(f / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            return String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(a)});
        }
        return String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(f / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)});
    }

    private void a(boolean z) {
        for (int i = 0; i < 8; i++) {
            b bVar = this.q[i];
            if (1 == bVar.f.a) {
                bVar.f.c[0] = dji.pilot.fpv.d.b.a(bVar.f.b[0]);
                bVar.f.c[1] = dji.pilot.fpv.d.b.a(bVar.f.b[1]);
                bVar.c.setText(a(i, bVar.f.c, 1));
            }
        }
    }

    private String a(int i, float[] fArr, int i2) {
        if (4 == i) {
            return String.format(Locale.US, "(%.2f, %.2f - %s)%%", new Object[]{Float.valueOf(fArr[0]), Float.valueOf(fArr[1]), this.t.getString(R.string.tau_window_reflection)});
        } else if (5 == i) {
            return String.format(Locale.US, "(%.2f, %.2f - %s)%%", new Object[]{Float.valueOf(fArr[0]), Float.valueOf(fArr[1]), this.t.getString(R.string.tau_window_transmission)});
        } else {
            String str;
            if (1 == i2) {
                str = "(%.2f~%.2f)" + dji.pilot.fpv.d.b.a(this.t);
            } else {
                str = "(%.2f~%.2f)%%";
            }
            return String.format(Locale.US, str, new Object[]{Float.valueOf(fArr[0]), Float.valueOf(fArr[1])});
        }
    }

    private boolean b(int i, float f) {
        if (4 == i) {
            if (this.q[4].f.c[0] > f || f > this.q[4].f.c[1] - ((((float) this.q[5].g) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)) {
                return false;
            }
            return true;
        } else if (5 == i) {
            if (this.q[5].f.c[0] > f || f > this.q[5].f.c[1] - ((((float) this.q[4].g) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)) {
                return false;
            }
            return true;
        } else if (this.q[i].f.c[0] > f || f > this.q[i].f.c[1]) {
            return false;
        } else {
            return true;
        }
    }

    private int a(View view) {
        for (int i = 0; i < 8; i++) {
            if (this.q[i].d == view) {
                return i;
            }
        }
        return 8;
    }

    private float a(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return AutoScrollHelper.NO_MAX;
        }
    }

    private void a(View view, Editable editable, int i) {
        if (view instanceof DJIEditText) {
            DJIEditText dJIEditText = (DJIEditText) view;
            String obj = editable.toString();
            if (!dji.pilot.publics.e.d.a(obj)) {
                int indexOf = obj.indexOf(46);
                if (indexOf > 0 && obj.length() > indexOf + 3) {
                    obj = obj.substring(0, indexOf + 2);
                    dJIEditText.setText(obj);
                }
                dJIEditText.setSelection(obj.length());
                p.a(this.t, dJIEditText, b(i, a(obj)));
                dji.pilot.fpv.camera.more.a.a("After[" + i + i.b + editable + dji.pilot.usercenter.protocol.d.H);
            }
        }
    }

    public void dispatchOnStart() {
        boolean z = true;
        a(true);
        int w = DJIGenSettingDataManager.getInstance().w();
        if (this.w == w) {
            z = false;
        }
        this.w = w;
        a(DataCameraGetPushTauParam.getInstance(), z);
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
    }

    public void dispatchOnStop() {
        for (int i = 0; i < 8; i++) {
            this.q[i].h = Integer.MIN_VALUE;
        }
        b(0);
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
