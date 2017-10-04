package dji.pilot.active;

import java.util.ArrayList;

public class DJIActiveDeviceModel {
    public ArrayList<DJIDeviceModel> models;

    public static class DJIDeviceModel {
        public String appVersion;
        public String deviceName;
        public int deviceType;
        public String email;
        public String firewareVersion;
        public String productType;
        public String registerPhone;
        public String sn;
        public String uid;
    }
}
