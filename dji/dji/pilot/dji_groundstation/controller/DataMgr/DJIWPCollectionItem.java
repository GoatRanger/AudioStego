package dji.pilot.dji_groundstation.controller.DataMgr;

import com.dji.frame.c.h;
import dji.thirdparty.afinal.a.a.a;
import java.util.ArrayList;
import java.util.List;

public class DJIWPCollectionItem {
    private int autoAddFlag = 0;
    private long createdDate = 0;
    private double distance = 0.0d;
    @a
    private int id = 0;
    private String location = "";
    private List<WayPoint> points = new ArrayList();
    private String pointsJsonStr = "";

    public DJIWPCollectionItem(long j) {
        this.createdDate = j;
    }

    public DJIWPCollectionItem(DJIWPCollectionItem dJIWPCollectionItem) {
        if (dJIWPCollectionItem != null) {
            this.id = dJIWPCollectionItem.getId();
            this.autoAddFlag = dJIWPCollectionItem.getAutoAddFlag();
            this.createdDate = dJIWPCollectionItem.getCreatedDate();
            this.location = dJIWPCollectionItem.getLocation();
            this.distance = dJIWPCollectionItem.getDistance();
            this.pointsJsonStr = dJIWPCollectionItem.getPointsJsonStr();
            for (WayPoint add : dJIWPCollectionItem.getPoints()) {
                this.points.add(add);
            }
        }
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public void setAutoAddFlag(int i) {
        this.autoAddFlag = i;
    }

    public int getAutoAddFlag() {
        return this.autoAddFlag;
    }

    public void setCreatedDate(long j) {
        this.createdDate = j;
    }

    public long getCreatedDate() {
        return this.createdDate;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public String getLocation() {
        return this.location;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public double getDistance() {
        return this.distance;
    }

    public List<WayPoint> getPoints() {
        return this.points;
    }

    public void setPointsJsonStr(String str) {
        this.pointsJsonStr = str;
        this.points = ((WayPoints) h.b(str, WayPoints.class)).points;
    }

    public String getPointsJsonStr() {
        this.pointsJsonStr = h.a(new WayPoints(this, this.points));
        return this.pointsJsonStr;
    }
}
