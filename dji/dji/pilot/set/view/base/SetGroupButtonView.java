package dji.pilot.set.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.thirdparty.a.c;

public abstract class SetGroupButtonView extends LinearLayout implements OnCheckedChangeListener {
    private TextView a;
    public int b = 0;
    public d c = new d(this) {
        final /* synthetic */ SetGroupButtonView a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
        }

        public void onFailure(a aVar) {
        }
    };
    private RadioGroup d;
    private RadioButton e;
    private RadioButton f;
    private boolean g = false;

    protected abstract void a();

    protected abstract int getLeftBtnStrId();

    protected abstract int getRightBtnStrId();

    protected abstract int getTitleId();

    protected abstract void setValue(int i);

    public SetGroupButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.set_item_group_button, null, false));
        this.a = (TextView) findViewById(R.id.set_item_title);
        this.a.setText(getTitleId());
        this.d = (RadioGroup) findViewById(R.id.set_item_radio_group);
        this.d.setOnCheckedChangeListener(this);
        this.e = (RadioButton) findViewById(R.id.set_item_left_btn);
        this.f = (RadioButton) findViewById(R.id.set_item_right_btn);
        this.e.setText(getLeftBtnStrId());
        this.f.setText(getRightBtnStrId());
        String string = getResources().getString(getLeftBtnStrId());
        String string2 = getResources().getString(getRightBtnStrId());
        if (string.length() > 6 || string2.length() > 6) {
            this.e.setTextSize(9.0f);
            this.f.setTextSize(9.0f);
        }
    }

    public void setSelect(int i) {
        this.g = true;
        if (i == 0) {
            this.d.check(R.id.set_item_left_btn);
        } else {
            this.d.check(R.id.set_item_right_btn);
        }
        this.g = false;
    }

    protected void onAttachedToWindow() {
        c.a().a(this);
        a();
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (!this.g) {
            if (i == R.id.set_item_left_btn) {
                setValue(0);
            } else {
                setValue(1);
            }
        }
    }
}
