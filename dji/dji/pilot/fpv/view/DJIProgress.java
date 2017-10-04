package dji.pilot.fpv.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.pilot.R;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIProgress extends DJIRelativeLayout {
    private DJITextView a;
    private DJITextView b;
    private SeekBar c;
    private DJITextView d;
    private int e;
    private Paint f;
    private int g = 0;

    public DJIProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        inflate(getContext(), R.layout.fpv_djiprogress, this);
        if (!isInEditMode()) {
            this.a = (DJITextView) findViewById(R.id.zs);
            this.b = (DJITextView) findViewById(R.id.zt);
            this.c = (SeekBar) findViewById(R.id.zu);
            this.d = (DJITextView) findViewById(R.id.zv);
            this.f = new Paint();
            this.f.setTextSize(this.d.getTextSize());
            this.f.setTypeface(this.d.getTypeface());
        }
    }

    public void init(int i, String str, String str2, OnSeekBarChangeListener onSeekBarChangeListener) {
        this.e = i;
        this.c.setMax(i);
        this.a.setText(str);
        this.b.setText(str2);
        this.c.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    public void init(int i, int i2, String str, String str2, OnSeekBarChangeListener onSeekBarChangeListener) {
        this.g = i;
        this.e = i2;
        this.c.setMax(i2);
        this.c.setTag("" + i);
        this.a.setText(str);
        this.b.setText(str2);
        this.c.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    public int getMyTag() {
        return this.g;
    }

    public void setValue(String str) {
        int width = this.c.getWidth();
        this.d.setText(str);
        width = (int) (((((float) width) * 1.0f) / ((float) this.e)) * ((float) this.c.getProgress()));
        DJITextView dJITextView = this.d;
        if (width < 0) {
            width = 0;
        }
        dJITextView.setPadding(width, 0, 0, 0);
    }

    private float a(String str) {
        return this.f.measureText(str);
    }

    public void setProgress(int i) {
        if (i > this.e) {
            this.c.setProgress(this.e);
        } else {
            this.c.setProgress(i);
        }
    }
}
