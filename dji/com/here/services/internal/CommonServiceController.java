package com.here.services.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.RemoteException;
import com.here.services.Api;
import com.here.services.Api.Options;
import com.here.services.Api.ServiceOptions;
import com.here.services.HereLocationApiClient;
import com.here.services.HereLocationApiClient.ConnectionCallbacks;
import com.here.services.HereLocationApiClient.Reason;
import com.here.services.internal.ILocationServiceController.Stub;
import com.here.services.internal.ServiceController.ConnectionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CommonServiceController {
    private static final String TAG = "services.internal.CommonServiceController";
    private final ConnectionCallbacks mCallbacks;
    private final ServiceConnection mConnection = new ServiceConnection() {
        private final DeathRecipient mDeathRecipient = new DeathRecipient() {
            public void binderDied() {
                AnonymousClass1.this.onServiceDisconnected(null);
            }
        };

        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (CommonServiceController.this) {
                if (CommonServiceController.this.mServiceController == null) {
                    return;
                }
                CommonServiceController.this.mServiceController.asBinder().unlinkToDeath(this.mDeathRecipient, 0);
                CommonServiceController.this.mServiceController = null;
                CommonServiceController.this.onControllerDisconnected();
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (CommonServiceController.this) {
                CommonServiceController.this.mServiceController = Stub.asInterface(iBinder);
                try {
                    CommonServiceController.this.mServiceController.asBinder().linkToDeath(this.mDeathRecipient, 0);
                    CommonServiceController.this.onControllerConnected();
                } catch (RemoteException e) {
                    CommonServiceController.this.mServiceController = null;
                }
            }
        }
    };
    private final Context mContext;
    private final Handler mHandler;
    private ILocationServiceController mServiceController;
    private final Set<ServiceOptions> mServiceOptions = new HashSet();
    private final Map<Api<? extends Options>, ServiceController> mServices = new HashMap();

    public CommonServiceController(Context context, List<ServiceOptions> list, ConnectionCallbacks connectionCallbacks, Map<Api<? extends Options>, Options> map) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        } else if (connectionCallbacks == null) {
            throw new IllegalArgumentException("callbacks is null");
        } else {
            this.mHandler = new Handler(Looper.getMainLooper());
            this.mContext = context;
            this.mCallbacks = connectionCallbacks;
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            for (ServiceOptions serviceOptions : list) {
                if (serviceOptions != null) {
                    this.mServiceOptions.add(serviceOptions);
                }
            }
            for (Entry entry : map.entrySet()) {
                ServiceController createController = ((Api) entry.getKey()).createController(this.mContext, (Options) entry.getValue());
                if (createController != null) {
                    if (entry.getValue() instanceof ServiceOptions) {
                        this.mServiceOptions.add((ServiceOptions) entry.getValue());
                    }
                    this.mServices.put(entry.getKey(), createController);
                }
            }
        }
    }

    public synchronized void startServiceAndConnect() {
        if (!isConnected()) {
            if (this.mServices.isEmpty()) {
                this.mCallbacks.onConnectionFailed(Reason.ServiceConfigurationError);
            } else if (validatePermissions()) {
                try {
                    if (!ServiceUtil.bindLocationService(this.mContext, this.mConnection, getServiceOptions())) {
                        this.mCallbacks.onConnectionFailed(Reason.ServiceInitializationError);
                    }
                } catch (ServiceNotFoundException e) {
                    this.mCallbacks.onConnectionFailed(Reason.ServiceNotFound);
                } catch (SecurityException e2) {
                    this.mCallbacks.onConnectionFailed(Reason.PermissionDenied);
                }
            } else {
                this.mCallbacks.onConnectionFailed(Reason.MissingPermissions);
            }
        }
    }

    public synchronized void stopServiceAndDisconnect() {
        if (isConnected()) {
            try {
                this.mContext.unbindService(this.mConnection);
                onControllerDisconnected();
            } catch (Exception e) {
            }
        }
    }

    public synchronized boolean isConnected() {
        return this.mServiceController != null;
    }

    public synchronized boolean changeServiceOptions(HereLocationApiClient.Options options) {
        boolean z = false;
        synchronized (this) {
            if (options == null) {
                throw new IllegalArgumentException("options is null");
            }
            if (isConnected()) {
                try {
                    Bundle bundle = new Bundle();
                    options.put(this.mContext, bundle);
                    z = this.mServiceController.updateOptions(bundle);
                } catch (RemoteException e) {
                }
            }
        }
        return z;
    }

    public synchronized ServiceController getServiceController(Api<? extends Options> api) {
        return (ServiceController) this.mServices.get(api);
    }

    private Bundle getServiceOptions() {
        Bundle bundle = new Bundle();
        for (ServiceOptions serviceOptions : this.mServiceOptions) {
            if (serviceOptions != null) {
                serviceOptions.put(this.mContext, bundle);
            }
        }
        return bundle;
    }

    private synchronized void onControllerDisconnected() {
        try {
            Iterator it = new ArrayList(this.mServices.values()).iterator();
            while (it.hasNext()) {
                ((ServiceController) it.next()).disconnect();
            }
            this.mServices.clear();
            try {
                this.mCallbacks.onDisconnected();
            } catch (Exception e) {
            }
            this.mServiceController = null;
        } catch (Throwable th) {
            try {
                this.mCallbacks.onDisconnected();
            } catch (Exception e2) {
            }
            this.mServiceController = null;
        }
    }

    private synchronized void onControllerConnected() {
        ConnectionListener anonymousClass2 = new ConnectionListener() {
            final Set<Api<? extends Options>> mConnectedApis = new HashSet();
            boolean mOnConnectedCalled = false;
            final Set<Api<? extends Options>> mPendingApis = new HashSet(CommonServiceController.this.mServices.keySet());

            public void onServiceDisconnect(Api<? extends Options> api) {
                this.mConnectedApis.remove(api);
                if (this.mPendingApis.isEmpty() && this.mConnectedApis.isEmpty()) {
                    CommonServiceController.this.mHandler.post(new Runnable() {
                        public void run() {
                            CommonServiceController.this.stopServiceAndDisconnect();
                        }
                    });
                } else {
                    checkAndReportOnConnected();
                }
            }

            public void onServiceConnectionFailed(Api<? extends Options> api) {
                this.mPendingApis.remove(api);
                CommonServiceController.this.mCallbacks.onInitializationFailed(api);
                if (this.mPendingApis.isEmpty() && this.mConnectedApis.isEmpty()) {
                    CommonServiceController.this.mHandler.post(new Runnable() {
                        public void run() {
                            CommonServiceController.this.stopServiceAndDisconnect();
                        }
                    });
                } else {
                    checkAndReportOnConnected();
                }
            }

            public void onServiceConnected(Api<? extends Options> api) {
                this.mConnectedApis.add(api);
                this.mPendingApis.remove(api);
                checkAndReportOnConnected();
            }

            synchronized void checkAndReportOnConnected() {
                if (!this.mOnConnectedCalled) {
                    if (this.mPendingApis.isEmpty()) {
                        this.mOnConnectedCalled = CommonServiceController.this.mHandler.post(new Runnable() {
                            public void run() {
                                CommonServiceController.this.mCallbacks.onConnected();
                            }
                        });
                    }
                }
            }
        };
        Iterator it = new ArrayList(this.mServices.values()).iterator();
        while (it.hasNext()) {
            ((ServiceController) it.next()).connect(anonymousClass2);
        }
    }

    private boolean validatePermissions() {
        List<String> arrayList = new ArrayList();
        arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
        arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        boolean z = true;
        for (String checkCallingOrSelfPermission : arrayList) {
            boolean z2;
            if (this.mContext.checkCallingOrSelfPermission(checkCallingOrSelfPermission) != 0) {
                z2 = false;
            } else {
                z2 = z;
            }
            z = z2;
        }
        return z;
    }
}
