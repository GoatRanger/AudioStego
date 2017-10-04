package dji.pilot.set.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.thirdparty.a.c;

public abstract class SetSwitchView extends LinearLayout implements OnCheckedChangeListener {
    protected TextView b;
    protected Switch c;
    protected boolean d;
    protected d e;
    protected boolean f = true;

    protected abstract void a();

    protected abstract int getTitleId();

    protected abstract void setValue(boolean z);

    public SetSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public SetSwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.set_item_switch, null, false));
        this.b = (TextView) findViewById(R.id.set_item_title);
        this.c = (Switch) findViewById(R.id.set_item_value);
        this.b.setText(getTitleId());
        int descStringId = getDescStringId();
        if (descStringId != 0) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.set_item_desc, this);
            ((TextView) inflate.findViewById(R.id.set_item_desc)).setText(descStringId);
            addView(inflate);
        }
        this.c.setOnCheckedChangeListener(this);
        this.d = false;
    }

    protected void setValueView(boolean z) {
        if (this.c.isChecked() != z) {
            this.c.setChecked(z);
        }
    }

    protected void onAttachedToWindow() {
        if (this.f && !c.a().c(this)) {
            c.a().a(this);
        }
        a();
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        if (this.f && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        setValue(z);
    }

    protected int getDescStringId() {
        return 0;
    }
}
