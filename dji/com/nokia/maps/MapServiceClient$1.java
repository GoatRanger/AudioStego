package com.nokia.maps;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

class MapServiceClient$1 implements ServiceConnection {
    final /* synthetic */ MapServiceClient a;

    MapServiceClient$1(MapServiceClient mapServiceClient) {
        this.a = mapServiceClient;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.a.a(componentName, iBinder);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.a.a(componentName);
    }
}
