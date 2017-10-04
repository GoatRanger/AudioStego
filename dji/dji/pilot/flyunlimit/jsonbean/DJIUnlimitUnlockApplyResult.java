package dji.pilot.flyunlimit.jsonbean;

import java.util.List;

public class DJIUnlimitUnlockApplyResult {
    public String areas_id = "";
    public String areas_type = "";
    public List<AreaData> data;
    public AreaExtra extra;
    public String signature = "";
    public long status;
    public boolean success;
    public long time;
    public String unlock_apply_id = "";

    public static class AreaData {
        public int id;
    }

    public static class AreaExtra {
        public String msg;
    }
}
