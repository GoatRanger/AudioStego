package dji.common.flightcontroller;

public enum DJIFlightControllerFlightMode {
    Manual(0),
    Atti(1),
    AttiCourseLock(2),
    AttiHover(3),
    Hover(4),
    GPSBlake(5),
    GPSAtti(6),
    GPSCourseLock(7),
    GPSHomeLock(8),
    GPSHotPoint(9),
    AssistedTakeOff(10),
    AutoTakeOff(11),
    AutoLanding(12),
    AttiLanding(13),
    GPSWaypoint(14),
    GoHome(15),
    ClickGo(16),
    Joystick(17),
    AttiLimited(23),
    GPSAttiLimited(24),
    GPSFollowMe(25),
    Tracking(26),
    Pointing(27),
    PANO(28),
    Farming(29),
    FPV(30),
    GPSSport(31),
    GPSNovice(32),
    ConfirmLanding(33),
    TerrainTracking(35),
    NaviAdvGoHome(36),
    NaviAdvLanding(37),
    TripodGPS(38),
    TrackHeadLock(39),
    ENGINE_START(41),
    GPSGentle(43),
    Unknown(255);
    
    private int data;

    private DJIFlightControllerFlightMode(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIFlightControllerFlightMode find(int i) {
        DJIFlightControllerFlightMode dJIFlightControllerFlightMode = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIFlightControllerFlightMode;
    }
}
