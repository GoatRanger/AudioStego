package dji.pilot2.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot.R$styleable;

@SuppressLint({"NewApi"})
public class DJIActiveViewButton extends RelativeLayout {
    private TextView a;
    private ImageView b;
    private String c = null;
    private int d;
    private Boolean e = Boolean.valueOf(false);

    public DJIActiveViewButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DJIActiveViewButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Activeviewbutton);
        this.c = obtainStyledAttributes.getString(0);
        this.d = obtainStyledAttributes.getResourceId(1, R.drawable.login_next);
        this.e = Boolean.valueOf(obtainStyledAttributes.getBoolean(2, false));
        obtainStyledAttributes.recycle();
    }

    public DJIActiveViewButton(Context context) {
        super(context);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.v2_active_view_button, this, true);
        this.a = (TextView) findViewById(R.id.c4v);
        this.a.setText(this.c);
        this.b = (ImageView) findViewById(R.id.c4w);
        this.b.setBackgroundResource(this.d);
        if (this.e.booleanValue()) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
    }

    public void setImageDisable(Boolean bool) {
        if (bool.booleanValue()) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
    }

    public void setTextString(String str) {
        this.a.setText(str);
    }
}
