package com.here.services.location.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import android.os.SystemClock;
import com.here.odnp.posclient.pos.IPositioningSession;
import com.here.odnp.util.SafeHandler;
import com.here.posclient.UpdateOptions;
import com.here.services.internal.IBoundService;
import com.here.services.internal.Manager.ConnectionListener;
import com.here.services.internal.ServiceUtil;
import com.here.services.location.internal.IPositioning.IPositionListener;
import com.here.services.location.internal.PositionListener.Stub;
import java.util.HashMap;
import java.util.Map;

public class PositioningClient implements IPositioning {
    private static final String TAG = "services.location.internal.PositioningClient";
    private IPositioningClient mClient;
    private Options mCombinedOptions;
    private Connection mConnection;
    private final Context mContext;
    private final SafeHandler mHandler;
    private final HandlerThread mHandlerThread = new HandlerThread("PosCln@" + hashCode());
    private final Stub mListener = new Stub() {
        public void onPositionUpdate(Location location) throws RemoteException {
            PositioningClient.this.handlePositionUpdate(location);
        }

        public void onOptionsChanged(UpdateOptions updateOptions, UpdateOptions updateOptions2) throws RemoteException {
            PositioningClient.this.handleOptionsChanged(updateOptions, updateOptions2);
        }
    };
    private final Map<IPositionListener, PositionRequest> mPositionRequests = new HashMap();

    class Connection implements ServiceConnection {
        private final DeathRecipient mDeathRecipient = new DeathRecipient() {
            public void binderDied() {
                Connection.this.onDisconnect();
            }
        };
        final ConnectionListener mListener;
        private IPositioningClient mService;

        Connection(ConnectionListener connectionListener) {
            this.mListener = connectionListener;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                if (ServiceUtil.isServiceNotAvailableBinder(iBinder)) {
                    throw new RemoteException("service is not available");
                }
                this.mService = IPositioningClient.Stub.asInterface(iBinder);
                this.mService.asBinder().linkToDeath(this.mDeathRecipient, 0);
                PositioningClient.this.handleServiceConnected(this.mService);
                if (this.mListener != null) {
                    this.mListener.onConnected();
                }
            } catch (RemoteException e) {
                PositioningClient.this.mContext.unbindService(this);
                if (this.mListener != null) {
                    this.mListener.onConnectionFailed();
                }
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            onDisconnect();
        }

        void onDisconnect() {
            if (this.mService != null) {
                this.mService.asBinder().unlinkToDeath(this.mDeathRecipient, 0);
                PositioningClient.this.handleServiceDisconnected(this.mService);
                this.mService = null;
                if (this.mListener != null) {
                    this.mListener.onDisconnected();
                }
            }
        }
    }

    static class PositionRequest {
        private final IPositionListener mListener;
        private long mNextUpdateTime;
        private Options mOptions;

        PositionRequest(Options options, IPositionListener iPositionListener) {
            this.mListener = iPositionListener;
            update(options);
        }

        void update(Options options) {
            this.mOptions = options;
            this.mNextUpdateTime = SystemClock.elapsedRealtime();
        }

        void offerPositionUpdate(Location location) {
            if (this.mNextUpdateTime <= SystemClock.elapsedRealtime()) {
                this.mNextUpdateTime = SystemClock.elapsedRealtime() + this.mOptions.getSmallestUpdateInterval();
                this.mListener.onPositionUpdate(location);
            }
        }

        void optionsChanged(UpdateOptions updateOptions, UpdateOptions updateOptions2) {
            this.mListener.onOptionsChanged(updateOptions, updateOptions2);
        }

        void sessionClosed() {
            this.mListener.onSessionClosed();
        }

        Options getOptions() {
            return this.mOptions;
        }
    }

    public synchronized boolean startPositionUpdates(Options options, IPositionListener iPositionListener) {
        boolean isEmpty;
        isEmpty = this.mPositionRequests.isEmpty();
        Options addPositionRequest = addPositionRequest(options, iPositionListener);
        if (isEmpty || !addPositionRequest.isEqual(this.mCombinedOptions)) {
            this.mCombinedOptions = addPositionRequest;
            if (this.mClient != null) {
                try {
                    this.mClient.startPositionUpdates(this.mCombinedOptions.getUpdateOptionsAsBundle(), this.mListener);
                } catch (RemoteException e) {
                    isEmpty = false;
                }
            }
        }
        isEmpty = true;
        return isEmpty;
    }

    public synchronized void stopPositionUpdates(IPositionListener iPositionListener) {
        Options removePositionRequest = removePositionRequest(iPositionListener);
        if (this.mClient != null) {
            if (this.mPositionRequests.isEmpty()) {
                try {
                    this.mClient.stopPositionUpdates(this.mListener);
                } catch (RemoteException e) {
                }
            } else if (!removePositionRequest.isEqual(this.mCombinedOptions)) {
                try {
                    this.mClient.startPositionUpdates(this.mCombinedOptions.getUpdateOptionsAsBundle(), this.mListener);
                } catch (RemoteException e2) {
                }
            }
        }
        this.mCombinedOptions = removePositionRequest;
    }

    public synchronized Location getLastPosition() {
        Location location = null;
        synchronized (this) {
            if (this.mClient != null) {
                try {
                    location = this.mClient.getLastPosition();
                } catch (RemoteException e) {
                }
            }
        }
        return location;
    }

    public synchronized void clearPositioningData() {
        if (this.mClient != null) {
            try {
                this.mClient.clearPositioningData();
            } catch (RemoteException e) {
            }
        }
    }

    public synchronized void close() {
        if (this.mConnection != null) {
            this.mContext.unbindService(this.mConnection);
            this.mConnection = null;
        }
        this.mHandlerThread.quit();
    }

    public void connect(ConnectionListener connectionListener) {
        bindService(connectionListener);
    }

    public void disconnect() {
        close();
    }

    static IPositioning open(Context context) {
        return new PositioningClient(context);
    }

    private PositioningClient(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.mContext = context;
        this.mHandlerThread.start();
        this.mHandler = new SafeHandler(this.mHandlerThread.getLooper());
    }

    private Options addPositionRequest(Options options, IPositionListener iPositionListener) {
        PositionRequest positionRequest = (PositionRequest) this.mPositionRequests.get(iPositionListener);
        if (positionRequest == null) {
            this.mPositionRequests.put(iPositionListener, new PositionRequest(options, iPositionListener));
        } else {
            positionRequest.update(options);
        }
        return getCombinedRequestOptions();
    }

    private Options removePositionRequest(IPositionListener iPositionListener) {
        this.mPositionRequests.remove(iPositionListener);
        return getCombinedRequestOptions();
    }

    private Options getCombinedRequestOptions() {
        Long l = null;
        Options options = new Options();
        if (this.mPositionRequests.isEmpty()) {
            return options;
        }
        long j = IPositioningSession.NotSet;
        long j2 = IPositioningSession.NotSet;
        Long l2 = null;
        Long l3 = null;
        for (PositionRequest options2 : this.mPositionRequests.values()) {
            Long l4;
            UpdateOptions updateOptions = options2.getOptions().getUpdateOptions();
            if (updateOptions.desiredUpdateInterval < j) {
                j = updateOptions.desiredUpdateInterval;
            }
            if (updateOptions.smallestUpdateInterval < j2) {
                j2 = updateOptions.smallestUpdateInterval;
            }
            if (updateOptions.allowedSourcesSet) {
                if (l3 == null) {
                    l3 = Long.valueOf(updateOptions.allowedSources);
                } else {
                    l3 = Long.valueOf(l3.longValue() | updateOptions.allowedSources);
                }
            }
            if (updateOptions.allowedTechnologiesSet) {
                if (l2 == null) {
                    l2 = Long.valueOf(updateOptions.allowedTechnologies);
                } else {
                    l2 = Long.valueOf(l2.longValue() | updateOptions.allowedTechnologies);
                }
            }
            if (!updateOptions.optionsSet) {
                l4 = l;
            } else if (l == null) {
                l4 = Long.valueOf(updateOptions.options);
            } else {
                l4 = Long.valueOf(updateOptions.options | l.longValue());
            }
            l = l4;
        }
        options.setDesiredUpdateInterval(j);
        options.setSmallestUpdateInterval(j2);
        if (l3 != null) {
            options.setAllowedSources(l3.longValue());
        }
        if (l2 != null) {
            options.setAllowedTechnologies(l2.longValue());
        }
        if (l != null) {
            options.setOptions(l.longValue());
        }
        return options;
    }

    private synchronized void bindService(ConnectionListener connectionListener) {
        if (this.mConnection == null) {
            try {
                Intent intent = ServiceUtil.getServiceInfo(this.mContext).getIntent();
                intent.setAction(IBoundService.ACTION_BIND_LOCATION_SERVICE);
                this.mConnection = new Connection(connectionListener);
                if (!this.mContext.bindService(intent, this.mConnection, 64)) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                this.mConnection = null;
                connectionListener.onConnectionFailed();
            }
        } else {
            connectionListener.onConnected();
        }
        return;
    }

    private synchronized void handleServiceConnected(IPositioningClient iPositioningClient) {
        this.mClient = iPositioningClient;
        if (this.mClient != null) {
            if (!this.mPositionRequests.isEmpty()) {
                try {
                    this.mClient.startPositionUpdates(this.mCombinedOptions.getUpdateOptionsAsBundle(), this.mListener);
                } catch (RemoteException e) {
                }
            }
        }
    }

    private synchronized void handleServiceDisconnected(IPositioningClient iPositioningClient) {
        if (iPositioningClient != null) {
            if (iPositioningClient.equals(this.mClient)) {
                this.mClient = null;
            }
        }
        disconnect();
    }

    private void handlePositionUpdate(final Location location) {
        if (location != null && location.hasAccuracy()) {
            if (!this.mHandler.post(new Runnable() {
                public void run() {
                    synchronized (PositioningClient.this) {
                        for (PositionRequest offerPositionUpdate : PositioningClient.this.mPositionRequests.values()) {
                            offerPositionUpdate.offerPositionUpdate(location);
                        }
                    }
                }
            })) {
            }
        }
    }

    private void handleOptionsChanged(final UpdateOptions updateOptions, final UpdateOptions updateOptions2) {
        if (!this.mHandler.post(new Runnable() {
            public void run() {
                synchronized (PositioningClient.this) {
                    for (PositionRequest optionsChanged : PositioningClient.this.mPositionRequests.values()) {
                        optionsChanged.optionsChanged(updateOptions, updateOptions2);
                    }
                }
            }
        })) {
        }
    }
}
