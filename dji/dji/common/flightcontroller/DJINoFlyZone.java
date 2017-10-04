package dji.common.flightcontroller;

public class DJINoFlyZone {
    DJILocationCoordinate2D zoneCenterCoordinate;
    float zoneRadius;

    public float getZoneRadius() {
        return this.zoneRadius;
    }

    void setZoneRadius(float f) {
        this.zoneRadius = f;
    }

    public DJILocationCoordinate2D getZoneCenterCoordinate() {
        return this.zoneCenterCoordinate;
    }

    void setZoneCenterCoordinate(DJILocationCoordinate2D dJILocationCoordinate2D) {
        this.zoneCenterCoordinate = dJILocationCoordinate2D;
    }
}
