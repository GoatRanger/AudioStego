package com.here.posclient.analytics;

public class Counters {
    private static final String TAG = "posclient.analytics.Counters";
    public final int event;

    public interface Handler {
        void onHandlePositioningCounters(PositioningCounters positioningCounters);

        void onHandleRadiomapCounters(RadiomapCounters radiomapCounters);
    }

    public static void parse(int i, long[] jArr, Handler handler) {
        switch (i) {
            case TrackerEvent.PositioningOnlineOutdoor /*111*/:
            case TrackerEvent.PositioningHybridOutdoor /*121*/:
            case TrackerEvent.PositioningOfflineOutdoor /*131*/:
            case TrackerEvent.PositioningOfflineCommonIndoor /*132*/:
            case TrackerEvent.PositioningOfflinePrivateIndoor /*133*/:
                handler.onHandlePositioningCounters(new PositioningCounters(i, jArr));
                return;
            case 211:
            case 212:
            case 213:
            case 221:
            case TrackerEvent.RadioMapManualCommonIndoor /*222*/:
            case TrackerEvent.RadioMapManualPrivateIndoor /*223*/:
                handler.onHandleRadiomapCounters(new RadiomapCounters(i, jArr));
                return;
            default:
                return;
        }
    }

    protected Counters(int i) {
        this.event = i;
    }
}
