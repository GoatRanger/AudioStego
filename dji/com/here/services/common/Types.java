package com.here.services.common;

public interface Types {

    public enum Source {
        Unspecified,
        LastKnown,
        Online,
        Offline,
        HighAccuracy,
        Cache,
        Learning,
        Hardware,
        Fusion
    }

    public enum Storage {
        Internal,
        External,
        SecondaryExternal
    }

    public enum Technology {
        Gnss,
        Wlan,
        Cell,
        Cellular,
        ECell,
        LocationArea,
        Network,
        Country,
        Ip,
        Sensors,
        Map,
        TrackingArea,
        Rnc,
        ENodeB,
        System,
        BluetoothLE
    }
}
