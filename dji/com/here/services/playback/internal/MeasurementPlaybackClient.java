package com.here.services.playback.internal;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import com.here.odnp.util.OdnpContext;
import com.here.services.internal.IBoundService;
import com.here.services.internal.Manager;
import com.here.services.internal.Manager.ConnectionListener;
import com.here.services.internal.ServiceUtil;
import com.here.services.internal.ServiceUtil.ServiceInfo;
import com.here.services.playback.internal.IMeasurementPlaybackClient.Stub;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeasurementPlaybackClient implements Manager {
    protected static final String ACTION_PLAYBACK_BEGIN = "com.here.odnp.test.playback-begin";
    protected static final String ACTION_PLAYBACK_END = "com.here.odnp.test.playback-end";
    protected static final String EXTRA_INT_TECHNOLOGIES = "technologies";
    protected static final String EXTRA_STR_FILENAME = "filename";
    private static final String TAG = "services.playback.internal.MeasurementPlaybackClient";
    private volatile IMeasurementPlaybackClient mClient;
    private Connection mConnection;
    private final Context mContext;
    private Handler mHandler;
    private final HandlerThread mHandlerThread = new HandlerThread("MeasurementPlaybackClient");
    private final List<Runnable> mPendingTasks = new ArrayList();
    private final Map<String, IPlaybackStateListener> mPlaybackListeners = new HashMap();
    private BroadcastReceiver mPlaybackReceiver;

    public interface IPlaybackStateListener {
        void onPlaybackFinished(String str);

        void onPlaybackStarted(String str);
    }

    class Connection implements ServiceConnection {
        private final DeathRecipient mDeathRecipient = new DeathRecipient() {
            public void binderDied() {
                Connection.this.onDisconnect();
            }
        };
        final ConnectionListener mListener;
        private IMeasurementPlaybackClient mService;

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
                MeasurementPlaybackClient.this.handleServiceConnected(this.mService);
                if (this.mListener != null) {
                    this.mListener.onConnected();
                }
            } catch (RemoteException e) {
                MeasurementPlaybackClient.this.mContext.unbindService(this);
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
                MeasurementPlaybackClient.this.handleServiceDisconnected(this.mService);
                this.mService = null;
                if (this.mListener != null) {
                    this.mListener.onDisconnected();
                }
            }
        }
    }

    private class PlaybackReceiver extends BroadcastReceiver {
        private PlaybackReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            final String stringExtra = intent.getStringExtra("filename");
            final String action = intent.getAction();
            MeasurementPlaybackClient.this.mHandler.post(new Runnable() {
                public void run() {
                    synchronized (MeasurementPlaybackClient.this) {
                        IPlaybackStateListener iPlaybackStateListener = (IPlaybackStateListener) MeasurementPlaybackClient.this.mPlaybackListeners.get(stringExtra);
                    }
                    if (iPlaybackStateListener != null) {
                        if (MeasurementPlaybackClient.ACTION_PLAYBACK_BEGIN.equals(action)) {
                            iPlaybackStateListener.onPlaybackStarted(stringExtra);
                        } else if (MeasurementPlaybackClient.ACTION_PLAYBACK_END.equals(action)) {
                            try {
                                iPlaybackStateListener.onPlaybackFinished(stringExtra);
                            } finally {
                                MeasurementPlaybackClient.this.mPlaybackListeners.remove(stringExtra);
                            }
                        }
                    }
                }
            });
        }
    }

    public MeasurementPlaybackClient(Context context) {
        this.mContext = context;
    }

    public boolean initialize() {
        this.mHandlerThread.start();
        if (this.mHandlerThread.getLooper() == null) {
            return false;
        }
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        return true;
    }

    public boolean startNetworkMeasurementPlayback(final IPlaybackStateListener iPlaybackStateListener, final PlaybackOptions playbackOptions) {
        return postTask(new Runnable() {
            public void run() {
                try {
                    synchronized (MeasurementPlaybackClient.this) {
                        if (MeasurementPlaybackClient.this.isBinderAlive()) {
                            MeasurementPlaybackClient.this.mPlaybackListeners.put(playbackOptions.getPlaybackFile().getAbsolutePath(), iPlaybackStateListener);
                            MeasurementPlaybackClient.this.mClient.startNetworkMeasurementPlayback(playbackOptions.asBundle());
                            return;
                        }
                    }
                } catch (RemoteException e) {
                    MeasurementPlaybackClient.this.mPlaybackListeners.remove(playbackOptions.getPlaybackFile().getAbsolutePath());
                }
            }
        });
    }

    public boolean startNetworkMeasurementPlayback(int i, String str) {
        return startNetworkMeasurementPlayback(new IPlaybackStateListener() {
            public void onPlaybackStarted(String str) {
            }

            public void onPlaybackFinished(String str) {
            }
        }, i, str);
    }

    public synchronized boolean startNetworkMeasurementPlayback(IPlaybackStateListener iPlaybackStateListener, int i, String str) {
        boolean startNetworkMeasurementPlayback;
        PlaybackOptions playbackOptions = new PlaybackOptions();
        try {
            playbackOptions.setPlaybackFile(str).setTechnologies(i);
            startNetworkMeasurementPlayback = startNetworkMeasurementPlayback(iPlaybackStateListener, playbackOptions);
        } catch (FileNotFoundException e) {
            startNetworkMeasurementPlayback = false;
        }
        return startNetworkMeasurementPlayback;
    }

    public synchronized void stopNetworkMeasurementPlayback() {
        if (postTask(new Runnable() {
            public void run() {
                try {
                    synchronized (MeasurementPlaybackClient.this) {
                        if (MeasurementPlaybackClient.this.isBinderAlive()) {
                            MeasurementPlaybackClient.this.mClient.stopNetworkMeasurementPlayback();
                            MeasurementPlaybackClient.this.mPlaybackListeners.clear();
                            return;
                        }
                    }
                } catch (RemoteException e) {
                }
            }
        })) {
        }
    }

    public void connect(ConnectionListener connectionListener) {
        bindService(connectionListener);
        registerBroadcastReceiver();
    }

    public synchronized void disconnect() {
        this.mPendingTasks.clear();
        this.mPlaybackListeners.clear();
        try {
            if (this.mConnection != null) {
                this.mContext.unbindService(this.mConnection);
                this.mConnection = null;
            }
            if (this.mPlaybackReceiver != null) {
                this.mContext.unregisterReceiver(this.mPlaybackReceiver);
                this.mPlaybackReceiver = null;
            }
            if (this.mClient != null) {
                this.mClient.unBind();
                this.mClient = null;
            }
        } catch (RemoteException e) {
            this.mClient = null;
        } catch (Exception e2) {
            this.mHandlerThread.quit();
        } catch (Throwable th) {
            this.mHandlerThread.quit();
        }
        this.mHandlerThread.quit();
    }

    private boolean isBinderAlive() {
        return this.mClient != null && this.mClient.asBinder().isBinderAlive();
    }

    private synchronized void bindService(ConnectionListener connectionListener) {
        if (this.mConnection == null) {
            try {
                ServiceInfo serviceInfo = ServiceUtil.getServiceInfo(this.mContext);
                Intent intent = serviceInfo.getIntent();
                intent.setAction(IBoundService.ACTION_BIND_MEASUREMENT_PLAYBACK_SERVICE);
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

    private synchronized void registerBroadcastReceiver() {
        if (this.mPlaybackReceiver == null) {
            IntentFilter intentFilter = new IntentFilter(ACTION_PLAYBACK_BEGIN);
            intentFilter.addAction(ACTION_PLAYBACK_END);
            this.mPlaybackReceiver = new PlaybackReceiver();
            this.mContext.registerReceiver(this.mPlaybackReceiver, intentFilter);
        }
    }

    private synchronized void handleServiceConnected(IMeasurementPlaybackClient iMeasurementPlaybackClient) {
        this.mClient = iMeasurementPlaybackClient;
        if (this.mClient != null) {
            for (Runnable post : this.mPendingTasks) {
                if (this.mHandler.post(post)) {
                }
            }
            this.mPendingTasks.clear();
        }
    }

    private synchronized void handleServiceDisconnected(IMeasurementPlaybackClient iMeasurementPlaybackClient) {
        if (iMeasurementPlaybackClient != null) {
            if (iMeasurementPlaybackClient.equals(this.mClient)) {
                this.mClient = null;
            }
        }
        disconnect();
    }
}
