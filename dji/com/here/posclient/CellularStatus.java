package com.here.posclient;

public class CellularStatus {
    public long interfaceIndex = 0;
    public long status = 0;

    public static final class StatusCode {
        public static final long DCH_FLAG = 4096;
        public static final long DENIED = 2;
        public static final long HOME = 3;
        public static final long ROAMING = 4;
        public static final long UNKNOWN = 0;
        public static final long UNREGISTERED = 1;
    }
}
