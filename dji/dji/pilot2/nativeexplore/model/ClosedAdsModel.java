package dji.pilot2.nativeexplore.model;

public class ClosedAdsModel {
    public int id;
    public String name;
    public String url;

    public ClosedAdsModel(String str, String str2) {
        this.name = str;
        this.url = str2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
