package com.here.services.radiomap.internal;

import android.content.Intent;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.RemoteException;
import com.here.odnp.posclient.IPosClientManager;
import com.here.odnp.util.DeviceMonitor;
import com.here.odnp.util.DeviceMonitor.Listener;
import com.here.odnp.util.DeviceMonitor.Listener.MonitorType;
import com.here.odnp.util.SafeHandler;
import com.here.odnp.util.SyncHandlerTask;
import com.here.posclient.RadioMapManager.IRadioMapStorageActionListener;
import com.here.posclient.RadioMapManager.RadioMapQueryAction;
import com.here.posclient.RadioMapManager.RadioMapStorageAction;
import com.here.posclient.Status;
import com.here.services.internal.IBoundService;
import com.here.services.radiomap.common.GeoArea;
import com.here.services.radiomap.internal.IRadioMapManagerClient.Stub;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RadioMapManagerClientService extends Stub implements Listener, IBoundService {
    private static final String TAG = "services.radiomap.internal.RadioMapManagerClientService";
    private final DeviceMonitor mDeviceMonitor;
    private final SafeHandler mHandler = new SafeHandler(Looper.getMainLooper());
    private final Map<IBinder, BoundListener> mListeners = new HashMap();
    private boolean mNetworkLocationEnabled;
    private final IRmmPosClientManager mRmmPosClientManager;

    class BoundListener implements DeathRecipient, IRadioMapStorageActionListener {
        final IBinder mBinder;
        final RadioMapActionListener mListener;
        boolean mUpdateAction;

        BoundListener(IBinder iBinder, RadioMapActionListener radioMapActionListener) {
            if (iBinder == null) {
                throw new IllegalArgumentException("binder is null");
            }
            this.mBinder = iBinder;
            if (radioMapActionListener == null) {
                throw new IllegalArgumentException("listener is null");
            }
            this.mListener = radioMapActionListener;
        }

        boolean link() {
            try {
                this.mBinder.linkToDeath(this, 0);
                return true;
            } catch (RemoteException e) {
                return false;
            }
        }

        void unlink() {
            this.mBinder.unlinkToDeath(this, 0);
        }

        public void onRadioMapStorageActionComplete(int i) {
            try {
                this.mListener.onRadioMapStorageActionComplete(i);
            } catch (RemoteException e) {
                RadioMapManagerClientService.this.onConnectionDied(this.mListener);
            }
        }

        public void onRadioMapActionProgress(int i) {
            try {
                this.mListener.onRadioMapActionProgress(i);
            } catch (RemoteException e) {
                RadioMapManagerClientService.this.onConnectionDied(this.mListener);
            }
        }

        public void onRadioMapQueryActionComplete(int i, long j) {
            try {
                this.mListener.onRadioMapQueryActionComplete(i, j);
            } catch (RemoteException e) {
                RadioMapManagerClientService.this.onConnectionDied(this.mListener);
            }
        }

        public void onClosed() {
            try {
                this.mListener.onSessionClosed();
            } catch (RemoteException e) {
                RadioMapManagerClientService.this.onConnectionDied(this.mListener);
            }
        }

        public void binderDied() {
            RadioMapManagerClientService.this.onConnectionDied(this.mListener);
        }

        BoundListener setIsUpdateAction(boolean z) {
            this.mUpdateAction = z;
            return this;
        }

        void onCancelled() {
            try {
                if (this.mUpdateAction) {
                    this.mListener.onRadioMapStorageActionComplete(Status.CancelError.toInt());
                } else {
                    this.mListener.onRadioMapQueryActionComplete(Status.CancelError.toInt(), 0);
                }
            } catch (RemoteException e) {
            }
        }
    }

    public RadioMapManagerClientService(IPosClientManager iPosClientManager, Intent intent) {
        if (iPosClientManager == null) {
            throw new IllegalArgumentException("posClientManager is null");
        }
        this.mRmmPosClientManager = RmmPosClientManager.create(iPosClientManager);
        this.mDeviceMonitor = null;
    }

    public boolean startRadioMapUpdate(GeoArea[] geoAreaArr, int i, String str, RadioMapActionListener radioMapActionListener) throws RemoteException {
        final String str2 = str;
        final GeoArea[] geoAreaArr2 = geoAreaArr;
        final int i2 = i;
        final RadioMapActionListener radioMapActionListener2 = radioMapActionListener;
        Object anonymousClass1 = new SyncHandlerTask<Boolean>() {
            public Boolean onRun() {
                return Boolean.valueOf(RadioMapManagerClientService.this.mRmmPosClientManager.startRadioMapUpdate(RadioMapStorageAction.valueOf(str2), RadioMapManagerClientService.allowedConnections(), geoAreaArr2, i2, RadioMapManagerClientService.this.bindListener(radioMapActionListener2, true)));
            }

            protected void onException(Exception exception) {
                setResult(Boolean.valueOf(false));
            }
        };
        if (this.mHandler.post(anonymousClass1)) {
            return ((Boolean) anonymousClass1.getResult()).booleanValue();
        }
        return false;
    }

    public boolean startRadioMapQuery(GeoArea[] geoAreaArr, int i, String str, RadioMapActionListener radioMapActionListener) throws RemoteException {
        final String str2 = str;
        final GeoArea[] geoAreaArr2 = geoAreaArr;
        final int i2 = i;
        final RadioMapActionListener radioMapActionListener2 = radioMapActionListener;
        Object anonymousClass2 = new SyncHandlerTask<Boolean>() {
            public Boolean onRun() {
                return Boolean.valueOf(RadioMapManagerClientService.this.mRmmPosClientManager.startRadioMapQuery(RadioMapQueryAction.valueOf(str2), RadioMapManagerClientService.allowedConnections(), geoAreaArr2, i2, RadioMapManagerClientService.this.bindListener(radioMapActionListener2, false)));
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

    public void stopRadioMapAction(final RadioMapActionListener radioMapActionListener) throws RemoteException {
        if (!this.mHandler.post(new Runnable() {
            public void run() {
                IRadioMapStorageActionListener iRadioMapStorageActionListener = (IRadioMapStorageActionListener) RadioMapManagerClientService.this.mListeners.remove(radioMapActionListener.asBinder());
                if (iRadioMapStorageActionListener != null) {
                    RadioMapManagerClientService.this.mRmmPosClientManager.stopRadioMapActions(iRadioMapStorageActionListener);
                }
            }
        })) {
        }
    }

    public void close() throws RemoteException {
        if (!this.mHandler.post(new Runnable() {
            public void run() {
                for (BoundListener boundListener : RadioMapManagerClientService.this.mListeners.values()) {
                    if (boundListener != null) {
                        boundListener.unlink();
                    }
                }
                RadioMapManagerClientService.this.mListeners.clear();
                RadioMapManagerClientService.this.mRmmPosClientManager.close();
            }
        })) {
        }
    }

    public void unBind() {
        try {
            close();
        } catch (RemoteException e) {
        }
    }

    public void onMonitoringStarted(MonitorType monitorType, boolean z) {
        this.mNetworkLocationEnabled = z;
    }

    public void onMonitorStateChanged(MonitorType monitorType, boolean z) {
        if (this.mNetworkLocationEnabled != z) {
            this.mNetworkLocationEnabled = z;
            if (!this.mNetworkLocationEnabled) {
                onNetworkLocationDisabled();
            }
        }
    }

    public void onMonitoringStopped(MonitorType monitorType) {
    }

    private IRadioMapStorageActionListener bindListener(RadioMapActionListener radioMapActionListener, boolean z) {
        IBinder asBinder = radioMapActionListener.asBinder();
        BoundListener boundListener = (BoundListener) this.mListeners.get(asBinder);
        if (boundListener == null) {
            boundListener = new BoundListener(asBinder, radioMapActionListener);
            if (!boundListener.link()) {
                return null;
            }
            this.mListeners.put(asBinder, boundListener);
        }
        return boundListener.setIsUpdateAction(z);
    }

    private void onConnectionDied(final RadioMapActionListener radioMapActionListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                BoundListener boundListener = (BoundListener) RadioMapManagerClientService.this.mListeners.remove(radioMapActionListener.asBinder());
                if (boundListener != null) {
                    boundListener.unlink();
                }
            }
        });
    }

    private static long allowedConnections() {
        return 3;
    }

    private void onNetworkLocationDisabled() {
        this.mHandler.post(new Runnable() {
            public void run() {
                for (Entry value : RadioMapManagerClientService.this.mListeners.entrySet()) {
                    BoundListener boundListener = (BoundListener) value.getValue();
                    RadioMapManagerClientService.this.mRmmPosClientManager.stopRadioMapActions(boundListener);
                    boundListener.onCancelled();
                }
            }
        });
    }
}
