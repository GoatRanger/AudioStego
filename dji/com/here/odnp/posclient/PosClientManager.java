package com.here.odnp.posclient;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.HandlerThread;
import com.here.odnp.adaptations.BatteryManager;
import com.here.odnp.adaptations.BatteryManager.IListener;
import com.here.odnp.adaptations.IMeasurementResultHandler;
import com.here.odnp.adaptations.MeasurementProvider;
import com.here.odnp.adaptations.NetworkManager;
import com.here.odnp.adaptations.PowerManager;
import com.here.odnp.ble.IBleManager;
import com.here.odnp.ble.PlatformBleManager;
import com.here.odnp.cell.ICellManager;
import com.here.odnp.cell.PlatformCellManager;
import com.here.odnp.config.OdnpConfigStatic;
import com.here.odnp.gnss.IGnssManager;
import com.here.odnp.gnss.PlatformGnssManager;
import com.here.odnp.net.IConnectivityManager.IConnectivityListener;
import com.here.odnp.net.PlatformConnectivityManager;
import com.here.odnp.posclient.analytics.IUsageTrackingSession;
import com.here.odnp.posclient.analytics.UsageTrackingSession;
import com.here.odnp.posclient.pos.IPositioningSession;
import com.here.odnp.posclient.pos.IPositioningSession.ILocationListener;
import com.here.odnp.posclient.pos.PositioningSession;
import com.here.odnp.posclient.rmm.IRmDownloadSession;
import com.here.odnp.posclient.rmm.RmDownloadSession;
import com.here.odnp.posclient.test.IPosClientTesterSession;
import com.here.odnp.posclient.test.PosClientTesterSession;
import com.here.odnp.power.PlatformAlarmManager;
import com.here.odnp.util.OdnpFileManager;
import com.here.odnp.util.SafeHandler;
import com.here.odnp.util.SyncHandlerTask;
import com.here.odnp.util.TrafficMonitor;
import com.here.odnp.util.WakeLock;
import com.here.odnp.wifi.IWifiManager;
import com.here.odnp.wifi.PlatformWifiManager;
import com.here.odnp.wifi.WifiFilter;
import com.here.posclient.AlarmManager;
import com.here.posclient.BleMeasurement;
import com.here.posclient.CellMeasurement;
import com.here.posclient.CellularStatus;
import com.here.posclient.ClientConfiguration;
import com.here.posclient.GnssStatus;
import com.here.posclient.INetworkManager.Connection;
import com.here.posclient.IPosClientObserver;
import com.here.posclient.InitOptions;
import com.here.posclient.MeasurementType;
import com.here.posclient.PosClientLib;
import com.here.posclient.PosClientLib.ConnectionChangeAction;
import com.here.posclient.PositionEstimate;
import com.here.posclient.PositioningFeature;
import com.here.posclient.RadioMapManager;
import com.here.posclient.RadioMapManager.IRadioMapStorageActionListener;
import com.here.posclient.RadioMapManager.RadioMapQueryAction;
import com.here.posclient.RadioMapManager.RadioMapStorageAction;
import com.here.posclient.Status;
import com.here.posclient.UpdateOptions;
import com.here.posclient.WifiMeasurement;
import com.here.posclient.WifiStatus;
import com.here.posclient.analytics.Tracker;
import com.here.posclient.analytics.UsageTrackingListener;
import com.here.posclient.ext.PositioningControl;
import com.here.posclient.ext.UsageTracking;
import com.here.services.common.Types.Storage;
import com.here.services.radiomap.common.GeoArea;
import com.here.services.util.HereServicesUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class PosClientManager implements IListener, IMeasurementResultHandler, IConnectivityListener, IPosClientManager, AlarmManager.IListener, IPosClientObserver {
    private static final int DEFAULT_CLEAR_DATA_FLAGS = 73;
    private static final String TAG = "odnp.posclient.PosClientManager";
    private String mAppId;
    private final BatteryManager mBatteryManager;
    private ClientConfiguration mClientConfiguration;
    private final Context mContext;
    private String mCustomerId;
    private final String mDataDir;
    private final AtomicBoolean mEnginesStopped = new AtomicBoolean();
    private Long mFeatures;
    private final SafeHandler mHandler;
    private final HandlerThread mHandlerThread = new HandlerThread("PosClientManager.Handler");
    private final Object mLogCapture = null;
    private final MeasurementProvider mMeasurementProvider;
    private final NetworkManager mNetworkManager;
    private boolean mOfflineMode;
    private AtomicBoolean mPosClientInitialized = new AtomicBoolean();
    private final Set<PositioningSession> mPositioners = new HashSet();
    private final PowerManager mPowerManager;
    private String mRadioMapDir;
    private final Set<RmDownloadSession> mRmDownloaders = new HashSet();
    private final Set<PosClientTesterSession> mTesters = new HashSet();
    private final TrafficMonitor mTrafficMonitor = new TrafficMonitor();
    private UpdateOptions mUpdateOptions = new UpdateOptions().setDisabledPowerOptions();
    private final Set<UsageTrackingSession> mUsageTrackers = new HashSet();
    private final WakeLock mWakeLock;
    private final String mWorkingDir;

    private PosClientManager(Context context, Bundle bundle) {
        this.mContext = context;
        this.mWakeLock = new WakeLock(this.mContext);
        this.mWorkingDir = OdnpFileManager.getPrivateDir(context).getAbsolutePath();
        this.mDataDir = OdnpFileManager.getDataDir(context).getAbsolutePath();
        this.mMeasurementProvider = new MeasurementProvider(this);
        this.mNetworkManager = new NetworkManager(this);
        this.mPowerManager = new PowerManager();
        this.mBatteryManager = new BatteryManager(context, this);
        fetchArguments(context, bundle);
        this.mHandlerThread.start();
        this.mHandler = new SafeHandler(this.mHandlerThread.getLooper());
    }

    public static IPosClientManager open(Context context, Bundle bundle) {
        IPosClientManager posClientManager = new PosClientManager(context, bundle);
        if (posClientManager.initialize()) {
            return posClientManager;
        }
        posClientManager.close();
        return null;
    }

    public void close() {
        uninitialize();
    }

    public boolean updateOptions(Bundle bundle) {
        if (bundle.containsKey(InitOptions.KEY_OPTION_OFFLINE_MODE)) {
            setOfflineMode(bundle.getBoolean(InitOptions.KEY_OPTION_OFFLINE_MODE, false));
        }
        return true;
    }

    public Context getContext() {
        return this.mContext;
    }

    public ClientConfiguration getClientConfiguration() {
        return this.mClientConfiguration;
    }

    public void handleGnssStatus(final GnssStatus gnssStatus) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.handleGnssStatusChanged(gnssStatus);
                }
            });
        }
    }

    public void handleCellularScanResult(final CellMeasurement cellMeasurement, final boolean z) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.handleCellularScanResult(cellMeasurement, z);
                }
            });
        }
    }

    public void handleWifiScanResult(long j, WifiMeasurement[] wifiMeasurementArr, boolean z, boolean z2) {
        if (isPosclientInitialized()) {
            final long j2 = j;
            final WifiMeasurement[] wifiMeasurementArr2 = wifiMeasurementArr;
            final boolean z3 = z;
            final boolean z4 = z2;
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.handleWifiScanResult(j2, wifiMeasurementArr2, z3, z4);
                }
            });
        }
    }

    public void handleWifiStateChanged(final WifiStatus wifiStatus) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.handleWifiStatusChanged(wifiStatus.value);
                }
            });
        }
    }

    public void handleGnssLocationUpdate(final PositionEstimate positionEstimate, final boolean z) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.handleGnssLocationUpdate(positionEstimate, z);
                }
            });
        }
    }

    public void handleBleScanResult(long j, BleMeasurement[] bleMeasurementArr, boolean z) {
        if (isPosclientInitialized()) {
            final long j2 = j;
            final BleMeasurement[] bleMeasurementArr2 = bleMeasurementArr;
            final boolean z2 = z;
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.handleBleScanResult(j2, bleMeasurementArr2, z2);
                }
            });
        }
    }

    public void handleRequestError(final MeasurementType measurementType, final Status status) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.handleRequestError(measurementType.value, status.toInt());
                }
            });
        }
    }

    public void onConnectionStateChanged(final ConnectionChangeAction connectionChangeAction, final Connection connection) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.handleConnectionChange(connectionChangeAction, connection);
                }
            });
        }
    }

    public void handleCellularStatusChange(final CellularStatus cellularStatus) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.handleCellularStatusChanged(cellularStatus);
                }
            });
        }
    }

    public synchronized void positionUpdate(PositionEstimate positionEstimate) {
        try {
            if (!this.mPositioners.isEmpty()) {
                if (positionEstimate == null) {
                    this.mWakeLock.release();
                } else if ((positionEstimate.source & this.mUpdateOptions.allowedSources) == 0) {
                    this.mWakeLock.release();
                } else if (HereServicesUtil.isNetworkLocationEnabled(this.mContext) || HereServicesUtil.isGpsLocationEnabled(this.mContext)) {
                    Location toAndroidLocation = PositionEstimate.toAndroidLocation(positionEstimate);
                    if (toAndroidLocation == null) {
                        this.mWakeLock.release();
                    } else {
                        String str = "";
                        str = "";
                        str = "";
                        for (PositioningSession locationChanged : this.mPositioners) {
                            locationChanged.locationChanged(toAndroidLocation);
                        }
                        this.mWakeLock.release();
                    }
                } else {
                    this.mWakeLock.release();
                }
            }
        } finally {
            this.mWakeLock.release();
        }
    }

    public synchronized void positioningError(Status status) {
        try {
            for (PositioningSession locationCalculationFailed : this.mPositioners) {
                locationCalculationFailed.locationCalculationFailed(status);
            }
            this.mWakeLock.release();
        } catch (Throwable th) {
            this.mWakeLock.release();
        }
    }

    public IPositioningSession createPositionerSession(ILocationListener iLocationListener) {
        return new PositioningSession(this, iLocationListener);
    }

    public synchronized void addPositioner(PositioningSession positioningSession) {
        this.mPositioners.add(positioningSession);
        startEngines();
    }

    public synchronized boolean removePositioner(PositioningSession positioningSession) {
        return this.mPositioners.remove(positioningSession);
    }

    public IRmDownloadSession createRmDownloaderSession(IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        return new RmDownloadSession(this, iRadioMapStorageActionListener);
    }

    public synchronized void addRmDownloader(RmDownloadSession rmDownloadSession) {
        this.mRmDownloaders.add(rmDownloadSession);
        startEngines();
    }

    public synchronized boolean removeRmDownloader(RmDownloadSession rmDownloadSession) {
        return this.mRmDownloaders.remove(rmDownloadSession);
    }

    public IUsageTrackingSession createUsageTrackingSession() {
        return new UsageTrackingSession(this) {
            public Status unsubscribe() {
                if (!PosClientManager.this.mPosClientInitialized.get()) {
                    return Status.GeneralError;
                }
                if (PosClientManager.this.mHandler.post(new Runnable() {
                    public void run() {
                        UsageTracking.unsubscribe();
                    }
                })) {
                    return Status.Ok;
                }
                return Status.InternalError;
            }

            public Status subscribe(final UsageTrackingListener usageTrackingListener, final Tracker... trackerArr) {
                if (!PosClientManager.this.mPosClientInitialized.get()) {
                    return Status.GeneralError;
                }
                Object anonymousClass2 = new SyncHandlerTask<Status>() {
                    public Status onRun() {
                        return UsageTracking.subscribe(usageTrackingListener, trackerArr);
                    }

                    protected void onException(Exception exception) {
                        setResult(Status.InternalError);
                    }
                };
                if (PosClientManager.this.mHandler.post(anonymousClass2)) {
                    return (Status) anonymousClass2.getResult();
                }
                return Status.InternalError;
            }

            public EnumSet<Tracker> getSupportedTrackers() {
                if (!PosClientManager.this.mPosClientInitialized.get()) {
                    return EnumSet.noneOf(Tracker.class);
                }
                Object anonymousClass3 = new SyncHandlerTask<EnumSet<Tracker>>() {
                    public EnumSet<Tracker> onRun() {
                        return UsageTracking.getSupportedTrackers();
                    }

                    protected void onException(Exception exception) {
                        setResult(EnumSet.noneOf(Tracker.class));
                    }
                };
                if (PosClientManager.this.mHandler.post(anonymousClass3)) {
                    return (EnumSet) anonymousClass3.getResult();
                }
                return EnumSet.noneOf(Tracker.class);
            }
        };
    }

    public synchronized void addUsageTrackingSession(UsageTrackingSession usageTrackingSession) {
        this.mUsageTrackers.add(usageTrackingSession);
        startEngines();
    }

    public synchronized boolean removeUsageTrackingSession(UsageTrackingSession usageTrackingSession) {
        return this.mUsageTrackers.remove(usageTrackingSession);
    }

    public IPosClientTesterSession createTesterSession() {
        return new PosClientTesterSession(this);
    }

    public synchronized void addTesterSession(PosClientTesterSession posClientTesterSession) {
        this.mTesters.add(posClientTesterSession);
        startEngines();
    }

    public synchronized boolean removeTesterSession(PosClientTesterSession posClientTesterSession) {
        return this.mTesters.remove(posClientTesterSession);
    }

    public void positioningConsentRevoked() {
        clearData(Integer.MAX_VALUE);
        this.mEnginesStopped.set(true);
        shutdown();
    }

    private boolean initialize() {
        Object anonymousClass11 = new SyncHandlerTask<Boolean>() {
            public Boolean onRun() {
                PosClientManager.this.mMeasurementProvider.setWifiFilter(WifiFilter.create()).setWifiManager(new PlatformWifiManager(PosClientManager.this.mContext)).setGnssManager(new PlatformGnssManager(PosClientManager.this.mContext, PosClientManager.this.mHandler)).setCellManager(new PlatformCellManager(PosClientManager.this.mContext)).setBleManager(new PlatformBleManager(PosClientManager.this.mContext));
                PosClientManager.this.mNetworkManager.setConnectivityManager(new PlatformConnectivityManager(PosClientManager.this.mContext)).open();
                PosClientManager.this.mPowerManager.setAlarmManager(new PlatformAlarmManager(PosClientManager.this.mContext, PosClientManager.this)).open();
                InitOptions customerId = new InitOptions().setPosClientObserver(PosClientManager.this).setMeasurementProvider(PosClientManager.this.mMeasurementProvider).setNetworkManager(PosClientManager.this.mNetworkManager).setPowerManager(PosClientManager.this.mPowerManager).setWorkingDir(PosClientManager.this.mWorkingDir).setDataDir(PosClientManager.this.mDataDir).setRadioMapDir(PosClientManager.this.mRadioMapDir).setAppId(PosClientManager.this.mAppId).setCustomerId(PosClientManager.this.mCustomerId);
                if (PosClientManager.this.mFeatures != null) {
                    customerId.setFeatures(PosClientManager.this.mFeatures.longValue());
                }
                if (PosClientManager.this.mOfflineMode) {
                    customerId.setDontUserNetwork();
                }
                PosClientManager.this.mPosClientInitialized.set(true);
                PosClientManager.this.mPosClientInitialized.set(PosClientLib.init(customerId));
                if (PosClientManager.this.mPosClientInitialized.get()) {
                    PosClientManager.this.mBatteryManager.start();
                    PosClientManager.this.mClientConfiguration = PosClientLib.getClientConfiguration();
                    if (PosClientManager.this.mClientConfiguration == null) {
                    }
                } else {
                    PosClientManager.this.mMeasurementProvider.close();
                    PosClientManager.this.mNetworkManager.close();
                    PosClientManager.this.mPowerManager.close();
                }
                return Boolean.valueOf(PosClientManager.this.mPosClientInitialized.get());
            }

            protected void onException(Exception exception) {
                setResult(Boolean.valueOf(false));
            }
        };
        if (this.mHandler.post(anonymousClass11)) {
            this.mPosClientInitialized.set(((Boolean) anonymousClass11.getResult()).booleanValue());
        } else {
            this.mPosClientInitialized.set(false);
        }
        return isPosclientInitialized();
    }

    private void uninitialize() {
        try {
            Object anonymousClass12 = new SyncHandlerTask<Void>() {
                public Void onRun() {
                    PosClientLib.uninit();
                    PosClientManager.this.mNetworkManager.close();
                    PosClientManager.this.mMeasurementProvider.close();
                    PosClientManager.this.mPowerManager.close();
                    PosClientManager.this.mBatteryManager.stop();
                    return null;
                }
            };
            if (this.mHandler.post(anonymousClass12)) {
                anonymousClass12.getResult();
            } else {
                this.mNetworkManager.close();
                this.mMeasurementProvider.close();
                this.mPowerManager.close();
                this.mBatteryManager.stop();
            }
            this.mWakeLock.release();
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientManager.this.mHandlerThread.quit();
                }
            });
            this.mPosClientInitialized.set(false);
        } catch (Throwable th) {
            this.mWakeLock.release();
            this.mHandler.post(/* anonymous class already generated */);
            this.mPosClientInitialized.set(false);
        }
    }

    public void onHandleGlobalLocationSettingChanged(final boolean z) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    if (!z) {
                        PosClientLib.clearData(73);
                    }
                    PositioningControl.handleGlobalLocationSettingChanged(z);
                    PosClientManager.this.mEnginesStopped.set(!z);
                }
            });
        }
    }

    public void onNetworkLocationEnabled() {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    PositioningControl.handleGlobalLocationSettingChanged(true);
                    PosClientManager.this.mEnginesStopped.set(false);
                }
            });
        }
    }

    public void onNetworkLocationDisabled(final boolean z) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    int i = 9;
                    if (!z) {
                        i = 73;
                    }
                    PosClientLib.clearData(i);
                    PositioningControl.handleGlobalLocationSettingChanged(false);
                    PosClientManager.this.mEnginesStopped.set(true);
                }
            });
        }
    }

    void onSetRadioMapPath(final String str) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    PositioningControl.setWorkingRadioMapPath(str);
                }
            });
        }
    }

    public void setOfflineMode(final boolean z) {
        if (isPosclientInitialized()) {
            if (!this.mHandler.post(new Runnable() {
                public void run() {
                    PositioningControl.setNetworkingEnabled(!z);
                }
            })) {
            }
        }
    }

    public boolean isOfflineModeSet() {
        if (!isPosclientInitialized()) {
            return false;
        }
        Object anonymousClass19 = new SyncHandlerTask<Boolean>() {
            public Boolean onRun() {
                return Boolean.valueOf(!PositioningControl.isNetworkingEnabled());
            }

            protected void onException(Exception exception) {
                setResult(Boolean.valueOf(false));
            }
        };
        if (this.mHandler.post(anonymousClass19)) {
            return ((Boolean) anonymousClass19.getResult()).booleanValue();
        }
        return false;
    }

    public Location onGetLastLocation() {
        Location location = null;
        if (isPosclientInitialized()) {
            this.mWakeLock.acquire();
            try {
                Object anonymousClass20 = new SyncHandlerTask<Location>() {
                    public Location onRun() {
                        return PositionEstimate.toAndroidLocation(PosClientLib.getLastPosition());
                    }
                };
                if (this.mHandler.post(anonymousClass20)) {
                    location = (Location) anonymousClass20.getResult();
                    this.mWakeLock.release();
                }
            } finally {
                this.mWakeLock.release();
            }
        }
        return location;
    }

    public void onRequestLastPosition() {
        if (isPosclientInitialized()) {
            if (this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientManager.this.positionUpdate(PosClientLib.getLastPosition());
                }
            })) {
                this.mWakeLock.acquire();
            }
        }
    }

    public Status onStartPositionUpdates() {
        if (!isPosclientInitialized()) {
            return Status.UsageError;
        }
        Object anonymousClass22 = new SyncHandlerTask<Status>() {
            public Status onRun() {
                return Status.fromInt(PosClientLib.startPositionUpdates());
            }
        };
        if (this.mHandler.post(anonymousClass22)) {
            return (Status) anonymousClass22.getResult();
        }
        return Status.GeneralError;
    }

    public void onStopPositionUpdates() {
        if (isPosclientInitialized()) {
            cancelLocationRequest();
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.stopPositionUpdates();
                }
            });
        }
    }

    public UpdateOptions onGetUpdateOptions() {
        return this.mUpdateOptions;
    }

    public void onSetUpdateOptions(UpdateOptions updateOptions) {
        this.mUpdateOptions = updateOptions;
        onUpdateOptions(this.mUpdateOptions);
    }

    public void onResetMeasurements() {
        if (this.mMeasurementProvider != null) {
            this.mMeasurementProvider.reset();
        }
    }

    public boolean onUpdateRadioMapCoverage(RadioMapStorageAction radioMapStorageAction, long j, GeoArea[] geoAreaArr, int i, IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        if (geoAreaArr == null) {
            throw new IllegalArgumentException("areas is null");
        } else if (iRadioMapStorageActionListener == null) {
            throw new IllegalArgumentException("listener is null");
        } else {
            final long j2 = j;
            final GeoArea[] geoAreaArr2 = geoAreaArr;
            final int i2 = i;
            final RadioMapStorageAction radioMapStorageAction2 = radioMapStorageAction;
            final IRadioMapStorageActionListener iRadioMapStorageActionListener2 = iRadioMapStorageActionListener;
            Object anonymousClass24 = new SyncHandlerTask<Boolean>() {
                public Boolean onRun() {
                    return Boolean.valueOf(RadioMapManager.updateRadioMapCoverage(j2, geoAreaArr2, i2, radioMapStorageAction2, iRadioMapStorageActionListener2));
                }

                protected void onException(Exception exception) {
                    setResult(Boolean.valueOf(false));
                }
            };
            if (this.mHandler.post(anonymousClass24)) {
                return ((Boolean) anonymousClass24.getResult()).booleanValue();
            }
            return false;
        }
    }

    public boolean onStartRadioMapQuery(RadioMapQueryAction radioMapQueryAction, long j, GeoArea[] geoAreaArr, int i, IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        if (geoAreaArr == null) {
            throw new IllegalArgumentException("areas is null");
        } else if (iRadioMapStorageActionListener == null) {
            throw new IllegalArgumentException("listener is null");
        } else {
            final long j2 = j;
            final GeoArea[] geoAreaArr2 = geoAreaArr;
            final int i2 = i;
            final RadioMapQueryAction radioMapQueryAction2 = radioMapQueryAction;
            final IRadioMapStorageActionListener iRadioMapStorageActionListener2 = iRadioMapStorageActionListener;
            Object anonymousClass25 = new SyncHandlerTask<Boolean>() {
                public Boolean onRun() {
                    return Boolean.valueOf(RadioMapManager.startRadioMapQuery(j2, geoAreaArr2, i2, radioMapQueryAction2, iRadioMapStorageActionListener2));
                }

                protected void onException(Exception exception) {
                    setResult(Boolean.valueOf(false));
                }
            };
            if (this.mHandler.post(anonymousClass25)) {
                return ((Boolean) anonymousClass25.getResult()).booleanValue();
            }
            return false;
        }
    }

    public void onStopRadioMapUpdate(final IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        if (!this.mHandler.post(new Runnable() {
            public void run() {
                RadioMapManager.stopRadioMapAction(iRadioMapStorageActionListener);
            }
        })) {
        }
    }

    private void onUpdateOptions(final UpdateOptions updateOptions) {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.setUpdateOptions(updateOptions);
                }
            });
        }
    }

    public void onRequestPosition() {
        if (isPosclientInitialized()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    UpdateOptions clone = PosClientManager.this.mUpdateOptions.clone();
                    clone.setSmallestUpdateInterval(0);
                    if (!PosClientLib.requestPosition(clone)) {
                    }
                }
            });
        }
    }

    private void cancelLocationRequest() {
    }

    public void resetPositioningFilter() {
        if (isPosclientInitialized()) {
            Object anonymousClass29 = new SyncHandlerTask<Void>() {
                public Void onRun() {
                    PosClientLib.clearData(265);
                    return null;
                }
            };
            if (this.mHandler.post(anonymousClass29)) {
                anonymousClass29.getResult();
            }
        }
    }

    public boolean getCollectionStatus() {
        return false;
    }

    public void dumpFingerprints() {
    }

    public void sendFingerprints() {
    }

    public boolean getActiveCollection() {
        return false;
    }

    public boolean setActiveCollection(boolean z) {
        return false;
    }

    public boolean getAutoUpload() {
        return false;
    }

    public boolean setAutoUpload(boolean z) {
        return false;
    }

    public long getGnssFingerprintCount() {
        return 0;
    }

    public long getNonGnssFingerprintCount() {
        return 0;
    }

    public void setRadioMapPath(String str) {
        onSetRadioMapPath(str);
    }

    public boolean setWifiManager(final IWifiManager iWifiManager) {
        if (iWifiManager == null) {
            throw new IllegalArgumentException("wifiManager is null");
        } else if (!isPosclientInitialized()) {
            return false;
        } else {
            Object anonymousClass39 = new SyncHandlerTask<Boolean>() {
                public Boolean onRun() {
                    PosClientManager.this.mMeasurementProvider.setWifiManager(iWifiManager, true);
                    return Boolean.valueOf(true);
                }

                protected void onException(Exception exception) {
                    setResult(Boolean.valueOf(false));
                }
            };
            if (this.mHandler.post(anonymousClass39)) {
                return ((Boolean) anonymousClass39.getResult()).booleanValue();
            }
            return false;
        }
    }

    public IWifiManager getWifiManager() {
        return this.mMeasurementProvider.getWifiManager();
    }

    public boolean setCellManager(final ICellManager iCellManager) {
        if (iCellManager == null) {
            throw new IllegalArgumentException("cellManager is null");
        } else if (!isPosclientInitialized()) {
            return false;
        } else {
            Object anonymousClass40 = new SyncHandlerTask<Boolean>() {
                public Boolean onRun() {
                    PosClientManager.this.mMeasurementProvider.setCellManager(iCellManager, true);
                    return Boolean.valueOf(true);
                }

                protected void onException(Exception exception) {
                    setResult(Boolean.valueOf(false));
                }
            };
            if (this.mHandler.post(anonymousClass40)) {
                return ((Boolean) anonymousClass40.getResult()).booleanValue();
            }
            return false;
        }
    }

    public ICellManager getCellManager() {
        return this.mMeasurementProvider.getCellManager();
    }

    public boolean setGnssManager(final IGnssManager iGnssManager) {
        if (iGnssManager == null) {
            throw new IllegalArgumentException("gnssManager is null");
        } else if (!isPosclientInitialized()) {
            return false;
        } else {
            Object anonymousClass41 = new SyncHandlerTask<Boolean>() {
                public Boolean onRun() {
                    PosClientManager.this.mMeasurementProvider.setGnssManager(iGnssManager, true);
                    return Boolean.valueOf(true);
                }

                protected void onException(Exception exception) {
                    setResult(Boolean.valueOf(false));
                }
            };
            if (this.mHandler.post(anonymousClass41)) {
                return ((Boolean) anonymousClass41.getResult()).booleanValue();
            }
            return false;
        }
    }

    public IGnssManager getGnssManager() {
        return this.mMeasurementProvider.getGnssManager();
    }

    public boolean setBleManager(final IBleManager iBleManager) {
        if (iBleManager == null) {
            throw new IllegalArgumentException("bleManager is null");
        } else if (!isPosclientInitialized()) {
            return false;
        } else {
            Object anonymousClass42 = new SyncHandlerTask<Boolean>() {
                public Boolean onRun() {
                    PosClientManager.this.mMeasurementProvider.setBleManager(iBleManager, true);
                    return Boolean.valueOf(true);
                }

                protected void onException(Exception exception) {
                    setResult(Boolean.valueOf(false));
                }
            };
            if (this.mHandler.post(anonymousClass42)) {
                return ((Boolean) anonymousClass42.getResult()).booleanValue();
            }
            return false;
        }
    }

    public IBleManager getBleManager() {
        return this.mMeasurementProvider.getBleManager();
    }

    public void clearData(final int i) {
        if (isPosclientInitialized()) {
            if (!this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.clearData(i);
                }
            })) {
            }
        }
    }

    public void setUsername(String str) {
    }

    public void toggleFeature(final PositioningFeature positioningFeature, final boolean z) {
        if (isPosclientInitialized()) {
            if (!this.mHandler.post(new Runnable() {
                public void run() {
                    PositioningControl.toggleFeature(positioningFeature.value, z);
                }
            })) {
            }
        }
    }

    public int availableFeatures() {
        if (!isPosclientInitialized()) {
            return 0;
        }
        Object anonymousClass46 = new SyncHandlerTask<Long>() {
            public Long onRun() {
                return Long.valueOf(PositioningControl.availableFeatures());
            }

            protected void onException(Exception exception) {
                setResult(Long.valueOf(0));
            }
        };
        if (this.mHandler.post(anonymousClass46)) {
            return ((Long) anonymousClass46.getResult()).intValue();
        }
        return 0;
    }

    public void dumpCachedData() {
        if (isPosclientInitialized()) {
            if (!this.mHandler.post(new Runnable() {
                public void run() {
                    PositioningControl.dumpCachedData();
                }
            })) {
            }
        }
    }

    public void handleGlobalLocationSettingChanged(final boolean z) {
        if (isPosclientInitialized()) {
            if (!this.mHandler.post(new Runnable() {
                public void run() {
                    PositioningControl.handleGlobalLocationSettingChanged(z);
                }
            })) {
            }
        }
    }

    private void shutdown() {
        Runnable anonymousClass49 = new Runnable() {
            public void run() {
                synchronized (PosClientManager.this) {
                    Iterator it = new ArrayList(PosClientManager.this.mTesters).iterator();
                    while (it.hasNext()) {
                        ((PosClientTesterSession) it.next()).close();
                    }
                    it = new ArrayList(PosClientManager.this.mRmDownloaders).iterator();
                    while (it.hasNext()) {
                        ((RmDownloadSession) it.next()).close();
                    }
                    it = new ArrayList(PosClientManager.this.mPositioners).iterator();
                    while (it.hasNext()) {
                        ((PositioningSession) it.next()).close();
                    }
                }
            }
        };
        this.mHandler.removeCallbacks();
        if (!this.mHandler.post(anonymousClass49)) {
        }
    }

    private void startEngines() {
        if (this.mEnginesStopped.get()) {
            onHandleGlobalLocationSettingChanged(true);
        }
    }

    public void onTimerExpired(final long j) {
        if (!this.mHandler.post(new Runnable() {
            public void run() {
                AlarmManager.alarmTimerExpired(j);
            }
        })) {
        }
    }

    public void onBatteryLevelChanged(final int i) {
        if (isPosclientInitialized()) {
            if (!this.mHandler.post(new Runnable() {
                public void run() {
                    PosClientLib.setBatteryLevel(i);
                }
            })) {
            }
        }
    }

    private final boolean isPosclientInitialized() {
        return this.mPosClientInitialized.get();
    }

    private void fetchArguments(Context context, Bundle bundle) {
        if (bundle != null) {
            this.mRadioMapDir = storageTypeToDirectoryName(context, bundle.getString(InitOptions.KEY_OPTION_RADIO_MAP_STORAGE));
            this.mAppId = bundle.getString(InitOptions.KEY_OPTION_APP_ID, "");
            this.mCustomerId = bundle.getString(InitOptions.KEY_OPTION_CUSTOMER_ID);
            this.mOfflineMode = bundle.getBoolean(InitOptions.KEY_OPTION_OFFLINE_MODE, false);
            this.mFeatures = bundle.containsKey(InitOptions.KEY_OPTION_FEATURES) ? Long.valueOf(bundle.getLong(InitOptions.KEY_OPTION_FEATURES)) : null;
        }
    }

    private static String storageTypeToDirectoryName(Context context, String str) {
        Storage storage = Storage.External;
        try {
            storage = Storage.valueOf(str);
        } catch (Exception e) {
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (storage == Storage.External) {
                if (HereServicesUtil.hasExternalWritableStorage()) {
                    stringBuilder.append(context.getExternalFilesDir(null).getAbsolutePath());
                }
            } else if (HereServicesUtil.hasExternalWritableStorage()) {
                stringBuilder.append(context.getExternalFilesDir(null).getAbsolutePath());
            }
        } catch (Exception e2) {
        }
        if (stringBuilder.length() == 0) {
            stringBuilder.append(OdnpFileManager.getPrivateDir(context).getAbsolutePath());
        }
        stringBuilder.append(File.separator).append(OdnpConfigStatic.RADIO_MAP_DOWNLOAD_ROOT);
        return stringBuilder.toString();
    }
}
