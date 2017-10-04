package com.nokia.maps;

import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.streetlevel.StreetLevelGesture;
import com.here.android.mpa.streetlevel.StreetLevelModel;

public interface db {
    void a(OnScreenCaptureListener onScreenCaptureListener);

    StreetLevelModel getPanorama();

    StreetLevelGesture getStreetLevelGesture();

    void onPause();

    void onResume();

    void requestRender();

    void setBlankStreetLevelImageVisible(boolean z);

    void setPanorama(StreetLevelModel streetLevelModel);
}
