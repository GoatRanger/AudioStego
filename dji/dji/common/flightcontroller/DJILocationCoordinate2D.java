package dji.common.flightcontroller;

public class DJILocationCoordinate2D {
    double latitude;
    double longitude;

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public DJILocationCoordinate2D(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public DJILocationCoordinate2D(DJILocationCoordinate2D dJILocationCoordinate2D) {
        this.latitude = dJILocationCoordinate2D.latitude;
        this.longitude = dJILocationCoordinate2D.longitude;
    }
}
