package dji.pilot.set.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.thirdparty.a.c;

public abstract class SetTextView extends LinearLayout implements OnClickListener {
    private static final int g = 0;
    protected TextView a;
    protected TextView b;
    protected int c;
    protected int[] d;
    protected String[] e;
    protected d f;

    public static class a {
        public int a;
        public int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    protected abstract void a();

    protected abstract int getStringArrayId();

    protected abstract int getTitleId();

    protected abstract int getValuesId();

    protected abstract void setValue(int i);

    public SetTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public SetTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.set_item_text, null, false));
        this.a = (TextView) findViewById(R.id.set_item_title);
        this.b = (TextView) findViewById(R.id.set_item_value);
        this.a.setText(getTitleId());
        int descStringId = getDescStringId();
        if (descStringId != 0) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.set_item_desc, this);
            ((TextView) inflate.findViewById(R.id.set_item_desc)).setText(descStringId);
            addView(inflate);
        }
        if (getValuesId() != 0) {
            this.d = getResources().getIntArray(getValuesId());
        }
        if (getStringArrayId() != 0) {
            this.e = getResources().getStringArray(getStringArrayId());
        }
        setOnClickListener(this);
        this.c = Integer.MIN_VALUE;
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

    protected void setValueView(int i) {
        CharSequence a = a(i);
        if (a == null) {
            this.b.setVisibility(8);
            return;
        }
        this.b.setVisibility(0);
        this.b.setText(a);
    }

    private String a(int i) {
        for (int i2 = 0; i2 < this.d.length; i2++) {
            if (i == this.d[i2]) {
                return this.e[i2];
            }
        }
        return null;
    }

    private int b(int i) {
        for (int i2 = 0; i2 < this.d.length; i2++) {
            if (i == this.d[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public void onEventMainThread(a aVar) {
        if (aVar.a == getTitleId()) {
            setValue(this.d[aVar.b]);
        }
    }

    public void onClick(View view) {
        if (!dji.pilot.set.c.a()) {
            int i = -1;
            if (this.c != Integer.MIN_VALUE) {
                i = b(this.c);
            }
            TextListView.showSelectView(i, this.e, getTitleId(), getContext());
        }
    }

    protected int getDescStringId() {
        return 0;
    }
}
