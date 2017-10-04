package dji.common.flightcontroller;

import dji.common.error.DJIError;

public class DJIRtkState {
    private float airSideAltitude;
    private DJILocationCoordinate2D airSideLocation;
    private DJIRTKReceiverInfo auxBeiDouChannel;
    private DJIRTKReceiverInfo auxGlonassChannel;
    private DJIRTKReceiverInfo auxGpsChannel;
    private DJIRTKReceiverInfo bsBeiDouChannel;
    private DJIRTKReceiverInfo bsGlonassChannel;
    private DJIRTKReceiverInfo bsGpsChannel;
    private float direction;
    private DJIError error;
    private float groundBsAltitude;
    private DJILocationCoordinate2D groundBsLocation;
    private boolean isDirectionValid;
    private boolean isRtkEnabled;
    private DJIRTKReceiverInfo mainBeiDouChannel;
    private DJIRTKReceiverInfo mainGlonassChannel;
    private DJIRTKReceiverInfo mainGpsChannel;
    private DJIRTKPositioningSolution positioningState;

    public DJIRtkState(DJIError dJIError, DJIRTKPositioningSolution dJIRTKPositioningSolution, DJIRTKReceiverInfo dJIRTKReceiverInfo, DJIRTKReceiverInfo dJIRTKReceiverInfo2, DJIRTKReceiverInfo dJIRTKReceiverInfo3, DJIRTKReceiverInfo dJIRTKReceiverInfo4, DJIRTKReceiverInfo dJIRTKReceiverInfo5, DJIRTKReceiverInfo dJIRTKReceiverInfo6, DJIRTKReceiverInfo dJIRTKReceiverInfo7, DJIRTKReceiverInfo dJIRTKReceiverInfo8, DJIRTKReceiverInfo dJIRTKReceiverInfo9, DJILocationCoordinate2D dJILocationCoordinate2D, float f, DJILocationCoordinate2D dJILocationCoordinate2D2, float f2, float f3, boolean z, boolean z2) {
        this.error = dJIError;
        this.positioningState = dJIRTKPositioningSolution;
        this.mainGpsChannel = dJIRTKReceiverInfo;
        this.auxGpsChannel = dJIRTKReceiverInfo2;
        this.bsGpsChannel = dJIRTKReceiverInfo3;
        this.mainBeiDouChannel = dJIRTKReceiverInfo4;
        this.auxBeiDouChannel = dJIRTKReceiverInfo5;
        this.bsBeiDouChannel = dJIRTKReceiverInfo6;
        this.mainGlonassChannel = dJIRTKReceiverInfo7;
        this.auxGlonassChannel = dJIRTKReceiverInfo8;
        this.bsGlonassChannel = dJIRTKReceiverInfo9;
        this.airSideLocation = dJILocationCoordinate2D;
        this.airSideAltitude = f;
        this.groundBsLocation = dJILocationCoordinate2D2;
        this.groundBsAltitude = f2;
        this.direction = f3;
        this.isDirectionValid = z;
        this.isRtkEnabled = z2;
    }

    void setError(DJIError dJIError) {
        this.error = dJIError;
    }

    void setPositioningSolution(DJIRTKPositioningSolution dJIRTKPositioningSolution) {
        this.positioningState = dJIRTKPositioningSolution;
    }

    void setMainGpsChannel(DJIRTKReceiverInfo dJIRTKReceiverInfo) {
        this.mainGpsChannel = dJIRTKReceiverInfo;
    }

    void setAuxGpsChannel(DJIRTKReceiverInfo dJIRTKReceiverInfo) {
        this.auxGpsChannel = dJIRTKReceiverInfo;
    }

    void setBsGpsChannel(DJIRTKReceiverInfo dJIRTKReceiverInfo) {
        this.bsGpsChannel = dJIRTKReceiverInfo;
    }

    void setMainBeiDouChannel(DJIRTKReceiverInfo dJIRTKReceiverInfo) {
        this.mainBeiDouChannel = dJIRTKReceiverInfo;
    }

    void setAuxBeiDouChannel(DJIRTKReceiverInfo dJIRTKReceiverInfo) {
        this.auxBeiDouChannel = dJIRTKReceiverInfo;
    }

    void setBsBeiDouChannel(DJIRTKReceiverInfo dJIRTKReceiverInfo) {
        this.bsBeiDouChannel = dJIRTKReceiverInfo;
    }

    void setMainGlonassChannel(DJIRTKReceiverInfo dJIRTKReceiverInfo) {
        this.mainGlonassChannel = dJIRTKReceiverInfo;
    }

    void setAuxGlonassChannel(DJIRTKReceiverInfo dJIRTKReceiverInfo) {
        this.auxGlonassChannel = dJIRTKReceiverInfo;
    }

    void setBsGlonassChannel(DJIRTKReceiverInfo dJIRTKReceiverInfo) {
        this.bsGlonassChannel = dJIRTKReceiverInfo;
    }

    void setAirSideLocation(DJILocationCoordinate2D dJILocationCoordinate2D) {
        this.airSideLocation = dJILocationCoordinate2D;
    }

    void setAirSideAltitude(float f) {
        this.airSideAltitude = f;
    }

    void setGroundBsLocation(DJILocationCoordinate2D dJILocationCoordinate2D) {
        this.groundBsLocation = dJILocationCoordinate2D;
    }

    void setGroundBsAltitude(float f) {
        this.groundBsAltitude = f;
    }

    void setDirection(float f) {
        this.direction = f;
    }

    void setDirectionValid(boolean z) {
        this.isDirectionValid = z;
    }

    void setRtkEnabled(boolean z) {
        this.isRtkEnabled = z;
    }

    public DJIError getError() {
        return this.error;
    }

    public DJIRTKPositioningSolution getPositioningSolution() {
        return this.positioningState;
    }

    public DJIRTKReceiverInfo getMobileStationReceiver1GpsInfo() {
        return this.mainGpsChannel;
    }

    public DJIRTKReceiverInfo getMobileStationReceiver2GpsInfo() {
        return this.auxGpsChannel;
    }

    public DJIRTKReceiverInfo getBaseStationReceiverGpsInfo() {
        return this.bsGpsChannel;
    }

    public DJIRTKReceiverInfo getMobileStationReceiver1BeiDouInfo() {
        return this.mainBeiDouChannel;
    }

    public DJIRTKReceiverInfo getMobileStationReceiver2BeiDouInfo() {
        return this.auxBeiDouChannel;
    }

    public DJIRTKReceiverInfo getBaseStationReceiverBeiDouInfo() {
        return this.bsBeiDouChannel;
    }

    public DJIRTKReceiverInfo getMobileStationReceiver1GlonassInfo() {
        return this.mainGlonassChannel;
    }

    public DJIRTKReceiverInfo getMobileStationReceiver2GlonassInfo() {
        return this.auxGlonassChannel;
    }

    public DJIRTKReceiverInfo getBaseStationReceiverGlonassInfo() {
        return this.bsGlonassChannel;
    }

    public DJILocationCoordinate2D getMobileStationAntenna1Location() {
        return this.airSideLocation;
    }

    public float getMobileStationAntenna1Altitude() {
        return this.airSideAltitude;
    }

    public DJILocationCoordinate2D getBaseStationLocation() {
        return this.groundBsLocation;
    }

    public float getBaseStationAltitude() {
        return this.groundBsAltitude;
    }

    public float getDirection() {
        return this.direction;
    }

    public boolean isDirectionValid() {
        return this.isDirectionValid;
    }

    public boolean isRtkEnabled() {
        return this.isRtkEnabled;
    }
}
