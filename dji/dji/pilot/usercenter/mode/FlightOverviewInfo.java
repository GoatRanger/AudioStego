package dji.pilot.usercenter.mode;

import dji.pilot.usercenter.protocol.c;
import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;
import java.util.List;

public class FlightOverviewInfo {
    public static final String INVALID_ALTITUDE = "1.09";
    public List<String> footprints = new ArrayList();
    public long mActiveTime = 0;
    public String mAircraftName = "";
    public String mArea = "";
    public String mBoardNum = c.T;
    public String mCity = "";
    public int mDroneType = 0;
    public double mLatitude = 0.0d;
    public double mLongitude = 0.0d;
    public String mSerialNum = "";
    public String mStreet = "";
    public String mSubStreet = "";
    public long mTimeStamp = 0;
    public float mTopAttitude = -2.14748365E9f;
    public long mTopAttitudeDate = 0;
    public float mTopDistance = 0.0f;
    public long mTopDistanceDate = 0;
    public float mTopHSpeed = 0.0f;
    public long mTopHSpeedDate = 0;
    public float mTopVSpeed = 0.0f;
    public long mTopVSpeedDate = 0;
    public double mTotalDistance = 0.0d;
    public int mTotalFlightTime = 0;
    public long mTotalTime = 0;
    public int mVersion = 1;

    public void copyOf(FlightOverviewInfo flightOverviewInfo) {
        this.mCity = flightOverviewInfo.mCity;
        this.mStreet = flightOverviewInfo.mStreet;
        this.mSubStreet = flightOverviewInfo.mSubStreet;
        this.mArea = flightOverviewInfo.mArea;
        this.mLongitude = flightOverviewInfo.mLongitude;
        this.mLatitude = flightOverviewInfo.mLatitude;
        this.mTotalTime = flightOverviewInfo.mTotalTime;
        this.mTotalDistance = flightOverviewInfo.mTotalDistance;
        this.mTotalFlightTime = flightOverviewInfo.mTotalFlightTime;
        this.mTimeStamp = flightOverviewInfo.mTimeStamp;
        this.mBoardNum = flightOverviewInfo.mBoardNum;
        this.mVersion = flightOverviewInfo.mVersion;
        this.mAircraftName = flightOverviewInfo.mAircraftName;
        this.mDroneType = flightOverviewInfo.mDroneType;
        this.mActiveTime = flightOverviewInfo.mActiveTime;
        this.mSerialNum = flightOverviewInfo.mSerialNum;
        this.mTopDistance = flightOverviewInfo.mTopDistance;
        this.mTopVSpeed = flightOverviewInfo.mTopVSpeed;
        this.mTopHSpeed = flightOverviewInfo.mTopHSpeed;
        this.mTopAttitude = flightOverviewInfo.mTopAttitude;
        this.mTopDistanceDate = flightOverviewInfo.mTopDistanceDate;
        this.mTopVSpeedDate = flightOverviewInfo.mTopVSpeedDate;
        this.mTopHSpeedDate = flightOverviewInfo.mTopHSpeedDate;
        this.mTopAttitudeDate = flightOverviewInfo.mTopAttitudeDate;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof FlightOverviewInfo)) {
            return equals;
        }
        FlightOverviewInfo flightOverviewInfo = (FlightOverviewInfo) obj;
        if (this.mBoardNum == flightOverviewInfo.mBoardNum || (this.mBoardNum != null && this.mBoardNum.endsWith(flightOverviewInfo.mBoardNum))) {
            return true;
        }
        return equals;
    }

    public int hashCode() {
        if (this.mBoardNum != null) {
            return this.mBoardNum.hashCode() + 527;
        }
        return 17;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(30);
        stringBuilder.append("sn[").append(this.mBoardNum).append(d.H);
        stringBuilder.append("city[").append(this.mCity).append(d.H);
        stringBuilder.append("time[").append(this.mTimeStamp).append(d.H);
        return stringBuilder.toString();
    }
}
