package com.here.android.mpa.common;

import android.content.Context;
import com.here.android.mpa.common.OnEngineInitListener.Error;
import com.nokia.maps.MapsEngine;
import com.nokia.maps.MapsEngine.e;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.aq;

public final class MapEngine {
    private static volatile MapEngine b = null;
    private static final Object c = new Object();
    private MapsEngine a;

    @Online
    public interface OnMapDownloadListener {
        void onMapDataDownloadEnd();

        void onMapDataDownloadInProgress();

        void onMapDataDownloadStart();
    }

    private MapEngine() {
    }

    @Online
    public static MapEngine getInstance() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new MapEngine();
                }
            }
        }
        return b;
    }

    @Online
    public void init(Context context, OnEngineInitListener onEngineInitListener) {
        try {
            this.a = MapsEngine.a(context, onEngineInitListener);
        } catch (Throwable e) {
            if (onEngineInitListener != null) {
                onEngineInitListener.onEngineInitializationCompleted(aq.a(Error.OPERATION_NOT_ALLOWED, "Cannot initialize due to invalid credentials. Please check if provided credentials in the AndroidManifest.xml are correct.", e));
            }
        } catch (Throwable e2) {
            if (onEngineInitListener != null) {
                onEngineInitListener.onEngineInitializationCompleted(aq.a(Error.UNKNOWN, "Unknown error occurred.", e2));
            }
        }
    }

    @Online
    public int getResourceReferenceCount() {
        if (this.a != null) {
            return this.a.x();
        }
        return 0;
    }

    @Online
    public void onPause() {
        if (this.a != null) {
            this.a.v();
        }
    }

    @Online
    public void onResume() {
        if (this.a != null) {
            this.a.w();
        }
    }

    @Online
    public void addMapDataDownloadListener(OnMapDownloadListener onMapDownloadListener) {
        if (this.a != null) {
            this.a.a(onMapDownloadListener);
        }
    }

    @Online
    public void removeMapDataDownloadListener(OnMapDownloadListener onMapDownloadListener) {
        if (this.a != null) {
            this.a.b(onMapDownloadListener);
        }
    }

    @HybridPlus
    public static void setOnline(boolean z) {
        MapsEngine.b(z);
    }

    @HybridPlus
    public static boolean isOnlineEnabled() {
        return MapsEngine.C();
    }

    @HybridPlus
    public static boolean isInitialized() {
        return MapsEngine.b() == e.EInitalized;
    }
}
