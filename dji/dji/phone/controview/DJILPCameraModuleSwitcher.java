package dji.phone.controview;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import dji.device.common.view.DJIAutoRotateImageView;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class DJILPCameraModuleSwitcher extends RelativeLayout {
    private String a = DJILPCameraModuleSwitcher.class.getSimpleName();
    private DJIAutoRotateImageView b;
    private DJIAutoRotateImageView c;
    private DJIStateImageViewCompat d;
    private DJIStateImageViewCompat e;
    private float f;
    private boolean g = true;
    private Animation h;
    private Animation i;
    private int j = 0;
    private a k;

    public interface a {
        void d(int i);
    }

    public DJILPCameraModuleSwitcher(Context context) {
        super(context);
        a();
    }

    public DJILPCameraModuleSwitcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public DJILPCameraModuleSwitcher(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.lp_camera_switch_view, null));
        this.b = (DJIAutoRotateImageView) findViewById(R.id.longan_camera_switch_photo);
        this.c = (DJIAutoRotateImageView) findViewById(R.id.longan_camera_switch_video);
        this.d = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_switch_thumb_photo);
        this.e = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_switch_thumb_video);
        b();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        if (dji.pilot.phonecamera.a.c.a().o() == 1) {
            switchToRecord();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public synchronized void setEnabled(boolean z) {
        super.setEnabled(z);
        this.g = z;
        if (z) {
            setAlpha(1.0f);
        } else {
            setAlpha(0.3f);
        }
    }

    public void setRotation(float f) {
        this.b.setRotation(f);
        this.c.setRotation(f);
        this.d.setRotation(f);
        this.e.setRotation(f);
        this.f = f;
    }

    public float getRotation() {
        return this.f;
    }

    public void switchToRecord() {
        if (this.j == 0) {
            Log.d(this.a, "switchToRecord: ");
            this.d.setVisibility(4);
            this.d.startAnimation(this.h);
            this.j = 1;
        }
    }

    public void switchToPhoto() {
        if (this.j == 1) {
            Log.d(this.a, "switchToPhoto: ");
            this.e.setVisibility(4);
            this.e.startAnimation(this.i);
            this.j = 0;
        }
    }

    private void b() {
        this.h = AnimationUtils.loadAnimation(getContext(), R.anim.longan_switch_slide_down);
        this.h.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJILPCameraModuleSwitcher a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.a.b.setImageResource(R.drawable.ic_leftbar_exchange_photo);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.e.setVisibility(4);
                this.a.d.setVisibility(4);
                this.a.b.setImageResource(R.drawable.ic_leftbar_exchange_photo);
                this.a.c.setImageResource(R.drawable.ic_leftbar_exchange_video_selected);
            }
        });
        this.i = AnimationUtils.loadAnimation(getContext(), R.anim.longan_switch_slide_up);
        this.i.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJILPCameraModuleSwitcher a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.a.c.setImageResource(R.drawable.ic_leftbar_exchange_video);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.d.setVisibility(4);
                this.a.e.setVisibility(4);
                this.a.c.setImageResource(R.drawable.ic_leftbar_exchange_video);
                this.a.b.setImageResource(R.drawable.ic_leftbar_exchange_photo_selected);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.g) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                setEnabled(false);
                if (this.k != null) {
                    if (this.j != 0) {
                        if (this.j == 1) {
                            this.k.d(0);
                            break;
                        }
                    }
                    this.k.d(1);
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onEventMainThread(dji.phone.d.c.a aVar) {
        setEnabled(true);
    }

    public void setSwitchCallback(a aVar) {
        this.k = aVar;
    }
}
