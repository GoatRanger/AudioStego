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

public class DJISeekbarRightValue extends LinearLayout implements OnSeekBarChangeListener {
    private TextView a;
    private SeekBar b;
    private OnSeekBarChangeListener c;
    private int d = 100;
    private int e = 0;
    private boolean f = true;

    public DJISeekbarRightValue(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_widget_seekbar_right_value);
        if (!isInEditMode()) {
            this.a = (TextView) findViewById(R.id.setting_ui_progress_right);
            this.b = (SeekBar) findViewById(R.id.setting_ui_progress_seekbar);
            this.b.setOnSeekBarChangeListener(this);
        }
    }

    public void initParams(int i, OnSeekBarChangeListener onSeekBarChangeListener) {
        initParams(i, 0, onSeekBarChangeListener);
    }

    public void initParams(int i, int i2, OnSeekBarChangeListener onSeekBarChangeListener) {
        this.d = i;
        this.b.setMax(i);
        this.e = i2;
        this.c = onSeekBarChangeListener;
    }

    public void setProgress(int i) {
        if (i > this.d) {
            this.b.setProgress(this.d);
            setRightValue(this.d);
            return;
        }
        this.b.setProgress(i);
        setRightValue(i);
    }

    private void setRightValue(int i) {
        if (this.f) {
            this.a.setText("" + (this.e + i) + "%");
            return;
        }
        this.a.setText("" + (this.e + i));
    }

    public int getProgress() {
        return this.b.getProgress();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        setRightValue(i);
        if (this.c != null) {
            this.c.onProgressChanged(seekBar, i, z);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        if (this.c != null) {
            this.c.onStartTrackingTouch(seekBar);
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        if (this.c != null) {
            this.c.onStopTrackingTouch(seekBar);
        }
    }

    public SeekBar getSeekbaBar() {
        return this.b;
    }
}
