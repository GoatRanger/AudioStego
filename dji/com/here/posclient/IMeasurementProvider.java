package com.here.posclient;

public interface IMeasurementProvider {
    void cancelCellularScan();

    void cancelWifiScan();

    int requestCurrentWlanList();

    int startBle();

    boolean startCellularScan();

    int startGnss();

    boolean startWifiScan();

    void stopBle();

    void stopGnss();

    int subscribe(int i);

    int supportedMeasurementTypes();

    void unsubscribe(int i);
}
