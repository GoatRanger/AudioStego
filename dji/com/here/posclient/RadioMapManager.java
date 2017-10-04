package com.here.posclient;

import com.here.services.radiomap.common.GeoArea;

public class RadioMapManager {
    public static final int RM_TECH_2G = 1;
    public static final int RM_TECH_3G_FDD = 2;
    public static final int RM_TECH_3G_TDD = 4;
    public static final int RM_TECH_4G = 8;
    public static final int RM_TECH_ALL_CELL = 15;
    public static final int RM_TECH_ALL_WIFI = 48;
    public static final int RM_TECH_HIGH_ACCURACY = 1024;
    public static final int RM_TECH_WIFI_COARSE = 16;
    public static final int RM_TECH_WIFI_DETAILED = 32;

    public interface IRadioMapStorageActionListener {
        void onClosed();

        void onRadioMapActionProgress(int i);

        void onRadioMapQueryActionComplete(int i, long j);

        void onRadioMapStorageActionComplete(int i);
    }

    public interface NetworkConnectionType {
        public static final long CONNTYPE_ALL = 15;
        public static final long CONNTYPE_ETHERNET = 4;
        public static final long CONNTYPE_MOBILE = 2;
        public static final long CONNTYPE_OTHER = 8;
        public static final long CONNTYPE_WIFI = 1;
    }

    public enum RadioMapQueryAction {
        LOCAL_SIZE,
        EXTENDED_SIZE,
        UPDATED_SIZE
    }

    public enum RadioMapStorageAction {
        CLEAR,
        EXTEND,
        UPDATE
    }

    public static native boolean startRadioMapQuery(long j, GeoArea[] geoAreaArr, int i, RadioMapQueryAction radioMapQueryAction, IRadioMapStorageActionListener iRadioMapStorageActionListener);

    public static native void stopRadioMapAction(IRadioMapStorageActionListener iRadioMapStorageActionListener);

    public static native boolean updateRadioMapCoverage(long j, GeoArea[] geoAreaArr, int i, RadioMapStorageAction radioMapStorageAction, IRadioMapStorageActionListener iRadioMapStorageActionListener);

    public static boolean updateRadioMapCoverage(GeoArea[] geoAreaArr, int i, RadioMapStorageAction radioMapStorageAction, IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        return updateRadioMapCoverage(1, geoAreaArr, i, radioMapStorageAction, iRadioMapStorageActionListener);
    }

    public static boolean startRadioMapQuery(GeoArea[] geoAreaArr, int i, RadioMapQueryAction radioMapQueryAction, IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        return startRadioMapQuery(1, geoAreaArr, i, radioMapQueryAction, iRadioMapStorageActionListener);
    }
}
