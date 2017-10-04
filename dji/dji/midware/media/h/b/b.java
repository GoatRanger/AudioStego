package dji.midware.media.h.b;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import dji.midware.media.k.b.a;

public interface b {
    Surface a();

    void a(float f);

    void a(int i, int i2);

    void a(int i, int i2, int i3, int i4);

    void a(a aVar);

    void a(Object obj);

    void a(Object obj, int i, int i2);

    void a(Object obj, int i, int i2, int i3, int i4);

    void a(boolean z, int i);

    Bitmap b(int i, int i2);

    void c();

    void onFrameAvailable(SurfaceTexture surfaceTexture);
}
