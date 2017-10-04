package dji.common.remotecontroller;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.usercenter.protocol.d;

public class DJIRCGPSData {
    public float accuracy;
    public boolean isValid;
    public double latitude;
    public double longitude;
    public int satelliteCount;
    public float speedEast;
    public float speedNorth;
    public DJIRCGpsTime time;

    public static class DJIRCGpsTime {
        public byte day;
        public byte hour;
        public byte minute;
        public byte month;
        public byte second;
        public int year;

        public DJIRCGpsTime(byte b, byte b2, byte b3, int i, byte b4, byte b5) {
            this.hour = b;
            this.minute = b2;
            this.second = b3;
            this.year = i;
            this.month = b4;
            this.day = b5;
        }

        public String toString() {
            return this.hour + ":" + this.minute + ":" + this.second + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.year + d.t + this.month + d.t + this.day;
        }
    }

    public DJIRCGPSData() {
        this.time = new DJIRCGpsTime();
    }

    public DJIRCGPSData(DJIRCGpsTime dJIRCGpsTime, double d, double d2, float f, float f2, int i, float f3, boolean z) {
        this.time = dJIRCGpsTime;
        this.latitude = d;
        this.longitude = d2;
        this.speedEast = f;
        this.speedNorth = f2;
        this.satelliteCount = i;
        this.accuracy = f3;
        this.isValid = z;
    }
}
