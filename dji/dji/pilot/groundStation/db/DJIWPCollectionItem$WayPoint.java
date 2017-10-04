package dji.pilot.groundStation.db;

public class DJIWPCollectionItem$WayPoint {
    public int craftYaw;
    public int gimbalPitch;
    public int gimbalYaw;
    public double height;
    public double lat;
    public double lng;

    public DJIWPCollectionItem$WayPoint(double d, double d2, double d3) {
        this.lat = d;
        this.lng = d2;
        this.height = d3;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double d) {
        this.lng = d;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double d) {
        this.height = d;
    }

    public int getCraftYaw() {
        return this.craftYaw;
    }

    public void setCraftYaw(int i) {
        this.craftYaw = i;
    }

    public int getGimbalYaw() {
        return this.gimbalYaw;
    }

    public void setGimbalYaw(int i) {
        this.gimbalYaw = i;
    }

    public int getGimbalPitch() {
        return this.gimbalPitch;
    }

    public void setGimbalPitch(int i) {
        this.gimbalPitch = i;
    }
}
