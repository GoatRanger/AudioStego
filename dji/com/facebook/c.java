package com.facebook;

public enum c {
    NONE(false),
    FACEBOOK_APPLICATION_WEB(true),
    FACEBOOK_APPLICATION_NATIVE(true),
    FACEBOOK_APPLICATION_SERVICE(true),
    WEB_VIEW(false),
    TEST_USER(true),
    CLIENT_TOKEN(true),
    DEVICE_AUTH(true);
    
    private final boolean i;

    private c(boolean z) {
        this.i = z;
    }

    boolean a() {
        return this.i;
    }
}
