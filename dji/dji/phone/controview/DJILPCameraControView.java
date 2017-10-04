package dji.phone.controview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.log.DJILogHelper;
import dji.phone.c.a;
import dji.phone.d.d;
import dji.phone.e.a.e;
import dji.phone.e.b;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.thirdparty.a.c;

public class DJILPCameraControView extends LinearLayout implements OnClickListener {
    private static final int[] k = new int[]{R.drawable.lp_handle_mode_singlephoto_single_0s_off, R.drawable.lp_handle_mode_singlephoto_2s_off, R.drawable.lp_handle_mode_singlephoto_5s_off, R.drawable.lp_handle_mode_singlephoto_10s_off, R.drawable.lp_handle_mode_hdr_off, R.drawable.lp_handle_mode_longexposure_4s_off, R.drawable.lp_handle_mode_longexposure_8s_off, R.drawable.lp_handle_mode_longexposure_16s_off, R.drawable.lp_handle_mode_longexposure_32s_off, R.drawable.lp_handle_mode_longexposure_infinity_off, R.drawable.lp_mode_video_auto_off, R.drawable.lp_mode_video_timelapse_off, R.drawable.lp_mode_video_motion_timelapse_off, R.drawable.lp_pano_180_off, R.drawable.lp_pano_330_off, R.drawable.lp_pano_wide_off};
    public DJILPCameraModuleSwitcher a;
    public DJILPCameraShutterButton b;
    public DJIStateImageViewCompat c;
    public DJIStateImageViewCompat d;
    public DJIStateImageViewCompat e;
    public DJIImageView f;
    private final String g = "DJILPCameraControllerView";
    private int h = -1;
    private boolean i = false;
    private a j;
    private int l = -1;

    public DJILPCameraControView(Context context) {
        super(context);
    }

    public DJILPCameraControView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJILPCameraControView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void init() {
        this.l = a.g();
        c.a().a(this);
        a();
        if (this.l == 1) {
            Log.d("DJILPCameraControllerView", "init: mCurrentModuleId == DJILPBaseModule.VIDEO_MODULE_INDEX");
            this.d.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_handle_mode_video_auto));
        }
        if (this.l == 0) {
            Log.d("DJILPCameraControllerView", "init: mCurrentModuleId == DJILPBaseModule.PHOTO_MODULE_INDEX");
        }
    }

    private void a() {
        this.a = (DJILPCameraModuleSwitcher) findViewById(R.id.longan_camera_switch);
        this.a.setOnClickListener(this);
        this.b = (DJILPCameraShutterButton) findViewById(R.id.longan_camera_control_shutter_view);
        this.b.setCameraPresenter(this.j);
        this.c = (DJIStateImageViewCompat) findViewById(R.id.lp_camera_switch_iv);
        this.c.setOnClickListener(this);
        this.d = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_mode_iv);
        this.d.setOnClickListener(this);
        this.f = (DJIImageView) findViewById(R.id.scene_mode_settings_arrow);
        this.e = (DJIStateImageViewCompat) findViewById(R.id.lp_camera_playback);
        this.e.setOnClickListener(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            init();
            this.i = true;
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
        this.i = false;
    }

    public boolean isOnAttch() {
        return this.i;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_camera_switch) {
            DJILogHelper.getInstance().LOGD("DJILPCameraControllerView", "longan_camera_switch clicked");
            Log.e("DJILPCameraControllerView", "onClick: mCurrentModuleId = " + this.l);
        }
        Log.e("DJILPCameraControllerView", "onClick: vId = " + id);
        if (id == R.id.longan_camera_mode_iv) {
            b();
        }
        if (id == R.id.lp_camera_switch_iv) {
            this.j.a();
        }
        if (id == R.id.lp_camera_playback) {
            c();
        }
    }

    private void b() {
        if (this.d.isSelected()) {
            this.d.setSelected(false);
            c.a().e(new b(e.BTN_CAMERA_MODE, dji.phone.e.a.c.e));
            this.f.setImageDrawable(getResources().getDrawable(R.drawable.ic_leftbar_slider_arrow));
            setBackgroundResource(R.drawable.leftbar_bg_mask);
            return;
        }
        this.d.setSelected(true);
        c.a().e(new b(e.BTN_CAMERA_MODE, dji.phone.e.a.c.d));
        this.f.setImageDrawable(getResources().getDrawable(R.drawable.ic_leftbar_slider_arrow_pre));
        setBackground(null);
    }

    private void c() {
        try {
            Intent intent = new Intent(getContext(), Class.forName("dji.playback.entryActivity.DJIPlaybackMainActivity"));
            intent.setFlags(131072);
            getContext().startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onEventMainThread(dji.phone.tutorial.c.b bVar) {
        Log.d("DJILPCameraControllerView", "onEventMainThread: event = " + bVar.name());
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

    public void onEventMainThread(dji.phone.d.a.a aVar) {
        this.d.setImageDrawable(getResources().getDrawable(k[a(aVar)]));
    }

    private int a(dji.phone.d.a.a aVar) {
        switch (aVar) {
            case SINGLE_2s:
                return 1;
            case SINGLE_5s:
                return 2;
            case SINGLE_10s:
                return 3;
            case SINGLE_HDR:
                return 4;
            case LONGEXPOSURE_4s:
                return 5;
            case LONGEXPOSURE_8s:
                return 6;
            case LONGEXPOSURE_16s:
                return 7;
            case LONGEXPOSURE_32s:
                return 8;
            case LONGEXPOSURE_INFINITY:
                return 9;
            case PANO_180:
                return 13;
            case PANO_330:
                return 14;
            case PANO_WIDE:
                return 15;
            default:
                return 0;
        }
    }

    public void onEventMainThread(dji.phone.d.a.c cVar) {
        this.d.setImageDrawable(getResources().getDrawable(k[a(cVar)]));
    }

    private int a(dji.phone.d.a.c cVar) {
        switch (cVar) {
            case NOT_VEDIO:
            case AUTO:
                return 10;
            case TIMELAPSE_STATIONARY:
                return 11;
            case TIMELAPSE_MOTION:
                return 12;
            default:
                return 13;
        }
    }

    public void switchToVideoUI() {
        Log.d("DJILPCameraControllerView", "switchToVideoUI: ");
        this.l = 1;
        b.getInstance().a(b.a.RECORD);
        if (this.a != null) {
            this.a.switchToRecord();
        }
        this.b.a.hide();
        this.b.b.show();
        onEventMainThread(d.getInstance().i());
    }

    public void switchToPhotoUI() {
        Log.d("DJILPCameraControllerView", "switchToPhotoUI: ");
        this.l = 0;
        b.getInstance().a(b.a.TAKEPHOTO);
        this.a.switchToPhoto();
        this.b.a.show();
        this.b.b.hide();
        if (this.h == -1) {
            this.h = 0;
        }
        onEventMainThread(d.getInstance().j());
    }

    public void switchToRecordingUI(boolean z) {
        this.b.b.setSelected(z);
        if (z) {
            c.a().e(new b(e.BTN_CAMERA_MODE, dji.phone.e.a.c.e));
            this.d.setVisibility(4);
            this.e.setVisibility(4);
            this.c.setVisibility(4);
            this.a.setVisibility(4);
            this.f.setVisibility(4);
            return;
        }
        this.d.setVisibility(0);
        this.e.setVisibility(0);
        this.c.setVisibility(0);
        this.a.setVisibility(0);
        this.f.setVisibility(0);
    }

    public void setCameraPresenter(a aVar) {
        this.j = aVar;
    }

    public void switchToTakePhotoUI(boolean z) {
        this.b.a.setSelected(z);
    }

    public void resetView() {
        if (this.d.isSelected()) {
            this.d.setSelected(false);
            this.f.setImageDrawable(getResources().getDrawable(R.drawable.ic_leftbar_slider_arrow));
            setBackgroundResource(R.drawable.leftbar_bg_mask);
        }
    }

    public void onEventMainThread(b bVar) {
        if (bVar.a == e.BTN_CAMERA_MODE && bVar.c == dji.phone.e.a.c.e) {
            resetView();
        }
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        float a = dji.phone.k.c.a(bVar.b());
        dji.phone.h.a.a(this.c, this.c.getRotation(), a);
        dji.phone.h.a.a(this.b, this.b.getRotation(), a);
        dji.phone.h.a.a(this.d, this.d.getRotation(), a);
        dji.phone.h.a.a(this.e, this.e.getRotation(), a);
        dji.phone.h.a.a(this.a, this.a.getRotation(), a);
    }

    public void setSwitchLensBtnEnable(boolean z) {
        this.c.setEnabled(z);
    }
}
