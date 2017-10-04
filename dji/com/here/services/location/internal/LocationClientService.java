package com.here.services.location.internal;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.RemoteException;
import com.here.odnp.posclient.IPosClientManager;
import com.here.odnp.posclient.pos.IPositioningSession.ILocationListener;
import com.here.odnp.util.SafeHandler;
import com.here.odnp.util.SyncHandlerTask;
import com.here.posclient.Status;
import com.here.posclient.UpdateOptions;
import com.here.services.internal.IBoundService;
import com.here.services.location.internal.IPositioningClient.Stub;

public class LocationClientService extends Stub implements IBoundService {
    private static final String TAG = "services.location.internal.LocationClientService";
    private final SafeHandler mHandler = new SafeHandler(Looper.getMainLooper());
    private final ILocationListener mLocationListener = new ILocationListener() {
        public void onLocationChanged(Location location) {
            try {
                synchronized (LocationClientService.this) {
                    if (LocationClientService.this.mPositionListener == null) {
                        return;
                    }
                    LocationClientService.this.mPositionListener.onPositionUpdate(location);
                }
            } catch (RemoteException e) {
            }
        }

        public void onClosed() {
        }

        public void onLocationResolvingFailed(Status status) {
        }

        public void onOptionsChanged(UpdateOptions updateOptions, UpdateOptions updateOptions2) {
            try {
                synchronized (LocationClientService.this) {
                    if (LocationClientService.this.mPositionListener == null) {
                        return;
                    }
                    LocationClientService.this.mPositionListener.onOptionsChanged(updateOptions, updateOptions2);
                }
            } catch (RemoteException e) {
            }
        }
    };
    private final ISdkPosClientManager mPosClientManager;
    private PositionListener mPositionListener;

    public LocationClientService(IPosClientManager iPosClientManager, Intent intent) {
        if (iPosClientManager == null) {
            throw new IllegalArgumentException("posClientManager is null");
        }
        this.mPosClientManager = ServicesPosClientManager.create(iPosClientManager, this.mLocationListener);
    }

    public boolean startPositionUpdates(Bundle bundle, final PositionListener positionListener) throws RemoteException {
        final UpdateOptions fromBundle = UpdateOptions.fromBundle(bundle);
        Object anonymousClass2 = new SyncHandlerTask<Boolean>() {
            public Boolean onRun() {
                IBinder asBinder = positionListener.asBinder();
                try {
                    asBinder.linkToDeath(new DeathRecipient() {
                        public void binderDied() {
                            synchronized (LocationClientService.this) {
                                LocationClientService.this.mPositionListener = null;
                            }
                        }
                    }, 0);
                    synchronized (LocationClientService.this) {
                        LocationClientService.this.mPositionListener = PositionListener.Stub.asInterface(asBinder);
                    }
                    return Boolean.valueOf(LocationClientService.this.mPosClientManager.startLocationUpdates(fromBundle));
                } catch (RemoteException e) {
                    return Boolean.valueOf(false);
                }
            }

            protected void onException(Exception exception) {
                setResult(Boolean.valueOf(false));
            }
        };
        if (this.mHandler.post(anonymousClass2)) {
            return ((Boolean) anonymousClass2.getResult()).booleanValue();
        }
        return false;
    }

    public void stopPositionUpdates(PositionListener positionListener) throws RemoteException {
        this.mHandler.post(new Runnable() {
            public void run() {
                synchronized (LocationClientService.this) {
                    LocationClientService.this.mPositionListener = null;
                }
                LocationClientService.this.mPosClientManager.stopLocationUpdates();
            }
        });
    }

    public Location getLastPosition() throws RemoteException {
        if (this.mPosClientManager != null) {
            return this.mPosClientManager.getLastPosition();
        }
        return null;
    }

    public void clearPositioningData() throws RemoteException {
        if (this.mPosClientManager != null) {
            this.mPosClientManager.positioningConsentRevoked();
        }
    }

    public void unBind() {
        try {
            stopPositionUpdates(null);
        } catch (RemoteException e) {
        }
    }
}
