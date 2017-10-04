package com.here.services.positioning.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import com.here.posclient.Status;
import com.here.posclient.analytics.Tracker;
import com.here.services.internal.IBoundService;
import com.here.services.internal.Manager;
import com.here.services.internal.Manager.ConnectionListener;
import com.here.services.internal.ServiceUtil;
import com.here.services.positioning.analytics.internal.IUsageTrackingClient.Stub;
import java.util.EnumSet;

public class UsageTrackingClient implements Manager {
    private static final String TAG = "services.positioning.internal.UsageTrackingManagerClient";
    private IUsageTrackingClient mClient;
    private Connection mConnection;
    private final Context mContext;

    class Connection implements ServiceConnection {
        private final DeathRecipient mDeathRecipient = new DeathRecipient() {
            public void binderDied() {
                Connection.this.onDisconnect();
            }
        };
        final ConnectionListener mListener;
        private IUsageTrackingClient mService;

        Connection(ConnectionListener connectionListener) {
            this.mListener = connectionListener;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                if (ServiceUtil.isServiceNotAvailableBinder(iBinder)) {
                    throw new RemoteException("service is not available");
                }
                this.mService = Stub.asInterface(iBinder);
                this.mService.asBinder().linkToDeath(this.mDeathRecipient, 0);
                UsageTrackingClient.this.handleServiceConnected(this.mService);
                if (this.mListener != null) {
                    this.mListener.onConnected();
                }
            } catch (RemoteException e) {
                UsageTrackingClient.this.mContext.unbindService(this);
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
                UsageTrackingClient.this.handleServiceDisconnected(this.mService);
                this.mService = null;
                if (this.mListener != null) {
                    this.mListener.onDisconnected();
                }
            }
        }
    }

    public static UsageTrackingClient open(Context context) {
        return new UsageTrackingClient(context);
    }

    public EnumSet<Tracker> getSupportedTrackers() {
        try {
            return UsageTrackingUtils.bundleToEnumSet(this.mClient.getSupportedTrackers());
        } catch (RemoteException e) {
            return EnumSet.noneOf(Tracker.class);
        }
    }

    public Status subscribe(UsageTrackingListener usageTrackingListener, Tracker... trackerArr) {
        try {
            return Status.fromInt(this.mClient.subscribe(usageTrackingListener, UsageTrackingUtils.trackersToBundle(trackerArr)));
        } catch (RemoteException e) {
            return Status.GeneralError;
        }
    }

    public void connect(ConnectionListener connectionListener) {
        bindService(connectionListener);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void disconnect() {
        /*
        r3 = this;
        r2 = 0;
        r0 = r3.mConnection;	 Catch:{ Exception -> 0x000f, all -> 0x0013 }
        if (r0 == 0) goto L_0x000c;
    L_0x0005:
        r0 = r3.mContext;	 Catch:{ Exception -> 0x000f, all -> 0x0013 }
        r1 = r3.mConnection;	 Catch:{ Exception -> 0x000f, all -> 0x0013 }
        r0.unbindService(r1);	 Catch:{ Exception -> 0x000f, all -> 0x0013 }
    L_0x000c:
        r3.mConnection = r2;
    L_0x000e:
        return;
    L_0x000f:
        r0 = move-exception;
        r3.mConnection = r2;
        goto L_0x000e;
    L_0x0013:
        r0 = move-exception;
        r3.mConnection = r2;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.here.services.positioning.analytics.internal.UsageTrackingClient.disconnect():void");
    }

    private synchronized void bindService(ConnectionListener connectionListener) {
        if (this.mConnection == null) {
            try {
                Intent intent = ServiceUtil.getServiceInfo(this.mContext).getIntent();
                intent.setAction(IBoundService.ACTION_BIND_USAGE_TRACKING_SERVICE);
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

    private UsageTrackingClient(Context context) {
        this.mContext = context;
    }

    private synchronized void handleServiceConnected(IUsageTrackingClient iUsageTrackingClient) {
        this.mClient = iUsageTrackingClient;
        if (this.mClient == null) {
        }
    }

    private synchronized void handleServiceDisconnected(IUsageTrackingClient iUsageTrackingClient) {
        if (iUsageTrackingClient != null) {
            if (iUsageTrackingClient.equals(this.mClient)) {
                this.mClient = null;
            }
        }
        disconnect();
    }
}
