package com.here.services.playback.internal.util;

import android.annotation.TargetApi;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.here.odnp.util.TimeManager;
import com.here.odnp.wifi.IWifiManager.WifiScanResultContainer;
import com.here.posclient.CellMeasurement;
import com.here.posclient.CellMeasurement.RANType;
import com.here.posclient.WifiMeasurement;
import com.here.services.playback.internal.PlaybackOptions;
import com.here.services.playback.internal.util.IPullParser.IListener;
import com.here.services.playback.internal.util.IPullParser.Measurement;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class LtaPullParser implements IPullParser {
    private static final String A_CID = "cId";
    private static final String A_ENDTIME = "endTime";
    private static final String A_HACC = "hAcc";
    private static final String A_ID = "id";
    private static final String A_LAC = "LAC";
    private static final String A_LAT = "lat";
    private static final String A_LON = "lon";
    private static final String A_MAC = "MAC";
    private static final String A_MCC = "MCC";
    private static final String A_MNC = "MNC";
    private static final String A_POS = "pos";
    private static final String A_RX = "rx";
    private static final String A_SSID = "SSID";
    private static final String A_STARTTIME = "startTime";
    private static final String A_TAC = "TAC";
    private static final String A_TIMESTAMP = "timestamp";
    private static final String E_COORDINATE = "coordinate";
    private static final String E_COORDINATE_ACCURACY = "coordinateAccuracy";
    private static final String E_GPS = "lfwGpsMeasurement";
    private static final String E_GSM = "gsm";
    private static final String E_LTE = "lte";
    private static final String E_ODNPMEASUREMENTS = "odnpMeasurement";
    private static final String E_TDSCDMA = "tdscdma";
    private static final String E_WCDMA = "wcdma";
    private static final String E_WIFI = "wifi";
    public static final int INVALID_RX_VALUE = Integer.MAX_VALUE;
    private static final String TAG = "services.playback.internal.util.LtaPullParser";
    private final SimpleDateFormat mDateFormat;
    private final XmlPullParser mParser = XmlPullParserFactory.newInstance().newPullParser();
    private final PlaybackOptions mPlaybackOptions;
    private TimeCalculator mTimeCalculator;

    static class LtaCellMeasurement extends Measurement {
        private final CellMeasurement mCellMeasurement;

        LtaCellMeasurement(CellMeasurement cellMeasurement, long j, long j2) {
            super(Type.Cell, j, j2);
            this.mCellMeasurement = cellMeasurement;
        }

        public void dispatch(IListener iListener) {
            if (iListener != null) {
                iListener.pushCell(this.mCellMeasurement);
            }
        }
    }

    static class LtaGnssMeasurement extends Measurement {
        private final Location mLocation = new Location("gps");

        @TargetApi(17)
        LtaGnssMeasurement(long j, long j2, double d, double d2, float f) {
            super(Type.Gnss, j, j2);
            this.mLocation.setAccuracy(f);
            this.mLocation.setLatitude(d);
            this.mLocation.setLongitude(d2);
            this.mLocation.setTime(TimeManager.currentTimeMillis() + TimeCalculator.timeSinceBootDiff(j));
            if (VERSION.SDK_INT >= 17) {
                this.mLocation.setElapsedRealtimeNanos(TimeUnit.MILLISECONDS.toNanos(j));
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

    static class LtaWifiScanResult extends Measurement {
        private final List<WifiMeasurement> mWifiScanResults = new ArrayList();

        LtaWifiScanResult(long j, long j2) {
            super(Type.Wifi, j, j2);
        }

        public void addWifiScan(WifiMeasurement wifiMeasurement) {
            this.mWifiScanResults.add(wifiMeasurement);
        }

        public void dispatch(IListener iListener) {
            if (iListener != null) {
                iListener.pushWifi(new WifiScanResultContainer(getId(), true, this.mWifiScanResults));
            }
        }
    }

    LtaPullParser(PlaybackOptions playbackOptions) throws XmlPullParserException {
        this.mPlaybackOptions = playbackOptions;
        this.mDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS", Locale.US);
    }

    public List<Measurement> pullNextMeasurements() {
        List<Measurement> arrayList = new ArrayList();
        while (!isEof() && arrayList.isEmpty()) {
            try {
                if (this.mParser.next() == 2) {
                    if (E_ODNPMEASUREMENTS.equalsIgnoreCase(this.mParser.getName())) {
                        pullOdnpMeasurement(arrayList);
                    } else if (E_GPS.equalsIgnoreCase(this.mParser.getName())) {
                        pullGpsMeasurement(arrayList);
                    }
                }
            } catch (Exception e) {
            }
        }
        return arrayList;
    }

    public boolean isEof() {
        try {
            return this.mParser.getEventType() == 1;
        } catch (Exception e) {
            return true;
        }
    }

    public void setInput(Reader reader) throws Exception {
        this.mTimeCalculator = null;
        this.mParser.setInput(reader);
    }

    public boolean isBleSupported() {
        return false;
    }

    public Measurement createEmptyBleMeasurement(long j) {
        return null;
    }

    String getAttributeValue(String str) {
        return this.mParser.getAttributeValue(null, str);
    }

    long getOptionalTimeAttribute(String str, long j) {
        try {
            j = this.mDateFormat.parse(this.mParser.getAttributeValue(null, str)).getTime();
        } catch (Exception e) {
        }
        return j;
    }

    long getTimeAttribute(String str) throws XmlPullParserException {
        try {
            return this.mDateFormat.parse(this.mParser.getAttributeValue(null, str)).getTime();
        } catch (ParseException e) {
            throw new XmlPullParserException(e.getMessage());
        }
    }

    long getOptionalLongAttribute(String str, long j) {
        try {
            j = Long.parseLong(this.mParser.getAttributeValue(null, str));
        } catch (Exception e) {
        }
        return j;
    }

    int getIntAttribute(String str) throws XmlPullParserException {
        try {
            return Integer.parseInt(this.mParser.getAttributeValue(null, str));
        } catch (NumberFormatException e) {
            throw new XmlPullParserException(e.getMessage());
        }
    }

    int getOptionalIntAttribute(String str, int i) {
        try {
            i = Integer.parseInt(this.mParser.getAttributeValue(null, str));
        } catch (Exception e) {
        }
        return i;
    }

    double getDoubleAttribute(String str) throws XmlPullParserException {
        try {
            return Double.parseDouble(this.mParser.getAttributeValue(null, str));
        } catch (NumberFormatException e) {
            throw new XmlPullParserException(e.getMessage());
        }
    }

    boolean getOptionalBooleanAttribute(String str, boolean z) {
        try {
            String attributeValue = this.mParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                z = Boolean.parseBoolean(attributeValue);
            }
        } catch (Exception e) {
        }
        return z;
    }

    void pullToEndTag(String str) throws XmlPullParserException, IOException {
        while (!isEof()) {
            if (this.mParser.next() == 3 && str.equalsIgnoreCase(this.mParser.getName())) {
                return;
            }
        }
    }

    void pullOdnpMeasurement(List<Measurement> list) throws XmlPullParserException, IOException {
        long longValue = Long.valueOf(this.mParser.getAttributeValue(null, "id")).longValue();
        long max = Math.max(getTimeAttribute(A_STARTTIME), getTimeAttribute(A_ENDTIME));
        do {
            if (this.mParser.getEventType() == 2) {
                LtaCellMeasurement pullWcdmaMeasurement;
                if (E_WCDMA.equalsIgnoreCase(this.mParser.getName())) {
                    pullWcdmaMeasurement = pullWcdmaMeasurement(getAdjustedTimeSinceBoot(max), longValue);
                    if (isSet(this.mPlaybackOptions.getTechnologies(), 1)) {
                        list.add(pullWcdmaMeasurement);
                    }
                } else if (E_LTE.equalsIgnoreCase(this.mParser.getName())) {
                    pullWcdmaMeasurement = pullLteMeasurement(getAdjustedTimeSinceBoot(max), longValue);
                    if (isSet(this.mPlaybackOptions.getTechnologies(), 1)) {
                        list.add(pullWcdmaMeasurement);
                    }
                } else if (E_GSM.equalsIgnoreCase(this.mParser.getName())) {
                    pullWcdmaMeasurement = pullGsmMeasurement(getAdjustedTimeSinceBoot(max), longValue);
                    if (isSet(this.mPlaybackOptions.getTechnologies(), 1)) {
                        list.add(pullWcdmaMeasurement);
                    }
                } else if (E_TDSCDMA.equalsIgnoreCase(this.mParser.getName())) {
                    pullWcdmaMeasurement = pullTdscdmaMeasurement(getAdjustedTimeSinceBoot(max), longValue);
                    if (isSet(this.mPlaybackOptions.getTechnologies(), 1)) {
                        list.add(pullWcdmaMeasurement);
                    }
                } else if (E_WIFI.equalsIgnoreCase(this.mParser.getName())) {
                    LtaWifiScanResult ltaWifiScanResult;
                    if (null == null) {
                        ltaWifiScanResult = new LtaWifiScanResult(getAdjustedTimeSinceBoot(max), longValue);
                        if (isSet(this.mPlaybackOptions.getTechnologies(), 2)) {
                            list.add(ltaWifiScanResult);
                        }
                    } else {
                        ltaWifiScanResult = null;
                    }
                    pullWifiMeasurements(ltaWifiScanResult);
                } else {
                    this.mParser.next();
                }
            } else if (this.mParser.getEventType() != 3) {
                this.mParser.next();
            } else if (!E_ODNPMEASUREMENTS.equalsIgnoreCase(this.mParser.getName())) {
                this.mParser.next();
            } else {
                return;
            }
        } while (!isEof());
    }

    LtaCellMeasurement pullWcdmaMeasurement(long j, long j2) throws XmlPullParserException, IOException {
        boolean z = false;
        CellMeasurement cellMeasurement = new CellMeasurement();
        cellMeasurement.type = RANType.UTRAFDD;
        cellMeasurement.gciL1Value = getIntAttribute(A_MCC);
        cellMeasurement.gciL2Value = getIntAttribute(A_MNC);
        cellMeasurement.gciL3Value = getOptionalIntAttribute(A_LAC, -1);
        cellMeasurement.hasGciL3Value = cellMeasurement.gciL3Value != -1;
        cellMeasurement.gciL4Value = getOptionalIntAttribute(A_CID, -1);
        if (cellMeasurement.gciL4Value != -1) {
            z = true;
        }
        cellMeasurement.hasGciL4Value = z;
        cellMeasurement.simulated = true;
        pullToEndTag(E_WCDMA);
        return new LtaCellMeasurement(cellMeasurement, j, j2);
    }

    LtaCellMeasurement pullTdscdmaMeasurement(long j, long j2) throws XmlPullParserException, IOException {
        boolean z = false;
        CellMeasurement cellMeasurement = new CellMeasurement();
        cellMeasurement.type = RANType.UTRATDD;
        cellMeasurement.gciL1Value = getIntAttribute(A_MCC);
        cellMeasurement.gciL2Value = getIntAttribute(A_MNC);
        cellMeasurement.gciL3Value = getOptionalIntAttribute(A_LAC, -1);
        cellMeasurement.hasGciL3Value = cellMeasurement.gciL3Value != -1;
        cellMeasurement.gciL4Value = getOptionalIntAttribute(A_CID, -1);
        if (cellMeasurement.gciL4Value != -1) {
            z = true;
        }
        cellMeasurement.hasGciL4Value = z;
        cellMeasurement.simulated = true;
        pullToEndTag(E_TDSCDMA);
        return new LtaCellMeasurement(cellMeasurement, j, j2);
    }

    LtaCellMeasurement pullLteMeasurement(long j, long j2) throws XmlPullParserException, IOException {
        boolean z = false;
        CellMeasurement cellMeasurement = new CellMeasurement();
        cellMeasurement.type = RANType.EUTRA;
        cellMeasurement.gciL1Value = getIntAttribute(A_MCC);
        cellMeasurement.gciL2Value = getIntAttribute(A_MNC);
        cellMeasurement.gciL3Value = getOptionalIntAttribute(A_TAC, -1);
        cellMeasurement.hasGciL3Value = cellMeasurement.gciL3Value != -1;
        cellMeasurement.gciL4Value = getOptionalIntAttribute(A_CID, -1);
        if (cellMeasurement.gciL4Value != -1) {
            z = true;
        }
        cellMeasurement.hasGciL4Value = z;
        cellMeasurement.simulated = true;
        pullToEndTag(E_LTE);
        return new LtaCellMeasurement(cellMeasurement, j, j2);
    }

    LtaCellMeasurement pullGsmMeasurement(long j, long j2) throws XmlPullParserException, IOException {
        boolean z = false;
        CellMeasurement cellMeasurement = new CellMeasurement();
        cellMeasurement.type = RANType.GERAN;
        cellMeasurement.gciL1Value = getIntAttribute(A_MCC);
        cellMeasurement.gciL2Value = getIntAttribute(A_MNC);
        cellMeasurement.gciL3Value = getOptionalIntAttribute(A_LAC, -1);
        cellMeasurement.hasGciL3Value = cellMeasurement.gciL3Value != -1;
        cellMeasurement.gciL4Value = getOptionalIntAttribute(A_CID, -1);
        if (cellMeasurement.gciL4Value != -1) {
            z = true;
        }
        cellMeasurement.hasGciL4Value = z;
        cellMeasurement.simulated = true;
        pullToEndTag(E_GSM);
        return new LtaCellMeasurement(cellMeasurement, j, j2);
    }

    void pullWifiMeasurements(LtaWifiScanResult ltaWifiScanResult) throws XmlPullParserException, IOException {
        while (!isEof()) {
            if (this.mParser.getEventType() == 2) {
                if (!E_WIFI.equalsIgnoreCase(this.mParser.getName())) {
                    return;
                }
                if (getOptionalBooleanAttribute(A_POS, true)) {
                    WifiMeasurement wifiMeasurement = new WifiMeasurement();
                    wifiMeasurement.bssid = WifiMeasurement.toMac64(formatBSSID(getAttributeValue(A_MAC)));
                    wifiMeasurement.ssid = getAttributeValue("SSID");
                    if (wifiMeasurement.ssid == null || wifiMeasurement.ssid.length() == 0) {
                        wifiMeasurement.ssid = wifiMeasurement.bssid;
                    }
                    wifiMeasurement.rxLevel = getOptionalIntAttribute(A_RX, Integer.MAX_VALUE);
                    if (VERSION.SDK_INT >= 17) {
                        wifiMeasurement.tsf = getOptionalLongAttribute("timestamp", Long.MIN_VALUE);
                    }
                    ltaWifiScanResult.addWifiScan(wifiMeasurement);
                } else {
                    this.mParser.next();
                }
            } else if (this.mParser.getEventType() == 3 && !E_WIFI.equalsIgnoreCase(this.mParser.getName())) {
                return;
            }
            this.mParser.next();
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

    void pullGpsMeasurement(List<Measurement> list) throws XmlPullParserException, IOException {
        Double d = null;
        try {
            long longValue = Long.valueOf(this.mParser.getAttributeValue(null, "id")).longValue();
            long timeAttribute = getTimeAttribute(A_ENDTIME);
            Double d2 = null;
            Double d3 = null;
            while (this.mParser.getEventType() != 1) {
                Double valueOf;
                Double d4;
                if (this.mParser.getEventType() == 2) {
                    if (E_COORDINATE.equalsIgnoreCase(this.mParser.getName())) {
                        d3 = Double.valueOf(getDoubleAttribute("lat"));
                        valueOf = Double.valueOf(getDoubleAttribute(A_LON));
                        d4 = d3;
                    } else if (E_COORDINATE_ACCURACY.equalsIgnoreCase(this.mParser.getName())) {
                        d = Double.valueOf(getDoubleAttribute(A_HACC));
                        valueOf = d2;
                        d4 = d3;
                    }
                    if (d4 != null || valueOf == null || d == null) {
                        this.mParser.next();
                        d2 = valueOf;
                        d3 = d4;
                    } else {
                        if (isSet(this.mPlaybackOptions.getTechnologies(), 4)) {
                            list.add(new LtaGnssMeasurement(getAdjustedTimeSinceBoot(timeAttribute), longValue, d4.doubleValue(), valueOf.doubleValue(), d.floatValue()));
                        }
                        pullToEndTag(E_GPS);
                    }
                }
                valueOf = d2;
                d4 = d3;
                if (d4 != null) {
                }
                this.mParser.next();
                d2 = valueOf;
                d3 = d4;
            }
            pullToEndTag(E_GPS);
        } catch (Throwable th) {
            pullToEndTag(E_GPS);
        }
    }

    long getAdjustedTimeSinceBoot(long j) {
        if (this.mTimeCalculator == null) {
            this.mTimeCalculator = new TimeCalculator(j);
        }
        return this.mTimeCalculator.getAdjustedTimeSinceBoot(j);
    }

    private boolean isSet(int i, int i2) {
        return (i2 & i) == i2;
    }
}
