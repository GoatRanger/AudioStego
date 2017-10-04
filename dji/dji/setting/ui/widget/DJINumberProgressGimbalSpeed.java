package dji.setting.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import dji.pilot.setting.ui.R;

public class DJINumberProgressGimbalSpeed extends LinearLayout implements OnSeekBarChangeListener {
    private TextView a;
    private TextView b;
    private TextView c;
    private SeekBar d;
    private OnSeekBarChangeListener e;
    private int f = 100;
    private int g = 0;

    public DJINumberProgressGimbalSpeed(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        addView(LayoutInflater.from(getContext()).inflate(R.layout.setting_ui_widget_seekbar, null));
        if (!isInEditMode()) {
            this.a = (TextView) findViewById(R.id.setting_ui_progress_left);
            this.b = (TextView) findViewById(R.id.setting_ui_progress_right);
            this.c = (TextView) findViewById(R.id.setting_ui_progress_number);
            this.d = (SeekBar) findViewById(R.id.setting_ui_progress_seekbar);
            this.d.setOnSeekBarChangeListener(this);
        }
    }

    public void initParams(int i, int i2, int i3, OnSeekBarChangeListener onSeekBarChangeListener) {
        initParams(i, i2, i3, 0, onSeekBarChangeListener);
    }

    public void initParams(int i, int i2, int i3, int i4, OnSeekBarChangeListener onSeekBarChangeListener) {
        this.a.setText(i2);
        this.b.setText(i3);
        this.d.setMax(i);
        this.g = i4;
        this.e = onSeekBarChangeListener;
    }

    public void initParams(int i, String str, String str2, int i2, OnSeekBarChangeListener onSeekBarChangeListener) {
        this.a.setText(str);
        this.b.setText(str2);
        this.d.setMax(i);
        this.g = i2;
        this.e = onSeekBarChangeListener;
    }

    public void setProgress(int i) {
        if (i > this.f) {
            this.d.setProgress(this.f);
        } else {
            this.d.setProgress(i);
        }
    }

    public int getProgress() {
        return this.d.getProgress();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.c.setText("" + (this.g + i));
        if (this.e != null) {
            this.e.onProgressChanged(seekBar, i, z);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        this.c.setVisibility(0);
        if (this.e != null) {
            this.e.onStartTrackingTouch(seekBar);
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        this.c.setVisibility(4);
        if (this.e != null) {
            this.e.onStopTrackingTouch(seekBar);
        }
    }

    public SeekBar getSeekbaBar() {
        return this.d;
    }
}
