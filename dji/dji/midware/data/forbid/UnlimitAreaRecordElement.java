package dji.midware.data.forbid;

public class UnlimitAreaRecordElement {
    public int area_id;
    public long begin_at;
    public long end_at;
    public String flycsn;
    public int id;
    public boolean is_offline_unlocked;
    public String user_id;

    public UnlimitAreaRecordElement(int i, long j, long j2, String str, String str2, boolean z) {
        this.area_id = i;
        this.begin_at = j;
        this.end_at = j2;
        this.flycsn = str;
        this.user_id = str2;
        this.is_offline_unlocked = z;
    }
}
