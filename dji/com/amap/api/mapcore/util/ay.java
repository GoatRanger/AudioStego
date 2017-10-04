package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Message;

class ay extends Handler {
    final /* synthetic */ ax a;

    ay(ax axVar) {
        this.a = axVar;
    }

    public void handleMessage(Message message) {
        if (message != null) {
            switch (message.what) {
                case 0:
                    this.a.b.showZoomControlsEnabled(this.a.h);
                    return;
                case 1:
                    this.a.b.showScaleEnabled(this.a.j);
                    return;
                case 2:
                    this.a.b.showCompassEnabled(this.a.i);
                    return;
                case 3:
                    this.a.b.showMyLocationButtonEnabled(this.a.f);
                    return;
                case 4:
                    this.a.b.showIndoorSwitchControlsEnabled(this.a.m);
                    return;
                default:
                    return;
            }
        }
    }
}
