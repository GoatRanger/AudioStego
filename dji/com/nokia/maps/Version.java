package com.nokia.maps;

import com.nokia.maps.annotation.OnlineNative;

@OnlineNative
public class Version {
    public final native String getNativeVersion();

    public static final String a() {
        return "3.2.1.439";
    }
}
