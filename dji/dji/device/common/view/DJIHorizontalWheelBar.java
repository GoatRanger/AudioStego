package dji.device.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import antistatic.spinnerwheel.WheelHorizontalView;
import antistatic.spinnerwheel.a.e;
import antistatic.spinnerwheel.b;
import antistatic.spinnerwheel.d;
import dji.pilot.fpv.R;

public class DJIHorizontalWheelBar extends RelativeLayout {
    WheelHorizontalView a;
    e b;

    public DJIHorizontalWheelBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void a(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.longan_new_timelapse_land_mode_view, null, false);
        addView(inflate);
        this.a = (WheelHorizontalView) inflate.findViewById(R.id.hor_wheel);
    }

    public void setAdapter(e eVar) {
        this.a.setViewAdapter(eVar);
    }

    public void setCurrentItem(int i) {
        this.a.setCurrentItem(i);
    }

    public WheelHorizontalView getWheel() {
        return this.a;
    }

    public e getAdapter() {
        return this.b;
    }

    public void addChangingListener(b bVar) {
        this.a.addChangingListener(bVar);
    }

    public void addScrollingListener(d dVar) {
        this.a.addScrollingListener(dVar);
    }
}
