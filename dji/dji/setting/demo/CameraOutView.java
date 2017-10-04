package dji.setting.demo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.pilot.setting.ui.R;
import dji.setting.demo.CameraTextureView.a;
import dji.thirdparty.a.c;

public class CameraOutView extends RelativeLayout {
    private static final String b = "CameraTest";
    LayoutParams a;
    private RelativeLayout c;
    private CameraTextureView d;
    private Handler e;

    public CameraOutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.c = (RelativeLayout) findViewById(R.id.camera_in_view);
        this.d = (CameraTextureView) findViewById(R.id.camera_texture_view);
        this.e = new Handler(Looper.getMainLooper());
        a(getWidth(), getHeight());
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        Log.d("onSizeChanged", "CameraOutView onSizeChanged");
        super.onSizeChanged(i, i2, i3, i4);
        this.e.postDelayed(new Runnable(this) {
            final /* synthetic */ CameraOutView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a(this.a.getWidth(), this.a.getHeight());
            }
        }, 100);
    }

    public void onEventMainThread(a aVar) {
        Log.d(b, "onEventMainThread VideoRadioType:" + aVar);
        a(getWidth(), getHeight());
    }

    private void a(int i, int i2) {
        if (i != 0 && i2 != 0) {
            int i3;
            if (this.d.getVideoRadioType() == a.Radio_4_3) {
                i3 = (i * 3) / 4;
                if (i3 > i2) {
                    i = (i * 4) / 3;
                } else {
                    i2 = i3;
                }
            } else {
                i3 = (i * 9) / 16;
                if (i3 > i2) {
                    i = (i * 16) / 9;
                } else {
                    i2 = i3;
                }
            }
            LayoutParams layoutParams = (LayoutParams) this.c.getLayoutParams();
            layoutParams.width = i;
            layoutParams.height = i2;
            this.c.requestLayout();
        }
    }
}
