package dji.device.timelapse;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.camera.a.c.a;
import dji.device.camera.a.c.b;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIRoundLinearLayoutCompat;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganNewTimelapseMainLayout extends DJIRoundLinearLayoutCompat implements OnClickListener {
    public static boolean e = false;
    LonganTimelapseSetLayout a;
    LonganTimelapseMotionLayout b;
    LayoutParams c;
    boolean d = false;

    public LonganNewTimelapseMainLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setHasFrame(true);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
        b.getInstance().b();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    private void a() {
        if (getResources().getConfiguration().orientation == 2) {
            this.c.addRule(3, 0);
            this.c.addRule(14, 0);
            this.c.addRule(0, R.id.longan_preview_setting_bar);
            this.c.addRule(15);
        } else {
            this.c.addRule(0, 0);
            this.c.addRule(15, 0);
            this.c.addRule(3, R.id.longan_preview_setting_bar);
            this.c.addRule(14);
        }
        setLayoutParams(this.c);
    }

    private void b() {
        c();
        c.a().a(this);
    }

    private void c() {
        this.c = (LayoutParams) getLayoutParams();
        this.a = (LonganTimelapseSetLayout) findViewById(R.id.longan_timelapse_set_ly);
        this.b = (LonganTimelapseMotionLayout) findViewById(R.id.longan_new_timelapse_motion_ly);
    }

    public int getPixel(int i) {
        return getResources().getDimensionPixelOffset(i);
    }

    public void onClick(View view) {
        view.getId();
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.HIDE_ALL) {
            c.a().e(m.HIDE_TIMELAPSE_LY);
            c.a().e(m.HIDE_INFO_BAR);
        } else if (mVar == m.SHOW_SET_PARMS_LY) {
            this.a.show();
            e = true;
            this.b.hide();
        } else if (mVar == m.SHOW_MOTION_LY) {
            this.a.hide();
            e = false;
            this.b.show();
        } else if (mVar == m.HIDE_TIMELAPSE_LY) {
            super.hide();
            e = false;
        } else if (mVar == m.SHOW_TIMELAPSE_LY) {
            super.show();
            e = true;
        } else if (mVar == m.HIDE_SHOTCUTS_CAMERA_LY || mVar == m.HIDE_SHOTCUTS_GIMBAL_LY) {
            if (dji.device.camera.a.c.getInstance().c() == b.TIMELAPSE && dji.device.camera.a.b.getInstance().d() == dji.device.camera.a.b.c.NOT_TIMING) {
                show();
            }
        } else if (mVar == m.SHOW_SHOTCUTS_CAMERA_LY || mVar == m.SHOW_SHOTCUTS_GIMBAL_LY) {
            hide();
        }
    }

    public void onEventMainThread(dji.device.camera.a.b.c cVar) {
        if (dji.device.camera.a.c.getInstance().d() != a.TIMELAPSE) {
            return;
        }
        if (cVar == dji.device.camera.a.b.c.TIMING) {
            hide();
        } else if (cVar == dji.device.camera.a.b.c.NOT_TIMING) {
            show();
            this.a.show();
            this.b.hide();
        }
    }

    public void onEventMainThread(dji.device.camera.a.c cVar) {
        if (cVar.c() != b.TIMELAPSE) {
            hide();
        } else if (cVar.c() == b.TIMELAPSE) {
            show();
        }
    }

    public void hide() {
        super.hide();
        e = false;
        c.a().e(m.HIDE_TIMELAPSE_LY);
    }

    public void show() {
        super.show();
        e = true;
        c.a().e(m.SHOW_TIMELAPSE_LY);
    }
}
