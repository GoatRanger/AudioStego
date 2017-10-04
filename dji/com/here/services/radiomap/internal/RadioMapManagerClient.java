package com.here.services.radiomap.internal;

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
import android.os.Looper;
import android.os.RemoteException;
import com.here.odnp.util.OdnpContext;
import com.here.posclient.RadioMapManager.RadioMapQueryAction;
import com.here.posclient.RadioMapManager.RadioMapStorageAction;
import com.here.services.internal.IBoundService;
import com.here.services.internal.Manager.ConnectionListener;
import com.here.services.internal.ServiceUtil;
import com.here.services.internal.ServiceUtil.ServiceInfo;
import com.here.services.radiomap.common.GeoArea;
import com.here.services.radiomap.internal.IRadioMapManager.IRadioMapActionListener;
import com.here.services.radiomap.internal.IRadioMapManagerClient.Stub;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RadioMapManagerClient implements IRadioMapManager {
    private static final String TAG = "services.radiomap.internal.RadioMapManagerClient";
    private IRadioMapManagerClient mClient;
    private Connection mConnection;
    private final Context mContext;
    final Handler mHandler;
    final HandlerThread mHandlerThread = new HandlerThread("IRadioMapManagerClient");
    final Map<IRadioMapActionListener, RadioMapActionRequest> mRequests = new HashMap();

    class Connection implements ServiceConnection {
        private final DeathRecipient mDeathRecipient = new DeathRecipient() {
            public void binderDied() {
                Connection.this.onDisconnect();
            }
        };
        private final ConnectionListener mListener;
        private IRadioMapManagerClient mService;
        private BroadcastReceiver mUserSwitchListener;

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
                if (VERSION.SDK_INT >= 17) {
                    startUserSwitchListener();
                }
                RadioMapManagerClient.this.handleServiceConnected(this.mService);
                if (this.mListener != null) {
                    this.mListener.onConnected();
                }
            } catch (RemoteException e) {
                RadioMapManagerClient.this.mContext.unbindService(this);
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
                if (VERSION.SDK_INT >= 17) {
                    stopUserSwitchListener();
                }
                this.mService.asBinder().unlinkToDeath(this.mDeathRecipient, 0);
                RadioMapManagerClient.this.handleServiceDisconnected(this.mService);
                this.mService = null;
                if (this.mListener != null) {
                    this.mListener.onDisconnected();
                }
            }
        }

        @TargetApi(17)
        private void startUserSwitchListener() {
            if (this.mUserSwitchListener == null) {
                this.mUserSwitchListener = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        Connection.this.onDisconnect();
                    }
                };
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.USER_BACKGROUND");
                RadioMapManagerClient.this.mContext.registerReceiver(this.mUserSwitchListener, intentFilter);
            }
        }

        @TargetApi(17)
        private void stopUserSwitchListener() {
            if (this.mUserSwitchListener != null) {
                RadioMapManagerClient.this.mContext.unregisterReceiver(this.mUserSwitchListener);
                this.mUserSwitchListener = null;
            }
        }
    }

    protected abstract class RadioMapActionRequest {
        protected final GeoArea[] mGeoAreas;
        protected final IRadioMapActionListener mListener;
        final Handler mListenerHandler;
        protected final RadioMapActionListener mRemoteListener = new RadioMapActionListener.Stub() {
            public void onRadioMapActionProgress(final int i) throws RemoteException {
                if (!RadioMapActionRequest.this.mListenerHandler.post(new Runnable() {
                    public void run() {
                        RadioMapActionRequest.this.mListener.onRadioMapActionProgress(i);
                    }
                })) {
                }
            }

            public void onRadioMapStorageActionComplete(final int i) throws RemoteException {
                if (!RadioMapActionRequest.this.mListenerHandler.post(new Runnable() {
                    public void run() {
                        RadioMapActionRequest.this.mListener.onRadioMapStorageActionComplete(i);
                    }
                })) {
                }
            }

            public void onRadioMapQueryActionComplete(final int i, final long j) throws RemoteException {
                if (!RadioMapActionRequest.this.mListenerHandler.post(new Runnable() {
                    public void run() {
                        RadioMapActionRequest.this.mListener.onRadioMapQueryActionComplete(i, j);
                    }
                })) {
                }
            }

            public void onSessionClosed() throws RemoteException {
                if (!RadioMapActionRequest.this.mListenerHandler.post(new Runnable() {
                    public void run() {
                        RadioMapActionRequest.this.mListener.onSessionClosed();
                    }
                })) {
                }
            }
        };
        private boolean mRequested;
        protected final int mTechnologies;

        abstract void onActionComplete(int i);

        abstract boolean onStartUpdates(IRadioMapManagerClient iRadioMapManagerClient);

        RadioMapActionRequest(List<GeoArea> list, int i, IRadioMapActionListener iRadioMapActionListener) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            this.mListenerHandler = new Handler(Looper.myLooper());
            this.mGeoAreas = (GeoArea[]) list.toArray(new GeoArea[0]);
            this.mTechnologies = i;
            this.mListener = iRadioMapActionListener;
        }

        void onServiceDisconnected() {
            this.mRequested = false;
        }

        public boolean startUpdates(IRadioMapManagerClient iRadioMapManagerClient) {
            if (iRadioMapManagerClient == null) {
                return false;
            }
            if (this.mRequested) {
                return true;
            }
            this.mRequested = onStartUpdates(iRadioMapManagerClient);
            if (!this.mRequested) {
                onActionComplete(1);
            }
            return this.mRequested;
        }

        void stopUpdates(IRadioMapManagerClient iRadioMapManagerClient) {
            if (this.mRequested) {
                this.mRequested = false;
                if (iRadioMapManagerClient != null) {
                    try {
                        iRadioMapManagerClient.stopRadioMapAction(this.mRemoteListener);
                    } catch (RemoteException e) {
                    }
                }
            }
        }
    }

    private class RadioMapQueryActionRequest extends RadioMapActionRequest {
        private final String mAction;

        RadioMapQueryActionRequest(List<GeoArea> list, int i, RadioMapQueryAction radioMapQueryAction, IRadioMapActionListener iRadioMapActionListener) {
            super(list, i, iRadioMapActionListener);
            this.mAction = radioMapQueryAction.name();
        }

        boolean onStartUpdates(IRadioMapManagerClient iRadioMapManagerClient) {
            try {
                return iRadioMapManagerClient.startRadioMapQuery(this.mGeoAreas, this.mTechnologies, this.mAction, this.mRemoteListener);
            } catch (RemoteException e) {
                return false;
            }
        }

        void onActionComplete(int i) {
            this.mListener.onRadioMapQueryActionComplete(i, 0);
        }
    }

    private class RadioMapStorageActionRequest extends RadioMapActionRequest {
        private final String mAction;

        RadioMapStorageActionRequest(List<GeoArea> list, int i, RadioMapStorageAction radioMapStorageAction, IRadioMapActionListener iRadioMapActionListener) {
            super(list, i, iRadioMapActionListener);
            this.mAction = radioMapStorageAction.name();
        }

        boolean onStartUpdates(IRadioMapManagerClient iRadioMapManagerClient) {
            try {
                return iRadioMapManagerClient.startRadioMapUpdate(this.mGeoAreas, this.mTechnologies, this.mAction, this.mRemoteListener);
            } catch (RemoteException e) {
                return false;
            }
        }

        void onActionComplete(int i) {
            this.mListener.onRadioMapStorageActionComplete(i);
        }
    }

    private RadioMapManagerClient(Context context) {
        this.mContext = context;
        this.mHandlerThread.start();
        Looper looper = this.mHandlerThread.getLooper();
        if (looper == null) {
            throw new NullPointerException("looper is null");
        }
        this.mHandler = new Handler(looper);
    }

    static IRadioMapManager open(Context context) {
        if (context != null) {
            return new RadioMapManagerClient(context);
        }
        throw new IllegalArgumentException("context is null");
    }

    public boolean startRadioMapUpdate(List<GeoArea> list, int i, RadioMapStorageAction radioMapStorageAction, IRadioMapActionListener iRadioMapActionListener) {
        if (list == null) {
            throw new IllegalArgumentException("area is null");
        } else if (radioMapStorageAction == null) {
            throw new IllegalArgumentException("action is null");
        } else if (iRadioMapActionListener != null) {
            return handleAddRequest(iRadioMapActionListener, new RadioMapStorageActionRequest(list, i, radioMapStorageAction, iRadioMapActionListener));
        } else {
            throw new IllegalArgumentException("listener is null");
        }
    }

    public boolean startRadioMapQuery(List<GeoArea> list, int i, RadioMapQueryAction radioMapQueryAction, IRadioMapActionListener iRadioMapActionListener) {
        if (list == null) {
            throw new IllegalArgumentException("area is null");
        } else if (radioMapQueryAction == null) {
            throw new IllegalArgumentException("action is null");
        } else if (iRadioMapActionListener != null) {
            return handleAddRequest(iRadioMapActionListener, new RadioMapQueryActionRequest(list, i, radioMapQueryAction, iRadioMapActionListener));
        } else {
            throw new IllegalArgumentException("listener is null");
        }
    }

    public void stopRadioMapAction(IRadioMapActionListener iRadioMapActionListener) {
        handleRemoveRequest(iRadioMapActionListener);
    }

    public synchronized void close() {
        this.mRequests.clear();
        if (this.mConnection != null) {
            this.mContext.unbindService(this.mConnection);
            this.mConnection = null;
        }
        this.mClient = null;
        this.mHandlerThread.quit();
    }

    public void connect(ConnectionListener connectionListener) {
        bindService(connectionListener);
    }

    public void disconnect() {
        close();
    }

    private void bindService(ConnectionListener connectionListener) {
        if (this.mConnection == null) {
            try {
                ServiceInfo serviceInfo = ServiceUtil.getServiceInfo(this.mContext);
                Intent intent = serviceInfo.getIntent();
                intent.setAction(IBoundService.ACTION_BIND_RADIOMAP_MANAGER_SERVICE);
                this.mConnection = new Connection(connectionListener);
                if (!OdnpContext.bindService(this.mContext, intent, this.mConnection, 64, serviceInfo.isMultiUser())) {
                    throw new RuntimeException();
                }
                return;
            } catch (Exception e) {
                this.mConnection = null;
                connectionListener.onConnectionFailed();
                return;
            }
        }
        connectionListener.onConnected();
    }

    private synchronized void handleServiceConnected(IRadioMapManagerClient iRadioMapManagerClient) {
        this.mClient = iRadioMapManagerClient;
        if (this.mClient != null) {
            for (Entry value : this.mRequests.entrySet()) {
                ((RadioMapActionRequest) value.getValue()).startUpdates(iRadioMapManagerClient);
            }
        }
    }

    private synchronized void handleServiceDisconnected(IRadioMapManagerClient iRadioMapManagerClient) {
        if (iRadioMapManagerClient != null) {
            if (iRadioMapManagerClient.equals(this.mClient)) {
                this.mClient = null;
                for (Entry value : this.mRequests.entrySet()) {
                    ((RadioMapActionRequest) value.getValue()).onServiceDisconnected();
                }
            }
        }
        disconnect();
    }

    private synchronized boolean handleAddRequest(IRadioMapActionListener iRadioMapActionListener, RadioMapActionRequest radioMapActionRequest) {
        boolean startUpdates;
        handleRemoveRequest(iRadioMapActionListener);
        this.mRequests.put(iRadioMapActionListener, radioMapActionRequest);
        if (this.mClient != null) {
            startUpdates = radioMapActionRequest.startUpdates(this.mClient);
        } else {
            startUpdates = true;
        }
        return startUpdates;
    }

    private synchronized void handleRemoveRequest(IRadioMapActionListener iRadioMapActionListener) {
        RadioMapActionRequest radioMapActionRequest = (RadioMapActionRequest) this.mRequests.remove(iRadioMapActionListener);
        if (radioMapActionRequest != null) {
            radioMapActionRequest.stopUpdates(this.mClient);
        }
    }
}
