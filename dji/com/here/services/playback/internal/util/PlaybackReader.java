package com.here.services.playback.internal.util;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.here.odnp.ble.BleScanResult;
import com.here.odnp.ble.IBleManager.BleScanResultContainer;
import com.here.odnp.ble.IBleManager.IBleListener;
import com.here.odnp.cell.ICellManager.ICellListener;
import com.here.odnp.gnss.IGnssManager.IGnnsListener;
import com.here.odnp.posclient.util.DebugLocationExtras;
import com.here.odnp.util.SafeHandler;
import com.here.odnp.util.TimeManager;
import com.here.odnp.wifi.IWifiManager.IWifiListener;
import com.here.odnp.wifi.IWifiManager.WifiScanResultContainer;
import com.here.posclient.CellMeasurement;
import com.here.posclient.CellMeasurement.RANType;
import com.here.posclient.WifiMeasurement;
import com.here.services.playback.internal.PlaybackOptions;
import com.here.services.playback.internal.util.IPullParser.Measurement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

public class PlaybackReader implements com.here.services.playback.internal.util.IPullParser.IListener {
    private static final String GL3_TYPE_VALUE_LAC = "lac";
    private static final String GL3_TYPE_VALUE_TAC = "tac";
    private static final String KEY_GL1 = "GL1";
    private static final String KEY_GL2 = "GL2";
    private static final String KEY_GL3 = "GL3";
    private static final String KEY_GL3_TYPE = "GL3TYPE";
    private static final String KEY_GL4 = "GL4";
    private static final String KEY_RAN_TYPE = "rantype";
    private static final String PLAYBACK_CELL_LOCATION_AVAILABLE_ACTION = "com.here.odnp.util.tst.PLAYBACK_CELL_LOCATION";
    private static final String TAG = "services.playback.internal.util.PlaybackReader";
    protected final Set<IBleListener> mBleListeners = new HashSet();
    private final Set<ICellListener> mCellListeners = new HashSet();
    private volatile boolean mClosed;
    private final Context mContext;
    private final Set<IGnnsListener> mGnssListeners = new HashSet();
    private SafeHandler mHandler;
    private HandlerThread mHandlerThread;
    private volatile CellMeasurement mLastCellScanResult;
    private volatile WifiScanResultContainer mLastWifiScanResult;
    private final IListener mListener;
    private final IModeProcessor mModeProcessor;
    private final IPullParser mParser;
    private final File mPlaybackFile;
    private final Random mRandom = new Random();
    private boolean mRepeat;
    private final Set<MeasurementPushTask> mScheduledMeasurements = new HashSet();
    private volatile boolean mStarted;
    private final Set<IWifiListener> mWifiListeners = new HashSet();

    public interface IListener {
        void onPlaybackFinished();
    }

    private interface IModeProcessor extends com.here.services.playback.internal.util.IPullParser.IListener {
        void cancelCellScan();

        void cancelWifiScan();

        void closeBle();

        boolean isClosed();

        void openBle();

        void start();

        void startBleUpdates();

        boolean startCellScan();

        boolean startWifiScan();

        void stop();

        void stopBleUpdates();
    }

    abstract class AbstractModeProcessor implements IModeProcessor {
        abstract void onMeasurementPushed(Type type);

        abstract void onStart();

        abstract void onStop();

        AbstractModeProcessor() {
        }

        public void start() {
            PlaybackReader.this.mStarted = true;
            PlaybackReader.this.mClosed = false;
            PlaybackReader.this.mScheduledMeasurements.clear();
            PlaybackReader.this.mHandler.removeCallbacks();
            PlaybackReader.this.mHandler.post(new Runnable() {
                public void run() {
                    AbstractModeProcessor.this.onStart();
                }
            });
        }

        public void stop() {
            PlaybackReader.this.mStarted = false;
            PlaybackReader.this.mClosed = true;
            onStop();
            PlaybackReader.this.mHandler.removeCallbacks();
            PlaybackReader.this.mScheduledMeasurements.clear();
        }

        public void pushBle(BleScanResultContainer bleScanResultContainer) {
            pushBleMeasurement(bleScanResultContainer, 0);
            onMeasurementPushed(Type.Ble);
        }

        public void pushCell(CellMeasurement cellMeasurement) {
            pushCellMeasurement(cellMeasurement, 0);
            onMeasurementPushed(Type.Cell);
        }

        public void pushGnss(Location location) {
            Iterator it = new ArrayList(PlaybackReader.this.mGnssListeners).iterator();
            while (it.hasNext()) {
                ((IGnnsListener) it.next()).onGnssLocationChanged(location, true);
            }
            onMeasurementPushed(Type.Gnss);
        }

        public void pushWifi(WifiScanResultContainer wifiScanResultContainer) {
            pushWifiScanResults(wifiScanResultContainer, 0);
            onMeasurementPushed(Type.Wifi);
        }

        void pushBleMeasurement(BleScanResultContainer bleScanResultContainer, long j) {
            if (bleScanResultContainer != null) {
                try {
                    for (BleScanResult bleScanResult : bleScanResultContainer.scanResultList) {
                        if (bleScanResult != null) {
                            bleScanResult.timestamp = TimeManager.currentTimeMillis() / 1000;
                            bleScanResult.elapsedRealtimeTimestamp = TimeManager.timeSinceBoot() + (PlaybackReader.this.mRandom.nextLong() % 550);
                        }
                    }
                    for (IBleListener onScanResultsAvailable : PlaybackReader.this.mBleListeners) {
                        onScanResultsAvailable.onScanResultsAvailable(bleScanResultContainer);
                    }
                } finally {
                    DebugLocationExtras.sendBleScanResultDebugInfo(PlaybackReader.this.mContext, bleScanResultContainer.scanResultList, true);
                }
            }
        }

        void pushCellMeasurement(CellMeasurement cellMeasurement, long j) {
            if (cellMeasurement != null) {
                try {
                    cellMeasurement.timeStamp = PlaybackReader.this.getRandomTimeStamp(j) / 1000;
                    for (ICellListener onCellMeasurementChanged : PlaybackReader.this.mCellListeners) {
                        onCellMeasurementChanged.onCellMeasurementChanged(cellMeasurement);
                    }
                } finally {
                    PlaybackReader.this.mLastCellScanResult = cellMeasurement;
                    PlaybackReader.this.mContext.sendBroadcast(PlaybackReader.this.createCellLocationIntent(cellMeasurement));
                }
            }
        }

        void pushWifiScanResults(WifiScanResultContainer wifiScanResultContainer, long j) {
            if (wifiScanResultContainer != null) {
                try {
                    if (VERSION.SDK_INT >= 17) {
                        for (WifiMeasurement wifiMeasurement : wifiScanResultContainer.scanResultList) {
                            if (wifiMeasurement.tsf == Long.MIN_VALUE) {
                                wifiMeasurement.tsf = PlaybackReader.this.getRandomTimeStamp(j);
                            }
                        }
                    }
                    for (IWifiListener onScanResultsAvailable : PlaybackReader.this.mWifiListeners) {
                        onScanResultsAvailable.onScanResultsAvailable(wifiScanResultContainer);
                    }
                } finally {
                    PlaybackReader.this.mLastWifiScanResult = wifiScanResultContainer;
                    DebugLocationExtras.sendWifiScanResultDebugInfo(PlaybackReader.this.mContext, wifiScanResultContainer.scanResultList);
                }
            }
        }

        void pushEmptyWifiScanResults() {
            WifiScanResultContainer wifiScanResultContainer = new WifiScanResultContainer(0, true, new ArrayList());
            try {
                for (IWifiListener onScanResultsAvailable : PlaybackReader.this.mWifiListeners) {
                    onScanResultsAvailable.onScanResultsAvailable(wifiScanResultContainer);
                }
            } finally {
                DebugLocationExtras.sendWifiScanResultDebugInfo(PlaybackReader.this.mContext, wifiScanResultContainer.scanResultList);
            }
        }

        boolean pushPreviousOrEmptyWifiScanResults() {
            if (PlaybackReader.this.mLastWifiScanResult != null) {
                pushWifiScanResults(PlaybackReader.this.mLastWifiScanResult, 0);
                return true;
            } else if (isClosed()) {
                return false;
            } else {
                pushEmptyWifiScanResults();
                return true;
            }
        }

        boolean pushPreviousCellMeasurement() {
            if (PlaybackReader.this.mLastCellScanResult == null) {
                PlaybackReader.this.pushCellScanError();
                return false;
            }
            pushCellMeasurement(PlaybackReader.this.mLastCellScanResult, 0);
            return true;
        }

        public boolean isClosed() {
            return PlaybackReader.this.mClosed;
        }

        protected boolean isStarted() {
            return PlaybackReader.this.mStarted;
        }

        protected boolean isClosedOrNotStarted() {
            return isClosed() || !isStarted();
        }
    }

    private class BleManager {
        private final IPullParser mParser;
        private final List<Measurement> mPendingBleMeasurements = new ArrayList();
        private final long mPeriod;

        BleManager(IPullParser iPullParser, long j) {
            this.mParser = iPullParser;
            this.mPeriod = j;
        }

        void start() {
            cancel();
            schedule();
        }

        void stop() {
            cancel();
        }

        void cancel() {
            PlaybackReader.this.mHandler.removeCallbacksAndMessages(this);
        }

        void addBleMeasurement(Measurement measurement) {
            this.mPendingBleMeasurements.add(measurement);
        }

        void schedule() {
            PlaybackReader.this.mHandler.postAtTime(new Runnable() {
                public void run() {
                    Measurement createEmptyBleMeasurement;
                    if (BleManager.this.mPendingBleMeasurements.isEmpty()) {
                        createEmptyBleMeasurement = BleManager.this.mParser.createEmptyBleMeasurement(0);
                    } else {
                        createEmptyBleMeasurement = (Measurement) BleManager.this.mPendingBleMeasurements.remove(0);
                    }
                    if (createEmptyBleMeasurement != null) {
                        PlaybackReader.this.mHandler.postAtTime(new MeasurementPushTask(createEmptyBleMeasurement), BleManager.this, SystemClock.uptimeMillis());
                        BleManager.this.schedule();
                    }
                }
            }, this, SystemClock.uptimeMillis() + this.mPeriod);
        }
    }

    private class EmptyBleGenerator {
        private final IPullParser mParser;
        private final long mPeriod;

        EmptyBleGenerator(IPullParser iPullParser, long j) {
            this.mParser = iPullParser;
            this.mPeriod = j;
        }

        void start() {
            cancel();
            schedule();
        }

        void stop() {
            cancel();
        }

        void reschedule() {
            cancel();
            schedule();
        }

        void schedule() {
            final Measurement createEmptyBleMeasurement = this.mParser.createEmptyBleMeasurement(this.mPeriod);
            if (createEmptyBleMeasurement != null) {
                PlaybackReader.this.mHandler.postAtTime(new Runnable() {
                    public void run() {
                        PlaybackReader.this.mHandler.postAtTime(new MeasurementPushTask(createEmptyBleMeasurement), EmptyBleGenerator.this, SystemClock.uptimeMillis());
                    }
                }, this, SystemClock.uptimeMillis() + TimeCalculator.timeSinceBootDiff(createEmptyBleMeasurement.getDueAt()));
            }
        }

        void cancel() {
            PlaybackReader.this.mHandler.removeCallbacksAndMessages(this);
        }
    }

    class ImmediateModeProcessor extends AbstractModeProcessor {
        BleManager mBleManager;
        private final List<Measurement> mPendingMeasurements = new ArrayList();

        ImmediateModeProcessor() {
            super();
        }

        public boolean startWifiScan() {
            if (isClosedOrNotStarted()) {
                return false;
            }
            if (this.mPendingMeasurements.isEmpty()) {
                fetchNextMeasurements();
            }
            if (this.mPendingMeasurements.isEmpty() || ((Measurement) this.mPendingMeasurements.get(0)).getType() != Type.Wifi) {
                return pushPreviousOrEmptyWifiScanResults();
            }
            return scheduleMeasurement((Measurement) this.mPendingMeasurements.remove(0), 500);
        }

        public void cancelWifiScan() {
        }

        public boolean startCellScan() {
            if (this.mPendingMeasurements.isEmpty() || ((Measurement) this.mPendingMeasurements.get(0)).getType() != Type.Cell) {
                return pushPreviousCellMeasurement();
            }
            return scheduleMeasurement((Measurement) this.mPendingMeasurements.remove(0), 500);
        }

        public void cancelCellScan() {
        }

        public void openBle() {
            if (this.mBleManager == null) {
                this.mBleManager = new BleManager(PlaybackReader.this.mParser, 550);
            }
        }

        public void closeBle() {
            this.mBleManager = null;
        }

        public void startBleUpdates() {
            if (PlaybackReader.this.mStarted && this.mBleManager != null) {
                this.mBleManager.start();
            }
        }

        public void stopBleUpdates() {
            if (this.mBleManager != null) {
                this.mBleManager.stop();
            }
        }

        void onStart() {
            fetchNextMeasurements();
        }

        void onStop() {
            closeBle();
        }

        void onMeasurementPushed(Type type) {
            if (type != Type.Gnss && this.mPendingMeasurements.isEmpty()) {
                fetchNextMeasurements();
            }
        }

        private void fetchNextMeasurements() {
            if (!isClosedOrNotStarted()) {
                if (!PlaybackReader.this.mParser.isEof()) {
                    Measurement measurement;
                    this.mPendingMeasurements.addAll(PlaybackReader.this.mParser.pullNextMeasurements());
                    List<Measurement> arrayList = new ArrayList();
                    Iterator it = this.mPendingMeasurements.iterator();
                    while (it.hasNext()) {
                        measurement = (Measurement) it.next();
                        if (measurement != null) {
                            switch (measurement.getType()) {
                                case Ble:
                                    it.remove();
                                    this.mBleManager.addBleMeasurement(measurement);
                                    break;
                                case Gnss:
                                    it.remove();
                                    arrayList.add(measurement);
                                    break;
                                default:
                                    break;
                            }
                        }
                        it.remove();
                    }
                    for (Measurement measurement2 : arrayList) {
                        measurement2.dispatch(this);
                    }
                } else if (!PlaybackReader.this.onEof()) {
                    PlaybackReader.this.close();
                }
            }
        }

        private boolean scheduleMeasurement(Measurement measurement, long j) {
            return PlaybackReader.this.mHandler.postAtTime(new MeasurementPushTask(measurement), SystemClock.uptimeMillis() + j);
        }
    }

    class MeasurementPushTask implements Runnable {
        final Measurement mMeasurement;

        MeasurementPushTask(Measurement measurement) {
            Math.max(0, measurement.getDueAt() - TimeManager.timeSinceBoot());
            this.mMeasurement = measurement;
            PlaybackReader.this.mScheduledMeasurements.add(this);
        }

        public void run() {
            PlaybackReader.this.mScheduledMeasurements.remove(this);
            this.mMeasurement.dispatch(PlaybackReader.this);
        }

        public boolean isDueBefore(long j) {
            return this.mMeasurement.getDueAt() < j;
        }

        public boolean isWifiMeasurement() {
            return this.mMeasurement.getType() == Type.Wifi;
        }

        public boolean isCellMeasurement() {
            return this.mMeasurement.getType() == Type.Cell;
        }

        public boolean isBleMeasurement() {
            return this.mMeasurement.getType() == Type.Ble;
        }
    }

    class SchedulingModeProcessor extends AbstractModeProcessor {
        private EmptyBleGenerator mBleGenerator;
        private Long mPreviousBleDispatchTime = null;

        SchedulingModeProcessor() {
            super();
        }

        public boolean startWifiScan() {
            if (PlaybackReader.this.mScheduledMeasurements.isEmpty()) {
                scheduleNextMeasurements();
            }
            return PlaybackReader.this.scheduledMeasurementsContains(Type.Wifi);
        }

        public void cancelWifiScan() {
        }

        public boolean startCellScan() {
            if (PlaybackReader.this.mScheduledMeasurements.isEmpty()) {
                scheduleNextMeasurements();
            }
            return PlaybackReader.this.scheduledMeasurementsContains(Type.Cell);
        }

        public void cancelCellScan() {
        }

        public void openBle() {
            this.mPreviousBleDispatchTime = null;
            if (this.mBleGenerator == null) {
                this.mBleGenerator = new EmptyBleGenerator(PlaybackReader.this.mParser, 650);
            }
        }

        public void closeBle() {
            this.mBleGenerator = null;
        }

        public void startBleUpdates() {
            if (this.mBleGenerator != null) {
                this.mBleGenerator.start();
            }
        }

        public void stopBleUpdates() {
            if (this.mBleGenerator != null) {
                this.mBleGenerator.stop();
            }
        }

        void onStart() {
            scheduleNextMeasurements();
        }

        void onStop() {
            closeBle();
        }

        void onMeasurementPushed(Type type) {
            if (type == Type.Ble && this.mBleGenerator != null) {
                this.mBleGenerator.reschedule();
            }
            scheduleNextMeasurements();
        }

        private boolean scheduleNextMeasurements() {
            if (isClosedOrNotStarted()) {
                return false;
            }
            if (PlaybackReader.this.mParser.isEof()) {
                if (!PlaybackReader.this.onEof()) {
                    PlaybackReader.this.close();
                }
                return false;
            }
            for (Measurement measurement : PlaybackReader.this.mParser.pullNextMeasurements()) {
                long timeSinceBootDiff = TimeCalculator.timeSinceBootDiff(measurement.getDueAt());
                if (measurement.getType() == Type.Ble) {
                    timeSinceBootDiff = Math.max(550, timeSinceBootDiff);
                    if (this.mPreviousBleDispatchTime != null) {
                        long longValue = this.mPreviousBleDispatchTime.longValue() - TimeManager.timeSinceBoot();
                        if (longValue > 0) {
                            timeSinceBootDiff += longValue;
                        }
                    }
                    this.mPreviousBleDispatchTime = Long.valueOf(TimeManager.timeSinceBoot() + timeSinceBootDiff);
                }
                if (!PlaybackReader.this.mHandler.postDelayed(new MeasurementPushTask(measurement), timeSinceBootDiff)) {
                    return false;
                }
            }
            return true;
        }
    }

    private PlaybackReader(Context context, PlaybackOptions playbackOptions, IListener iListener) throws XmlPullParserException, FileNotFoundException, IOException {
        if (iListener == null) {
            throw new IllegalArgumentException("listener is null");
        } else if (playbackOptions.getPlaybackFile() == null) {
            throw new IllegalArgumentException("file is null");
        } else {
            this.mHandlerThread = new HandlerThread("PlaybackReader@" + hashCode());
            this.mHandlerThread.start();
            this.mHandler = new SafeHandler(this.mHandlerThread.getLooper());
            this.mContext = context;
            this.mListener = iListener;
            this.mPlaybackFile = playbackOptions.getPlaybackFile();
            this.mRepeat = playbackOptions.getRepeat();
            if (this.mPlaybackFile.getName().toLowerCase(Locale.US).endsWith(".ist")) {
                this.mParser = new IstPullParser(playbackOptions);
            } else {
                this.mParser = new LtaPullParser(playbackOptions);
            }
            initializeParser();
            switch (playbackOptions.getMode()) {
                case Immediate:
                    this.mModeProcessor = new ImmediateModeProcessor();
                    return;
                default:
                    this.mModeProcessor = new SchedulingModeProcessor();
                    return;
            }
        }
    }

    public static PlaybackReader open(Context context, PlaybackOptions playbackOptions, IListener iListener) {
        try {
            return new PlaybackReader(context, playbackOptions, iListener);
        } catch (XmlPullParserException e) {
            return null;
        } catch (FileNotFoundException e2) {
            return null;
        } catch (IOException e3) {
            return null;
        }
    }

    public String getPlaybackFileName() {
        return this.mPlaybackFile.getAbsolutePath();
    }

    public boolean start() {
        this.mModeProcessor.start();
        return true;
    }

    private void initializeParser() throws XmlPullParserException, FileNotFoundException {
        try {
            this.mParser.setInput(new InputStreamReader(new FileInputStream(this.mPlaybackFile), Charset.defaultCharset()));
        } catch (FileNotFoundException e) {
            onFileNotFound();
            throw e;
        } catch (XmlPullParserException e2) {
            throw e2;
        } catch (Exception e3) {
        }
    }

    boolean onEof() {
        if (!this.mRepeat) {
            return this.mHandler.post(new Runnable() {
                public void run() {
                    PlaybackReader.this.mListener.onPlaybackFinished();
                }
            }, false);
        }
        try {
            initializeParser();
            return start();
        } catch (Exception e) {
            return false;
        }
    }

    void onFileNotFound() {
    }

    long getRandomTimeStamp(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        return j == 0 ? currentTimeMillis : currentTimeMillis - (this.mRandom.nextLong() % j);
    }

    public void addWifiListener(final IWifiListener iWifiListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mWifiListeners.add(iWifiListener);
            }
        });
    }

    public void removeWifiListener(final IWifiListener iWifiListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mWifiListeners.remove(iWifiListener);
                PlaybackReader.this.closeIfNoListeners();
            }
        });
    }

    public boolean startWifiScan() {
        return this.mHandler.post(new Runnable() {
            public void run() {
                if (!PlaybackReader.this.mModeProcessor.startWifiScan()) {
                    PlaybackReader.this.pushWifiScanError();
                }
            }
        });
    }

    public void cancelWifiScan() {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mModeProcessor.cancelWifiScan();
            }
        });
    }

    public boolean startBleScan() {
        return this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mModeProcessor.startBleUpdates();
            }
        });
    }

    public void stopBleScan() {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mModeProcessor.stopBleUpdates();
            }
        });
    }

    public boolean startGnss() {
        return true;
    }

    public void stopGnss() {
    }

    public List<WifiMeasurement> getLastWifiScanResult() {
        return this.mLastWifiScanResult == null ? new ArrayList() : new ArrayList(this.mLastWifiScanResult.scanResultList);
    }

    public void addCellListener(final ICellListener iCellListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mCellListeners.add(iCellListener);
            }
        });
    }

    public void removeCellListener(final ICellListener iCellListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mCellListeners.remove(iCellListener);
                PlaybackReader.this.closeIfNoListeners();
            }
        });
    }

    public void addGnssListener(final IGnnsListener iGnnsListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mGnssListeners.add(iGnnsListener);
            }
        });
    }

    public void removeGnssListener(final IGnnsListener iGnnsListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mGnssListeners.remove(iGnnsListener);
                PlaybackReader.this.closeIfNoListeners();
            }
        });
    }

    public void addBleListener(final IBleListener iBleListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mBleListeners.add(iBleListener);
                if (PlaybackReader.this.mParser.isBleSupported()) {
                    PlaybackReader.this.mModeProcessor.openBle();
                }
            }
        });
    }

    public void removeBleListener(final IBleListener iBleListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mBleListeners.remove(iBleListener);
                if (PlaybackReader.this.mBleListeners.isEmpty()) {
                    PlaybackReader.this.mModeProcessor.stopBleUpdates();
                }
                PlaybackReader.this.closeIfNoListeners();
            }
        });
    }

    public boolean isBluetoothEnabled() {
        return this.mParser.isBleSupported();
    }

    public boolean startCellScan() {
        return this.mHandler.post(new Runnable() {
            public void run() {
                if (!PlaybackReader.this.mModeProcessor.startCellScan()) {
                    PlaybackReader.this.pushCellScanError();
                }
            }
        });
    }

    void pushCellScanError() {
        for (ICellListener onCellScanFailed : this.mCellListeners) {
            onCellScanFailed.onCellScanFailed();
        }
    }

    void pushWifiScanError() {
        for (IWifiListener onWifiScanFailed : this.mWifiListeners) {
            onWifiScanFailed.onWifiScanFailed();
        }
    }

    public void cancelCellScan() {
        this.mHandler.post(new Runnable() {
            public void run() {
                PlaybackReader.this.mModeProcessor.cancelCellScan();
            }
        });
    }

    public void close() {
        if (this.mStarted) {
            final ConditionVariable conditionVariable = new ConditionVariable();
            if (this.mHandler.post(new Runnable() {
                public void run() {
                    PlaybackReader.this.mModeProcessor.stop();
                    conditionVariable.open();
                }
            })) {
                conditionVariable.block();
            }
            this.mHandlerThread.quit();
            this.mHandlerThread = null;
        }
    }

    private Intent createCellLocationIntent(CellMeasurement cellMeasurement) {
        Intent intent = new Intent(PLAYBACK_CELL_LOCATION_AVAILABLE_ACTION);
        if (cellMeasurement != null) {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_RAN_TYPE, new String(cellMeasurement.type.name()).toLowerCase(Locale.US));
            bundle.putInt(KEY_GL1, cellMeasurement.gciL1Value);
            bundle.putInt(KEY_GL2, cellMeasurement.gciL2Value);
            if (cellMeasurement.hasGciL3Value) {
                bundle.putInt(KEY_GL3, cellMeasurement.gciL3Value);
                if (cellMeasurement.type == RANType.EUTRA) {
                    bundle.putString(KEY_GL3_TYPE, GL3_TYPE_VALUE_TAC);
                } else {
                    bundle.putString(KEY_GL3_TYPE, GL3_TYPE_VALUE_LAC);
                }
            }
            if (cellMeasurement.hasGciL4Value) {
                bundle.putInt(KEY_GL4, cellMeasurement.gciL4Value);
            }
            intent.putExtras(bundle);
        }
        return intent;
    }

    private void closeIfNoListeners() {
        if (this.mGnssListeners.isEmpty() && this.mCellListeners.isEmpty() && this.mWifiListeners.isEmpty() && this.mBleListeners.isEmpty()) {
            close();
        }
    }

    private boolean scheduledMeasurementsContains(Type type) {
        if (this.mScheduledMeasurements.isEmpty()) {
            return false;
        }
        for (MeasurementPushTask measurementPushTask : this.mScheduledMeasurements) {
            switch (type) {
                case Ble:
                    if (!measurementPushTask.isBleMeasurement()) {
                        break;
                    }
                    return true;
                case Wifi:
                    if (!measurementPushTask.isWifiMeasurement()) {
                        break;
                    }
                    return true;
                case Cell:
                    if (!measurementPushTask.isCellMeasurement()) {
                        break;
                    }
                    return true;
                default:
                    break;
            }
        }
        return false;
    }

    public void pushBle(BleScanResultContainer bleScanResultContainer) {
        this.mModeProcessor.pushBle(bleScanResultContainer);
    }

    public void pushCell(CellMeasurement cellMeasurement) {
        this.mModeProcessor.pushCell(cellMeasurement);
    }

    public void pushGnss(Location location) {
        this.mModeProcessor.pushGnss(location);
    }

    public void pushWifi(WifiScanResultContainer wifiScanResultContainer) {
        this.mModeProcessor.pushWifi(wifiScanResultContainer);
    }
}
