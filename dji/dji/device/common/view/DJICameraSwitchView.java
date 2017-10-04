package dji.device.common.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.pilot.fpv.R;

public class DJICameraSwitchView extends RelativeLayout {
    c a = null;
    float b;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    boolean i;
    float j = 20.0f;
    private DJIAutoRotateImageView k;
    private DJIAutoRotateImageView l;
    private DJIStateImageViewCompat m;
    private DJIStateImageViewCompat n;
    private final int o = 1;
    private Animation p;
    private Animation q;
    private a r = a.PHOTOING;
    private Matrix s = new Matrix();

    public interface c {
        void a(MODE mode);
    }

    public interface b {
        void a();
    }

    public enum a {
        PHOTOING,
        RECORDING
    }

    public DJICameraSwitchView(Context context) {
        super(context);
        a();
    }

    public DJICameraSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public DJICameraSwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.longan_camera_switch_view, null));
        this.k = (DJIAutoRotateImageView) findViewById(R.id.longan_camera_switch_photo);
        this.l = (DJIAutoRotateImageView) findViewById(R.id.longan_camera_switch_video);
        this.m = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_switch_thumb_photo);
        this.n = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_switch_thumb_video);
        b();
        a(getResources().getConfiguration().orientation);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(configuration.orientation);
    }

    private void a(int i) {
        int i2 = 0;
        if (i == 1) {
            i2 = -90;
        }
        setRotation((float) i2);
    }

    public void setOnModeChengeCallback(c cVar) {
        if (cVar != null) {
            this.a = cVar;
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            setAlpha(1.0f);
        } else {
            setAlpha(0.3f);
        }
    }

    public void setState(a aVar) {
        this.r = aVar;
    }

    public a getState() {
        return this.r;
    }

    public boolean isOnLeftOrTop() {
        return this.r == a.PHOTOING;
    }

    public void switchToRecord(b bVar) {
        this.m.setVisibility(4);
        this.m.startAnimation(this.p);
        if (bVar != null) {
            bVar.a();
        }
    }

    public void switchToPhoto(b bVar) {
        this.n.setVisibility(4);
        this.n.startAnimation(this.q);
        if (bVar != null) {
            bVar.a();
        }
    }

    private void b() {
        this.p = AnimationUtils.loadAnimation(getContext(), R.anim.longan_switch_slide_down);
        this.p.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJICameraSwitchView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.n.setVisibility(0);
                this.a.m.setVisibility(4);
                this.a.k.setImageResource(R.drawable.longan_photo_mini_off);
                this.a.l.setImageResource(R.drawable.longan_video_mini_on);
            }
        });
        this.q = AnimationUtils.loadAnimation(getContext(), R.anim.longan_switch_slide_up);
        this.q.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJICameraSwitchView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.m.setVisibility(0);
                this.a.n.setVisibility(4);
                this.a.k.setImageResource(R.drawable.longan_photo_mini_on);
                this.a.l.setImageResource(R.drawable.longan_video_mini_off);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                this.a.a(DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO ? MODE.RECORD : MODE.TAKEPHOTO);
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
