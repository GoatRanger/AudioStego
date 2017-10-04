package dji.phone.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.RadioButton;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.device.common.view.DJIRadioGroup;
import dji.device.common.view.DJIRadioGroup.b;
import dji.log.DJILogHelper;
import dji.phone.controview.a;
import dji.phone.d.d;
import dji.phone.e.a.e;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJILinearLayout;
import dji.thirdparty.a.c;

public class DJILPCameraLevel1MenuView extends DJILinearLayout implements b {
    LayoutParams a;
    TextView b;
    TextView c;
    DJIRadioGroup d;
    DJIRadioGroup e;
    TextView f;
    private final String g = "DJILPCameraLevel1MenuView";
    private a h;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b = new int[dji.phone.e.a.a.values().length];

        static {
            try {
                b[dji.phone.e.a.a.c.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[dji.phone.e.a.a.d.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            a = new int[e.values().length];
            try {
                a[e.VIEW_CAMERA_MODE.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[e.BTN_CAMERA_VIDEO_AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public DJILPCameraLevel1MenuView(Context context) {
        super(context);
    }

    public DJILPCameraLevel1MenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJILPCameraLevel1MenuView(Context context, AttributeSet attributeSet, int i) {
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
            DJILogHelper.getInstance().LOGD("init log", "L1 init");
            c.a().a(this);
            this.b = (TextView) findViewById(R.id.longan_modeset_single_tv);
            this.c = (TextView) findViewById(R.id.longan_modeset_pano_tv);
            this.f = (TextView) findViewById(R.id.longan_modeset_longex_tv);
            this.a = (LayoutParams) getLayoutParams();
            this.d = (DJIRadioGroup) findViewById(R.id.lp_radiogroup_photomodes);
            this.d.setOnCheckedChangeListener(this);
        }
    }

    public void setAnimationVisibility(int i) {
        if (i == 0) {
            show();
        } else {
            hide();
        }
    }

    public void show(boolean z) {
        if (z) {
            setAnimationVisibility(0);
        } else {
            setAnimationVisibility(4);
        }
    }

    public void onCheckedChanged(DJIRadioGroup dJIRadioGroup, int i) {
        RadioButton radioButtonByCheckedId = dJIRadioGroup.getRadioButtonByCheckedId(i);
        if (radioButtonByCheckedId != null && radioButtonByCheckedId.isChecked()) {
            boolean z = false;
            if (i == R.id.longan_modeset_single) {
                d.getInstance().a(dji.phone.d.a.b.SINGLE, true);
            } else if (i == R.id.longan_modeset_pano) {
                d.getInstance().a(dji.phone.d.a.b.PANO, true);
            } else if (i == R.id.longan_handle_mode_video_auto) {
                a();
                z = true;
            } else if (i == R.id.longan_handle_mode_video_timelapse) {
                if (dji.phone.f.a.getInstance().c()) {
                    dji.phone.k.b.showLong(R.string.lp_gimbal_sleep_warning);
                    a(R.id.longan_handle_mode_video_auto);
                } else {
                    d.getInstance().a(dji.phone.d.a.c.TIMELAPSE_STATIONARY, true);
                    c.a().e(new dji.phone.e.b(e.BTN_CAMERA_VIDEO_TLP, dji.phone.e.a.c.d));
                    z = true;
                }
            } else if (i == R.id.longan_handle_mode_video_motion_timelapse) {
                if (dji.phone.f.a.getInstance().c()) {
                    dji.phone.k.b.showLong(R.string.lp_gimbal_sleep_warning);
                    a(R.id.longan_handle_mode_video_auto);
                } else {
                    d.getInstance().a(dji.phone.d.a.c.TIMELAPSE_MOTION, true);
                    c.a().e(new dji.phone.e.b(e.BTN_CAMERA_VIDEO_TLP, dji.phone.e.a.c.d));
                    z = true;
                }
            } else if (i == R.id.longan_modeset_longex) {
                if (dji.phone.f.a.getInstance().c()) {
                    dji.phone.k.b.showLong(R.string.lp_gimbal_sleep_warning);
                    a(R.id.longan_modeset_single);
                } else {
                    d.getInstance().a(dji.phone.d.a.b.LONGEXPOSURE, true);
                }
            }
            if (!(i == R.id.longan_handle_mode_video_timelapse || i == R.id.longan_handle_mode_video_motion_timelapse)) {
                c.a().e(new dji.phone.e.b(e.BTN_CAMERA_VIDEO_TLP, dji.phone.e.a.c.e));
            }
            if (z) {
                c.a().e(new dji.phone.e.b(e.BTN_CAMERA_MODE, dji.phone.e.a.c.e));
            }
        }
    }

    private void a() {
        d.getInstance().a(dji.phone.d.a.c.AUTO, true);
        c.a().e(new dji.phone.e.b(e.BTN_CAMERA_MODE, dji.phone.e.a.c.e));
    }

    private void a(int i) {
        if (this.d.findViewById(i) != null) {
            this.d.check(i);
        } else {
            this.e.check(i);
        }
    }

    private DJIRadioGroup a(DJIRadioGroup dJIRadioGroup, int i) {
        if (findViewById(i) != null) {
            return (DJIRadioGroup) ((ViewStub) findViewById(i)).inflate();
        }
        dJIRadioGroup.setVisibility(0);
        return dJIRadioGroup;
    }

    private void b() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(8);
        }
    }

    public void switchModuleToVideo() {
        b();
        this.e = a(this.e, R.id.longan_radiogroup_videomodes_vs);
        DJIRadioGroup dJIRadioGroup = this.e;
        if (dJIRadioGroup != null) {
            dJIRadioGroup.setOrientation(getOrientation());
            dJIRadioGroup.setOnCheckedChangeListener(this);
        }
    }

    public void switchModuleToPhoto() {
        b();
        this.d = (DJIRadioGroup) findViewById(R.id.lp_radiogroup_photomodes);
        this.d.setVisibility(0);
        DJIRadioGroup dJIRadioGroup = this.d;
        if (dJIRadioGroup != null) {
            dJIRadioGroup.setOrientation(getOrientation());
            dJIRadioGroup.setOnCheckedChangeListener(this);
        }
    }

    public void show() {
        if (dji.phone.controview.b.getInstance().b() == dji.phone.controview.b.a.RECORD) {
            switchModuleToVideo();
        } else if (dji.phone.controview.b.getInstance().b() == dji.phone.controview.b.a.TAKEPHOTO) {
            switchModuleToPhoto();
        }
        super.show();
    }

    public void onEventMainThread(dji.phone.e.b bVar) {
        e eVar = bVar.a;
        dji.phone.e.a.c cVar = bVar.c;
        switch (eVar) {
            case VIEW_CAMERA_MODE:
                if (cVar != dji.phone.e.a.c.f) {
                    if (cVar == dji.phone.e.a.c.g) {
                        hide();
                        break;
                    }
                }
                show();
                break;
                break;
            case BTN_CAMERA_VIDEO_AUTO:
                if (cVar == dji.phone.e.a.c.d && d.getInstance().i() != dji.phone.d.a.c.AUTO) {
                    a(R.id.longan_handle_mode_video_auto);
                    break;
                }
        }
        switch (AnonymousClass1.b[bVar.b.ordinal()]) {
            case 1:
            case 2:
                if (d.getInstance().i() != dji.phone.d.a.c.AUTO) {
                    a(R.id.longan_handle_mode_video_auto);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setCameraPresenter(a aVar) {
        this.h = aVar;
    }
}
