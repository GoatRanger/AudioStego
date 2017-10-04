package dji.common.flightcontroller;

public class DJIFlyZoneInformation {
    private FlyZoneCategory category;
    private String endTime;
    private int flyZoneId;
    private double latitude;
    private double longitude;
    private String name;
    private double radius;
    private FlyZoneShape shape;
    private String startTime;
    private FlyZoneType type;
    private String unlockEndTime = "Not available";
    private String unlockStartTime = "Not available";

    public String getUnlockStartTime() {
        return this.unlockStartTime;
    }

    public String getUnlockEndTime() {
        return this.unlockEndTime;
    }

    public int getFlyZoneId() {
        return this.flyZoneId;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getRadius() {
        return this.radius;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public FlyZoneType getFlyZoneType() {
        return this.type;
    }

    public FlyZoneShape getShape() {
        return this.shape;
    }

    public FlyZoneCategory getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public void setFlyZoneId(int i) {
        this.flyZoneId = i;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public void setRadius(double d) {
        this.radius = d;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public void setType(FlyZoneType flyZoneType) {
        this.type = flyZoneType;
    }

    public void setShape(FlyZoneShape flyZoneShape) {
        this.shape = flyZoneShape;
    }

    public void setCategory(FlyZoneCategory flyZoneCategory) {
        this.category = flyZoneCategory;
    }

    public void setUnlockStartTime(String str) {
        this.unlockStartTime = str;
    }

    public void setUnlockEndTime(String str) {
        this.unlockEndTime = str;
    }

    public void setName(String str) {
        this.name = str;
    }
}
