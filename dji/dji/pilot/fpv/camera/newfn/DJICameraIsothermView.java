package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.alipay.sdk.j.i;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraTauParamIsothermEnable;
import dji.midware.data.model.P3.DataCameraTauParamIsothermValue;
import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.model.p;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.publics.widget.DJIEditText;
import dji.pilot.publics.widget.DJISwitch;
import dji.pilot.usercenter.protocol.d;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.util.Locale;

public class DJICameraIsothermView extends ScrollView implements dji.pilot.fpv.view.DJIStageView.a {
    protected static final boolean a = false;
    protected static final String b = DJICameraIsothermView.class.getSimpleName();
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    private static final int f = 3;
    private static final ParamCmd[] g = new ParamCmd[]{ParamCmd.m, ParamCmd.l, ParamCmd.k};
    private DJISwitch h = null;
    private DJIRelativeLayout i = null;
    private DJITextView j = null;
    private final b[] k = new b[3];
    private OnCheckedChangeListener l = null;
    private final TextWatcher[] m = new TextWatcher[3];
    private OnEditorActionListener n = null;
    private Context o = null;
    private boolean p = false;
    private int q = Integer.MAX_VALUE;
    private int r = Integer.MIN_VALUE;

    private final class a implements TextWatcher {
        final /* synthetic */ DJICameraIsothermView a;
        private int b;

        private a(DJICameraIsothermView dJICameraIsothermView, int i) {
            this.a = dJICameraIsothermView;
            this.b = 3;
            this.b = i;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            this.a.a(this.a.k[this.b].c, editable);
        }
    }

    private static final class b {
        public DJIRelativeLayout a;
        public DJITextView b;
        public DJIEditText c;
        public DJITextView d;
        public final int[] e;
        public int f;
        public volatile int g;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = new int[dji.pilot.fpv.camera.more.a.K_.length];
            this.f = Integer.MIN_VALUE;
            this.g = Integer.MIN_VALUE;
        }
    }

    public DJICameraIsothermView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = context;
    }

    public void onEventMainThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        int i;
        int i2;
        boolean isIsothermEnable = dataCameraGetPushTauParam.isIsothermEnable();
        if (this.p != isIsothermEnable) {
            this.p = isIsothermEnable;
            this.h.setChecked(isIsothermEnable);
            a();
        }
        int isothermUnit = dataCameraGetPushTauParam.getIsothermUnit();
        if (this.q != isothermUnit) {
            this.q = isothermUnit;
            b();
        }
        int isothermUpper = dataCameraGetPushTauParam.getIsothermUpper();
        if (1 == isothermUnit) {
            isothermUpper = Math.round(dji.pilot.fpv.d.b.a((float) isothermUpper));
        }
        if (this.k[0].f == isothermUpper || !(this.k[0].g == Integer.MIN_VALUE || this.k[0].g == isothermUpper)) {
            i = 0;
        } else {
            this.k[0].f = isothermUpper;
            this.k[0].g = Integer.MIN_VALUE;
            i = 1;
        }
        int isothermMiddle = dataCameraGetPushTauParam.getIsothermMiddle();
        if (1 == isothermUnit) {
            isothermMiddle = Math.round(dji.pilot.fpv.d.b.a((float) isothermMiddle));
        }
        if (this.k[1].f == isothermMiddle || !(this.k[1].g == Integer.MIN_VALUE || this.k[1].g == isothermMiddle)) {
            i2 = 0;
        } else {
            this.k[1].f = isothermMiddle;
            this.k[1].g = Integer.MIN_VALUE;
            i2 = 1;
        }
        int isothermLower = dataCameraGetPushTauParam.getIsothermLower();
        if (1 == isothermUnit) {
            isothermLower = Math.round(dji.pilot.fpv.d.b.a((float) isothermLower));
        }
        if (this.k[2].f == isothermLower || !(this.k[2].g == Integer.MIN_VALUE || this.k[2].g == isothermLower)) {
            isothermUnit = 0;
        } else {
            this.k[2].f = isothermLower;
            this.k[2].g = Integer.MIN_VALUE;
            isothermUnit = 1;
        }
        if (i != 0) {
            this.k[0].c.setText(a(isothermUpper));
        }
        if (i2 != 0) {
            this.k[1].c.setText(a(isothermMiddle));
        }
        if (isothermUnit != 0) {
            this.k[2].c.setText(a(isothermLower));
        }
        dji.pilot.fpv.camera.more.a.a("Isotherm [" + isothermUpper + i.b + isothermMiddle + i.b + isothermLower + d.H);
    }

    private void a() {
        int i = 0;
        if (this.p) {
            this.i.show();
            while (i < 3) {
                this.k[i].a.show();
                i++;
            }
            return;
        }
        this.i.go();
        while (i < 3) {
            this.k[i].a.go();
            i++;
        }
    }

    private void b() {
        if (this.q == 0) {
            this.j.setText(dji.pilot.fpv.camera.more.a.getInstance().aD()[dji.pilot.fpv.camera.more.a.a(dji.pilot.fpv.camera.more.a.getInstance().aE(), this.q, 0)]);
            for (int i = 0; i < 3; i++) {
                b bVar = this.k[i];
                System.arraycopy(dji.pilot.fpv.camera.more.a.K_, 0, bVar.e, 0, bVar.e.length);
                bVar.d.setText(a(bVar.e));
            }
            return;
        }
        a(true);
    }

    private void a(boolean z) {
        int w = DJIGenSettingDataManager.getInstance().w();
        if (z || this.r != w) {
            this.r = w;
            this.j.setText(dji.pilot.fpv.d.b.b(this.o));
            int round = Math.round(dji.pilot.fpv.d.b.a((float) dji.pilot.fpv.camera.more.a.L_[0]));
            int round2 = Math.round(dji.pilot.fpv.d.b.a((float) dji.pilot.fpv.camera.more.a.L_[1]));
            for (w = 0; w < 3; w++) {
                b bVar = this.k[w];
                bVar.e[0] = round;
                bVar.e[1] = round2;
                bVar.d.setText(a(bVar.e));
            }
        }
    }

    private String a(int i) {
        return String.valueOf(i);
    }

    private String a(int[] iArr) {
        return String.format(Locale.US, "(%d~%d)", new Object[]{Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1])});
    }

    private void a(final CompoundButton compoundButton, final boolean z) {
        if (compoundButton == this.h && this.p != z) {
            compoundButton.setEnabled(false);
            new DataCameraTauParamIsothermEnable().a(z).b(false).start(new dji.midware.e.d(this) {
                final /* synthetic */ DJICameraIsothermView c;

                public void onSuccess(Object obj) {
                    this.c.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.p = z;
                            compoundButton.setEnabled(true);
                            this.a.c.a();
                        }
                    });
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.c.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            boolean z = true;
                            compoundButton.setEnabled(true);
                            CompoundButton compoundButton = compoundButton;
                            if (z) {
                                z = false;
                            }
                            compoundButton.setChecked(z);
                        }
                    });
                }
            });
            dji.pilot.fpv.camera.more.a.a("Set Isotherm enable[" + z + d.H);
        }
    }

    private int a(View view) {
        for (int i = 0; i < 3; i++) {
            if (this.k[i].c == view) {
                return i;
            }
        }
        return 3;
    }

    private boolean a(int i, int i2) {
        if (i == 0) {
            if (this.k[1].f > i2 || i2 > this.k[i].e[1]) {
                return false;
            }
            return true;
        } else if (1 == i) {
            if (this.k[2].f > i2 || i2 > this.k[0].f) {
                return false;
            }
            return true;
        } else if (2 != i) {
            return false;
        } else {
            if (this.k[i].e[0] > i2 || i2 > this.k[1].f) {
                return false;
            }
            return true;
        }
    }

    private void a(View view, Editable editable) {
        if (view instanceof DJIEditText) {
            view = (DJIEditText) view;
            String obj = editable.toString();
            if (!dji.pilot.publics.e.d.a(obj)) {
                int intValue;
                try {
                    intValue = Integer.valueOf(obj).intValue();
                } catch (Exception e) {
                    intValue = Integer.MIN_VALUE;
                }
                p.a(this.o, view, a(a(view), intValue));
                dji.pilot.fpv.camera.more.a.a("After[" + a(view) + i.b + editable + d.H);
            }
        }
    }

    private void a(EditText editText, boolean z, String str) {
        p.a(this.o, editText, true);
        editText.setText(str);
        if (z) {
            editText.setSelection(str.length());
        }
    }

    private void a(TextView textView, int i, KeyEvent keyEvent) {
        final int a = a((View) textView);
        dji.pilot.fpv.camera.more.a.a("Edit Action On Index[" + a + "]actionId[" + i + d.H);
        if (a != 3) {
            int intValue;
            String charSequence = textView.getText().toString();
            try {
                intValue = Integer.valueOf(charSequence).intValue();
            } catch (Exception e) {
                intValue = Integer.MIN_VALUE;
            }
            if (intValue == this.k[a].f) {
                b(a);
            } else if (dji.pilot.publics.e.d.a(charSequence) || !a(a, intValue)) {
                a(this.k[a].c, true, a(this.k[a].f));
                Toast.makeText(this.o, R.string.tau_isotherm_value_incorrect, 1).show();
            } else {
                b(a);
                this.k[a].g = intValue;
                new DataCameraTauParamIsothermValue().a(g[a]).a((short) Math.round(dji.pilot.fpv.d.b.b((float) intValue))).b(false).start(new dji.midware.e.d(this) {
                    final /* synthetic */ DJICameraIsothermView b;

                    public void onSuccess(Object obj) {
                        this.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                dji.pilot.fpv.camera.more.a.a("Success On Index[" + a + d.H);
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                dji.pilot.fpv.camera.more.a.a("Fail On Index[" + a + d.H);
                                this.a.b.k[a].g = Integer.MIN_VALUE;
                                this.a.b.a(this.a.b.k[a].c, true, this.a.b.a(this.a.b.k[a].f));
                            }
                        });
                    }
                });
            }
        }
    }

    private void b(int i) {
        try {
            ((InputMethodManager) this.o.getSystemService("input_method")).hideSoftInputFromWindow(this.k[i].c.getWindowToken(), 2);
        } catch (Throwable th) {
        }
    }

    private void c() {
        this.l = new OnCheckedChangeListener(this) {
            final /* synthetic */ DJICameraIsothermView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.a(compoundButton, z);
            }
        };
        this.n = new OnEditorActionListener(this) {
            final /* synthetic */ DJICameraIsothermView a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                this.a.a(textView, i, keyEvent);
                return true;
            }
        };
    }

    private void d() {
        int[] iArr = new int[]{R.string.tau_isotherm_upper, R.string.tau_isotherm_middle, R.string.tau_isotherm_lower};
        int[] iArr2 = new int[]{R.id.lr, R.id.ls, R.id.lt};
        int[] iArr3 = dji.pilot.fpv.camera.more.a.K_;
        for (int i = 0; i < 3; i++) {
            b bVar = new b();
            bVar.a = (DJIRelativeLayout) findViewById(iArr2[i]);
            bVar.b = (DJITextView) bVar.a.findViewById(R.id.lu);
            bVar.d = (DJITextView) bVar.a.findViewById(R.id.lw);
            bVar.c = (DJIEditText) bVar.a.findViewById(R.id.lv);
            bVar.b.setText(iArr[i]);
            bVar.d.setText(a(iArr3));
            this.m[i] = new a(i);
            bVar.c.addTextChangedListener(this.m[i]);
            bVar.c.setOnEditorActionListener(this.n);
            System.arraycopy(iArr3, 0, bVar.e, 0, iArr3.length);
            this.k[i] = bVar;
        }
        this.h = (DJISwitch) findViewById(R.id.ln);
        this.i = (DJIRelativeLayout) findViewById(R.id.lo);
        this.j = (DJITextView) findViewById(R.id.lq);
        this.h.setOnCheckedChangeListener(this.l);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJICameraIsothermView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                ViewParent parent = this.a.getParent();
                if (parent instanceof DJIStageView) {
                    ((DJICameraBaseListView) ((DJIStageView) parent).createStageView(R.layout.camera_newfn_base_listview, R.string.tau_isotherm_units, true)).updateData(105);
                }
            }
        });
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            c();
            d();
        }
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        for (int i = 0; i < 3; i++) {
            this.k[i].g = Integer.MIN_VALUE;
        }
        b(0);
    }

    public void dispatchOnResume() {
        onEventMainThread(DataCameraGetPushTauParam.getInstance());
        if (this.q != 0) {
            a(false);
        }
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    public void dispatchOnPause() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public View getView() {
        return this;
    }
}
