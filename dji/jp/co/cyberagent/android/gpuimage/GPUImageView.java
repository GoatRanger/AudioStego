package jp.co.cyberagent.android.gpuimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import dji.midware.media.h.d;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.concurrent.Semaphore;

public class GPUImageView extends FrameLayout {
    public a a = null;
    private GPUImageGLSurfaceView b;
    private b c;
    private e d;

    private class GPUImageGLSurfaceView extends GLSurfaceView {
        final /* synthetic */ GPUImageView a;
        private Semaphore b = null;

        public GPUImageGLSurfaceView(GPUImageView gPUImageView, Context context) {
            this.a = gPUImageView;
            super(context);
        }

        public GPUImageGLSurfaceView(GPUImageView gPUImageView, Context context, AttributeSet attributeSet) {
            this.a = gPUImageView;
            super(context, attributeSet);
        }

        public void setWaiter(Semaphore semaphore) {
            this.b = semaphore;
        }

        protected void onMeasure(int i, int i2) {
            if (this.a.a != null) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(this.a.a.a, 1073741824), MeasureSpec.makeMeasureSpec(this.a.a.b, 1073741824));
            } else {
                super.onMeasure(i, i2);
            }
            if (this.b != null) {
                this.b.release();
                this.b = null;
            }
        }
    }

    public static class a {
        int a;
        int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public GPUImageView(Context context) {
        super(context);
        if (!isInEditMode()) {
            a(context, null);
        }
    }

    public GPUImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a(context, attributeSet);
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.b = new GPUImageGLSurfaceView(this, context, attributeSet);
        addView(this.b);
        this.c = new b(getContext());
        this.c.a(this.b);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public b getGPUImage() {
        return this.c;
    }

    public void setRotation(z zVar) {
        this.c.a(zVar);
        requestRender();
    }

    public void setFilter(e eVar) {
        this.d = eVar;
        this.c.a(eVar);
        requestRender();
    }

    public e getFilter() {
        return this.d;
    }

    public void setImage(Bitmap bitmap) {
        this.c.a(bitmap);
    }

    public void requestRender() {
        this.b.requestRender();
    }

    public Bitmap cutBitmap(int i, int i2, float f, float f2, float f3, float f4) throws InterruptedException, OutOfMemoryError {
        final Semaphore semaphore = new Semaphore(0);
        final int round = Math.round(((float) i) * f);
        final int round2 = Math.round(((float) i) * f3);
        final int round3 = Math.round(((float) i2) * f2);
        final int round4 = Math.round(((float) i2) * f4);
        this.c.a(new Runnable(this) {
            final /* synthetic */ GPUImageView b;

            public void run() {
                semaphore.release();
            }
        });
        requestRender();
        semaphore.acquire();
        final Bitmap createBitmap = Bitmap.createBitmap(round2, round4, Config.ARGB_8888);
        final int[] iArr = new int[round2];
        this.c.a(new Runnable(this) {
            final /* synthetic */ GPUImageView h;

            public void run() {
                Buffer wrap = IntBuffer.wrap(iArr);
                for (int i = 0; i < round4; i++) {
                    GLES20.glReadPixels(round, round3 + i, round2, 1, d.c, 5121, wrap);
                    GPUImageNativeLibrary.RGBA2ARGB(iArr);
                    createBitmap.setPixels(iArr, 0, round2, 0, (round4 - 1) - i, round2, 1);
                }
                semaphore.release();
            }
        });
        requestRender();
        semaphore.acquire();
        return createBitmap;
    }

    public Bitmap cutBitmapBaseOnPhotoSize(final int i, int i2, float f, float f2, float f3, float f4) throws InterruptedException, OutOfMemoryError {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Do not call this method from the UI thread!");
        }
        final int round = Math.round((((float) this.b.getMeasuredHeight()) / ((float) this.b.getMeasuredWidth())) * ((float) i));
        this.a = new a(i, round);
        final Semaphore semaphore = new Semaphore(0);
        this.b.setWaiter(semaphore);
        post(new Runnable(this) {
            final /* synthetic */ GPUImageView c;

            public void run() {
                LayoutParams layoutParams = new FrameLayout.LayoutParams(i, round);
                layoutParams.setMargins(10000, 0, 0, 0);
                this.c.b.setLayoutParams(layoutParams);
                this.c.b.requestLayout();
            }
        });
        semaphore.acquire();
        this.c.a(new Runnable(this) {
            final /* synthetic */ GPUImageView d;

            public void run() {
                this.d.c.a().onSurfaceChanged(null, i, round);
                semaphore.release();
            }
        });
        requestRender();
        semaphore.acquire();
        return cutBitmap(i, round, f, f2, f3, f4);
    }

    public Bitmap getBitmap() throws InterruptedException, OutOfMemoryError {
        final Semaphore semaphore = new Semaphore(0);
        final int measuredWidth = this.b.getMeasuredWidth();
        final int measuredHeight = this.b.getMeasuredHeight();
        final Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Config.ARGB_8888);
        final int[] iArr = new int[measuredWidth];
        this.c.a(new Runnable(this) {
            final /* synthetic */ GPUImageView f;

            public void run() {
                Buffer wrap = IntBuffer.wrap(iArr);
                for (int i = 0; i < measuredHeight; i++) {
                    GLES20.glReadPixels(0, i, measuredWidth, 1, d.c, 5121, wrap);
                    GPUImageNativeLibrary.RGBA2RGB(iArr);
                    createBitmap.setPixels(iArr, 0, measuredWidth, 0, (measuredHeight - 1) - i, measuredWidth, 1);
                }
                semaphore.release();
            }
        });
        requestRender();
        semaphore.acquire();
        return createBitmap;
    }

    public void onPause() {
        this.b.onPause();
    }

    public void onResume() {
        this.b.onResume();
    }
}
