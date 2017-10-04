package dji.midware.data.forbid;

public class FlyForbidElement {
    public int area_id;
    public long begin_at;
    public String city = "";
    public int country;
    public int disable;
    public long end_at;
    public int id;
    public double lat;
    public int level;
    public double lng;
    public String name = "";
    public String points = "";
    public int radius;
    public int shape;
    public int type;
    public long updated_at;
    public int warning;

    public FlyForbidElement copyOf() {
        FlyForbidElement flyForbidElement = new FlyForbidElement();
        flyForbidElement.area_id = this.area_id;
        flyForbidElement.type = this.type;
        flyForbidElement.shape = this.shape;
        flyForbidElement.lat = this.lat;
        flyForbidElement.lng = this.lng;
        flyForbidElement.radius = this.radius;
        flyForbidElement.warning = this.warning;
        flyForbidElement.level = this.level;
        flyForbidElement.disable = this.disable;
        flyForbidElement.updated_at = this.updated_at;
        flyForbidElement.begin_at = this.begin_at;
        flyForbidElement.end_at = this.end_at;
        flyForbidElement.name = this.name;
        flyForbidElement.country = this.country;
        flyForbidElement.city = this.city;
        flyForbidElement.points = this.points;
        return flyForbidElement;
    }
}
