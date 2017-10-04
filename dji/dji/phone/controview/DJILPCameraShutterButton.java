package dji.phone.controview;

import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import dji.device.common.view.DJICameraAnimViewCompat;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.log.DJILogHelper;
import dji.phone.d.a;
import dji.phone.d.d;
import dji.pilot.fpv.R;
import dji.pilot.phonecamera.a.c;

public class DJILPCameraShutterButton extends RelativeLayout implements OnClickListener {
    public static boolean e = false;
    DJIStateImageViewCompat a;
    DJIStateImageViewCompat b;
    Animation c;
    DJICameraAnimViewCompat d;
    AudioManager f;
    boolean g = true;
    boolean h = false;
    private final String i = "LonganShutterButton";
    private a j;

    public DJILPCameraShutterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.j = null;
    }

    private void a() {
        this.a = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_controll_photo);
        this.b = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_controll_record);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.f = (AudioManager) getContext().getSystemService("audio");
        b();
        setEnabled(true);
        if (c.a().o() == 1) {
            this.b.show();
            this.a.hide();
        } else if (c.a().o() == 0) {
            this.a.show();
            this.b.hide();
        }
    }

    private void b() {
        this.c = AnimationUtils.loadAnimation(getContext(), R.anim.longan_saving_rotate);
        this.c.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJILPCameraShutterButton a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.a.h = true;
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.h = false;
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_camera_controll_photo) {
            Log.d("LonganShutterButton", "onClick: takePicture ");
            this.j.b();
        } else if (id != R.id.longan_camera_controll_record) {
        } else {
            if ((d.getInstance().i() == a.c.TIMELAPSE_STATIONARY || d.getInstance().i() == a.c.TIMELAPSE_MOTION) && !e) {
                dji.thirdparty.a.c.a().e(dji.phone.e.a.a.n);
            } else if (this.g) {
                setCanStopOrStartRecording(false);
                if (e) {
                    Log.d("LonganShutterButton", "onClick: stopVideoRecording ");
                    this.j.f();
                    return;
                }
                Log.d("LonganShutterButton", "onClick: startVideoRecording ");
                this.j.e();
            }
        }
    }

    public void updateVideoRecording(boolean z) {
        this.b.setSelected(z);
        e = z;
        setCanStopOrStartRecording(true);
    }

    private void setCanStopOrStartRecording(boolean z) {
        this.g = z;
        DJILogHelper.getInstance().LOGD("LonganShutterButton", "setCanStopOrStartRecording", false, true);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            setAlpha(1.0f);
            this.a.setEnabled(true);
            this.b.setEnabled(true);
            return;
        }
        setAlpha(0.3f);
        this.a.setEnabled(false);
        this.b.setEnabled(false);
    }

    public void setCameraPresenter(a aVar) {
        this.j = aVar;
    }
}
