package com.here.services.playback;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.playback.MeasurementPlaybackApi.Listener;
import com.here.services.playback.MeasurementPlaybackApi.Listener.Error;
import com.here.services.playback.MeasurementPlaybackApi.Options;
import com.here.services.playback.internal.MeasurementPlaybackClient;
import com.here.services.playback.internal.MeasurementPlaybackClient.IPlaybackStateListener;
import com.here.services.playback.internal.MeasurementPlaybackServicesController;

public class MeasurementPlaybackProvider implements MeasurementPlaybackApi {
    private static final String TAG = "services.playback.MeasurementPlaybackProvider";
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private ListenerProxy mListenerProxy;
    final Provider<MeasurementPlaybackServicesController> mProvider;

    static class ListenerProxy implements IPlaybackStateListener {
        final String mFilename;
        final Listener mPlaybackListener;

        ListenerProxy(Listener listener, String str) {
            this.mPlaybackListener = listener;
            this.mFilename = str;
        }

        public void onPlaybackStarted(String str) {
            if (this.mFilename.equals(str)) {
                this.mPlaybackListener.onPlaybackStarted();
            }
        }

        public void onPlaybackFinished(String str) {
            if (this.mFilename.equals(str)) {
                this.mPlaybackListener.onPlaybackFinished();
            }
        }
    }

    public MeasurementPlaybackProvider(Context context, Provider<MeasurementPlaybackServicesController> provider) {
        this.mProvider = provider;
    }

    public void playback(HereLocationApiClient hereLocationApiClient, Options options, final Listener listener) {
        if (options == null) {
            throw new IllegalArgumentException("options are null");
        } else if (listener == null) {
            throw new IllegalArgumentException("listener is null");
        } else {
            try {
                MeasurementPlaybackClient playbackClient = getPlaybackClient(hereLocationApiClient);
                if (playbackClient == null) {
                    throw new RuntimeException("playbackClient is null");
                }
                if (this.mListenerProxy != null) {
                    stop(hereLocationApiClient);
                }
                this.mListenerProxy = new ListenerProxy(listener, options.mPlaybackFile.getAbsolutePath());
                playbackClient.startNetworkMeasurementPlayback(this.mListenerProxy, options.asPlaybackOptions());
            } catch (Exception e) {
                if (!this.mHandler.post(new Runnable() {
                    public void run() {
                        listener.onPlaybackError(Error.General);
                    }
                })) {
                    listener.onPlaybackError(Error.General);
                }
                this.mListenerProxy = null;
            }
        }
    }

    public void stop(HereLocationApiClient hereLocationApiClient) {
        try {
            MeasurementPlaybackClient playbackClient = getPlaybackClient(hereLocationApiClient);
            if (playbackClient != null) {
                playbackClient.stopNetworkMeasurementPlayback();
                this.mListenerProxy = null;
            }
        } finally {
            this.mListenerProxy = null;
        }
    }

    private MeasurementPlaybackClient getPlaybackClient(HereLocationApiClient hereLocationApiClient) {
        MeasurementPlaybackServicesController measurementPlaybackServicesController = (MeasurementPlaybackServicesController) this.mProvider.getController(hereLocationApiClient);
        if (measurementPlaybackServicesController == null) {
            return null;
        }
        return measurementPlaybackServicesController.getMeasurementPlaybackClient();
    }
}
