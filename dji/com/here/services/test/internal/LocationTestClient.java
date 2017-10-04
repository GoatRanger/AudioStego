package com.here.services.test.internal;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import com.here.odnp.util.OdnpContext;
import com.here.odnp.util.SyncHandlerTask;
import com.here.posclient.ClientConfiguration;
import com.here.posclient.PositioningFeature;
import com.here.services.internal.IBoundService;
import com.here.services.internal.Manager.ConnectionListener;
import com.here.services.internal.ServiceNotFoundException;
import com.here.services.internal.ServiceUtil;
import com.here.services.internal.ServiceUtil.ServiceInfo;
import com.here.services.test.internal.ILocationTestClient.Stub;
import java.util.ArrayList;
import java.util.List;

public class LocationTestClient implements ILocationTest {
    private static final String TAG = "services.test.internal.LocationTestClient";
    private volatile ILocationTestClient mClient;
    private Connection mConnection;
    private final Context mContext;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread = new HandlerThread("LocationTestClient");
    private final List<Runnable> mPendingTasks = new ArrayList();
    private BroadcastReceiver mUserSwitchListener;

    class Connection implements ServiceConnection {
        private final DeathRecipient mDeathRecipient = new DeathRecipient() {
            public void binderDied() {
                Connection.this.onDisconnect();
            }
        };
        final ConnectionListener mListener;
        private ILocationTestClient mService;

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
                LocationTestClient.this.handleServiceConnected(this.mService);
                if (this.mListener != null) {
                    this.mListener.onConnected();
                }
            } catch (RemoteException e) {
                LocationTestClient.this.mContext.unbindService(this);
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
                LocationTestClient.this.handleServiceDisconnected(this.mService);
                this.mService = null;
                if (this.mListener != null) {
                    this.mListener.onDisconnected();
                }
            }
        }
    }

    LocationTestClient(Context context) throws ServiceNotFoundException {
        this.mContext = context;
        this.mHandlerThread.start();
        if (this.mHandlerThread.getLooper() == null) {
            throw new NullPointerException("HandlerThread's looper is null");
        }
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        if (VERSION.SDK_INT >= 17) {
            startUserSwitchListener();
        }
    }

    public synchronized void toggleFeature(final PositioningFeature positioningFeature, final boolean z) {
        if (postTask(new Runnable() {
            public void run() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            LocationTestClient.this.mClient.toggleFeature(positioningFeature.name(), z);
                        }
                    }
                } catch (RemoteException e) {
                }
            }
        })) {
        }
    }

    public synchronized int availableFeatures() {
        int intValue;
        Object anonymousClass2 = new SyncHandlerTask<Integer>() {
            public Integer onRun() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            Integer valueOf = Integer.valueOf(LocationTestClient.this.mClient.availableFeatures());
                            return valueOf;
                        }
                    }
                } catch (RemoteException e) {
                }
                return Integer.valueOf(0);
            }

            protected void onException(Exception exception) {
                setResult(Integer.valueOf(0));
            }
        };
        if (postTask(anonymousClass2)) {
            intValue = ((Integer) anonymousClass2.getResult()).intValue();
        } else {
            intValue = 0;
        }
        return intValue;
    }

    public void toggleTechnology(int i, boolean z) {
    }

    public int enabledTechnologies() {
        return 0;
    }

    public synchronized void dumpData() {
        if (postTask(new Runnable() {
            public void run() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            LocationTestClient.this.mClient.dumpData();
                            return;
                        }
                    }
                } catch (RemoteException e) {
                }
            }
        })) {
        }
    }

    public synchronized void dumpHeapData() {
        if (postTask(new Runnable() {
            public void run() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            LocationTestClient.this.mClient.dumpHeapData();
                            return;
                        }
                    }
                } catch (RemoteException e) {
                }
            }
        })) {
        }
    }

    public synchronized void dumpCachedData() {
        if (postTask(new Runnable() {
            public void run() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            LocationTestClient.this.mClient.dumpCachedData();
                            return;
                        }
                    }
                } catch (RemoteException e) {
                }
            }
        })) {
        }
    }

    public void clearData(final int i) {
        if (!postTask(new Runnable() {
            public void run() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            LocationTestClient.this.mClient.clearData(i);
                            return;
                        }
                    }
                } catch (RemoteException e) {
                }
            }
        })) {
        }
    }

    public void setUsername(final String str) {
        if (!postTask(new Runnable() {
            public void run() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            LocationTestClient.this.mClient.setUsername(str);
                            return;
                        }
                    }
                } catch (RemoteException e) {
                }
            }
        })) {
        }
    }

    public boolean getCollectionStatus() {
        if (!isBinderAlive()) {
            return false;
        }
        Object anonymousClass8 = new SyncHandlerTask<Boolean>() {
            public Boolean onRun() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            Boolean valueOf = Boolean.valueOf(LocationTestClient.this.mClient.getCollectionStatus());
                            return valueOf;
                        }
                    }
                } catch (RemoteException e) {
                }
                return Boolean.valueOf(false);
            }

            protected void onException(Exception exception) {
                setResult(Boolean.valueOf(false));
            }
        };
        if (postTask(anonymousClass8)) {
            return ((Boolean) anonymousClass8.getResult()).booleanValue();
        }
        return false;
    }

    public void dumpFingerprints() {
        if (!postTask(new Runnable() {
            public void run() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            LocationTestClient.this.mClient.dumpFingerprints();
                            return;
                        }
                    }
                } catch (RemoteException e) {
                }
            }
        })) {
        }
    }

    public void sendFingerprints() {
        if (!postTask(new Runnable() {
            public void run() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            LocationTestClient.this.mClient.sendFingerprints();
                            return;
                        }
                    }
                } catch (RemoteException e) {
                }
            }
        })) {
        }
    }

    public boolean getActiveCollection() {
        if (!isBinderAlive()) {
            return false;
        }
        Object anonymousClass11 = new SyncHandlerTask<Boolean>() {
            public Boolean onRun() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            Boolean valueOf = Boolean.valueOf(LocationTestClient.this.mClient.getActiveCollection());
                            return valueOf;
                        }
                    }
                } catch (RemoteException e) {
                }
                return Boolean.valueOf(false);
            }

            protected void onException(Exception exception) {
                setResult(Boolean.valueOf(false));
            }
        };
        if (postTask(anonymousClass11)) {
            return ((Boolean) anonymousClass11.getResult()).booleanValue();
        }
        return false;
    }

    public boolean setActiveCollection(final boolean z) {
        Object anonymousClass12 = new SyncHandlerTask<Boolean>() {
            public Boolean onRun() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            Boolean valueOf = Boolean.valueOf(LocationTestClient.this.mClient.setActiveCollection(z));
                            return valueOf;
                        }
                    }
                } catch (RemoteException e) {
                }
                return Boolean.valueOf(false);
            }

            protected void onException(Exception exception) {
                setResult(Boolean.valueOf(false));
            }
        };
        if (postTask(anonymousClass12)) {
            return ((Boolean) anonymousClass12.getResult()).booleanValue();
        }
        return false;
    }

    public boolean getAutoUpload() {
        if (!isBinderAlive()) {
            return false;
        }
        Object anonymousClass13 = new SyncHandlerTask<Boolean>() {
            public Boolean onRun() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            Boolean valueOf = Boolean.valueOf(LocationTestClient.this.mClient.getAutoUpload());
                            return valueOf;
                        }
                    }
                } catch (RemoteException e) {
                }
                return Boolean.valueOf(false);
            }

            protected void onException(Exception exception) {
                setResult(Boolean.valueOf(false));
            }
        };
        if (postTask(anonymousClass13)) {
            return ((Boolean) anonymousClass13.getResult()).booleanValue();
        }
        return false;
    }

    public boolean setAutoUpload(final boolean z) {
        Object anonymousClass14 = new SyncHandlerTask<Boolean>() {
            public Boolean onRun() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            Boolean valueOf = Boolean.valueOf(LocationTestClient.this.mClient.setAutoUpload(z));
                            return valueOf;
                        }
                    }
                } catch (RemoteException e) {
                }
                return Boolean.valueOf(false);
            }

            protected void onException(Exception exception) {
                setResult(Boolean.valueOf(false));
            }
        };
        if (postTask(anonymousClass14)) {
            return ((Boolean) anonymousClass14.getResult()).booleanValue();
        }
        return false;
    }

    public long getGnssFingerprintCount() {
        if (!isBinderAlive()) {
            return 0;
        }
        Object anonymousClass15 = new SyncHandlerTask<Long>() {
            public Long onRun() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            Long valueOf = Long.valueOf(LocationTestClient.this.mClient.getGnssFingerprintCount());
                            return valueOf;
                        }
                    }
                } catch (RemoteException e) {
                }
                return Long.valueOf(0);
            }

            protected void onException(Exception exception) {
                setResult(Long.valueOf(0));
            }
        };
        if (postTask(anonymousClass15)) {
            return ((Long) anonymousClass15.getResult()).longValue();
        }
        return 0;
    }

    public long getNonGnssFingerprintCount() {
        if (!isBinderAlive()) {
            return 0;
        }
        Object anonymousClass16 = new SyncHandlerTask<Long>() {
            public Long onRun() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            Long valueOf = Long.valueOf(LocationTestClient.this.mClient.getNonGnssFingerprintCount());
                            return valueOf;
                        }
                    }
                } catch (RemoteException e) {
                }
                return Long.valueOf(0);
            }

            protected void onException(Exception exception) {
                setResult(Long.valueOf(0));
            }
        };
        if (postTask(anonymousClass16)) {
            return ((Long) anonymousClass16.getResult()).longValue();
        }
        return 0;
    }

    public ClientConfiguration getClientConfiguration() {
        if (!isBinderAlive()) {
            return null;
        }
        Object anonymousClass17 = new SyncHandlerTask<ClientConfiguration>() {
            public ClientConfiguration onRun() {
                try {
                    synchronized (LocationTestClient.this) {
                        if (LocationTestClient.this.isBinderAlive()) {
                            ClientConfiguration clientConfiguration = LocationTestClient.this.mClient.getClientConfiguration();
                            return clientConfiguration;
                        }
                    }
                } catch (RemoteException e) {
                }
                return null;
            }
        };
        if (postTask(anonymousClass17)) {
            return (ClientConfiguration) anonymousClass17.getResult();
        }
        return null;
    }

    public synchronized void close() {
        if (VERSION.SDK_INT >= 17) {
            stopUserSwitchListener();
        }
        this.mPendingTasks.clear();
        if (this.mConnection != null) {
            this.mContext.unbindService(this.mConnection);
            this.mConnection = null;
        }
        if (this.mClient != null) {
            try {
                this.mClient.unBind();
                this.mClient = null;
            } catch (RemoteException e) {
                this.mClient = null;
            } catch (Throwable th) {
                this.mClient = null;
            }
        }
        this.mHandlerThread.quit();
    }

    public void connect(ConnectionListener connectionListener) {
        bindService(connectionListener);
    }

    public void disconnect() {
        close();
    }

    private synchronized void handleServiceConnected(ILocationTestClient iLocationTestClient) {
        this.mClient = iLocationTestClient;
        if (this.mClient != null) {
            for (Runnable post : this.mPendingTasks) {
                if (this.mHandler.post(post)) {
                }
            }
            this.mPendingTasks.clear();
        }
    }

    private synchronized void handleServiceDisconnected(ILocationTestClient iLocationTestClient) {
        if (iLocationTestClient != null) {
            if (iLocationTestClient.equals(this.mClient)) {
                this.mClient = null;
            }
        }
        disconnect();
    }

    private synchronized void bindService(ConnectionListener connectionListener) {
        if (this.mConnection == null) {
            try {
                ServiceInfo serviceInfo = ServiceUtil.getServiceInfo(this.mContext);
                Intent intent = serviceInfo.getIntent();
                intent.setAction(IBoundService.ACTION_BIND_LOCATION_TEST_SERVICE);
                this.mConnection = new Connection(connectionListener);
                if (!OdnpContext.bindService(this.mContext, intent, this.mConnection, 64, serviceInfo.isMultiUser())) {
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

    private synchronized boolean postTask(Runnable runnable) {
        boolean z = true;
        synchronized (this) {
            if (this.mClient == null) {
                this.mPendingTasks.add(runnable);
            } else if (!this.mHandler.post(runnable)) {
                z = false;
            }
        }
        return z;
    }

    private boolean isBinderAlive() {
        return this.mClient != null && this.mClient.asBinder().isBinderAlive();
    }

    @TargetApi(17)
    private void startUserSwitchListener() {
        if (this.mUserSwitchListener == null) {
            this.mUserSwitchListener = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (LocationTestClient.this.mConnection != null) {
                        LocationTestClient.this.mContext.unbindService(LocationTestClient.this.mConnection);
                        LocationTestClient.this.mConnection = null;
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.USER_BACKGROUND");
            this.mContext.registerReceiver(this.mUserSwitchListener, intentFilter);
        }
    }

    @TargetApi(17)
    private void stopUserSwitchListener() {
        if (this.mUserSwitchListener != null) {
            this.mContext.unregisterReceiver(this.mUserSwitchListener);
            this.mUserSwitchListener = null;
        }
    }
}
