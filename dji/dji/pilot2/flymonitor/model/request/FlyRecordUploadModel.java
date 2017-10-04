package dji.pilot2.flymonitor.model.request;

import java.util.ArrayList;
import java.util.List;

public class FlyRecordUploadModel {
    public List<RecordModel> records = new ArrayList();

    public static class RecordModel {
        public double altitude;
        public long date;
        public String droneID;
        public String droneType;
        public int flightTime;
        public int is_illegal;
        public double latitude;
        public double longitude;
        public String orderID;
        public double speed;
        public int status;
        public String userName;
        public int yaw;
    }
}
