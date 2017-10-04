package dji.pilot.active;

import java.util.ArrayList;

public class DJIActiveSnModel {
    public ArrayList<DJISnModel> item;
    public int status;
    public String status_msg;

    public static class DJISnModel {
        public int deviceType;
        public int enabled;
        public String sn;
        public long timestamp;
    }
}
