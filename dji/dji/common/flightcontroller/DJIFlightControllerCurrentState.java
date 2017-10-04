package dji.common.flightcontroller;

import com.alipay.sdk.j.i;
import com.here.odnp.debug.DebugFile;

public class DJIFlightControllerCurrentState {
    private int aircraftHeadDirection;
    private DJILocationCoordinate3D aircraftLocation = new DJILocationCoordinate3D(this.homepoint, 0.0f);
    private boolean areMotorsOn;
    private DJIAttitude attitude = new DJIAttitude();
    private DJIFlightControllerFlightMode flightMode;
    private String flightModeString;
    private int flightTime;
    private int goHomeHeight;
    private DJIGoHomeStatus goHomeStatus;
    private DJIGPSSignalStatus gpsSignalStatus;
    private float homePointAltitude;
    private DJILocationCoordinate2D homepoint = new DJILocationCoordinate2D();
    private DJIFlightOrientationMode iocMode;
    private boolean isFailsafe;
    private boolean isFlying;
    private boolean isGoingHome;
    private boolean isHomePointSet;
    private boolean isIMUPreheating;
    private boolean isMultipModeOpen;
    private boolean isReachLimitedHeight;
    private boolean isReachLimitedRadius;
    private boolean isUltrasonicBeingUsed;
    private boolean isUltrasonicError;
    private boolean isVisionSensorBeingUsed;
    private DJIFlightControllerNoFlyStatus noFlyStatus;
    private DJILocationCoordinate2D noFlyZoneCenter;
    private double noFlyZoneRadius;
    private DJIAircraftRemainingBatteryState remainingBattery;
    private double satelliteCount;
    private DJIFlightControllerSmartGoHomeStatus smartGoHomeStatus = new DJIFlightControllerSmartGoHomeStatus();
    private float ultrasonicHeight;
    private float velocityX;
    private float velocityY;
    private float velocityZ;

    public float getVelocityX() {
        return this.velocityX;
    }

    public void setVelocityX(float f) {
        this.velocityX = f;
    }

    public float getVelocityY() {
        return this.velocityY;
    }

    public void setVelocityY(float f) {
        this.velocityY = f;
    }

    public float getVelocityZ() {
        return this.velocityZ;
    }

    public void setVelocityZ(float f) {
        this.velocityZ = f;
    }

    public DJIFlightControllerNoFlyStatus getNoFlyStatus() {
        return this.noFlyStatus;
    }

    public void setNoFlyStatus(DJIFlightControllerNoFlyStatus dJIFlightControllerNoFlyStatus) {
        this.noFlyStatus = dJIFlightControllerNoFlyStatus;
    }

    public DJILocationCoordinate2D getNoFlyZoneCenter() {
        return this.noFlyZoneCenter;
    }

    public void setNoFlyZoneCenter(DJILocationCoordinate2D dJILocationCoordinate2D) {
        this.noFlyZoneCenter = dJILocationCoordinate2D;
    }

    public double getNoFlyZoneRadius() {
        return this.noFlyZoneRadius;
    }

    public void setNoFlyZoneRadius(double d) {
        this.noFlyZoneRadius = d;
    }

    public DJIAircraftRemainingBatteryState getRemainingBattery() {
        return this.remainingBattery;
    }

    public void setRemainingBattery(DJIAircraftRemainingBatteryState dJIAircraftRemainingBatteryState) {
        this.remainingBattery = dJIAircraftRemainingBatteryState;
    }

    public DJILocationCoordinate2D getHomeLocation() {
        return this.homepoint;
    }

    public void setHomepoint(DJILocationCoordinate2D dJILocationCoordinate2D) {
        this.homepoint = dJILocationCoordinate2D;
    }

    public float getHomePointAltitude() {
        return this.homePointAltitude;
    }

    public void setHomePointAltitude(float f) {
        this.homePointAltitude = f;
    }

    public DJIFlightOrientationMode getOrientaionMode() {
        return this.iocMode;
    }

    public void setIOCMode(DJIFlightOrientationMode dJIFlightOrientationMode) {
        this.iocMode = dJIFlightOrientationMode;
    }

    public boolean isGoingHome() {
        return this.isGoingHome;
    }

    public void setGoingHome(boolean z) {
        this.isGoingHome = z;
    }

    public DJIGoHomeStatus getGoHomeStatus() {
        return this.goHomeStatus;
    }

    public void setGoHomeStatus(DJIGoHomeStatus dJIGoHomeStatus) {
        this.goHomeStatus = dJIGoHomeStatus;
    }

    public boolean isMultipModeOpen() {
        return this.isMultipModeOpen;
    }

    public void setMultipModeOpen(boolean z) {
        this.isMultipModeOpen = z;
    }

    public boolean isReachLimitedHeight() {
        return this.isReachLimitedHeight;
    }

    public void setReachLimitedHeight(boolean z) {
        this.isReachLimitedHeight = z;
    }

    public boolean isReachLimitedRadius() {
        return this.isReachLimitedRadius;
    }

    public void setReachLimitedRadius(boolean z) {
        this.isReachLimitedRadius = z;
    }

    public int getAircraftHeadDirection() {
        return this.aircraftHeadDirection;
    }

    public void setAircraftHeadDirection(int i) {
        this.aircraftHeadDirection = i;
    }

    public boolean isHomePointSet() {
        return this.isHomePointSet;
    }

    public void setHomePointSet(boolean z) {
        this.isHomePointSet = z;
    }

    public int getGoHomeHeight() {
        return this.goHomeHeight;
    }

    public void setGoHomeHeight(int i) {
        this.goHomeHeight = i;
    }

    public double getSatelliteCount() {
        return this.satelliteCount;
    }

    public void setSatelliteCount(double d) {
        this.satelliteCount = d;
    }

    public DJILocationCoordinate3D getAircraftLocation() {
        return this.aircraftLocation;
    }

    public void setAircraftLocation(DJILocationCoordinate3D dJILocationCoordinate3D) {
        this.aircraftLocation = dJILocationCoordinate3D;
    }

    public DJIAttitude getAttitude() {
        return this.attitude;
    }

    public void setAttitude(DJIAttitude dJIAttitude) {
        this.attitude = dJIAttitude;
    }

    public boolean isFailsafe() {
        return this.isFailsafe;
    }

    public void setFailsafe(boolean z) {
        this.isFailsafe = z;
    }

    public DJIFlightControllerFlightMode getFlightMode() {
        return this.flightMode;
    }

    public void setFlightMode(DJIFlightControllerFlightMode dJIFlightControllerFlightMode) {
        this.flightMode = dJIFlightControllerFlightMode;
    }

    public boolean isFlying() {
        return this.isFlying;
    }

    public void setFlying(boolean z) {
        this.isFlying = z;
    }

    public boolean areMotorsOn() {
        return this.areMotorsOn;
    }

    public void setMotorsOn(boolean z) {
        this.areMotorsOn = z;
    }

    public boolean isUltrasonicBeingUsed() {
        return this.isUltrasonicBeingUsed;
    }

    public void setUltrasonicBeingUsed(boolean z) {
        this.isUltrasonicBeingUsed = z;
    }

    public boolean isIMUPreheating() {
        return this.isIMUPreheating;
    }

    public void setIMUPreheating(boolean z) {
        this.isIMUPreheating = z;
    }

    public boolean isVisionSensorBeingUsed() {
        return this.isVisionSensorBeingUsed;
    }

    public void setVisionSensorsBeingUsed(boolean z) {
        this.isVisionSensorBeingUsed = z;
    }

    public boolean isUltrasonicError() {
        return this.isUltrasonicError;
    }

    public void setUltrasonicError(boolean z) {
        this.isUltrasonicError = z;
    }

    public DJIGPSSignalStatus getGpsSignalStatus() {
        return this.gpsSignalStatus;
    }

    public void setGpsSignalStatus(DJIGPSSignalStatus dJIGPSSignalStatus) {
        this.gpsSignalStatus = dJIGPSSignalStatus;
    }

    public float getUltrasonicHeight() {
        return this.ultrasonicHeight;
    }

    public void setUltrasonicHeight(float f) {
        this.ultrasonicHeight = f;
    }

    public String getFlightModeString() {
        return this.flightModeString;
    }

    public void setFlightModeString(String str) {
        this.flightModeString = str;
    }

    public DJIFlightControllerSmartGoHomeStatus getSmartGoHomeStatus() {
        return this.smartGoHomeStatus;
    }

    public void setSmartGoHomeStatus(DJIFlightControllerSmartGoHomeStatus dJIFlightControllerSmartGoHomeStatus) {
        this.smartGoHomeStatus = dJIFlightControllerSmartGoHomeStatus;
    }

    public int getFlightTime() {
        return this.flightTime;
    }

    public void setFlightTime(int i) {
        this.flightTime = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append(i.d);
        stringBuffer.append(DebugFile.EOL);
        return stringBuffer.toString();
    }
}
