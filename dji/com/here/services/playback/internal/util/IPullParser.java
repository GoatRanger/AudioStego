package com.here.services.playback.internal.util;

import android.location.Location;
import com.here.odnp.ble.IBleManager.BleScanResultContainer;
import com.here.odnp.wifi.IWifiManager.WifiScanResultContainer;
import com.here.posclient.CellMeasurement;
import java.io.Reader;
import java.util.List;

public interface IPullParser {
    public static final long INVALID_TIMESTAMP_VALUE = Long.MIN_VALUE;
    public static final String KEY_MEASUREMENT_ID = "com.here.services.location:measurementId";

    public interface IListener {
        void pushBle(BleScanResultContainer bleScanResultContainer);

        void pushCell(CellMeasurement cellMeasurement);

        void pushGnss(Location location);

        void pushWifi(WifiScanResultContainer wifiScanResultContainer);
    }

    public static abstract class Measurement {
        private final long mDueAt;
        private final long mId;
        private final Type mType;

        enum Type {
            Ble,
            Cell,
            Gnss,
            Wifi
        }

        abstract void dispatch(IListener iListener);

        Measurement(Type type, long j, long j2) {
            this.mType = type;
            this.mDueAt = j;
            this.mId = j2;
        }

        Type getType() {
            return this.mType;
        }

        long getDueAt() {
            return this.mDueAt;
        }

        long getId() {
            return this.mId;
        }
    }

    Measurement createEmptyBleMeasurement(long j);

    boolean isBleSupported();

    boolean isEof();

    List<Measurement> pullNextMeasurements();

    void setInput(Reader reader) throws Exception;
}
