package dji.common.flightcontroller;

public class DJILocationCoordinate3D {
    private float altitude;
    private DJILocationCoordinate2D coordinate2D;

    public DJILocationCoordinate2D getCoordinate2D() {
        return this.coordinate2D;
    }

    void setCoordinate2D(DJILocationCoordinate2D dJILocationCoordinate2D) {
        this.coordinate2D = dJILocationCoordinate2D;
    }

    public double getLatitude() {
        return this.coordinate2D.latitude;
    }

    void setLatitude(double d) {
        this.coordinate2D.latitude = d;
    }

    public double getLongitude() {
        return this.coordinate2D.longitude;
    }

    void setLongitude(double d) {
        this.coordinate2D.longitude = d;
    }

    public float getAltitude() {
        return this.altitude;
    }

    void setAltitude(float f) {
        this.altitude = f;
    }

    public DJILocationCoordinate3D(DJILocationCoordinate2D dJILocationCoordinate2D, float f) {
        this.coordinate2D = dJILocationCoordinate2D;
        this.altitude = f;
    }

    public DJILocationCoordinate3D(double d, double d2, float f) {
        this.coordinate2D = new DJILocationCoordinate2D(d, d2);
        this.altitude = f;
    }
}
