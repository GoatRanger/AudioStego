package dji.pilot.flyunlimit.jsonbean;

import java.io.Serializable;

public class UnlockListItem implements Serializable {
    public static final long serialVersionUID = 8502706820090766657L;
    public String areas_type = "";
    public String begin_at = "";
    public String end_at = "";
    public int id;
    public String location = "";
    public String os = "";
    public String places = "";
    public String status = "";

    public UnlockListItem(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.places = str;
        this.location = str2;
        this.begin_at = str3;
        this.end_at = str4;
        this.status = str5;
        this.areas_type = str6;
        this.os = str7;
    }
}
