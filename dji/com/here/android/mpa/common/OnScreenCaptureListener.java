package com.here.android.mpa.common;

import android.graphics.Bitmap;
import com.nokia.maps.annotation.Online;

@Online
public interface OnScreenCaptureListener {
    void onScreenCaptured(Bitmap bitmap);
}
