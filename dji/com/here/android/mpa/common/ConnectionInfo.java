package com.here.android.mpa.common;

import com.nokia.maps.ConnectionInfoImpl;
import com.nokia.maps.annotation.Online;

@Online
public final class ConnectionInfo {
    private ConnectionInfoImpl a = new ConnectionInfoImpl();

    public long getBytesDownloaded() {
        return this.a.getBytesDownloaded();
    }
}
