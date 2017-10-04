package com.here.odnp.adaptations;

import com.here.posclient.BleMeasurement;
import com.here.posclient.CellMeasurement;
import com.here.posclient.CellularStatus;
import com.here.posclient.GnssStatus;
import com.here.posclient.MeasurementType;
import com.here.posclient.PositionEstimate;
import com.here.posclient.Status;
import com.here.posclient.WifiMeasurement;
import com.here.posclient.WifiStatus;

public interface IMeasurementResultHandler {
    void handleBleScanResult(long j, BleMeasurement[] bleMeasurementArr, boolean z);

    void handleCellularScanResult(CellMeasurement cellMeasurement, boolean z);

    void handleCellularStatusChange(CellularStatus cellularStatus);

    void handleGnssLocationUpdate(PositionEstimate positionEstimate, boolean z);

    void handleGnssStatus(GnssStatus gnssStatus);

    void handleRequestError(MeasurementType measurementType, Status status);

    void handleWifiScanResult(long j, WifiMeasurement[] wifiMeasurementArr, boolean z, boolean z2);

    void handleWifiStateChanged(WifiStatus wifiStatus);
}
