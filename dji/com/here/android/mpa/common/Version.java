package com.here.android.mpa.common;

import com.nokia.maps.annotation.Online;

@Online
public final class Version {
    public static final int SDK_API_INT = 2;

    public static final String getSdkVersion() {
        return com.nokia.maps.Version.a();
    }
}
