package com.here.services.location.internal;

import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import com.here.posclient.UpdateOptions;
import com.here.services.location.LocationListener;
import com.here.services.location.OptionsChangedEvent;
import com.here.services.location.internal.IPositioning.IPositionListener;

public class ListenerProxy implements IPositionListener {
    final Handler mHandler = new Handler(Looper.getMainLooper());
    final LocationListener mListener;

    public ListenerProxy(LocationListener locationListener) {
        this.mListener = locationListener;
    }

    public void onPositionUpdate(final Location location) {
        this.mHandler.post(new Runnable() {
            public void run() {
                ListenerProxy.this.mListener.onLocationChanged(location);
            }
        });
    }

    public void onSessionClosed() {
    }

    public void onOptionsChanged(UpdateOptions updateOptions, UpdateOptions updateOptions2) {
        final OptionsChangedEvent optionsChangedEvent = new OptionsChangedEvent(updateOptions, updateOptions2);
        if (optionsChangedEvent.hasChanged()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    ListenerProxy.this.mListener.onOptionsChanged(optionsChangedEvent);
                }
            });
        }
    }
}
