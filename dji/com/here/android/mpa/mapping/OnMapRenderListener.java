package com.here.android.mpa.mapping;

import com.nokia.maps.annotation.Online;

@Online
public interface OnMapRenderListener {

    @Online
    public static abstract class OnMapRenderListenerAdapter implements OnMapRenderListener {
        public void onPreDraw() {
        }

        public void onPostDraw(boolean z, long j) {
        }

        public void onSizeChanged(int i, int i2) {
        }

        public void onGraphicsDetached() {
        }

        public void onRenderBufferCreated() {
        }
    }

    void onGraphicsDetached();

    void onPostDraw(boolean z, long j);

    void onPreDraw();

    void onRenderBufferCreated();

    void onSizeChanged(int i, int i2);
}
