package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public interface AudioPlayerDelegate {
    boolean playFiles(String[] strArr);

    boolean playText(String str);
}
