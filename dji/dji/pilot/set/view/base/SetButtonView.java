package dji.pilot.set.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.midware.e.d;
import dji.pilot.set.R;

public abstract class SetButtonView extends LinearLayout implements OnClickListener {
    protected TextView c;
    protected Button d;
    protected d e;

    protected abstract int getButtonStringId();

    protected abstract int getTitleId();

    public SetButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public SetButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.set_item_btn, null, false));
        this.c = (TextView) findViewById(R.id.set_item_title);
        this.d = (Button) findViewById(R.id.set_item_btn);
        this.c.setText(getTitleId());
        if (getButtonStringId() > 0) {
            this.d.setText(getButtonStringId());
        }
        int descStringId = getDescStringId();
        if (descStringId != 0) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.set_item_desc, this);
            ((TextView) inflate.findViewById(R.id.set_item_desc)).setText(descStringId);
            addView(inflate);
        }
        this.d.setOnClickListener(this);
        a();
    }

    protected void a() {
    }

    protected void b() {
        this.d.setEnabled(true);
        this.d.setAlpha(1.0f);
    }

    protected void c() {
        this.d.setEnabled(false);
        this.d.setAlpha(0.3f);
    }

    protected int getDescStringId() {
        return 0;
    }
}
