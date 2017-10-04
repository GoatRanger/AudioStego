package com.here.services.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.here.odnp.config.OdnpConfigStatic;
import com.here.odnp.util.OdnpContext;
import com.here.services.internal.IServiceNotAvailable.Stub;

public class ServiceUtil {
    private static final String EXTRA_SERVICE_IS_MULTIUSER = "serviceIsMultiuser";
    private static final int OEM_BIND_FLAGS = 0;
    private static final int SDK_BIND_FLAGS = 1;
    private static final String TAG = "services.internal.ServiceUtil";
    private static final Stub mServiceNotAvailableStub = new Stub() {
    };

    public static class ServiceInfo {
        private final Context mContext;
        private final int mFlags;
        private final Intent mIntent;
        private final boolean mMultiUser;

        ServiceInfo(Context context, Intent intent, int i, boolean z) {
            this.mContext = context;
            this.mIntent = intent;
            this.mFlags = i;
            this.mMultiUser = z;
        }

        boolean bind(ServiceConnection serviceConnection) {
            return OdnpContext.bindService(this.mContext, this.mIntent, serviceConnection, this.mFlags, this.mMultiUser);
        }

        void putExtras(Bundle bundle) {
            if (bundle != null && this.mIntent != null) {
                this.mIntent.putExtras(bundle);
            }
        }

        public Intent getIntent() {
            return this.mIntent;
        }

        public boolean isMultiUser() {
            return this.mMultiUser;
        }
    }

    public static boolean bindLocationService(Context context, ServiceConnection serviceConnection, Bundle bundle) throws ServiceNotFoundException {
        if (serviceConnection == null) {
            throw new IllegalArgumentException("connection is null");
        }
        ServiceInfo serviceInfo = getServiceInfo(context);
        if (serviceInfo == null) {
            throw new ServiceNotFoundException("service not found");
        }
        serviceInfo.putExtras(bundle);
        return serviceInfo.bind(serviceConnection);
    }

    public static boolean isServiceNotAvailableBinder(IBinder iBinder) {
        if (iBinder == null) {
            return false;
        }
        try {
            if (mServiceNotAvailableStub.getInterfaceDescriptor().equals(iBinder.getInterfaceDescriptor())) {
                return true;
            }
            return false;
        } catch (RemoteException e) {
            return false;
        }
    }

    public static ServiceInfo getServiceInfo(Context context) throws ServiceNotFoundException {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        ServiceInfo sdkServiceInfo = getSdkServiceInfo(context);
        if (sdkServiceInfo == null) {
            sdkServiceInfo = getOemServiceInfo(context);
        }
        if (sdkServiceInfo != null) {
            return sdkServiceInfo;
        }
        throw new ServiceNotFoundException("service not found");
    }

    private static ServiceInfo getSdkServiceInfo(Context context) {
        try {
            if (context.getPackageManager().getServiceInfo(new ComponentName(context, OdnpConfigStatic.SDK_SERVICE_CLASS_NAME), 0) == null) {
                throw new RuntimeException("getSdkServiceIntent: getServiceInfo returned null");
            }
            Intent intent = new Intent(context, Class.forName(OdnpConfigStatic.SDK_SERVICE_CLASS_NAME));
            intent.setAction(IBoundService.ACTION_BIND_CONTROLLER);
            return new ServiceInfo(context, intent, 1, false);
        } catch (Exception e) {
            return null;
        }
    }

    private static ServiceInfo getOemServiceInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(OdnpConfigStatic.OEM_PACKAGE_NAME, OdnpConfigStatic.OEM_SERVICE_CLASS_NAME);
        try {
            android.content.pm.ServiceInfo serviceInfo = packageManager.getServiceInfo(componentName, 128);
            if (serviceInfo == null) {
                throw new RuntimeException("getOemServiceInfo: getServiceInfo returned null");
            }
            boolean isServiceMultiUser = isServiceMultiUser(serviceInfo);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.setAction(IBoundService.ACTION_BIND_CONTROLLER);
            return new ServiceInfo(context, intent, 0, isServiceMultiUser);
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean isServiceMultiUser(android.content.pm.ServiceInfo serviceInfo) {
        if (serviceInfo != null) {
            return serviceInfo.metaData.getBoolean(EXTRA_SERVICE_IS_MULTIUSER);
        }
        return false;
    }
}
