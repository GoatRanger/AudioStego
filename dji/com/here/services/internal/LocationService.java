package com.here.services.internal;

import android.content.Intent;
import android.os.IBinder;
import com.here.odnp.util.OdnpAssetManager;
import com.here.odnp.util.OdnpAssetManager.Asset;
import com.here.odnp.util.OdnpAssetManager.AsyncCopyListener;
import com.here.odnp.util.OdnpAssetManager.CopyTask;

public final class LocationService extends ServiceBase implements AsyncCopyListener {
    private static final Asset[] ASSETS = new Asset[]{new Asset("config/config.bin", false, true), new Asset("config/slp.p12", false, true), new Asset("config/rfg_key.pem", false, true)};
    private static final String TAG = "services.internal.LocationService";
    private LocationServiceController mController;
    private CopyTask mCopyTask;

    public void onCreate() {
        super.onCreate();
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
        }
        this.mCopyTask = OdnpAssetManager.asyncCopyAssets(this, ASSETS, this);
    }

    public void onDestroy() {
        synchronized (this) {
            if (this.mController != null) {
                this.mController.unBind();
            }
        }
        super.onDestroy();
    }

    public synchronized IBinder onBind(Intent intent) {
        IBinder onServiceBind;
        if (this.mController != null) {
            onServiceBind = this.mController.onServiceBind(intent);
        } else if (isLocationServiceControllerAction(intent)) {
            this.mController = new LocationServiceController(this).openController(this.mCopyTask, intent.getExtras());
            onServiceBind = this.mController == null ? this.mController : this.mController;
        } else {
            onServiceBind = null;
        }
        return onServiceBind;
    }

    public synchronized boolean onUnbind(Intent intent) {
        boolean z;
        if (this.mController == null) {
            z = false;
        } else if (isLocationServiceControllerAction(intent)) {
            this.mController.unBind();
            z = true;
        } else {
            z = this.mController.onServiceUnbind(intent);
        }
        return z;
    }

    public void onLowMemory() {
    }

    public void asyncCopyFinished(boolean z) {
        if (!z) {
        }
    }

    void onAllBindingsClosed() {
        this.mController = null;
        stopSelf();
    }
}
