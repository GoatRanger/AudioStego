package dji.setting.demo;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import dji.midware.e.h;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.media.h.b.b;
import dji.midware.media.h.e;
import dji.midware.natives.FPVController;
import dji.midware.util.i;
import dji.thirdparty.a.c;
import java.util.Timer;
import java.util.TimerTask;

public class CameraTextureView extends TextureView implements SurfaceTextureListener, h {
    public static boolean a = false;
    private static final String b = "CameraTest";
    private DJIVideoDecoder c = null;
    private b d = null;
    private Handler e = null;
    private a f = a.Radio_None;
    private int g = 0;

    public enum a {
        Radio_None,
        Radio_16_9,
        Radio_4_3
    }

    public CameraTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    private void a() {
        Log.d(b, "CameraTextureView init");
        this.e = new Handler(Looper.getMainLooper());
        setSurfaceTextureListener(this);
        if (a) {
            new Timer("simple fps").schedule(new TimerTask(this) {
                final /* synthetic */ CameraTextureView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.g = 0;
                }
            }, 0, 1000);
        }
    }

    private void b() {
        Log.d(b, "CameraTextureView uninit");
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.d(b, "CameraTextureView onSurfaceTextureAvailable");
        this.d = e.a();
        this.d.a(surfaceTexture, i, i2);
        this.c = new DJIVideoDecoder(getContext(), this.d);
        this.c.setRecvDataCallBack(this);
        FPVController.native_setDecodeMode(i.b(getContext(), "DecodeMode", false));
        c();
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.d(b, "CameraTextureView onSurfaceTextureSizeChanged width: " + getWidth() + ", height : " + getHeight());
        if (this.d != null) {
            this.d.a(i, i2);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Log.d(b, "CameraTextureView onSurfaceTextureDestroyed");
        if (this.c != null) {
            this.c.release();
            this.c.setSurface(null);
            this.c = null;
        }
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void oneFrameComeIn() {
        if (a) {
            this.g++;
        }
    }

    public void resetVideoSurface(int i, int i2) {
        Log.d(b, "CameraTextureView resetVideoSurface width: " + i + ", height : " + i2);
        c();
    }

    private void c() {
        int i = DJIVideoDecoder.width;
        int i2 = DJIVideoDecoder.height;
        if (i == 0 || i2 == 0) {
            this.f = a.Radio_None;
        } else if (i * 3 == i2 * 4) {
            this.f = a.Radio_4_3;
        } else if (i * 9 == i2 * 16) {
            this.f = a.Radio_16_9;
        } else {
            this.f = a.Radio_None;
        }
        c.a().e(this.f);
    }

    public a getVideoRadioType() {
        return this.f;
    }
}
