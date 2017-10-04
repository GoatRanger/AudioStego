package com.here.services.playback.internal.util;

import android.annotation.TargetApi;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import com.alipay.sdk.j.i;
import com.here.odnp.ble.BleScanResult;
import com.here.odnp.ble.IBleManager.BleScanResultContainer;
import com.here.odnp.util.TimeManager;
import com.here.odnp.wifi.IWifiManager.WifiScanResultContainer;
import com.here.posclient.BleMeasurement;
import com.here.posclient.WifiMeasurement;
import com.here.services.playback.internal.PlaybackOptions;
import com.here.services.playback.internal.util.IPullParser.IListener;
import com.here.services.playback.internal.util.IPullParser.Measurement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class IstPullParser implements IPullParser {
    static final String TAG = "services.playback.internal.util.IstPullParser";
    private final int mBleTagTypes;
    private boolean mEof;
    private final boolean mFastForwardLongBreaks;
    private boolean mHasBleMeasurements;
    private BufferedReader mInput;
    private final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US);
    private TimeCalculator mTimeCalculator;

    interface IMeasurementParser {
        boolean appendLine(String str, List<Measurement> list);

        Measurement getResult();
    }

    private abstract class MeasurementParserBase implements IMeasurementParser {
        boolean mAddGnnsTried;

        abstract Measurement onGetResult();

        private MeasurementParserBase() {
        }

        boolean tryAddGnssMeasurement(String str, List<Measurement> list) {
            if (this.mAddGnnsTried) {
                return false;
            }
            try {
                String[] split = str.split(i.b);
                IstPullParser.this.addGnssMeasurement(list, split, IstPullParser.this.getAdjustedTimeSinceBoot(IstPullParser.this.mSimpleDateFormat.parse(split[4]).getTime()));
                return true;
            } catch (ParseException e) {
                return true;
            } finally {
                this.mAddGnnsTried = true;
            }
        }

        public Measurement getResult() {
            this.mAddGnnsTried = false;
            return onGetResult();
        }
    }

    private class BleMeasurementParser extends MeasurementParserBase {
        private final IstBleScanResult mResult;

        public class PlaybackBleScanResult extends BleScanResult {
            public int tagType;
        }

        BleMeasurementParser(long j, long j2) {
            super();
            long min = Math.min(j, TimeManager.timeSinceBoot() + 550);
            if (j != min) {
                this.mResult = new IstBleScanResult(min, j2, IstPullParser.this.mBleTagTypes);
            } else {
                this.mResult = new IstBleScanResult(min, j2, IstPullParser.this.mBleTagTypes);
            }
        }

        public boolean appendLine(String str, List<Measurement> list) {
            if (!str.startsWith("\t")) {
                return tryAddGnssMeasurement(str, list);
            }
            try {
                this.mResult.addBleScanResult(parseBleAp(str));
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public Measurement onGetResult() {
            return this.mResult;
        }

        private PlaybackBleScanResult parseBleAp(String str) {
            PlaybackBleScanResult playbackBleScanResult = new PlaybackBleScanResult();
            String[] split = str.split(",");
            if (split.length < 7) {
                playbackBleScanResult.deviceAddress = "0000000000000000";
            } else {
                playbackBleScanResult.deviceAddress = BleMeasurement.toMac64(IstPullParser.formatBSSID(split[0].substring(1)));
                playbackBleScanResult.deviceName = split[2];
                playbackBleScanResult.rssi = Integer.parseInt(split[1]);
                playbackBleScanResult.timestamp = IstPullParser.this.getAdjustedCurrentTimeMillis(Long.parseLong(split[5])) / 1000;
                playbackBleScanResult.elapsedRealtimeTimestamp = IstPullParser.this.getAdjustedTimeSinceBoot(Long.parseLong(split[5]));
                if (split.length < 8) {
                    playbackBleScanResult.tagType = 16;
                } else {
                    playbackBleScanResult.tagType = IstPullParser.this.getBleTagType(split[7]);
                }
            }
            return playbackBleScanResult;
        }
    }

    static class IstBleScanResult extends Measurement {
        private final int mBleTagTypes;
        private final List<BleScanResult> mResult = new ArrayList();

        IstBleScanResult(long j, long j2, int i) {
            super(Type.Ble, j, j2);
            this.mBleTagTypes = i;
        }

        public void dispatch(IListener iListener) {
            if (iListener != null) {
                iListener.pushBle(new BleScanResultContainer(getId(), true, this.mResult));
            }
        }

        void addBleScanResult(PlaybackBleScanResult playbackBleScanResult) {
            if ((this.mBleTagTypes & playbackBleScanResult.tagType) != 0) {
                this.mResult.add(playbackBleScanResult);
            }
        }
    }

    static class IstGnssMeasurement extends Measurement {
        private final Location mLocation = new Location("gps");

        @TargetApi(17)
        IstGnssMeasurement(long j, long j2, double d, double d2, float f) {
            super(Type.Gnss, j, j2);
            this.mLocation.setAccuracy(f);
            this.mLocation.setLatitude(d);
            this.mLocation.setLongitude(d2);
            this.mLocation.setTime(System.currentTimeMillis());
            if (VERSION.SDK_INT >= 17) {
                this.mLocation.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
            }
            Bundle bundle = new Bundle();
            bundle.putLong("com.here.services.location:measurementId", getId());
            this.mLocation.setExtras(bundle);
        }

        public void dispatch(IListener iListener) {
            if (iListener != null) {
                iListener.pushGnss(this.mLocation);
            }
        }
    }

    static class IstWifiScanResult extends Measurement {
        private final List<WifiMeasurement> mResult = new ArrayList();

        IstWifiScanResult(long j, long j2) {
            super(Type.Wifi, j, j2);
        }

        public void dispatch(IListener iListener) {
            if (iListener != null) {
                iListener.pushWifi(new WifiScanResultContainer(getId(), true, this.mResult));
            }
        }

        void addWifiScanResult(WifiMeasurement wifiMeasurement) {
            this.mResult.add(wifiMeasurement);
        }
    }

    private class WifiMeasurementParser extends MeasurementParserBase {
        private final IstWifiScanResult mResult;

        WifiMeasurementParser(long j, long j2) {
            super();
            this.mResult = new IstWifiScanResult(j, j2);
        }

        public boolean appendLine(String str, List<Measurement> list) {
            if (!str.startsWith("\t")) {
                return tryAddGnssMeasurement(str, list);
            }
            try {
                this.mResult.addWifiScanResult(parseWifiAp(str));
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public Measurement onGetResult() {
            return this.mResult;
        }

        private WifiMeasurement parseWifiAp(String str) {
            WifiMeasurement wifiMeasurement = new WifiMeasurement();
            String[] split = str.split(",");
            if (split.length < 7) {
                wifiMeasurement.bssid = WifiMeasurement.toMac64("00:00:00:00:00:00");
            } else {
                wifiMeasurement.bssid = WifiMeasurement.toMac64(IstPullParser.formatBSSID(split[0].substring(1)));
                wifiMeasurement.ssid = split[2];
                wifiMeasurement.setFrequency(Integer.parseInt(split[4]));
                wifiMeasurement.rxLevel = Integer.parseInt(split[1]);
                wifiMeasurement.tsf = Long.parseLong(split[5]);
            }
            return wifiMeasurement;
        }
    }

    IstPullParser(PlaybackOptions playbackOptions) {
        this.mFastForwardLongBreaks = playbackOptions.getFastForwardLongBreaks();
        this.mBleTagTypes = playbackOptions.getBleTagTypes();
        this.mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public List<Measurement> pullNextMeasurements() {
        List<Measurement> arrayList = new ArrayList();
        IMeasurementParser iMeasurementParser = null;
        while (true) {
            try {
                this.mInput.mark(1024);
                String readLine = this.mInput.readLine();
                if (readLine == null) {
                    throw new IOException("end of file");
                } else if (!(readLine.startsWith("MAP_CALIBRATION") || readLine.startsWith("EXTENSION_"))) {
                    if (iMeasurementParser == null) {
                        iMeasurementParser = createMeasurementGenerator(readLine, arrayList);
                        if (iMeasurementParser == null) {
                            continue;
                        }
                    }
                    if (!iMeasurementParser.appendLine(readLine, arrayList)) {
                        break;
                    }
                }
            } catch (IOException e) {
                this.mEof = true;
                if (iMeasurementParser != null) {
                    arrayList.add(iMeasurementParser.getResult());
                }
            }
        }
        arrayList.add(iMeasurementParser.getResult());
        this.mInput.reset();
        return arrayList;
    }

    public boolean isEof() {
        return this.mEof;
    }

    public void setInput(Reader reader) throws Exception {
        this.mEof = false;
        this.mTimeCalculator = null;
        this.mInput = new BufferedReader(reader);
        this.mHasBleMeasurements = checkBleSupport(this.mInput);
    }

    public boolean isBleSupported() {
        return this.mHasBleMeasurements;
    }

    public Measurement createEmptyBleMeasurement(long j) {
        return new IstBleScanResult(TimeManager.timeSinceBoot() + j, 0, this.mBleTagTypes);
    }

    IMeasurementParser createMeasurementGenerator(String str, List<Measurement> list) {
        try {
            String[] split = str.split(i.b);
            long longValue = Long.valueOf(split[0]).longValue();
            long time = this.mSimpleDateFormat.parse(split[4]).getTime();
            if (this.mFastForwardLongBreaks) {
                doFastForwardAdjustments(time);
            }
            time = getAdjustedTimeSinceBoot(time);
            if (split.length <= 9 || !split[8].equalsIgnoreCase("BLE")) {
                return new WifiMeasurementParser(time, longValue);
            }
            return new BleMeasurementParser(time, longValue);
        } catch (Exception e) {
            return null;
        }
    }

    void addGnssMeasurement(List<Measurement> list, String[] strArr, long j) {
        try {
            long longValue = Long.valueOf(strArr[0]).longValue();
            String[] split = strArr[6].split(",");
            String[] split2 = strArr[7].split(",");
            if (split.length != 0 && split[0].length() != 0) {
                list.add(new IstGnssMeasurement(j, longValue, Double.valueOf(split[0]).doubleValue(), Double.valueOf(split[1]).doubleValue(), Float.valueOf(split2[2]).floatValue()));
            }
        } catch (Exception e) {
        }
    }

    long getAdjustedTimeSinceBoot(long j) {
        if (this.mTimeCalculator == null) {
            this.mTimeCalculator = new TimeCalculator(j);
        }
        return this.mTimeCalculator.getAdjustedTimeSinceBoot(j);
    }

    long getAdjustedCurrentTimeMillis(long j) {
        if (this.mTimeCalculator == null) {
            this.mTimeCalculator = new TimeCalculator(j);
        }
        return this.mTimeCalculator.getAdjustedCurrentTimeMillis(j);
    }

    void doFastForwardAdjustments(long j) {
        if (this.mTimeCalculator != null) {
            this.mTimeCalculator.doFastForwardAdjustments(j);
        }
    }

    static String formatBSSID(String str) {
        int i = 0;
        if (str.indexOf(58) != -1) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.substring(0, 2));
        int i2 = 0;
        while (i < 5) {
            i2 += 2;
            stringBuilder.append(":").append(str.substring(i2, i2 + 2));
            i++;
        }
        return stringBuilder.toString();
    }

    private boolean checkBleSupport(BufferedReader bufferedReader) {
        try {
            boolean z;
            bufferedReader.mark(Integer.MAX_VALUE);
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                if (!readLine.startsWith("\t") && readLine.contains("BLE")) {
                    z = true;
                    break;
                }
                readLine = bufferedReader.readLine();
            }
            z = false;
            try {
                bufferedReader.reset();
                return z;
            } catch (IOException e) {
                return z;
            }
        } catch (IOException e2) {
            return false;
        } catch (Throwable th) {
            bufferedReader.reset();
        }
    }

    private int getBleTagType(String str) {
        if ("Nokia Location Tag".equals(str)) {
            return 1;
        }
        if ("Nokia Location Tag v2".equals(str)) {
            return 2;
        }
        if ("Apple iBeacon".equals(str)) {
            return 4;
        }
        if ("BluVision sBeacon".equals(str)) {
            return 8;
        }
        if ("Eddystone".equals(str)) {
            return 32;
        }
        return 16;
    }
}
