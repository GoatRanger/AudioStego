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

public abstract class SetGroupButtonThreeView extends LinearLayout implements OnCheckedChangeListener {
    public int a = 0;
    public d b = new d(this) {
        final /* synthetic */ SetGroupButtonThreeView a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
        }

        public void onFailure(a aVar) {
        }
    };
    private TextView c;
    private RadioGroup d;
    private RadioButton e;
    private RadioButton f;
    private RadioButton g;

    protected abstract void a();

    protected abstract int getLeftBtnStrId();

    protected abstract int getMiddleBtnStrId();

    protected abstract int getRightBtnStrId();

    protected abstract int getTitleId();

    protected abstract void setValue(int i);

    public SetGroupButtonThreeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.set_item_group_button_three, null, false));
        this.c = (TextView) findViewById(R.id.set_item_title);
        this.c.setText(getTitleId());
        this.d = (RadioGroup) findViewById(R.id.set_item_radio_group);
        this.d.setOnCheckedChangeListener(this);
        this.e = (RadioButton) findViewById(R.id.set_item_left_btn);
        this.f = (RadioButton) findViewById(R.id.set_item_middle_btn);
        this.g = (RadioButton) findViewById(R.id.set_item_right_btn);
        this.e.setText(getLeftBtnStrId());
        this.f.setText(getMiddleBtnStrId());
        this.g.setText(getRightBtnStrId());
    }

    public void setSelect(int i) {
        switch (i) {
            case 0:
                this.d.check(R.id.set_item_left_btn);
                return;
            case 1:
                this.d.check(R.id.set_item_middle_btn);
                return;
            case 2:
                this.d.check(R.id.set_item_right_btn);
                return;
            default:
                return;
        }
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
        if (i == R.id.set_item_left_btn) {
            setValue(0);
        }
        if (i == R.id.set_item_middle_btn) {
            setValue(1);
        }
        if (i == R.id.set_item_right_btn) {
            setValue(2);
        }
    }
}
