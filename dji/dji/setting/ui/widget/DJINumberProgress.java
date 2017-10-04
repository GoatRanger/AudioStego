package dji.setting.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;

public class DJINumberProgress extends LinearLayout implements OnSeekBarChangeListener {
    private TextView a;
    private TextView b;
    private SeekBar c;
    private OnSeekBarChangeListener d;
    private int e = 100;
    private int f = 0;
    private int g = 1;

    public DJINumberProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_widget_seekbar);
        if (!isInEditMode()) {
            this.a = (TextView) findViewById(R.id.setting_ui_progress_desc);
            this.b = (TextView) findViewById(R.id.setting_ui_progress_number);
            this.c = (SeekBar) findViewById(R.id.setting_ui_progress_seekbar);
            this.c.setOnSeekBarChangeListener(this);
        }
    }

    public void initParams(int i, int i2, int i3, OnSeekBarChangeListener onSeekBarChangeListener) {
        initParams(i, i2, i3, 0, onSeekBarChangeListener);
    }

    public void initParams(int i, int i2, int i3, int i4, OnSeekBarChangeListener onSeekBarChangeListener) {
        this.a.setText(String.format("(%s-%s)", new Object[]{getResources().getString(i2), getResources().getString(i3)}));
        this.c.setMax(i);
        this.f = i4;
        this.d = onSeekBarChangeListener;
    }

    public void initParams(int i, String str, String str2, int i2, OnSeekBarChangeListener onSeekBarChangeListener) {
        this.a.setText(String.format("(%s-%s)", new Object[]{str, str2}));
        this.c.setMax(i);
        this.e = i;
        this.f = i2;
        this.d = onSeekBarChangeListener;
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.d = onSeekBarChangeListener;
    }

    public void setDiv(int i) {
        this.g = i;
    }

    public void setProgress(int i) {
        if (i > this.e) {
            this.c.setProgress(this.e);
        } else {
            this.c.setProgress(i);
        }
    }

    public int getProgress() {
        return this.c.getProgress();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (this.g == 1) {
            this.b.setText("" + (this.f + i));
        } else {
            this.b.setText(String.format("%.1f", new Object[]{Float.valueOf(((float) (this.f + i)) / ((float) this.g))}));
        }
        if (this.d != null) {
            this.d.onProgressChanged(seekBar, i, z);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        if (this.d != null) {
            this.d.onStartTrackingTouch(seekBar);
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        if (this.d != null) {
            this.d.onStopTrackingTouch(seekBar);
        }
    }

    public SeekBar getSeekbaBar() {
        return this.c;
    }
}
