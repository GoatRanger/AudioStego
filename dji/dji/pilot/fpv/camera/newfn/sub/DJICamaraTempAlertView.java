package dji.pilot.fpv.camera.newfn.sub;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.c;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.model.p;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.e.d;
import dji.pilot.publics.widget.DJIEditText;
import dji.pilot.publics.widget.DJISwitch;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJICamaraTempAlertView extends ScrollView implements a {
    protected static final String a = DJICamaraTempAlertView.class.getSimpleName();
    private DJISwitch b = null;
    private DJIRelativeLayout c = null;
    private DJITextView d = null;
    private DJIEditText e = null;
    private OnCheckedChangeListener f = null;
    private TextWatcher g = null;
    private OnEditorActionListener h = null;
    private final Context i;
    private c j = dji.pilot.fpv.camera.more.a.getInstance().aH();

    public DJICamaraTempAlertView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = context;
    }

    private void a(boolean z) {
        this.b.setChecked(z);
        if (z) {
            this.c.show();
            float a = b.a((float) this.j.c());
            this.e.setText(String.format("%.0f", new Object[]{Float.valueOf(a)}));
            return;
        }
        this.c.go();
    }

    private void a() {
        this.f = new OnCheckedChangeListener(this) {
            final /* synthetic */ DJICamaraTempAlertView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (compoundButton == this.a.b) {
                    this.a.j.a(z);
                    this.a.a(z);
                }
            }
        };
        this.g = new TextWatcher(this) {
            final /* synthetic */ DJICamaraTempAlertView a;

            {
                this.a = r1;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                p.a(this.a.i, this.a.e, this.a.a(this.a.e) != Integer.MAX_VALUE);
            }
        };
        this.h = new OnEditorActionListener(this) {
            final /* synthetic */ DJICamaraTempAlertView a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (textView == this.a.e) {
                    int a = this.a.a(textView);
                    if (a != Integer.MAX_VALUE) {
                        this.a.j.a(Math.round(b.b((float) a)));
                    }
                }
                return false;
            }
        };
    }

    private int a(TextView textView) {
        int i = Integer.MAX_VALUE;
        CharSequence text = textView.getText();
        if (!(text == null || d.a(text.toString()))) {
            try {
                i = Integer.parseInt(text.toString());
            } catch (Exception e) {
            }
        }
        return i;
    }

    private void b() {
        this.d.setText(b.a(this.i));
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
            this.b = (DJISwitch) findViewById(R.id.op);
            this.c = (DJIRelativeLayout) findViewById(R.id.oq);
            this.d = (DJITextView) findViewById(R.id.ot);
            this.e = (DJIEditText) findViewById(R.id.os);
            this.b.setOnCheckedChangeListener(this.f);
            this.e.addTextChangedListener(this.g);
            this.e.setOnEditorActionListener(this.h);
        }
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        a(this.j.b());
        b();
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
