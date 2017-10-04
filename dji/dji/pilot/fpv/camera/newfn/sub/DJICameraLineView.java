package dji.pilot.fpv.camera.newfn.sub;

import android.content.Context;
import android.support.v4.widget.AutoScrollHelper;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.d$a;
import dji.pilot.fpv.camera.more.d.e;
import dji.pilot.fpv.camera.widget.DJICameraColorView;
import dji.pilot.fpv.model.p;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.e.d;
import dji.pilot.publics.widget.DJIEditText;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.text.DecimalFormat;

public class DJICameraLineView extends ScrollView implements e, a {
    private final DJILinearLayout[] a = new DJILinearLayout[5];
    private DJIEditText b = null;
    private DJIEditText o = null;
    private DJILinearLayout p = null;
    private DJICameraColorView q = null;
    private Context r = null;
    private OnClickListener s = null;
    private DJICameraColorView.a t = null;
    private TextWatcher u = null;
    private TextWatcher v = null;
    private InputFilter w = null;
    private OnEditorActionListener x = null;
    private final DecimalFormat y = new DecimalFormat("#.##");

    public DJICameraLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = context;
    }

    private void a() {
        this.s = new OnClickListener(this) {
            final /* synthetic */ DJICameraLineView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int i = 0;
                while (i < 5) {
                    if (this.a.a[i] != view) {
                        i++;
                    } else if (this.a.p != view) {
                        dji.pilot.fpv.camera.more.a.getInstance().e(i);
                        this.a.a(i);
                        return;
                    } else {
                        return;
                    }
                }
            }
        };
        this.t = new DJICameraColorView.a(this) {
            final /* synthetic */ DJICameraLineView a;

            {
                this.a = r1;
            }

            public void a(d$a dji_pilot_fpv_camera_more_d_a, int i) {
                dji.pilot.fpv.camera.more.a.getInstance().b(dji_pilot_fpv_camera_more_d_a);
            }
        };
        this.u = new TextWatcher(this) {
            final /* synthetic */ DJICameraLineView a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                this.a.a(this.a.b, editable);
            }
        };
        this.v = new TextWatcher(this) {
            final /* synthetic */ DJICameraLineView a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                this.a.a(this.a.o, editable);
            }
        };
        this.w = new InputFilter(this) {
            final /* synthetic */ DJICameraLineView a;

            {
                this.a = r1;
            }

            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                String obj = spanned.toString();
                int indexOf = obj.indexOf(46);
                if (indexOf <= 0 || i3 <= indexOf || obj.length() < indexOf + 3) {
                    return charSequence;
                }
                return "";
            }
        };
        this.x = new OnEditorActionListener(this) {
            final /* synthetic */ DJICameraLineView a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                this.a.a(textView, i, keyEvent);
                return true;
            }
        };
    }

    private String a(float f) {
        return this.y.format((double) f);
    }

    private void a(TextView textView, int i, KeyEvent keyEvent) {
        String charSequence = textView.getText().toString();
        float a = a(charSequence);
        float f = 2.14748365E9f;
        if (textView == this.b) {
            f = dji.pilot.fpv.camera.more.a.getInstance().w();
        } else if (textView == this.o) {
            f = dji.pilot.fpv.camera.more.a.getInstance().x();
        }
        if (Math.abs(a - f) < 0.005f) {
            a(textView);
        } else if (d.a(charSequence) || !a(textView, a)) {
            textView.setText(a(f));
            Toast.makeText(this.r, R.string.tau_isotherm_value_incorrect, 1).show();
        } else {
            a(textView);
            if (textView == this.b) {
                dji.pilot.fpv.camera.more.a.getInstance().a(a);
            } else if (textView == this.o) {
                dji.pilot.fpv.camera.more.a.getInstance().b(a);
            }
        }
    }

    private void a(TextView textView) {
        try {
            ((InputMethodManager) this.r.getSystemService("input_method")).hideSoftInputFromWindow(textView.getWindowToken(), 2);
        } catch (Throwable th) {
        }
    }

    private void a(int i) {
        if (this.p != null) {
            this.p.setSelected(false);
        }
        this.p = this.a[i];
        this.p.setSelected(true);
    }

    private boolean a(TextView textView, float f) {
        return ap_[0] < f && f <= ap_[1];
    }

    private float a(String str) {
        float f = AutoScrollHelper.NO_MAX;
        if (!d.a(str)) {
            try {
                f = Float.parseFloat(str);
            } catch (Exception e) {
            }
        }
        return f;
    }

    private void a(DJIEditText dJIEditText, Editable editable) {
        if (editable != null) {
            String obj = editable.toString();
            if (!d.a(obj)) {
                p.a(this.r, dJIEditText, a((TextView) dJIEditText, a(obj)));
            }
        }
    }

    private void a(int i, int i2, int i3, boolean z, int i4) {
        DJILinearLayout dJILinearLayout = (DJILinearLayout) findViewById(i);
        ((ImageView) dJILinearLayout.findViewById(R.id.l0)).setImageResource(i2);
        if (z) {
            ((DJITextView) dJILinearLayout.findViewById(R.id.l1)).setText(getResources().getString(i3, new Object[]{Float.valueOf(aq_[i4][0]), Float.valueOf(aq_[i4][1])}));
        } else {
            ((DJITextView) dJILinearLayout.findViewById(R.id.l1)).setText(i3);
        }
        ((DJIImageView) dJILinearLayout.findViewById(R.id.l2)).go();
        this.a[i4] = dJILinearLayout;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
            a(R.id.lx, R.drawable.advanced_more_off, R.string.app_off_lower, false, 0);
            a(R.id.ly, R.drawable.camera_framingline1, R.string.camera_ref_line_239, true, 1);
            a(R.id.lz, R.drawable.camera_framingline2, R.string.camera_ref_line_235, true, 2);
            a(R.id.m0, R.drawable.camera_framingline3, R.string.camera_ref_line_185, true, 3);
            this.a[4] = (DJILinearLayout) findViewById(R.id.m1);
            ((DJITextView) findViewById(R.id.m3)).setText(getResources().getString(R.string.camera_ref_line_format, new Object[]{Float.valueOf(ap_[0]), Float.valueOf(ap_[1])}));
            this.b = (DJIEditText) findViewById(R.id.m4);
            this.o = (DJIEditText) findViewById(R.id.m5);
            this.q = (DJICameraColorView) findViewById(R.id.ky);
            this.q.setDescResId(R.string.camera_ref_line_color);
            this.q.setOnColorListener(this.t);
            for (int i = 0; i < 5; i++) {
                this.a[i].setOnClickListener(this.s);
            }
            this.b.addTextChangedListener(this.u);
            this.b.setOnEditorActionListener(this.x);
            this.b.setFilters(new InputFilter[]{this.w});
            this.o.addTextChangedListener(this.v);
            this.o.setOnEditorActionListener(this.x);
            this.o.setFilters(new InputFilter[]{this.w});
        }
    }

    public void dispatchOnStart() {
        a(dji.pilot.fpv.camera.more.a.getInstance().u());
        this.b.setText(a(dji.pilot.fpv.camera.more.a.getInstance().w()));
        this.o.setText(a(dji.pilot.fpv.camera.more.a.getInstance().x()));
        this.q.setColor(dji.pilot.fpv.camera.more.a.getInstance().v());
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
