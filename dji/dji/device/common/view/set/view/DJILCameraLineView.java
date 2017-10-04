package dji.device.common.view.set.view;

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
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.camera.view.ref.DJILCameraColorView;
import dji.device.common.view.set.view.DJIStageViewCompat.a;
import dji.device.common.view.set.view.a.b;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJITextView;
import java.text.DecimalFormat;

public class DJILCameraLineView extends ScrollView implements a, b {
    private final ViewGroup[] m = new ViewGroup[5];
    private EditText n = null;
    private EditText o = null;
    private ViewGroup p = null;
    private DJILCameraColorView q = null;
    private Context r = null;
    private OnClickListener s = null;
    private DJILCameraColorView.a t = null;
    private TextWatcher u = null;
    private TextWatcher v = null;
    private InputFilter w = null;
    private OnEditorActionListener x = null;
    private final DecimalFormat y = new DecimalFormat("#.##");

    public DJILCameraLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = context;
    }

    private void a() {
        this.s = new OnClickListener(this) {
            final /* synthetic */ DJILCameraLineView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int i = 0;
                while (i < 5) {
                    if (this.a.m[i] != view) {
                        this.a.m[i].findViewById(R.id.longan_shotcuts_itemlist_arrow).setVisibility(4);
                        i++;
                    } else if (this.a.p != view) {
                        DJICameraDataManagerCompat.getInstance().updateLineRef(i);
                        this.a.a(i);
                        this.a.m[i].findViewById(R.id.longan_shotcuts_itemlist_arrow).setVisibility(0);
                        return;
                    } else {
                        return;
                    }
                }
            }
        };
        this.t = new DJILCameraColorView.a(this) {
            final /* synthetic */ DJILCameraLineView a;

            {
                this.a = r1;
            }

            public void a(DJICameraDataManagerCompat.b bVar, int i) {
                DJICameraDataManagerCompat.getInstance().updateLineRefColor(bVar);
            }
        };
        this.u = new TextWatcher(this) {
            final /* synthetic */ DJILCameraLineView a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                this.a.a(this.a.n, editable);
            }
        };
        this.v = new TextWatcher(this) {
            final /* synthetic */ DJILCameraLineView a;

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
            final /* synthetic */ DJILCameraLineView a;

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
            final /* synthetic */ DJILCameraLineView a;

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
        if (textView == this.n) {
            f = DJICameraDataManagerCompat.getInstance().getLineRefCW();
        } else if (textView == this.o) {
            f = DJICameraDataManagerCompat.getInstance().getLineRefCH();
        }
        if (Math.abs(a - f) < 0.005f) {
            a(textView);
        } else if (charSequence.isEmpty() || !a(textView, a)) {
            textView.setText(a(f));
            Toast.makeText(this.r, R.string.longan_fpv_input_error, 1).show();
        } else {
            a(textView);
            if (textView == this.n) {
                DJICameraDataManagerCompat.getInstance().updateLineRefCW(a);
            } else if (textView == this.o) {
                DJICameraDataManagerCompat.getInstance().updateLineRefCH(a);
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
            this.p.findViewById(R.id.longan_shotcuts_itemlist_arrow).setVisibility(4);
        }
        this.p = this.m[i];
        this.p.findViewById(R.id.longan_shotcuts_itemlist_arrow).setVisibility(0);
    }

    private boolean a(TextView textView, float f) {
        return k_[0] < f && f <= k_[1];
    }

    private float a(String str) {
        float f = AutoScrollHelper.NO_MAX;
        if (!str.isEmpty()) {
            try {
                f = Float.parseFloat(str);
            } catch (Exception e) {
            }
        }
        return f;
    }

    private void a(EditText editText, Editable editable) {
        if (editable != null) {
            String obj = editable.toString();
            if (!obj.isEmpty()) {
                warningText(this.r, editText, a((TextView) editText, a(obj)));
            }
        }
    }

    public static void warningText(Context context, TextView textView, boolean z) {
        if (z) {
            textView.setTextColor(context.getResources().getColor(R.color.setting_ui_edittext_selected));
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.setting_ui_edittext_invalid));
        }
    }

    private void a(int i, int i2, int i3, boolean z, int i4) {
        ViewGroup viewGroup = (ViewGroup) findViewById(i);
        ((ImageView) viewGroup.findViewById(R.id.longan_shotcuts_itemlist_value_iv)).setImageResource(i2);
        viewGroup.findViewById(R.id.longan_shotcuts_itemlist_arrow).setVisibility(4);
        if (z) {
            ((DJITextView) viewGroup.findViewById(R.id.longan_shotcuts_itemlist_title)).setText(getResources().getString(i3, new Object[]{Float.valueOf(l[i4][0]), Float.valueOf(l[i4][1])}));
        } else {
            ((DJITextView) viewGroup.findViewById(R.id.longan_shotcuts_itemlist_title)).setText(i3);
        }
        this.m[i4] = viewGroup;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
            a(R.id.camera_newfn_line_none_ly, R.drawable.advanced_more_off, R.string.app_off, false, 0);
            a(R.id.camera_newfn_line_239_ly, R.drawable.longan_camera_framingline1, R.string.camera_ref_line_239, true, 1);
            a(R.id.camera_newfn_line_235_ly, R.drawable.longan_camera_framingline2, R.string.camera_ref_line_235, true, 2);
            a(R.id.camera_newfn_line_185_ly, R.drawable.longan_camera_framingline3, R.string.camera_ref_line_185, true, 3);
            this.m[4] = (ViewGroup) findViewById(R.id.camera_newfn_line_custom_ly);
            ((DJITextView) findViewById(R.id.camera_newfn_line_custom_value_tv)).setText(getResources().getString(R.string.camera_ref_line_format, new Object[]{Float.valueOf(k_[0]), Float.valueOf(k_[1])}));
            this.n = (EditText) findViewById(R.id.camera_newfn_line_custom_width_et);
            this.o = (EditText) findViewById(R.id.camera_newfn_line_custom_height_et);
            this.q = (DJILCameraColorView) findViewById(R.id.camera_newfn_cp_color_ly);
            this.q.setDescResId(R.string.camera_ref_line_color);
            this.q.setOnColorListener(this.t);
            for (int i = 0; i < 5; i++) {
                this.m[i].setOnClickListener(this.s);
            }
            this.n.addTextChangedListener(this.u);
            this.n.setOnEditorActionListener(this.x);
            this.n.setFilters(new InputFilter[]{this.w});
            this.o.addTextChangedListener(this.v);
            this.o.setOnEditorActionListener(this.x);
            this.o.setFilters(new InputFilter[]{this.w});
        }
    }

    public void dispatchOnStart() {
        a(DJICameraDataManagerCompat.getInstance().getLineRef());
        this.n.setText(a(DJICameraDataManagerCompat.getInstance().getLineRefCW()));
        this.o.setText(a(DJICameraDataManagerCompat.getInstance().getLineRefCH()));
        this.q.setColor(DJICameraDataManagerCompat.getInstance().getLineRefColor());
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
