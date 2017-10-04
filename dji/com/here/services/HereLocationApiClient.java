package com.here.services;

import android.content.Context;
import android.os.Bundle;
import com.here.posclient.InitOptions;
import com.here.services.Api.Options.None;
import com.here.services.Api.Options.Optional;
import com.here.services.Api.Options.Required;
import com.here.services.Api.ServiceOptions;
import com.here.services.common.Types.Storage;
import com.here.services.internal.CommonServiceController;
import com.here.services.internal.ServiceController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HereLocationApiClient {
    final CommonServiceController mCommonServiceController;

    public interface ConnectionCallbacks {
        void onConnected();

        void onConnectionFailed(Reason reason);

        void onDisconnected();

        void onInitializationFailed(Api<? extends com.here.services.Api.Options> api);
    }

    public static class Builder {
        Map<Api<? extends com.here.services.Api.Options>, com.here.services.Api.Options> mApis = new HashMap();
        final ClientOptions mClientOptions;
        final Context mContext;
        final ConnectionCallbacks mListener;
        Options mOptions = new Options();
        SdkOptions mSdkOptions;

        public Builder(Context context, String str, ConnectionCallbacks connectionCallbacks) {
            if (context == null) {
                throw new IllegalArgumentException("context is null");
            } else if (connectionCallbacks == null) {
                throw new IllegalArgumentException("listener is null");
            } else {
                this.mContext = context;
                this.mClientOptions = new ClientOptions(str);
                this.mListener = connectionCallbacks;
            }
        }

        public Builder setCustomerId(String str) {
            this.mClientOptions.setCustomerId(str);
            return this;
        }

        public Builder setOptions(Options options) {
            if (options == null) {
                throw new IllegalArgumentException("options is null");
            }
            this.mOptions = options;
            return this;
        }

        public Builder setSdkOptions(SdkOptions sdkOptions) {
            if (sdkOptions == null) {
                throw new IllegalArgumentException("options is null");
            }
            this.mSdkOptions = sdkOptions;
            return this;
        }

        public Builder addApi(Api<? extends None> api) {
            if (api == null) {
                throw new IllegalArgumentException("api is null");
            }
            this.mApis.put(api, null);
            return this;
        }

        public Builder addApi(Api<? extends Optional> api, Optional optional) {
            if (api == null) {
                throw new IllegalArgumentException("api is null");
            }
            this.mApis.put(api, optional);
            return this;
        }

        public Builder addApi(Api<? extends Required> api, Required required) {
            if (api == null) {
                throw new IllegalArgumentException("api is null");
            } else if (required == null) {
                throw new IllegalArgumentException("required options is null");
            } else {
                this.mApis.put(api, required);
                return this;
            }
        }

        public HereLocationApiClient build() {
            List arrayList = new ArrayList();
            arrayList.add(this.mClientOptions);
            arrayList.add(this.mOptions);
            if (this.mSdkOptions != null) {
                arrayList.add(this.mSdkOptions);
            }
            return new HereLocationApiClient(this.mContext, arrayList, this.mListener, this.mApis);
        }
    }

    private static class ClientOptions implements ServiceOptions {
        final String mAppId;
        String mCustomerId;

        private ClientOptions(String str) {
            if (str == null) {
                throw new IllegalArgumentException("appId is null");
            }
            this.mAppId = str;
        }

        void setCustomerId(String str) {
            this.mCustomerId = str;
        }

        public void put(Context context, Bundle bundle) {
            if (bundle == null) {
                throw new IllegalArgumentException("bundle is null");
            }
            bundle.putString(InitOptions.KEY_OPTION_APP_ID, this.mAppId);
            bundle.putString(InitOptions.KEY_OPTION_CUSTOMER_ID, this.mCustomerId);
        }
    }

    public static class Options implements ServiceOptions {
        boolean mOfflineMode = false;
        Storage mStorage = Storage.External;

        public Options setOfflineMode(boolean z) {
            this.mOfflineMode = z;
            return this;
        }

        public Options setDownloadStorage(Storage storage) {
            this.mStorage = storage;
            return this;
        }

        public void put(Context context, Bundle bundle) {
            if (bundle == null) {
                throw new IllegalArgumentException("bundle is null");
            }
            bundle.putBoolean(InitOptions.KEY_OPTION_OFFLINE_MODE, this.mOfflineMode);
            bundle.putString(InitOptions.KEY_OPTION_RADIO_MAP_STORAGE, this.mStorage.name());
        }
    }

    public enum Reason {
        ServiceInitializationError,
        ServiceConfigurationError,
        ServiceNotFound,
        PermissionDenied,
        ApiNotLicensed,
        ApiInitializationFailed,
        MissingPermissions
    }

    public static class SdkOptions implements ServiceOptions {
        final long mEnabledFeatures;

        public SdkOptions(long j) {
            this.mEnabledFeatures = j;
        }

        public void put(Context context, Bundle bundle) {
            if (bundle == null) {
                throw new IllegalArgumentException("bundle is null");
            }
            bundle.putLong(InitOptions.KEY_OPTION_FEATURES, this.mEnabledFeatures);
        }
    }

    private HereLocationApiClient(Context context, List<ServiceOptions> list, ConnectionCallbacks connectionCallbacks, Map<Api<? extends com.here.services.Api.Options>, com.here.services.Api.Options> map) {
        this.mCommonServiceController = new CommonServiceController(context, list, connectionCallbacks, map);
    }

    public void connect() {
        this.mCommonServiceController.startServiceAndConnect();
    }

    public void disconnect() {
        this.mCommonServiceController.stopServiceAndDisconnect();
    }

    public boolean isConnected() {
        return this.mCommonServiceController.isConnected();
    }

    public boolean changeOptions(Options options) {
        return this.mCommonServiceController.changeServiceOptions(options);
    }

    public ServiceController getServiceController(Api<? extends com.here.services.Api.Options> api) {
        return this.mCommonServiceController.getServiceController(api);
    }
}
