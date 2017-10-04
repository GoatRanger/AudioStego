package com.here.android.mpa.common;

import com.nokia.maps.annotation.HybridPlus;

public final class MapSettings {
    @HybridPlus
    public static boolean setIsolatedDiskCacheRootPath(String str, String str2) {
        return com.nokia.maps.MapSettings.a(str, str2);
    }
}
