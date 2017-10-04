package dji.pilot.longan;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.e.h;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.media.h.b.b;
import dji.midware.natives.FPVController;
import dji.pilot.longan.LonganVideoSurfaceView.a;
import dji.thirdparty.a.c;

public class LonganCameraLiveView extends RelativeLayout implements h, a {
    public final String a = "CameraLiveView";
    protected RatioType b = RatioType.OTHER;
    protected dji.midware.util.a c;
    protected LonganVideoSurfaceView d;
    protected b e = null;
    protected a f = null;
    private Handler g = new Handler(Looper.getMainLooper());
    private Handler h;
    private volatile ExposureMode i = ExposureMode.i;

    public LonganCameraLiveView(Context context) {
        super(context);
    }

    public LonganCameraLiveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = DataCameraGetPushShotParams.getInstance().getImageRatio();
        c.a().a(this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(getLayoutParams().width, getLayoutParams().height);
        layoutParams.addRule(13);
        addView(this.d, layoutParams);
        this.c = new dji.midware.util.a();
        this.c.a(new dji.midware.util.a.a(this) {
            final /* synthetic */ LonganCameraLiveView a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, final int i3, final int i4) {
                System.out.println("AutoVideoSizeCalculator onVideoSizeChanged width : " + i + ", height: " + i2 + "real width:" + i3 + "real height" + i4);
                this.a.g.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 c;

                    public void run() {
                        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
                        layoutParams.addRule(13, -1);
                        this.c.a.d.setLayoutParams(layoutParams);
                    }
                });
            }
        });
        this.d.setRenderer();
        this.d.setRenderListener(this);
        b();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
        c.a().d(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    private void a() {
        this.h = new Handler();
        this.d = new LonganVideoSurfaceView(getContext());
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        b();
    }

    private dji.midware.util.a.b.b getVideoType() {
        if (this.f == null || this.f.b == null) {
            return null;
        }
        DJIVideoDecoder dJIVideoDecoder = this.f.b;
        int i = DJIVideoDecoder.width;
        DJIVideoDecoder dJIVideoDecoder2 = this.f.b;
        int i2 = DJIVideoDecoder.height;
        ProductType c = i.getInstance().c();
        if (c == ProductType.KumquatS || c == ProductType.Pomato) {
            float f = (((float) i) * 1.0f) / ((float) i2);
            if (Math.abs(f - dji.midware.util.a.b.a) < Math.abs(f - dji.midware.util.a.b.c)) {
                return dji.midware.util.a.b.b.a;
            }
            if (Math.abs(f - dji.midware.util.a.b.c) < Math.abs(f - dji.midware.util.a.b.b)) {
                return dji.midware.util.a.b.b.b;
            }
            return dji.midware.util.a.b.b.c;
        } else if (this.b == RatioType.R_4_3 && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
            return dji.midware.util.a.b.b.d;
        } else {
            if (this.b == RatioType.R_3_2 && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
                return dji.midware.util.a.b.b.e;
            }
            return dji.midware.util.a.b.b.a;
        }
    }

    private int getShowWidth() {
        return getWidth();
    }

    private int getShowHeight() {
        return getHeight();
    }

    private void b() {
        dji.midware.util.a.b a = this.c.a();
        dji.midware.util.a.b.b videoType = getVideoType();
        if (videoType != null) {
            a.a(videoType);
            a.a(getShowWidth(), getShowHeight());
            this.c.b();
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        RatioType imageRatio = DataCameraGetPushShotParams.getInstance().getImageRatio();
        if (imageRatio != this.b) {
            this.b = imageRatio;
            b();
        }
    }

    public void onRenderCreatedPrepared() {
        b();
    }

    public a onRenderCreated(b bVar) {
        System.out.println("xxxgbac glsurface");
        this.e = bVar;
        if (this.f == null) {
            a(bVar);
        } else {
            this.f.a(bVar);
        }
        return this.f;
    }

    public void onRenderDestroy() {
        if (this.f != null) {
            this.f.a(null);
        }
    }

    protected void a(b bVar) {
        this.f = new a(getContext(), bVar);
        this.f.a((h) this);
        this.f.a();
        FPVController.native_setDecodeMode(false);
    }

    public void oneFrameComeIn() {
    }

    public void resetVideoSurface(int i, int i2) {
        b();
    }

    private void c() {
        if (this.f != null) {
            this.f.a(null);
            this.f.b();
            this.f = null;
        }
        if (this.e != null) {
            this.e.c();
            this.e = null;
        }
    }

    public Bitmap getBitmap() {
        if (this.d != null) {
            return this.d.getBitmap();
        }
        return null;
    }

    public void setYUVScale(float f) {
        if (this.e != null) {
            this.e.a(f);
        }
    }

    public void resetToManager() {
        if (this.f != null) {
            this.f.a();
        }
    }
}
