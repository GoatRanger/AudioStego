package dji.phone.rightbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.phone.e.a.e;
import dji.phone.e.b;
import dji.phone.h.a;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class DJILPCameraQuickSettingView extends LinearLayout implements OnClickListener {
    private static final String b = "DJILPCameraQuickSettingView";
    LayoutParams a;
    private Animation c;
    private Animation d;
    private DJIStateImageViewCompat e;
    private DJIStateImageViewCompat f;
    private DJIStateImageViewCompat g;
    private DJIStateImageViewCompat h;
    private boolean i = false;

    public DJILPCameraQuickSettingView(Context context) {
        super(context);
    }

    public DJILPCameraQuickSettingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJILPCameraQuickSettingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void init() {
        if (!isInEditMode()) {
            c.a().a(this);
            this.e = (DJIStateImageViewCompat) findViewById(R.id.longan_fpv_home);
            this.f = (DJIStateImageViewCompat) findViewById(R.id.longan_fpv_camera);
            this.g = (DJIStateImageViewCompat) findViewById(R.id.longan_fpv_setting);
            this.h = (DJIStateImageViewCompat) findViewById(R.id.longan_fpv_gimbal);
            this.e.setOnClickListener(this);
            this.f.setOnClickListener(this);
            this.g.setOnClickListener(this);
            this.h.setOnClickListener(this);
            a();
            if (this.i) {
                this.h.setEnabled(true);
            }
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void a() {
        this.c = AnimationUtils.loadAnimation(getContext(), R.anim.main_contain_slide_right_in);
        this.d = AnimationUtils.loadAnimation(getContext(), R.anim.main_contain_slide_top_in);
    }

    public void onEventMainThread(b bVar) {
        e eVar = bVar.a;
        if (bVar.c == dji.phone.e.a.c.e) {
            switch (eVar) {
                case BTN_SHOTCUT_CAMERA:
                    b();
                    return;
                case BTN_SHOTCUT_GIMBAL:
                    c();
                    return;
                case BTN_CAMERA_ID_SWITCHER:
                    resetView();
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(dji.phone.tutorial.c.b bVar) {
        Log.d(b, "onEventMainThread: event = " + bVar.name());
        switch (bVar) {
            case START:
                setVisibility(4);
                return;
            case STOP:
                setVisibility(0);
                break;
            case FINISH:
                break;
            default:
                return;
        }
        setVisibility(0);
    }

    private void b() {
        if (this.f.isSelected()) {
            this.f.setSelected(false);
        }
        d();
    }

    private void c() {
        if (this.h.isSelected()) {
            this.h.setSelected(false);
        }
        d();
    }

    private void d() {
        if (e()) {
            setBackground(getResources().getDrawable(R.drawable.right_to_left_mask_bg));
        } else {
            setBackgroundColor(getResources().getColor(R.color.black_80P_longan));
        }
    }

    public void resetView() {
        b();
        c();
    }

    private boolean e() {
        return (this.f.isSelected() || this.h.isSelected()) ? false : true;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_fpv_home) {
            ((Activity) getContext()).finish();
        } else if (id == R.id.longan_fpv_camera) {
            if (this.f.isSelected()) {
                this.f.setSelected(false);
                c.a().e(new b(e.BTN_SHOTCUT_CAMERA, dji.phone.e.a.c.e));
                return;
            }
            this.f.setSelected(true);
            c.a().e(new b(e.BTN_SHOTCUT_CAMERA, dji.phone.e.a.c.d));
        } else if (id == R.id.longan_fpv_setting) {
            try {
                Class cls = Class.forName("dji.pilot.set.SetProxy");
                cls.getMethod("showSetActivity", new Class[]{Context.class}).invoke(cls, new Object[]{getContext()});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id != R.id.longan_fpv_gimbal) {
        } else {
            if (this.h.isSelected()) {
                this.h.setSelected(false);
                c.a().e(new b(e.BTN_SHOTCUT_GIMBAL, dji.phone.e.a.c.e));
                return;
            }
            this.h.setSelected(true);
            c.a().e(new b(e.BTN_SHOTCUT_GIMBAL, dji.phone.e.a.c.d));
        }
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        float a = dji.phone.k.c.a(bVar.b());
        a.a(this.f, this.f.getRotation(), a);
        a.a(this.h, this.h.getRotation(), a);
        a.a(this.e, this.e.getRotation(), a);
        a.a(this.g, this.g.getRotation(), a);
    }
}
