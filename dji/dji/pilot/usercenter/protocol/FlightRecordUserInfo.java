package dji.pilot.usercenter.protocol;

import java.util.List;

public class FlightRecordUserInfo {
    public int Code = 0;
    public List<DroneList> DroneList;
    public String Email = "";
    public String ErrMsg = "";

    public static class DroneList {
        public String Boardnum = "";
        public List<String> CountryList;
        public int DroneType = 0;
        public float FlyHeight = 0.0f;
        public long FlyHeightDate = 0;
        public float MaxFlyDistance = 0.0f;
        public long MaxFlyDistanceDate = 0;
        public long MaxFlyTime = 0;
        public long MaxFlyTimeDate = 0;
        public float MaxHeight = 0.0f;
        public long MaxHeightDate = 0;
        public float MaxHorizontalSpeed = 0.0f;
        public long MaxHorizontalSpeedDate = 0;
        public float MaxVirticalSpeed = 0.0f;
        public long MaxVirticalSpeedDate = 0;
        public float TakeOffAltitude = 0.0f;
        public long TakeOffAltitudeDate = 0;
        public int TotalFlyCount = 0;
        public float TotalFlyDistance = 0.0f;
        public float TotalFlyTime = 0.0f;
    }
}
