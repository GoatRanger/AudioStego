package com.here.services.radiomap.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.here.posclient.RadioMapManager.RadioMapQueryAction;
import com.here.posclient.RadioMapManager.RadioMapStorageAction;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.radiomap.common.GeoArea;
import com.here.services.radiomap.internal.IRadioMapManager;
import com.here.services.radiomap.internal.IRadioMapManager.IRadioMapActionListener;
import com.here.services.radiomap.internal.RadioMapServicesController;
import com.here.services.radiomap.manager.RadioMapManagerApi.Options;
import com.here.services.radiomap.manager.RadioMapManagerApi.QueryOptions;
import com.here.services.radiomap.manager.RadioMapManagerListener.Status;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RadioMapManagerProvider implements RadioMapManagerApi {
    private static final String TAG = "services.radiomap.manager.RadioMapManagerProvider";
    private final Map<RadioMapManagerListener, ListenerProxy> mListenerProxies = new HashMap();
    private final Provider<RadioMapServicesController> mProvider;

    static class ListenerProxy implements IRadioMapActionListener {
        final Handler mHandler = new Handler(Looper.getMainLooper());
        final RadioMapManagerListener mListener;

        ListenerProxy(RadioMapManagerListener radioMapManagerListener) {
            this.mListener = radioMapManagerListener;
        }

        public void onRadioMapActionProgress(final int i) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    ListenerProxy.this.mListener.onProgress(i);
                }
            });
        }

        public void onRadioMapStorageActionComplete(final int i) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    ListenerProxy.this.mListener.onUpdateCompleted(Status.fromInt(i));
                }
            });
        }

        public void onRadioMapQueryActionComplete(final int i, final long j) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    ListenerProxy.this.mListener.onQueryCompleted(Status.fromInt(i), j);
                }
            });
        }

        public void onSessionClosed() {
        }
    }

    public RadioMapManagerProvider(Context context, Provider<RadioMapServicesController> provider) {
        this.mProvider = provider;
    }

    public void clear(HereLocationApiClient hereLocationApiClient, List<GeoArea> list, Options options, RadioMapManagerListener radioMapManagerListener) {
        startRadioMapAction(hereLocationApiClient, list, options, radioMapManagerListener, RadioMapStorageAction.CLEAR);
    }

    public void update(HereLocationApiClient hereLocationApiClient, List<GeoArea> list, Options options, RadioMapManagerListener radioMapManagerListener) {
        startRadioMapAction(hereLocationApiClient, list, options, radioMapManagerListener, RadioMapStorageAction.UPDATE);
    }

    public void extend(HereLocationApiClient hereLocationApiClient, List<GeoArea> list, Options options, RadioMapManagerListener radioMapManagerListener) {
        startRadioMapAction(hereLocationApiClient, list, options, radioMapManagerListener, RadioMapStorageAction.EXTEND);
    }

    public void query(HereLocationApiClient hereLocationApiClient, List<GeoArea> list, QueryOptions queryOptions, RadioMapManagerListener radioMapManagerListener) {
        IRadioMapManager radioMapManager = getRadioMapManager(hereLocationApiClient);
        if (radioMapManager != null) {
            radioMapManager.startRadioMapQuery(list, optionsAsTechnologies(queryOptions), optionsAsAction(queryOptions), getListenerProxy(radioMapManagerListener));
        }
    }

    public void stop(HereLocationApiClient hereLocationApiClient, RadioMapManagerListener radioMapManagerListener) {
        IRadioMapManager radioMapManager = getRadioMapManager(hereLocationApiClient);
        if (radioMapManager != null) {
            ListenerProxy listenerProxy = (ListenerProxy) this.mListenerProxies.remove(radioMapManagerListener);
            if (listenerProxy != null) {
                radioMapManager.stopRadioMapAction(listenerProxy);
            }
        }
    }

    private IRadioMapManager getRadioMapManager(HereLocationApiClient hereLocationApiClient) {
        RadioMapServicesController radioMapServicesController = (RadioMapServicesController) this.mProvider.getController(hereLocationApiClient);
        if (radioMapServicesController == null) {
            return null;
        }
        return radioMapServicesController.getRadioMapManager();
    }

    private ListenerProxy getListenerProxy(RadioMapManagerListener radioMapManagerListener) {
        if (radioMapManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        ListenerProxy listenerProxy = (ListenerProxy) this.mListenerProxies.get(radioMapManagerListener);
        if (listenerProxy != null) {
            return listenerProxy;
        }
        listenerProxy = new ListenerProxy(radioMapManagerListener);
        this.mListenerProxies.put(radioMapManagerListener, listenerProxy);
        return listenerProxy;
    }

    private void startRadioMapAction(HereLocationApiClient hereLocationApiClient, List<GeoArea> list, Options options, RadioMapManagerListener radioMapManagerListener, RadioMapStorageAction radioMapStorageAction) {
        IRadioMapManager radioMapManager = getRadioMapManager(hereLocationApiClient);
        if (radioMapManager != null) {
            radioMapManager.startRadioMapUpdate(list, optionsAsTechnologies(options), radioMapStorageAction, getListenerProxy(radioMapManagerListener));
        }
    }

    private int optionsAsTechnologies(Options options) {
        int i = 0;
        if (options.mInclude2G) {
            i = 1;
        }
        if (options.mInclude3G) {
            i |= 2;
        }
        if (options.mIncludeLte) {
            i |= 8;
        }
        if (options.mIncludeWifiCoarse) {
            i |= 16;
        }
        if (options.mIncludeWifiDetailed) {
            i |= 32;
        }
        if (options.mIncludeHighAccuracy) {
            return i | 1024;
        }
        return i;
    }

    private RadioMapQueryAction optionsAsAction(QueryOptions queryOptions) {
        RadioMapQueryAction radioMapQueryAction = RadioMapQueryAction.LOCAL_SIZE;
        switch (queryOptions.mTarget) {
            case Extend:
                return RadioMapQueryAction.EXTENDED_SIZE;
            case Update:
                return RadioMapQueryAction.UPDATED_SIZE;
            default:
                return radioMapQueryAction;
        }
    }
}
