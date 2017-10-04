package com.nokia.maps;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.here.android.mpa.common.OnScreenCaptureListener;
import java.nio.ByteBuffer;

final class MapImpl$d implements Runnable {
    OnScreenCaptureListener a = null;
    final /* synthetic */ MapImpl b;

    public MapImpl$d(MapImpl mapImpl, OnScreenCaptureListener onScreenCaptureListener) {
        this.b = mapImpl;
        this.a = onScreenCaptureListener;
    }

    public void run() {
        byte[] bArr = new byte[((MapImpl.n(this.b) * MapImpl.o(this.b)) * 4)];
        Bitmap bitmap = null;
        if (MapImpl.a(this.b, bArr)) {
            try {
                bitmap = Bitmap.createBitmap(MapImpl.n(this.b), MapImpl.o(this.b), Config.ARGB_8888);
                bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
            } catch (Exception e) {
                bj.c(MapImpl.E(), e.getLocalizedMessage(), new Object[0]);
            }
        }
        ez.a(new Runnable(this) {
            final /* synthetic */ MapImpl$d b;

            public void run() {
                this.b.a.onScreenCaptured(bitmap);
            }
        });
    }
}
