package dji.midware.data.forbid;

public class StrongWarningUnlockedElement {
    public int area_id;
    public long begin_at;
    public long end_at;
    public String flycsn;
    public int id;

    public StrongWarningUnlockedElement(int i, long j, long j2, String str) {
        this.area_id = i;
        this.begin_at = j;
        this.end_at = j2;
        this.flycsn = str;
    }
}
